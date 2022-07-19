package com.sist.dao;

import java.util.*;
import java.sql.*;
import com.sist.conn.*;
/*
 *  table => 데이터 받기 (VO)
 *  		오라클 제어 (DAO)
 *  table 한개당 VO, DAO
 *  	=>  재사용
 */


public class DataBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBConnection dbconn = DBConnection.newInstance();
	
	public List<DataBoardVO> databoardListData(int page){
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		
		try {
			conn = dbconn.getConnection();
			// 정렬 => 인덱스, 페이지 = 인라인뷰
			String sql = "SELECT no, subject, name, regdate, hit, num "
					+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(databoard db_no_pk) */ no, subject, name, regdate, hit "
					+ "FROM databard)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 10; // 한페이지에 10개 출력
			int start = (rowSize*page)-(rowSize-1); // rownum => 1부터 시작
			int end = rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DataBoardVO vo = new DataBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
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
	
	// 총 페이지 구하기
	public int databoardTotalPage() {
		int total = 0;
		try {
			conn = dbconn.getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM databoard";
			ps = conn.prepareStatement(sql);
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
	
	// 데이터 첨부 (업로드)
	
	// 상세보기 => 조회수 증가 , 다운로드(response) 
	
	// 수정
	
	// 삭제
	
}
