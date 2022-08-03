package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FreeBoardModel {
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize * curpage;
		
		Map map = new HashMap();
		map.put("page", page);
		map.put("start", start);
		map.put("end", end);
		List<FreeBoardVO> list = FreeBoardDAO.boardListData(map);
		
		int totalpage = FreeBoardDAO.boardTotalPage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		FreeBoardVO vo = new FreeBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		FreeBoardDAO.boardInsert(vo);
		return "redirect:../freeboard/list.do";
	}
	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		FreeBoardVO vo =  FreeBoardDAO.boardDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
}
