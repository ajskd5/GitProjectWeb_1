package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.*;
public class DiaryModel {
	public String diary_main(HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d"); // 08 -> 오류
		String today = sdf.format(date); // 오늘 날짜
		
		StringTokenizer st = new StringTokenizer(today, "-");
		String sy = st.nextToken();
		String sm = st.nextToken();
		String sd = st.nextToken(); 
		
		// default
		String strYear = request.getParameter("year");
		if(strYear == null) {
			strYear = sy;
		}
		String strMonth = request.getParameter("month");
		if(strMonth == null) {
			strMonth = sm;
		}
		
		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		int day = Integer.parseInt(sd);
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		
		return "../diary/diary.jsp";
	}
	
	public String diary_ok(HttpServletRequest request, HttpServletResponse response) {
		// 처리 => 결과값 => request.setAttribute()
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d"); // 08 -> 오류
		String today = sdf.format(date); // 오늘 날짜
		
		StringTokenizer st = new StringTokenizer(today, "-");
		String sy = st.nextToken();
		String sm = st.nextToken();
		String sd = st.nextToken(); 
		
		// default
		String strYear = request.getParameter("year");
		if(strYear == null) {
			strYear = sy;
		}
		String strMonth = request.getParameter("month");
		if(strMonth == null) {
			strMonth = sm;
		}
		
		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		int day = Integer.parseInt(sd);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1); // 0부터 시작 (0=1월, 1=2월,  ... )
		cal.set(Calendar.DATE, 1); // 달력을 1일부터 가져와야함 day는 오늘 날짜임
		int week = cal.get(Calendar.DAY_OF_WEEK); // cal에 해당하는 요일 구해줌 (1일의 요일)
		int lastday = cal.getActualMaximum(Calendar.DATE); // 달의 마지막 날짜 (반복문을 돌리기 위함)
		
		String[] strWeek = {"일", "월", "화", "수", "목", "금", "토"};
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("week", week-1); // week는 1부터 시작함 (1:일, 2:월, ...)
		request.setAttribute("lastday", lastday);
		request.setAttribute("strWeek", strWeek);
		
		return "../diary/diary_ok.jsp";
	}
}
