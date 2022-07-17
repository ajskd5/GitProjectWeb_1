package com.sist.dao;

import java.sql.*;
import java.util.*;
import com.sist.vo.*;
import com.sist.conn.*;

public class BookDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBConnection dbconn = DBConnection.newInstance();
	
	public void linkInsert(BookVO vo){
		try {
			conn = dbconn.getConnection();
			String sql = "INSERT INTO qwer "
					+ "VALUES((SELECT NVL(MAX(no)+1, 1) FROM qwer), "
					+ "?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getLink());
			ps.setString(2, vo.getTitle());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(ps);
		}
	}
	
	public List<BookVO> bookLinkData(){
		List<BookVO> list = new ArrayList<BookVO>();
		
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT no, link, title "
					+ "FROM qwer "
					+ "ORDER BY no";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setNo(rs.getInt(1));
				vo.setLink(rs.getString(2));
				vo.setTitle(rs.getString(3));
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
}