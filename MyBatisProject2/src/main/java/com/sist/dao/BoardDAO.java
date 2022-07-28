package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class BoardDAO {
	private static SqlSessionFactory ssf;
	static {
		// XML 파싱 (읽기) => 데이터 주입
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 목록
	public static List<BoardVO> boardListData(Map map){
		SqlSession session = null; // 주소 읽기
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			session = ssf.openSession();
			list = session.selectList("boardListData", map);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close(); // 반환
		}
		return list;
	}
	
	// 상세
}
