<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL => 내장 객체
	String name = "홍길동;
	${name} ==> 출력 못함
	------ 일반 변수 출력 못함
	request.setAttribute("name", "홍길동")
	${name} => 키
	------- request.getAttribute("name"
	session.setAttribute("id", "admin")
	${sessionScope.id}
	
	===> request, session에 추가된 값을 출력 => p.525
	*** 1. requestScope => request.getAttribute() : request.setAttribute("key", value)
													value 출력 : getAttribute("key")
													=> ${requestScope.key}
														${key} => requestScope 생략 가능
	*** 2. sessionScope => session.getAttribute() : session.setAttribute("key", value)
													value 출력 : getAttribute("key")
													=> ${sessionScope.key}
														${key} => sessionScope 생략 가능
				*** request, session => 같은 키가 있는 경우에는 request가 우선 순위(생략시 request부터 가져옴)
	3. applicationScope => application.getAttribute()
	4. param => request.getParameter("name") ==> ${param.name}
	5. paramValue => request.getParameterValues("hobby") ==> ${paramValues.hobby}
	
	*** requset, session에 추가된 값을 출력한다
	
--%>
<%
	String name = "홍길동";
	// ${} => request, session 값을 추가 => setAttribute()
	request.setAttribute("name", name);
	session.setAttribute("name", "심청이");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름:${name } ()생략시 request가 우선순위<br>
	이름:${sessionScope.name }<br>
	
</body>
</html>