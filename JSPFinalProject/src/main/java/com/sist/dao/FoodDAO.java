package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.sist.vo.*;
import java.io.*;

public class FoodDAO {
	// XML 파싱 (등록된 데이터 읽기)
	private static SqlSessionFactory ssf;
	static { // 자동 수행
		try {
			// xml 읽기
				// src/main/java => Config.xml (classpath영역 => MyBatis에서 자동 인식)
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // getConnection, disConnection 완료
	
	// 기능
	
	// static => 시작하자마자 메인페이지에서 출력
	public static List<FoodCategoryVO> foodCategoryData(){
		SqlSession session = null;
		List<FoodCategoryVO> list = null;
		try {
			session = ssf.openSession(); // getConnection
			list = session.selectList("foodCategoryData");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	// 카테고리 리스트
	/*
	<select id="foodListData" resultType="FoodVO" parameterType="int">
		SELECT fno, poster, name, tel, type
		FROM food_house
		WHERE cno = #{cno}
	</select>
	*/
	public static List<FoodVO> foodListData(int cno){
		SqlSession session = null;
		List<FoodVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodListData", cno);
			// WHERE cno = #{cno} => #(?)여기에 값 넣어줌 ▲
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	/*
	<select id="foodCategoryInfoData" resultType="FoodCategoryVO" parameterType="int">
		SELECT title, subject
		FROM food_category
		WHERE cno = #{cno}
	</select>
	 */
	public static FoodCategoryVO foodCategoryInfoData(int cno) {
		SqlSession session = null;
		FoodCategoryVO vo = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodCategoryInfoData", cno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}
}
