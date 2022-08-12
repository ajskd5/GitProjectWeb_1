package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
/*
 * 사용자 => URL 이용해서 연결 ==> Controller(요청 처리) ==> Model(분산)
 * 								사용자 요청 ==> 요청 처리 ==> 결과값 JSP전송
 * 												-> 나눠서 작업(Model)
 * Controller ==> 고정 (Spring.jar) => Spring/Struts => @RequestMapping => jsp
 */

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	/* http:localhost:8080 /JSPDateProject/diary/diary.do?year=2022&month=8		=> URL
	   ---- --------------  -----------------------------  -----------------
	   port(80) 서버주소				URI						request
	   						--------------
	   							Context
	*/
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length()+1);
		
		String jsp = "";
		if(uri.equals("diary/diary.do")) {
			// Model클래스의 메소드 => 처리 (요청처리)
			DiaryModel model = new DiaryModel();
			jsp = model.diary_main(request, response);
		} else if(uri.equals("diary/diary_ok.do")) {
			DiaryModel model = new DiaryModel();
			jsp = model.diary_ok(request, response);
		}
		
		// 결과값 JSP로 전송
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}
