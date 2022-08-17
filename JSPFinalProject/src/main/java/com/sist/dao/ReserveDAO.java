package com.sist.dao;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.sist.vo.*;
import java.io.*;

public class ReserveDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//<select id="reserveFoodData" resultType="FoodVO" parameterType="String">
	public static List<FoodVO> reserveFoodData(String type){
		List<FoodVO> list = null;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("reserveFoodData", type);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
}
