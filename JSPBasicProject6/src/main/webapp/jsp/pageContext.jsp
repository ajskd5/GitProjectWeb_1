<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- p.187
	String name = request.getParameter("name")
		=> String name = pageContext.getRequest().getParameter("name");
		내장 객체를 얻어올 때 사용
	
	다른 파일을 포함하거나 파일 이동이 가능
	----------------------------------------
	파일 이동
		response =====================> sendRedirect()
		pageContext ==================> forward() => request을 화면 이동과 동시에 전송
										자신에서 파일을 불러옴 (url주소 안바뀜) => errorpage
	액션 태그
		<jsp:include>	<jsp:forward>
		include => 
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>