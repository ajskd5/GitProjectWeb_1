<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	p.119
	JSP의 스크립트
		=> JSP = Java + HTML(CSS, JavaScript)
				-----	--------------------- 구분해서 코딩
		스크립트 => 자바 코딩 위치
		=> 자바 코딩
			1. 일반 자바
				<% %> => 스크립트릿
			2. 선언식 : 메소드 선언, 전역변수
				<%! %>
			3. 표현식 : 변수값 자바에서 나오는 결과값 출력
							System.out.println()
				<%= %>
	
--%>
<%--  메소드 선언 --%>
<%!
	public int add(int a, int b){
		return a + b;	
	}
	// 자바 문법을 따라 간다 (주석 포함)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 메소드 호출 --%>
	<%
		int res = add(10, 20); // _jspService() 메소드 영역에 코딩
	%>
	<%-- 결과값 출력 
		out.println(); 이게 포함되어 있음
	--%>
	<%= res /* 이렇게만 주석 가능 */%> <%-- ; 사용할 수 없음 --%>
</body>
</html>