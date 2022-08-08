package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *  Model
 *  	1. 사용자 요청 데이터 받기 ( 없을 수 있음)
 *  	2. 데이터베이스 연결
 *  	3. request에 값 담기
 *  	4. 어떤 JSP로 보낼지 설정
 *  
 *  JSP
 *  	requets에 있는 데이터 출력
 */
@Controller
public class FoodModel {
	@RequestMapping("food/food_list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		// 카테고리 번호
		String cno = request.getParameter("cno");
		// cno => DAO => 데이터 읽기
		List<FoodVO> list = FoodDAO.foodListData(Integer.parseInt(cno));
		FoodCategoryVO vo = FoodDAO.foodCategoryInfoData(Integer.parseInt(cno));
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		
		request.setAttribute("main_jsp", "../food/food_list.jsp");
		return "../main/main.jsp"; // forward
	}
	
	//쿠키 전송 (상세보기하면 쿠키에 기록 남겨서 메인페이지 최근 본 글 추가하려고)
	@RequestMapping("food/food_detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response) {
		// 쿠키만 전송 / 화면 출력X
		String fno = request.getParameter("fno");
		Cookie cookie = new Cookie("food"+fno, fno);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24); // 저장기간 하루
		response.addCookie(cookie);
		// 상세보기로 이동  (insert_ok.do => list.do로 이동) 이거랑 같은거
		return "redirect:../food/food_detail.do?fno="+fno; // request초기화 => 화면만 이동 (sendRedirect) (Spring.jar => dispatcher에서 그렇게 만들었음)
	}
	
	@RequestMapping("food/food_detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		
		String fno = request.getParameter("fno"); // 위에 쿠키전송에서 fno 넘겨준거 받음
		// 데이터베이스 연동
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(fno));
		// request 에 담아서 넘겨줌
		String address = vo.getAddress();
		String addr1 = address.substring(0, address.lastIndexOf("지번"));
		String addr2 = address.substring(address.lastIndexOf("지번")+2);
		request.setAttribute("addr1", addr1.trim());
		request.setAttribute("addr2", addr2.trim());
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../food/food_detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_find.do")
	public String food_find (HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		String addr = request.getParameter("addr");
		if(addr == null) {
			addr = "강남";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("address", addr);
		
		List<FoodVO> list = FoodDAO.foodLocationFindData(map);
		int totalpage = FoodDAO.foodLocationFindTotalPage(addr);
		
		request.setAttribute("addr", addr);
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("main_jsp", "../food/food_find.jsp");
		return "../main/main.jsp";
	}
}
