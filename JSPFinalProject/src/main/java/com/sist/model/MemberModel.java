package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.MemberVO;

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
	
	// 회원가입 ok
/*
ID       NOT NULL VARCHAR2(20)  
PWD      NOT NULL VARCHAR2(10)  
NAME     NOT NULL VARCHAR2(34)  
SEX               VARCHAR2(10)  
BIRTHDAY NOT NULL VARCHAR2(30)  
EMAIL             VARCHAR2(100) 
POST     NOT NULL VARCHAR2(20)  
ADDR1    NOT NULL VARCHAR2(200) 
ADDR2             VARCHAR2(200) 
TEL               VARCHAR2(30)  
CONTENT           CLOB          
ADMIN             CHAR(1)       
LOGIN             CHAR(1)
 */
	@RequestMapping("member/join_ok.do")
	public String member_join_ok(HttpServletRequest request, HttpServletResponse response) {
		// 사용자 전송값 받기
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String post = request.getParameter("post");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String content = request.getParameter("content");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setTel(tel1+"-"+tel2);
		vo.setContent(content);
		// 데이터베이스 연동
		// 요청 처리
		MemberDAO.memberInsert(vo);
		// 화면 이동
		return "redirect:../main/main.do"; // include될 파일이 아니라 main.do로 홈 include 해야함
	}
	
	// 로그인처리
	@RequestMapping("member/login_ok.do")
	public String member_login_ok(HttpServletRequest request, HttpServletResponse response) {	
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO vo = MemberDAO.isLogin(id, pwd);
		String result = vo.getMsg();
		// 로그인되면 session에 저장하고 return		=> session은 서버에 저장 (브라우저 종료, 로그아웃시 해제)
		//															=> 모든 JSP에서 사용 가능
		if(result.equals("OK")) {
			HttpSession session = request.getSession();
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		request.setAttribute("result", result);
		return "../member/login_ok.jsp"; // res=>(NOID, NOPWD, OK) 이걸 ok.jsp에서 출력하고 ajax가 출력한거 읽어감
	}
	/*
	 * 1. 화면  ==> 실제 화면 출력 ==> forward : request 전송
	 * 				기존 화면 출력 ==>  request가 필요 없는 경우 : redirect
	 * 					=> insert, update, delete
	 */
	// 로그아웃
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate(); // 저장된 모든 데이터 삭제
		return "redirect:../main/main.do";
	}
}
