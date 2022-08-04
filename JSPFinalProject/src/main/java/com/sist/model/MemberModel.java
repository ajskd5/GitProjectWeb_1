package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;

@Controller
public class MemberModel {
	// 아이디 중복체크
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request, HttpServletResponse response) {
		
		return "../member/idcheck.jsp";
	}
	@RequestMapping("member/idcheck_ok.do")
	public String member_idcheck_ok(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = MemberDAO.memberIdCheck(id);
		
		request.setAttribute("count", count);
		return "../member/idcheck_ok.jsp";
	}
	// 이메일 중복체크
	@RequestMapping("member/email_check.do")
	public String member_email_check(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		int count = MemberDAO.memberEmailCheck(email);
		
		request.setAttribute("count", count);
		return "../member/idcheck_ok.jsp"; // 이렇게 해도 어차피 count만 출력하는 애라 상관없음
	}
	// 전화번호 중복체크
		@RequestMapping("member/tel_check.do")
		public String member_tel_check(HttpServletRequest request, HttpServletResponse response) {
			String tel = request.getParameter("tel");
			int count = MemberDAO.memberTelCheck(tel);
			
			request.setAttribute("count", count);
			return "../member/idcheck_ok.jsp"; // 이렇게 해도 어차피 count만 출력하는 애라 상관없음
		}
	
	// 로그인창 띄우기
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request, HttpServletResponse response) {

		return "../member/login.jsp";
	}
	
	//회원가입
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../member/join.jsp");
		return "../main/main.jsp";
	}
	
	
}
