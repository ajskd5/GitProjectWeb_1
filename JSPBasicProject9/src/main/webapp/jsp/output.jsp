<%@page import="com.sist.dao.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	MemberBean mb = new MemberBean();
	String name = request.getParameter("name");
	String sex = request.getParameter("sex");
	String age = request.getParameter("age");
	String address = request.getParameter("address");
	String tel = request.getParameter("tel");
	
	mb.setName(name);
	mb.setSex(sex);
	mb.setAge(Integer.parseInt(age));
	mb.setAddress(address);
	mb.setTel(tel);
%>
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