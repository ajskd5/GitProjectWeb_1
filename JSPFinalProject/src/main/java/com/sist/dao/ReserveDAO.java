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
	
	// 음식 리스트 출력 <select id="reserveFoodData" resultType="FoodVO" parameterType="String">
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
	
	// 예약날짜 가져오기 <select id="reserveGetDate" resultType="String" parameterType="int">
	public static String reserveGetDate(int fno) {
		String rday = "";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			rday = session.selectOne("reserveGetDate", fno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return rday;
	}
	
	// 일마다 저장된 시간대 가져옴 <select id="reserveGetTime" resultType="String" parameterType="int">
	public static String reserveGetTime(int rno) {
		String rtime = "";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			rtime = session.selectOne("reserveGetTime", rno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return rtime;
	}
	// 시간대에 해당하는 시간 가져오기 (1이면 09:00 2면 10:00) <select id="reserveRealTime" resultType="String" parameterType="int">
	public static String reserveRealTime(int tno) {
		String rtime = "";
		SqlSession session = null;
		try {
			session = ssf.openSession();
			rtime = session.selectOne("reserveRealTime", tno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return rtime;
	}
	
	
	// 예약 등록 <insert id="reserveInsert" parameterType="ReserveVO">
	public static void reserveInsert(ReserveVO vo) {
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.update("reserveInsert", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	// 마이페이지에서 예약 목록 출력 <select id="reserveMypageData" resultType="ReserveVO" parameterType="String">
	public static List<ReserveVO> reserveMypageData(String id){
		SqlSession session = null;
		List<ReserveVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("reserveMypageData", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	// 관리자페이지에서 예약 목록 출력 <select id="reserveAdminpageData" resultType="ReserveVO">
	public static List<ReserveVO> reserveAdminpageData(){
		SqlSession session = null;
		List<ReserveVO> list = null;
		try {
			session = ssf.openSession();
			list = session.selectList("reserveAdminpageData");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	// 관리자페이지에서 예약승인 <update id="reserveAdminUpdate" parameterType="int">
	public static void reserveAdminUpdate(int no) {
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.update("reserveAdminUpdate", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	// 마이페이지 예약 취소 <delete id="reserveCancel" parameterType="int">
	public static void reserveCancel(int no) {
		SqlSession session = null;
		try {
			session = ssf.openSession(true);
			session.delete("reserveCancel", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	// 마이페이지 예약 정보 <select id="reserveInfoData" resultType="ReserveVO" parameterType="int">
	public static ReserveVO reserveInfoData(int no) {
		ReserveVO vo = new ReserveVO();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("reserveInfoData", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return vo;
	}
}
