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
					+ "FROM databoard)) "
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
	public void databoardInsert(DataBoardVO vo) {
		try {
			conn = dbconn.getConnection();
			String sql = "INSERT INTO databoard(no, name, subject, content, pwd, filename, filesize) "
					+ "VALUES(db_no_seq.nextval, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(ps);
		}
	}
	
	// 상세보기 => 조회수 증가 , 파일 다운로드(response) 
	public DataBoardVO databoardDetail(int no) {
		DataBoardVO vo = new DataBoardVO();
		try {
			conn = dbconn.getConnection();
			// 조회수 증가
			String sql = "UPDATE databoard SET "
					+ "hit = hit+1 "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			// 값 가져오기, 파일 다운로드
			sql = "SELECT no, name, subject, content, regdate, hit, filename, filesize "
					+ "FROM databoard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			// 다운로드
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbconn.disConnection(ps);
		}
		return vo;
	}
	
	// 수정
	
	// 삭제
	
}
