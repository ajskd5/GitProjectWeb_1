package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.vo.LicenseCategoryVO;
import com.sist.vo.LicenseLinkVO;
import com.sist.vo.LicenseVO;
import com.sist.vo.PosterVO;

public class LicenseDAO {
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
	public LicenseDAO() { // 멤버변수의 초기화 => 시작과 동시에 등록 (드라이버, 서버연결)
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
	
	// l_list_1 출력
	public List<LicenseLinkVO> licenseListData(){
		List<LicenseLinkVO> list = new ArrayList<LicenseLinkVO>();
		try {
			getConnection();
			String sql = "SELECT ll_no, ll_name, ll_link, l_cno "
					+ "FROM l_list_1 "
					+ "ORDER BY ll_no ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LicenseLinkVO vo = new LicenseLinkVO();
				vo.setLl_no(rs.getInt(1));
				vo.setLl_name(rs.getString(2));
				vo.setLl_link(rs.getString(3));
				vo.setL_cno(rs.getInt(4));
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
	
	// l_list_1 타이틀 링크 카테고리번호 입력
	public void linkInsert(LicenseLinkVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO l_list_1(ll_no, ll_name, ll_link) "
					+ "VALUES((Select NVL(MAX(ll_no)+1, 1) FROM l_list_1), "
					+ "?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getLl_name());
			ps.setString(2, vo.getLl_link());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// l_category_1 카테고리 입력
	public void licenseCategory(LicenseCategoryVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO l_category_1 "
					+ "VALUES((SELECT NVL(MAX(l_cno)+1, 1) FROM l_category_1), "
					+ "?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getL_cname());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	// l_category_1 출력
	public List<LicenseCategoryVO> CategoryData(){
		List<LicenseCategoryVO> list = new ArrayList<LicenseCategoryVO>();
		try {
			getConnection();
			String sql = "SELECT l_cno, l_cname "
					+ "FROM l_category_1 "
					+ "ORDER BY l_cno ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LicenseCategoryVO vo = new LicenseCategoryVO();
				vo.setL_cno(rs.getInt(1));
				vo.setL_cname(rs.getString(2));
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
	
	// l_poster 입력
	public void posterInsert(PosterVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO l_poster_1 "
					+ "VALUES((SELECT NVL(MAX(lp_no)+1, 1) FROM l_poster_1), "
					+ "?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getLp_poster());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	// l_poster 출력
	public List<PosterVO> PosterData(int i){
		List<PosterVO> list = new ArrayList<PosterVO>();
		try {
			getConnection();
			String sql = "SELECT lp_no, lp_poster "
					+ "FROM l_poster_1 "
					+ "WHERE lp_no =?"
					+ "ORDER BY lp_no ASC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				PosterVO vo = new PosterVO();
//				vo.setLp_no(rs.getInt(1));
//				vo.setLp_poster(rs.getString(2));
//				list.add(vo);
//			}
			rs.next();
			PosterVO vo = new PosterVO();
			vo.setLp_no(rs.getInt(1));
			vo.setLp_poster(rs.getString(2));
			list.add(vo);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	// license_1 상세정보 입력
	public void licenseInsert(LicenseVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO license_1 "
					+ "VALUES((SELECT NVL(MAX(l_no)+1, 1) FROM license_1), "
					+ "?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getL_name());
			ps.setString(2, vo.getL_info());
			ps.setString(3, vo.getL_info2());
			ps.setString(4, vo.getL_schedule());
			ps.setString(5, vo.getL_img());
			ps.setString(6, vo.getL_content());
			ps.setInt(7, vo.getL_cno());
			ps.setInt(8, vo.getL_pno());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	public List<LicenseVO> licenseData(int l_no){
		List<LicenseVO> list = new ArrayList<LicenseVO>();
		try {
			getConnection();
			String sql = "Sl_no, l_name, l_info, l_info2, l_schedule, l_img, l_content, l_cno, "
					+ "(SELECT lp_poster FROM l_poster_1 WHERE l_poster_1.lp_no = license_1.l_pno) "
					+ "FROM license_1"
					+ "WHERE l_no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, l_no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			LicenseVO vo = new LicenseVO();
			vo.setL_no(rs.getInt(1));
			vo.setL_name(rs.getString(2));
			vo.setL_info(rs.getString(3));
			vo.setL_info2(rs.getString(4));
			vo.setL_schedule(rs.getString(5));
			vo.setL_img(rs.getString(6));
			vo.setL_content(rs.getString(7));
			vo.setL_cno(rs.getInt(8));
			vo.setL_poster(rs.getString(9));
			list.add(vo);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
}
