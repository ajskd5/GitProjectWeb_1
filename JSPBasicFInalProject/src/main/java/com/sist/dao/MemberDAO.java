package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.conn.DBConnection;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBConnection dbconn = DBConnection.newInstance();
	
	// 로그인 결과 3개 (ID가 없는 경우, PWD가 틀린 경우, 로그인)
	public String isLogin(String id, String pwd) {
		String result = "";
		try {
			conn = dbconn.getConnection();
			//  1. ID 체크
			String sql = "SELECT COUNT(*) FROM jspMember "
					+ "WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			
			if(count == 0) { // ID가 없는 경우
				result = "NOID";
			} else { // ID가 있는 경우
				// 비밀번호 검색
				sql = "SELECT pwd, name FROM jspMember "
						+ "WHERE id=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				rs.next();
				String db_pwd = rs.getString(1);
				String name = rs.getString(2);
				rs.close();
				
				if(db_pwd.equals(pwd)) { // 로그인 성공
					result = name;
				} else {
					result = "NOPWD"; // 비밀번호 틀림
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(ps);
		}
		return result;
	}
	
}
