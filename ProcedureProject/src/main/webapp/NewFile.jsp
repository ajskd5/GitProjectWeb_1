<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	StudentDAO dao = new StudentDAO();
	List<StudentVO> list = dao.stdListData();
	
	out.println("<html>");
	out.println("<body>");
	out.print("<ul>");
	for(StudentVO vo : list) {
		out.print("<li>" + vo.getHakbun() + "-" + vo.getName() + "-" + vo.getKor() + "-" 
				+ vo.getEng() + "-" + vo.getMath() + "</li>");
	}
	out.print("</ul>");
	out.println("</body>");
	out.println("</html>");
	%>
</body>
</html>