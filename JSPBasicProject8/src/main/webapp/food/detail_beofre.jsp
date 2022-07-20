<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String fno = request.getParameter("no");
		// key가 중복이 없음 (최신 방문 여러번해도 하나만 뜸)
	Cookie cookie = new Cookie("f" + fno, fno);
%>