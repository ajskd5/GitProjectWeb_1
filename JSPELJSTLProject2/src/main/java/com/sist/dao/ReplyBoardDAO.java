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
	
	public void boardInsert(ReplyBoardVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO replyBoard(no, name, subject, content, pwd, group_id) VALUES("
					+ "(SELECT NVL(MAX(no)+1, 1) FROM replyBoard), ?, ?, ?, ?, "
					+ "(SELECT NVL(MAX(group_id)+1, 1) FROM replyBoard))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// 답변하기
	public void replyInsert(int pno, ReplyBoardVO vo) {
		try {
			getConnection();
			conn.setAutoCommit(false); // 하나라도 실패하면 catch로 감
			// 상위 글의 정보 읽기 (gi, gt, gs) => SELECT
			String sql = "SELECT group_id, group_step, group_tab "
					+ "FROM replyBoard "
					+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int gi = rs.getInt(1);
			int gs = rs.getInt(2);
			int gt = rs.getInt(3);
			
			// gs의 순서 변경 => UPDATE ==> 답변 핵심 쿼리
			/*						gi	gs	gt	
			 *  AAAAAA				1	0	0
			 * 	 	=>BBBBBB		1	1	1
			 *  		=>CCCCCC	1	2	2
			 *  			=>DDDD 	1	3	3
			 *  	=>EEEEEE		1	1	1   => B, C, D의 gs값 1씩 증가하면 됨 (E가 맨 위로 올라감)
			 */
			sql = "UPDATE replyBoard Set "
					+ "group_step = group_step + 1 "
					+ "WHERE group_id=? AND group_step>?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			
			// 실제 저장 => INSERT
			sql = "INSERT INTO replyBoard(no, name, subject, content, pwd, group_id, group_step, group_tab, root) "
					+ "VALUES((SELECT NVL(MAX(no)+1, 1) FROM replyBoard), ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, pno);
			ps.executeUpdate();
			// depth 변경 => UPDATE
			sql = "UPDATE replyBoard SET "
					+ "depth = depth+1 "
					+ "WHERE no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
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
	
	// 상세보기
	public ReplyBoardVO boardDetailData(int no) {
		ReplyBoardVO vo = new ReplyBoardVO();
		try {
			getConnection();
			String sql = "UPDATE replyBoard SET "
					+ "hit = hit + 1 "
					+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql = "SELECT no, name, subject, content, regdate, hit, pwd "
					+ "FROM replyBoard "
					+ "WHERE no = ?";
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
			vo.setPwd(rs.getString(7));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	
}
