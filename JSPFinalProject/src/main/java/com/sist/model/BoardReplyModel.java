package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
/*
 *  1. VO : 데이터 모아서 전송
 *  2. mapper : SQL 문장
 *  3. DAO: 실제 오라클 연결 => SQL 문장 전송, 결과값 읽기
 *  4. Model : JSP로 요청 결과값 전송
 *  5. JSP : Model에서 보낸 데이터 출력
 *  
 */

@Controller
public class BoardReplyModel {
	@RequestMapping("board_reply/list.do") // 리스트 출력
	public String board_reply_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (rowSize*curpage) - (rowSize-1);
		int end = rowSize*curpage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<BoardReplyVO> list = BoardReplyDAO.boardReplyListData(map);
		int totalpage = BoardReplyDAO.boardReplyTotalPage();
		
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("main_jsp", "../board_reply/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("board_reply/insert.do")// 글쓰기
	public String board_reply_insert(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../board_reply/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("board_reply/insert_ok.do")
	public String board_reply_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String pno = request.getParameter("pno");
		
		BoardReplyVO vo = new BoardReplyVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardReplyDAO.boardReplyInsertOk(Integer.parseInt(pno),vo);
		
		return "redirect:../board_reply/list.do";
	}
	
	//상세보기
	@RequestMapping("board_reply/detail.do")
	public String board_reply_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		BoardReplyVO vo = BoardReplyDAO.boardReplyDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../board_reply/detail.jsp");
		return "../main/main.jsp";
	}
	
	// 수정하기
	@RequestMapping("board_reply/update.do")
	public String board_reply_update(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		request.setAttribute("main_jsp", "../board_reply/update.jsp");
		return "../main/main.jsp";
	}
}
