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
}