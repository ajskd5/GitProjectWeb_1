package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
// JSP =>DBCP
// 프로젝트 => MyBatis
public class BoardDAO {
	private Connection conn; // 연결
	private PreparedStatement ps; // SQL문장 송수신
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			System.out.println("getConnection() : 에러");
			e.printStackTrace();
		}
	}
	
	public void disConnection() {
		try {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("disConnection() : 에러");
			e.printStackTrace();
		}
	}
	
	// 기능
	// 1-1 목록 출력 (인라인뷰)
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			// 인덱스 /*+ ~*/
			// --+ ~ => 주석이라 이렇게 줄 수 있음
			String sql = "SELECT no, subject, name, regdate, hit, num "
					+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(freeboard fb_no_pk)*/ no, subject, name, regdate, hit "
					+ "FROM freeboard)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps= conn.prepareStatement(sql);
			int rowSize=10;
			int start = (page*rowSize) - (rowSize-1);
			int end = page*rowSize;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("boardListData : 에러");
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	// 1-2 총 페이지 (CEIL)
	
}
