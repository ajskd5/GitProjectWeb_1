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
			ps = conn.prepareStatement(sql);
			int rowSize=10;
			int start = (page*rowSize) - (rowSize-1);
			int end = page*rowSize;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("boardListData 에러");
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	// 1-2 총 페이지 (CEIL)
	public int boardTotal() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM freeboard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			System.out.println("boardTotal 에러");
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return total;
	}
	
	// 상세보기
	public BoardVO boardDetail(int no, int type){
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			if(type==1) { // DETAIL
				String sql = "UPDATE freeboard SET "
						+ "hit=hit+1 "
						+ "WHERE no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate(); // 조회수 증가
			}
			
			// 실제 데이터 가져오기
			String sql = "SELECT no, name, subject, content, regdate, hit "
					+ "FROM freeboard "
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
			rs.close();

			
		} catch (Exception e) {
			System.out.println("boardDetail 에러");
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	
	// 수정, 삭제
	public void boardUpdate(BoardVO vo) {
		try {
			getConnection();
			String sql = "UPDATE freeboard SET "
					+ "name=?, subject=?, content=? "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setInt(4, vo.getNo());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("boardUpdate 에러");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	public void boardDelete(int no) {
		try {
			getConnection();
			String sql = "DELETE FROM freeboard "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("boardDelete 에러");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// 새글 => 회원가입, 장바구니, 예매, 글쓰기 INSERT
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO freeboard(no, name, subject, content, pwd) "
				      +"VALUES((SELECT NVL(MAX(no)+1,1) FROM freeboard),?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("boardInsert() 에러");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// 찾기
	
}
