package com.sist.dao;

import java.io.Reader;
import java.util.*;
import com.sist.vo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * 	DAO => DAO + mapper + VO (데이터베이스 연결)
 * 	MODEL => VO + DAO 호출 (요청값 전송) ==> JSP
 */
public class BoardReplyDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//<select id="boardReplyListData" resultType="BoardReplyVO" parameterType="hashmap">
	public static List<BoardReplyVO> boardReplyListData(Map map){
		SqlSession session = null;
		List<BoardReplyVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("boardReplyListData", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	//<insert id="boardReplyInsert" parameterType="BoardReplyVO">
	public static void boardReplyInsert(BoardReplyVO vo) {
		SqlSession session = null;
		try {
			// AutoCommit / 트랜젝션
			session = ssf.openSession(true);
			session.insert("boardReplyInsert", vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	// 총페이지 구하기 <select id="boardReplyTotalPage" resultType="int">
	public static int boardReplyTotalPage() {
		SqlSession session = null;
		int total = 0;
		try {
			session = ssf.openSession();
			total = session.selectOne("boardReplyTotalPage");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return total;
	}
	
	// 관리자페이지 답변 달기 <select id="boardReplyAdminData" resultType="BoardReplyVO">
	public static List<BoardReplyVO> boardReplyAdminData(){
		SqlSession session = null;
		List<BoardReplyVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("boardReplyAdminData");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}	
		return list;
	}
	
	// 관리자페이지 게시글 => 상세보기 <select id="boardReplyDetailData" resultType="BoardReplyVO" parameterType="int">
	public static BoardReplyVO boardReplyDetailData(int no) {
		SqlSession session = null;
		BoardReplyVO vo = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("boardReplyDetailData", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}	
		return vo;
	}
	
	/*
	<!-- 관리자가 답변달기 ?, ?, 답글달면 게시글 isReply에 1로 수정-->
	<select id="boardReplyInfoData" resultType="int" parameterType="int">
		SELECT group_id FROM project_replyBoard WHERE no=#{no}
	</select>
	<insert id="boardReplyInsertOk" parameterType="BoardReplyVO">
		<selectKey>
			SELECT NVL(MAX(no)+1, +1) as no FROM project_replyBoard
		</selectKey>
		INSERT INTO project_replyBoard(no, name, subject, content, pwd, group_id, group_step, group_tab)
		VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, 
		#{group_id}, 1, 1) 
	</insert>
	<update id="boardReplyIsReply" parameterType="int">
		UPDATE project_replyBoard SET
		isreply = 1
		WHERE no=#{no}
	</update>
	 */
	public static void boardReplyInsertOk(int pno, BoardReplyVO vo) {
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			int gi = session.selectOne("boardReplyInfoData", pno);
			
			vo.setGroup_id(gi);
			session.insert("boardReplyInsertOk", vo);
			session.update("boardReplyIsReply", pno);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}	
	}
}
