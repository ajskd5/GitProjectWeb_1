<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	Redirect는 id못 받음
	Forward는 받음
	Forward는 파일이 바뀌는게 아니라 붙여넣기 
 --%>
	<a href="redirect.jsp?id=admin1">Redirect</a><br>
	<a href="forward.jsp?id=admin2">Forward</a><br>
	
</body>
</html>