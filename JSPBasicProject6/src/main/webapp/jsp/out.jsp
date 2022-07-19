<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<%--
	out : JspWriter => 출력 버퍼 관리
	<%= %>
	=> print, println, getBufferSize(), getRemaining(), clear()
	   -------------- <% %> HTML 쓸때 %닫고 해야하는데 너무 복잡하면 println으로 써도 됨 (권장 X)
	   HTML을 출력하는 메모리 크기 => 8kb
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	출력버퍼의 크기 : <%=out.getBufferSize() %><br>
	남아있는 버퍼 : <%=out.getRemaining() %><br>
	사용중인 버퍼 확인 : <%=out.getBufferSize()-out.getRemaining() %><br>
	<%
		String name = "홍길동";
		out.println(name);
	%>
	<%-- <%=name %> --%>
</body>
</html>