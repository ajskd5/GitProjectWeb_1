<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 라이브러리 가져옴 p.545 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
	prefix="core"쓰면 core로 써야함
	<core:
	prefix="c"
	<c:
--%>
<%--
	JSTL (p.542) : JSP Standard Tag Lib => tag로 자바 제어문을 제공 (XML 형식)
		= core : 변수 선언, 화면 출력, 제어문, 화면 이동
			변수 선언
				<c:set var="" value="">
						key		value값
				ex)
					<c:set var="name" value="박문수"> ==> request.setAttribute("name", "박문수")
					<c:out value=""> : 화면 출력
					
					<c:if> => 단점 (else가 없음) => 사용자 정의
					<c:choose> 						다중 조건문
						<c:when></c:when> 				if
						<c:otherwise></c:otherwise>		else
					</c:choose>
					반복문
					<c:forEach var="i" begin="1" end="10" step="1">
					</c:forEach>
						=> for(int i=1; i<=10; i++) 이거랑 같음
					<c:forEach var="vo" items="list">
						=> for(SawonVO vo : list)
						
					<c:forTokens> => StringTokenizer => st.hasTokens
					
					화면 이동
					response.sendRedirect() ===> <c:redirect url="">
					
		= fmt : 변환 (날짜, 숫자 변환)
			<fmt:formatDate>	=> 오라클 => SimpleDateFormat
			<fmt:formatNumber>	=> 오라클 => NumberForamt
		= fn : String => 메소드
			${fn:substring()}, ${fn:trim()}, ... 	=> 보통 자바에서 처리 (자동 완성기)
		--------
		= xml
		= sql
		-------- 사용 안함 (sql => DAO)
		
		===> 변경 (JSP가 없음) => HTML 자체 출력
						루프 출력 : ThymeLeaf, Vue, React, Ajax
		===> 변경 (SQL 없음) => JPA
--%>
<%
	String name = "박문수"; // ${name} => request.setAttribute("name", name);
	request.setAttribute("name", name);
%>
<c:set var="addr" value="서울시 강남구"/>
<%-- request.setAttribute("addr", "서울시 강남구") --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : ${name}<br>
	주소 : ${addr}<br>
	<c:out value="${addr }"/> <%-- Jquery와 충돌될 경우 이렇게 씀 --%>
</body>
</html>