package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
//DBCP
public class LocationDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 연결 객체 가지고 오기 => getConnection (생성하는게 아닌 생성된 객체 가지고 옴)
	public void getConnection() {
		try {
			// 저장된 위치 가지고 온다 (JNDI) => java://comp/env => Connection 객체 저장
			// 1. 탐색기 열기
			Context init = new InitialContext();
			
			// 2. C드라이브 열기
			Context c = (Context)init.lookup("java://comp/env");
			// 3. 저장된 폴더에서 Connection 읽어옴
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection(); // 미리 생성된 Connection 객체를 얻어옴
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 반환 => disConnection (ps.close(), conn.close() ) 쓰는거 전과 동일
	public void disConnection() {
		try {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 기능 => 전하고 동일
	public List<LocationVO> locationListData(int page){
		List<LocationVO> list = new ArrayList<LocationVO>();
		
		try {
			getConnection(); // 미리 생성된 Connection 객체 주소 얻어옴
			String sql = "SELECT no, title, poster, num "
					+ "FROM (SELECT no, title, poster, rownum as num "
					+ "FROM (SELECT no, title, poster "
					+ "FROM seoul_location ORDER BY no ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			// rownum 을 이용시 Top-n만 가능 (중간에 있는 데이터를 자르기 못함)
			ps = conn.prepareStatement(sql);
			int rowSize = 12;
			int start = (rowSize*page)-(rowSize-1);
			int end = (rowSize*page);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LocationVO vo = new LocationVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection(); // 닫기 => 반환 (commons-dbcp.jar) => 다른 사용자가 재사용 가능함 (일정 Connection 객체로 사용)
			
		}
		return list;
	}
	
	// 총 페이지 구하기
	public int locationTotalPage() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/12.0) FROM seoul_location";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return total;
	}
}
