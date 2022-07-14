package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 변환 (전송방법) => HTML, XML, JSON
		response.setContentType("text/HTML; charset=UTF-8");
		// 2. 브라우저에서 읽어갈 위치의 메모리 설정
		PrintWriter out = response.getWriter();out.println("<html>");
		
		out.println("<head>");
		out.println("<style type=text/css>");
		out.println("div{ width:100%; }");
		out.println("h1{ margin-top:50px; text-align:center}");
		out.println("table{ margin:0px auto;}"); // 가운데 정렬
		out.println("</style>");
		out.println("<link rel=stylesheet href=table.css>");
		out.println("</head>");
		out.println("<body>");	
		
		out.println("<div>");
		out.println("<h1>자유게시판</h1>");
		
		out.println("<table width=700 class=table_content>");
		out.println("<tr>");
		out.println("<th align=right width=15%>이름</th>");
		out.println("<td width=85%><input type=text name=name size=15></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th align=right width=15%>제목</th>");
		out.println("<td width=85%><input type=text name=subject size=45></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th align=right width=15%>내용</th>");
		out.println("<td width=85%><textarea rows=10 cols=50 name=content></textarea></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th align=right width=15%>비밀번호</th>");
		out.println("<td width=85%><input type=password name=pwd size=10></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type=submit value=글쓰기>");
		out.println("<input type=button value=취소 onclick=\"javascript:history.back()\">");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
