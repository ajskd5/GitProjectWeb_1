package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	<!-- 이름이 있는지 확인 -->
	<select id="memberNameCount" resultType="int" parameterType="String">
		SELECT COUNT(*) FROM project_member
		WHERE NAME = #{name}
	</select>
	<!-- 이메일 가져오기 -->
	<select id="memberGetEmail" resultType="String" parameterType="String">
		SELECT email FROM project_member
		WHERE name=#{name}
	</select>
	<select id="emailIdFind" resultType="String" parameterType="hashmap">
		SELECT RPAD(SUBSTR(id, 1, 1), LENGTH(id), '*') FROM project_member
		WHERE name=#{name} AND email=#{email}
	</select>
	 */
	
	public static String emailIdFind(String name, String email) {
		String result = "";
		SqlSession session = ssf.openSession();
		int count = session.selectOne("memberNameCount", name);
		if(count == 0) {
			result = "존재하지 않는 이름입니다!";
		} else {
			String db_email = session.selectOne("memberGetEmail", name);
			if(db_email.equals(email)) {
				Map map = new HashMap();
				map.put("name", name);
				map.put("email", email);
				result = session.selectOne("emailIdFind", map);
			} else {
				result = "이메일이 틀립니다!";
			}
		}
		session.close();
		return result;
	}
	
	// 전화번호로 찾기
	public static String telIdFind(String name, String tel) {
		String result = "";
		SqlSession session = ssf.openSession();
		int count = session.selectOne("memberNameCount", name);
		if(count == 0) {
			result = "존재하지 않는 이름입니다!";
		} else {
			String db_tel = session.selectOne("memberGetTel", name);
			if(db_tel.equals(tel)) {
				Map map = new HashMap();
				map.put("name", name);
				map.put("tel", tel);
				result = session.selectOne("telIdFind", map);
			} else {
				result = "전화번호가 틀립니다!";
			}
		}
		session.close();
		return result;
	}
}
