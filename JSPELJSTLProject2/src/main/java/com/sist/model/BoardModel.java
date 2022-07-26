package com.sist.model;
import java.io.IOException;
import java.util.*;
import com.sist.dao.*;
import java.text.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardModel {
	public void boardListData(HttpServletRequest request) {
		ReplyBoardDAO dao = new ReplyBoardDAO();
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		List<ReplyBoardVO> list = dao.boardListData(curpage);
		int count = dao.boardRowCount();
		int totalpage = (int)(Math.ceil(count/10.0));
		count = count-((curpage*10)-10);
		// JSP 로 결과값 전송
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		request.setAttribute("today", today);
	}
	
	// 글쓰기
	public void boardInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		ReplyBoardVO vo = new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		ReplyBoardDAO dao = new ReplyBoardDAO();
		dao.boardInsert(vo);
		
		try {
			response.sendRedirect("list.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// JSP는 화면에 출력만, 모든 처리는 Java에서 함 => MVC
		// MODEL => vo, dao, model
	}
	
	// 상세보기
	public void boardDetailData(HttpServletRequest request) {
		String no = request.getParameter("no");
		ReplyBoardDAO dao = new ReplyBoardDAO();
		ReplyBoardVO vo =  dao.boardDetailData(Integer.parseInt(no));
		
		// JSP로 전송 (dao.boardDetailData 결과값 = vo => 여기서 vo 그대로 받음)
		request.setAttribute("vo", vo);
	}
	
	//======================= JSP(요청) : click, 	입력, 마우스 ====> MODEL에서 받아서 처리 === 결과값 ==> JSP
}
