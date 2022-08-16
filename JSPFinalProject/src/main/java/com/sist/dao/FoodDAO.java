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
	
	/* 카테고리 제목, 부제목 가져오기
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
	
	//상세보기
	public static FoodVO foodDetailData(int fno) {
		SqlSession session = null;
		FoodVO vo = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodDetailData", fno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}
	
	/* 지역별 맛집 찾기
	 * 
	 <select id="foodLocationFindData" resultType="FoodVO" parameterType="hashmap">
		SELECT fno, name, score, poster, type, address, num
		FROM (SELECT fno, name, score, poster, type, address, rownum as num
		FROM (SELECT fno, name, score, poster, type, address 
		FROM food_location WHERE address LIKE '%'||#{address}||'%' ORDER BY fno ASC))
		WHERE num BETWEEN #{start} AND #{end}
	</select>
	<select id="foodLocationFindTotalPage" resultType="int" parameterType="String">
		SELECT CEIL(COUNT(*)/12.0) FROM food_location
		WHERE address LIKE '%'||#{address}||'%'
	</select>
	 */
	public static List<FoodVO> foodLocationFindData(Map map){
		SqlSession session = null;
		List<FoodVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodLocationFindData", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public static int foodLocationFindTotalPage(String address) {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("foodLocationFindTotalPage", address);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}
	
	
	/*
	 * ===================  찜하기  ======================================
	<insert id="foodJjimInsert" parameterType="JjimVO">
		<selectKey keyProperty="no" resultType="int" order="BEFORE">
			SELECT NVL(MAX(no)+1, 1) as no FROM jjim
		</selectKey>
		INSERT INTO jjim VALUES(#{no}, #{id}, #{fno})
	</insert>
	<!-- 찜 여부 확인 -->
	<select id="foodJjimCount" resultType="int" parameterType="JjimVO">
		SELECT COUNT(*) FROM jjim
		WHERE fno=#{fno} AND id=#{id}
	</select>
	 */
	public static void foodJjimInsert(JjimVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.insert("foodJjimInsert", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public static int foodJjimCount(JjimVO vo) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("foodJjimCount", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}
	
	// 마이페이지 찜 목록
	//<select id="foodJjimListData" resultType="FoodVO" parameterType="int">
	public static FoodVO foodJjimListData(int fno) {
		SqlSession session = null;
		FoodVO vo = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodJjimListData", fno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}
	//<select id="foodJjimGetFno" resultType="int" parameterType="String">
	public static List<Integer> foodJjimGetFno(String id){
		List<Integer> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodJjimGetFno", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	// 찜 취소
	//<delete id="foodJjimDelete" parameterType="JjimVO">
	public static void foodJjimDelete(JjimVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.delete("foodJjimDelete", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	
}
