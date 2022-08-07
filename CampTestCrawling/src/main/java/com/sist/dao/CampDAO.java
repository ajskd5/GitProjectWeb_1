package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.*;

public class CampDAO {
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
	public CampDAO() { // 멤버변수의 초기화 => 시작과 동시에 등록 (드라이버, 서버연결)
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
	// link 입력
	public void linkInsert(CampLinkVO vo){
		try {
			getConnection();
			String sql = "INSERT INTO camplink_1 "
					+ "VALUES((SELECT NVL(MAX(no)+1, 1) FROM camplink_1), "
					+ "?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getLink());
			ps.setString(2, vo.getTitle());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	public List<CampLinkVO> linkList(){
		List<CampLinkVO> list = new ArrayList<CampLinkVO>();
		try {
			getConnection();
			String sql = "SELECT no, link, title "
					+ "FROM camplink_1 "
					+ "ORDER BY no";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CampLinkVO vo = new CampLinkVO();
				vo.setNo(rs.getInt(1));
				vo.setLink(rs.getString(2));
				vo.setTitle(rs.getString(3));
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

	public void campInsert(CampVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO camp_1 "
					+ "VALUES((SELECT NVL(MAX(c_no)+1, 1) FROM camp_1), "
					+ "?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPoster());
			ps.setString(3, vo.getInfo());
			ps.setString(4, vo.getImgs());
			ps.setString(5, vo.getContent());
			ps.setString(6, vo.getInfo2());
			ps.setString(7, vo.getEtcinfo());
			ps.setString(8, vo.getGallery());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	/*
	 * vo.setType(category);
						vo.setTitle(title.text());
						vo.setPoster("https://www.campingon.co.kr" + poster.attr("src"));
						vo.setPrice(price);
						vo.setImgs(img);
	 */
	public void goodsInsert(GoodsVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO campgoods_1 "
					+ "VALUES((SELECT NVL(MAX(no)+1, 1) FROM campgoods_1), "
					+ "?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getType());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getPoster());
			ps.setInt(4, vo.getPrice());
			ps.setString(5, vo.getImgs());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
}
