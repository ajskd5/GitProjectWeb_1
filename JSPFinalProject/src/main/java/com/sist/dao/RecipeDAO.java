package com.sist.dao;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.sist.vo.*;
import java.io.*;

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			// XML에 등록된 모든 데이터 읽어서 저장 => HashMap
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//<select id="recipeListData" resultType="RecipeVO" parameterType="hashmap">
	public static List<RecipeVO> recipeListData(Map map){
		List<RecipeVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("recipeListData", map);
		} catch (Exception e) {
			System.out.println("DAO recipeListData error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close(); // 반환 => POOLED(DBCP) => Connection생성(반환하지 않으면 8개가 끝)
			}
		}
		return list;
	}
	
	//<select id="chefListData" resultType="ChefVO" parameterType="hashmap">
	public static List<ChefVO> chefListData(Map map){
		List<ChefVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("chefListData", map);
		} catch (Exception e) {
			System.out.println("DAO chefListData error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	//<select id="chefMakeRecipeData" resultType="RecipeVO" parameterType="hashmap">
	public static List<RecipeVO> chefMakeRecipeData(Map map){
		List<RecipeVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("chefMakeRecipeData", map);
		} catch (Exception e) {
			System.out.println("DAO chefMakeRecipeData error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	// 레시피 총페이지 <select id="recipeTotalPage" resultType="int">
	public static int recipeTotalPage() {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("recipeTotalPage");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return total;
	}
	
	// 쉐프 총페이지 <select id="chefTotalPage" resultType="int">
	public static int chefTotalPage() {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("chefTotalPage");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return total;
	}
	
	// 검색 총페이지 <select id="chefMakeTotalPage" resultType="int" parameterType="String">
	public static int chefMakeTotalPage(String chef) {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("chefMakeTotalPage", chef);
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
