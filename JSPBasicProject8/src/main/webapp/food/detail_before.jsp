<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String fno = request.getParameter("no");
		// key가 중복이 없음 (최신 방문 여러번해도 하나만 뜸)
	Cookie cookie = new Cookie("f" + fno, fno);
	cookie.setPath("/");
	cookie.setMaxAge(60*60*24);
	response.addCookie(cookie);
	
	//이동
	response.sendRedirect("food_detail.jsp?no=" + fno);
	/*
		이동 /  데이터 전송
		------------------
		GET
			자바스크립트 : location.href
			HTML : <a>, <form>
			자바 : sendRedirect, forward
		POST
			자바스크립트 : ajax, axios
			HTML : <form>
	*/
%>