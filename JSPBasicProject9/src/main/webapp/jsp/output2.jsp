<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%-- output 에서 값 받은거랑 똑같음 --%>
<jsp:useBean id="mb" class="com.sist.dao.MemberBean">
	<jsp:setProperty name="mb" property="*"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : <%=mb.getName() %><br>
	성별 : <%=mb.getSex() %><br>
	나이 : <%=mb.getAge() %><br>
	주소 : <%=mb.getAddress() %><br>
	전화번호 : <%=mb.getTel() %><br>
</body>
</html>