<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <jsp:forward page="output.jsp"></jsp:forward> --%>
<%
	RequestDispatcher rd = request.getRequestDispatcher("output.jsp");
	rd.forward(request, response);
%>
<%-- 이게 액션태그 forward랑 같음 (request 보냄) --%>