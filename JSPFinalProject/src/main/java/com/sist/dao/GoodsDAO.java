package com.sist.dao;

import java.io.*;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.GoodsVO;

public class GoodsDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			// XML 파싱 (Config.xml(Connection), Mapper(PreparedStatement, ResultSet)
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 상품 메인 <select id="goodsMainData" resultType="GoodsVO" parameterType="String">
	public static List<GoodsVO> goodsMainData(String table_name){
		List<GoodsVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("goodsMainData", table_name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	
	// 상품 리스트 <select id="goodsListData" resultType="GoodsVO" parameterType="hashmap">
	public static List<GoodsVO> goodsListData(Map map){
		List<GoodsVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("goodsListData", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	// 총페이지 구하기 <select id="goodsTotalPage" resultType="int" parameterType="String">
	public static int goodsTotalPage(String table_name){
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("goodsTotalPage", table_name);
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
