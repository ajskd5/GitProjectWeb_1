package com.sist.dao;
// 사용자 요청을 받아서 => 오라클 연결 => 결과값 가지고오는 역할
import java.sql.*; // 오라클 연결 => 모든 데이터베이스 연결 가능
import java.util.*; // Collection
/*
 *  1. 드라이버 등록
 *  2. 오라클 연결
 *  3. 오라클 연결 해제
 *  4. 기능 수행 => 요구사항
 *  ------------------------
 *  	=> 	1) 목록 출력 => 페이지 나누기 (인라인뷰)
 *  		2) 장르별 처리
 *  		3) 검색 => 찾기
 */
public class MusicDAO {
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
	public MusicDAO() { // 멤버변수의 초기화 => 시작과 동시에 등록 (드라이버, 서버연결)
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
	// ↑필수 조건
	
	// 기능
	// 1. 목록 출력 => 페이지 나누기 (핵심)
	/*
	 *  1. 자바
	 *  2. 오라클 (***) => 인라인뷰
	 *  === View : 한개 이상의 테이블 참조해서 만든 가상 테이블
	 *  		=> 테이블과 동일하게 사용 가능
	 *  		=> DML (INSERT, UPDATE, DELETE) => 실제 참조하는 테이블에 저장
	 *  			가급적 READ ONLY 속성
	 *  		=> 종류 ==> SQL문장을 저장하고 나중에 필요시 재사용
	 *  			실제 데이터 저장이 아니라 SQL문장 저장
	 *  			보안, SQL문장 단순화 가능
	 *  			1) 단일뷰 : 테이블 한개 연결 => 필요한 데이터만 모아서 사용
	 *  			2) 복합뷰 : 두개 이상의 테이블 연결
	 *  			3) 인라인뷰 => SELECT 이용
	 *  				=> rownum : 데이터 INSERT시마다 지정되는 ROW번호
	 *  					------ 단점 => Top-N (처음부터는 가능, 중간부터는 불가능)
	 *  			=> 사용법
	 *  			CREATE OR REPLACE VIEW view_name
	 *  			AS
	 *  				SELECT ~~
	 *  
	 * 	인라인뷰	SELECT ~~ FROM (SELECT ~~)
	 *  	=> 오라클 기록상에 남아있지 않는다 (한번 사용하고 버림)
	 */
	public List<MusicVO> musicListData(int cno, int page){
		// cno => 장르별 페이지 나누기
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			// 무조건 사용 가능
			// 1. 오라클 연결
			getConnection();
			String sql = "SELECT mno, cno, poster, title, singer, album, idcrement, state, num "
					+ "FROM (SELECT mno, cno, poster, title, singer, album, idcrement, state, rownum as num "
					+ "FROM (SELECT mno, cno, poster, title, singer, album, idcrement, state "
					+ "FROM music "
					+ "WHERE cno=? "
					+ "ORDER BY mno ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			// ? 에 값 채우기
			int rowSize = 10;
			int start = (rowSize*page)-(rowSize-1); // rownum => 1번부터 시작 	1, 11, 21, 31, ...
			int end = rowSize*page; // 마지막 번호 1 2 3 ... 10 / 11 12 13 ... 20
			
			ps.setInt(1, cno);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			// 실행 요청 => 결과값 받기
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setCno(rs.getInt(2));
				vo.setPoster(rs.getString(3));
				vo.setTitle(rs.getNString(4));
				vo.setSinger(rs.getString(5));
				vo.setAlbum(rs.getString(6));
				vo.setIdcrement(rs.getInt(7));
				vo.setState(rs.getString(8));
				
				list.add(vo);
			}
			rs.close();			
		} catch (Exception e) {
			e.printStackTrace();
			/* 에러 메세지
			 *  getMessage() : 에러 메세지만 출력
			 *  printStackTrace() : 실행된 과정 보여줌 (에러 부분을 추출)
			 */
		} finally {
			disConnection();
		}
		return list;
	}
	
	// 총 페이지 가지고 오기
	public int musicTotalPage(int cno) {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) "
					+ "FROM music "
					+ "WHERE cno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
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
