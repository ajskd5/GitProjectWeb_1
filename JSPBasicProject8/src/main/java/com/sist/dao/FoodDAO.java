package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.conn.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBConnection dbconn = DBConnection.newInstance();
	
	// 목록 출력
	public List<FoodVO> foodListData(String fd, int page){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			conn = dbconn.getConnection();
			// 검색별 페이지 나누기
			String sql = "SELECT fno, poster, name, score, type, address, num "
					+ "FROM (SELECT fno, poster, name, score, type, address, rownum as num "
					+ "FROM (SELECT fno, poster, name, score, type, address "
					+ "FROM food_location WHERE address LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fd);
			int rowSize=9;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setScore(rs.getDouble(4));
				vo.setType(rs.getString(5));
				vo.setAddress(rs.getString(6));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(ps);
		}
		return list;
	}
	// 총 페이지
	public int foodTotalPage(String fd) {
		int total = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT CEIL(COUNT(*)/9.0) "
					+ "FROM food_location "
					+ "WHERE address LIKE '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fd);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(ps);
		}
		return total;
	}
	
	// 상세 보기
	// 로그인 처리
	
}
