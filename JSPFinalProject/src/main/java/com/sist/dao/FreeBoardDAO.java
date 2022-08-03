package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;

public class FreeBoardDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//<select id="boardListData" resultType="FreeBoardVO" parameterType="hashmap">
	public static List<FreeBoardVO> boardListData(Map map){
		List<FreeBoardVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("boardListData", map);
		} catch (Exception e) {
			System.out.println("DAO boardListData error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close(); // 반환 => POOLED(DBCP) => Connection생성(반환하지 않으면 8개가 끝)
			}
		}
		return list;
	}
	// tatalpage 구하기
	public static int boardTotalPage(){
		int total=0;
		SqlSession session=null;
		try{
			session=ssf.openSession();
			total=session.selectOne("boardTotalPage");
		}catch(Exception ex){
			System.out.println("DAO boardTotalPage : error");
			ex.printStackTrace();
		}
		finally{
			if(session!=null) {
				session.close(); // POOL => 반환
			}
		}
		return total;
	}
	// 글쓰기
	public static void boardInsert(FreeBoardVO vo) {
		List<FreeBoardVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession(true); // true를 날려야 AutoCommit
			session.insert("boardInsert", vo); // commit X
			//session.commit(); 아니면 이렇게 커밋 날림
		} catch (Exception e) {
			System.out.println("DAO boardInsert error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close(); // 반환 => POOLED(DBCP) => Connection생성(반환하지 않으면 8개가 끝)
			}
		}
	}
	
	//상세보기
	//<select id="boardDetailData" resultType="FreeBoardVO" parameterType="int">
	public static FreeBoardVO boardDetailData(int no) {
		FreeBoardVO vo = new FreeBoardVO();
		SqlSession session=null;
		try{
			session=ssf.openSession();
			session.update("hitIncrement", no); // 조회수 증가
			session.commit(); // 커밋
			vo=session.selectOne("boardDetailData", no);
		}catch(Exception ex){
			System.out.println("DAO boardDetailData : error");
			ex.printStackTrace();
			//session.rollback(); 안써도 catch에 오면 자동 롤백
		}
		finally{
			if(session!=null) {
				session.close(); // POOL => 반환
			}
		}
		return vo;
	}
	
	// 게시글 수정하기
	public static FreeBoardVO boardUpdateData(int no) {
		FreeBoardVO vo = new FreeBoardVO();
		SqlSession session=null;
		try{
			session=ssf.openSession();
			vo=session.selectOne("boardDetailData", no);
		}catch(Exception ex){
			System.out.println("DAO boardDetailData : error");
			ex.printStackTrace();
			//session.rollback(); 안써도 catch에 오면 자동 롤백
		} finally{
			if(session!=null) {
				session.close(); // POOL => 반환
			}
		}
		return vo;
	}
	
	// 게시판 비밀번호 가져오기
	//<select id="boardGetPassword" resultType="String" parameterType="int">
	public static String boardPwdCheck(int no, String pwd) {
		String result = "";
		SqlSession session=null;
		try {
			session = ssf.openSession();
			String db_pwd = session.selectOne("boardGetPassword", no);
			if(db_pwd.equals(pwd)) {
				result = "yes";
			} else {
				result = "no";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null) {
				session.close(); // POOL => 반환
			}
		}
		return result;
	}
	
	// 상세보기 => 수정하기
	//<update id="boardUpdate" parameterType="FreeBoardVO">
	public static void boardUpdate(FreeBoardVO vo) {
		List<FreeBoardVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.update("boardUpdate", vo);
		} catch (Exception e) {
			System.out.println("DAO boardUpdate error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close(); // 반환 => POOLED(DBCP) => Connection생성(반환하지 않으면 8개가 끝)
			}
		}
	}
}
