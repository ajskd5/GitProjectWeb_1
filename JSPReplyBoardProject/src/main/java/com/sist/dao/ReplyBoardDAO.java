package com.sist.dao;
import java.util.*; // List
import java.sql.*;  // Connection, Statement, ResultSet
import javax.sql.*; // DataSource
import javax.naming.*; // Context
public class ReplyBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 객제 주소 가지고 오기 (Connection) => 톰캣에 의해 10개 생성 되어 있음 => POOL (java://comp/env)
	// 객체명 => jdbc/oracle
	public void getConnection() {
		try {
			Context init = new InitialContext(); // JNDI
			Context c = (Context)init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 반환 => 다음 사용자 재사용 => Connection의 생성 갯수를 제한
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
	
	// 기능
	public List<ReplyBoardVO> boardListData(int page){
		List<ReplyBoardVO> list =new ArrayList<ReplyBoardVO>();
		try {
			getConnection();
			String sql = "SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD'), hit, group_tab, num "
					+ "FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
					+ "FROM (SELECT no, subject, name, regdate, hit, group_tab "
					+ "FROM replyBoard ORDER BY group_id DESC, group_step ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 10;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReplyBoardVO vo = new ReplyBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
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
	
	public int boardRowCount() {
		int count = 0;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) FROM replyBoard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return count;
	}
	
	// 답변하기
	public void replyInsert(ReplyBoardVO vo) {
		try {
			getConnection();
			conn.setAutoCommit(false); // 하나라도 실패하면 catch로 감
			// 상위 글의 정보 읽기 (gi, gt, gs) => SELECT
			// gs의 순서 변경 => UPDATE
			// 실제 저장 => INSERT
			// depth 변경 => UPDATE
			//========================> 일괄처리 (트랜잭션)
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e3) {
				// TODO: handle exception
			}
			disConnection();
		}
	}
	
}
