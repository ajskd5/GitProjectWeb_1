package com.sist.dao;

import java.util.*;
import com.sist.vo.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
	
	//아이디 중복체크 <select id="memberIdCheck" resultType="int" parameterType="String">
	public static int memberIdCheck(String id) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("memberIdCheck", id);
		} catch (Exception e) {
			System.out.println("DAO memberIdCheck error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return count;
	}
}
