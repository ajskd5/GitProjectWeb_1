package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class ReserveModel {
	@RequestMapping("reserve/reserve.do")
	public String reserve_main(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../reserve/reserve.jsp");
		return "../main/main.jsp";
	}
	
	// reserve ajax
	// 음식 리스트(종류별)
	@RequestMapping("reserve/food_list.do")
	public String reserve_food_list(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if(type == null) {
			type = "한식";
		}
		
		// 데이터 읽기
		List<FoodVO> list = ReserveDAO.reserveFoodData(type);
		
		// 데이터 => JSP로 전송
		request.setAttribute("list", list);
		return "../reserve/food_list.jsp";
	}
	
	// 달력출력 => 맛집 클릭하면
	@RequestMapping("reserve/reserve_date.do")
	public String reserve_date(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		String today = new SimpleDateFormat("yyyy-M-d").format(new Date()); // 오늘 날짜 가져오기
		StringTokenizer st = new StringTokenizer(today, "-");
		String strYear = st.nextToken();
		String strMonth = st.nextToken();
		String strDay = st.nextToken();
		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		int day = Integer.parseInt(strDay);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1); // 1일의 요일을 알아야 함
		int week = cal.get(Calendar.DAY_OF_WEEK); // 1일의 요일 가져옴
		int lastday = cal.getActualMaximum(Calendar.DATE); // 마지막 날짜 가져옴(30 / 31 / 28 / 29)
		
		// DAO => 예약이 가능한 날 체크
		
		
		
		return "../reserve/food_list.jsp";
	}
	
	
	
	
}
