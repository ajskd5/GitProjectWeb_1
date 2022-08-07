package com.sist.dao;

import java.sql.*;
import java.util.*;
import com.sist.vo.*;
import com.sist.conn.*;

public class BookDAO {
	// 오라클 연결 클래스
	private Connection conn;
	// 오라클 SQL문장 전송 => 결과값 읽기
	private PreparedStatement ps;
	/*
	 * PreparedStatement : SQL 문장
	 * CallableStatement : PL/SQL => Procedure
	 * => 실행요청
	 * 		executeQuery => 결과값 받음 => SELECT
	 *  	executeUpdate => 실행 요청 => COMMIT 포함하고 있음 => INSERT, UPDATE, DELETE
	 *  ==> 한개의 메소드(기능)에서 SQL문장이 한개 이상일 수 있다
	 *  ex) 게시판 상세보기 => 1. 조회수 증가, 2. 내용 읽기
	 *  	답변 => SQL 5개, 삭제 => SQL 7개
	 *  댓글, 묻고 답하기, 답변(실시간 상담), 공지, 회원
	 */
	// 오라클 주소
	private final String URL = "jdbc:oracle:thin:@211.63.89.131:1521:XE";
	
	//드라이버 설정 => ojdbc8.jar
	public BookDAO() { // 멤버변수의 초기화 => 시작과 동시에 등록 (드라이버, 서버연결)
		// 한번 수행 => 기능
		try {
			// 리플렉션 => 스프링 => 메모리 할당, 메소드 호출 변수의 초기값 주입
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 오라클 닫기
	public void disConnection() {
		try {
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// booklink_1 입력
	public void linkInsert(BookLinkVO vo){
		try {
			getConnection();
			String sql = "INSERT INTO booklink_1 "
					+ "VALUES((SELECT NVL(MAX(gl_no)+1, 1) FROM booklink_1), "
					+ "?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getGl_link());
			ps.setString(2, vo.getGl_title());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	// booklink_1 출력
	public List<BookLinkVO> bookLinkData(){
		List<BookLinkVO> list = new ArrayList<BookLinkVO>();
		
		try {
			getConnection();
			String sql = "SELECT gl_no, gl_link, gl_title "
					+ "FROM booklink_1 "
					+ "ORDER BY gl_no";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BookLinkVO vo = new BookLinkVO();
				vo.setGl_no(rs.getInt(1));
				vo.setGl_link(rs.getString(2));
				vo.setGl_title(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	// bookDetail 입력
	public void BookInsert(BookVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO bookdetail_1(g_no, g_name, g_writer, g_poster, g_price, g_info, g_introduce, g_index) "
					+ "VALUES((SELECT NVL(MAX(g_no)+1, 1) FROM bookdetail_1), "
					+ "?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getG_name());
			ps.setString(2, vo.getG_writer());
			ps.setString(3, vo.getG_poster());
			ps.setInt(4, vo.getG_price());
			ps.setString(5, vo.getG_info());
			ps.setString(6, vo.getG_introduce());
			ps.setString(7, vo.getG_index());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
}