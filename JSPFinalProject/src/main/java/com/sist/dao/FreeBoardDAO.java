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
			System.out.println("boardTotalPage : error");
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
	
	//
	
}
