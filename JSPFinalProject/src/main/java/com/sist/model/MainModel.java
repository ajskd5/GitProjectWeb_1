package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request, HttpServletResponse response) {
		// 시작하자마자 메인페이지에서 출력
		List<FoodCategoryVO> list = FoodDAO.foodCategoryData();
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../main/home.jsp"); // 데이터 받아서 출력
		return "../main/main.jsp";  // include
	}
}
