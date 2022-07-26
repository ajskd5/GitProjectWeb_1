package com.sist.model;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.*;
import com.sist.dao.*;
import java.text.*;
import javax.servlet.http.*;

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
		request.setAttribute("msg", "관리자가 삭제한 게시물입니다!");
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
	
	// 답변
	public void boardReplyInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String pno = request.getParameter("pno"); // 상위 게시물
		
		ReplyBoardVO vo = new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO 연동
		ReplyBoardDAO dao = new ReplyBoardDAO();
		dao.replyInsert(Integer.parseInt(pno), vo);
		
		// 화면 이동 (list.jsp)
		try {
			response.sendRedirect("list.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 수정하기 (데이터 가져오기)
	public void boardUpdateData(HttpServletRequest request) {
		// update.jsp?no=${vo.no}
		String no = request.getParameter("no");
		
		//DAO에서 데이터 읽기 
		ReplyBoardDAO dao = new ReplyBoardDAO();
		ReplyBoardVO vo = dao.boardUpdateData(Integer.parseInt(no));
		
		// 읽은 데이터 request에 담아서 넘겨줌 (update.jsp)
		request.setAttribute("vo", vo);
	}
	//수정하기 확인버튼
	public void boardUpdateOk(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no"); // 상위 게시물
		
		ReplyBoardVO vo = new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		
		// DAO 연동
		ReplyBoardDAO dao = new ReplyBoardDAO();
		
		// 결과값 전송
		boolean bCheck = dao.boardUpdate(vo);
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("no", no);
	}
	
	//삭제
	public void boardDelete(HttpServletRequest request) {
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		// DAO 연동
		ReplyBoardDAO dao = new ReplyBoardDAO();
		// 결과값 request에 담기
		String res = dao.boardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("res", res);
	}
}
