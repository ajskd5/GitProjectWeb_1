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
	//이메일 중복체크
	public static int memberEmailCheck(String email) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("memberEmailCheck", email);
		} catch (Exception e) {
			System.out.println("DAO memberEmailCheck error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return count;
	}
	//전화번호 중복체크
	public static int memberTelCheck(String tel) {
		int count = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			count = session.selectOne("memberTelCheck", tel);
		} catch (Exception e) {
			System.out.println("DAO memberTelCheck error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return count;
	}
	
/*
ID       NOT NULL VARCHAR2(20)  
PWD      NOT NULL VARCHAR2(10)  
NAME     NOT NULL VARCHAR2(34)  
SEX               VARCHAR2(10)  
BIRTHDAY NOT NULL VARCHAR2(30)  
EMAIL             VARCHAR2(100) 
POST     NOT NULL VARCHAR2(20)  
ADDR1    NOT NULL VARCHAR2(200) 
ADDR2             VARCHAR2(200) 
TEL               VARCHAR2(30)  
CONTENT           CLOB          
ADMIN             CHAR(1)       
LOGIN             CHAR(1)       
*/
	//회원가입 <select id="memberInsert" parameterType="MemberVO">
	public static void memberInsert (MemberVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession(true); // AutoCommit
			session.insert("memberInsert", vo);
		} catch (Exception e) {
			System.out.println("DAO memberTelCheck error");
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	/*
	 * <!-- 아이디 중복체크 -->
	<select id="memberIdCount" resultType="int" parameterType="String">
		SELECT COUNT(*) FROM project_member WHERE id=#{id}
	</select>
		<!-- 비밀번호 확인 -->
	<select id="memberInfoData" resultType="MemberVO" parameterType="String">
		SELECT id, pwd, name, admin FROM project_member
		WHERE id=#{id}
	</select>
	 */
	public static MemberVO isLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			int count = session.selectOne("memberIdCount", id);
			if(count==0) {
				vo.setMsg("NOID");
			} else {
				vo = session.selectOne("memberInfoData", id);
				if(pwd.equals(vo.getPwd())) { // 로그인
					vo.setMsg("OK");
				} else { // 비밀번호 틀린 상태
					vo.setMsg("NOPWD");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return vo;
	}
	
	// =====================================================================================
	// 회원수정
	// 정보가져오기
	//<select id="memberDetailData" resultType="MemberVO" parameterType="String">
	public static MemberVO memberDetailData(String id) {
		MemberVO vo = new MemberVO();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("memberDetailData", id);
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			if(session != null) {
				session.close();
			}
		}
		return vo;
	}
	
	// 정보 수정
	// <update id="memberUpdate" parameterType="MemberVO">
	public static boolean memberUpdate(MemberVO vo) {
		boolean bCheck = false;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			// 비밀번호 확인
			MemberVO pvo = session.selectOne("memberInfoData", vo.getId());
			if(pvo.getPwd().equals(vo.getPwd())) {
				bCheck=true;
				session.update("memberUpdate", vo);
				session.commit();
			} else {
				bCheck=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return bCheck;
	}
	
	// 회원 탈퇴
	//<delete id="memberDelete" parameterType="String">
	public static boolean memberDelete(String id, String pwd) {
		boolean bCheck = false;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			// 비밀번호 확인
			MemberVO pvo = session.selectOne("memberInfoData", id);
			if(pvo.getPwd().equals(pwd)) {
				bCheck=true;
				session.delete("memberDelete", id);
				session.commit();
			} else {
				bCheck=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return bCheck;
	}
}
