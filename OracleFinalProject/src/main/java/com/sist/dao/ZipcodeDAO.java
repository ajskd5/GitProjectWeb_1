package com.sist.dao;

import java.sql.*;
import java.util.*;

public class ZipcodeDAO {
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
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 설정 => ojdbc8.jar
	public ZipcodeDAO() { // 멤버변수의 초기화 => 시작과 동시에 등록 (드라이버, 서버연결)
		// 한번 수행 => 기능
		try {
			// 리플렉션 => 스프링 => 메모리 할당, 메소드 호출 변수의 초기값 주입
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// 오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			// TODO: handle exception
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
			// TODO: handle exception
		}
	}
	
	// 1. 우편번호 검색 => LIKE, NVL
	public List<ZipcodeVO> postFind(String dong){
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		try {
			getConnection();
			String sql = "SELECT zipcode, sido, gugun, dong, NVL(bunji, ' ') " // 한칸을 띄어야 공백 (안 띄우면 null)
					+ "FROM zipcode "
					+ "WHERE dong LIKE '%'||?||'%'";
					//	MySQL =>  CONCAT('%',?,'%')
			ps = conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ZipcodeVO vo = new ZipcodeVO();
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setGugun(rs.getString(3));
				vo.setDong(rs.getString(4));
				vo.setBunji(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	// 2. 검색 수 출력  => COUNT
	public int postFindCount(String dong) {
		int count = 0;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) "
					+ "FROM zipcode "
					+ "WHERE dong LIKE '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disConnection();
		}
		
		return count;
	}
}
