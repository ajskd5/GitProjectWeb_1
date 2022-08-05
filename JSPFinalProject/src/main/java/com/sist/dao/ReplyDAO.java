package com.sist.dao;

import java.io.Reader;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.sist.vo.*;

public class ReplyDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//<select id="replyListData" resultType="ReplyVO" parameterType="ReplyVO">
	public static List<ReplyVO> replyListData(ReplyVO vo){
		List<ReplyVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("replyListData", vo);
		} catch (Exception e) {
			System.out.println("DAO replyListData error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close(); // 반환 => POOLED(DBCP) => Connection생성(반환하지 않으면 8개가 끝)
			}
		}
		return list;
	}
	
	// 댓글 달기 <insert id="replyInsertData" parameterType="ReplyVO">
	// 게시물 제목 댓글수 증가 <update id="countIncrement" parameterType="ReplyVO">
	public static void replyInsertData(ReplyVO vo) {
		List<ReplyVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession(true); // true를 날려야 AutoCommit
			session.update("countIncrement", vo);
			session.insert("replyInsertData", vo); // commit X
			//session.commit(); 아니면 이렇게 커밋 날림
		} catch (Exception e) {
			System.out.println("DAO replyInsert error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close(); // 반환 => POOLED(DBCP) => Connection생성(반환하지 않으면 8개가 끝)
			}
		}
	}
	
	//<delete id="replyDelete" parameterType="int">
	public static void replyDelete(int no) {
		SqlSession session = ssf.openSession(true);
		session.delete("replyDelete", no);
		session.close();
	}
	
	// 수정 <update id="replyUpdate" parameterType="ReplyVO">
	public static void replyUpdate(ReplyVO vo) {
		SqlSession session = ssf.openSession(true);
		session.delete("replyUpdate", vo);
		session.close();
	}
}
