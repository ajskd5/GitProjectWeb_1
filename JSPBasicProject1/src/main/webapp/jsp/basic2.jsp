<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.*" %>
<%--
	생략할 수 있는 import
	1. java.lang.*
	2. javax.servlet.http => request, rsponse
 --%>
 <%
	// 1. 사용자가 보내준 데이터 받기
	// 2. 데이터베이스 연동
	// 3. 데이터 읽기
	// 자바 => HTML
	// MVC ==> Model => View
	// insert / update / delete => 화면 분리 (입력창, 처리창 따로 만들어서 처리)
	// 데이터 읽기 => java		화면 출력 => HTML
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	오늘 날짜 : <%= sdf.format(date) %> <%-- ;을 사용하지 않는다 --%>
	<%--
		out.println(=> 여기에 들어가는 문장은 ;을 사용하지 않음);
	 --%>
	
</body>
</html>