package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.vo.*;


@WebServlet("/BoardUpdate")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 변환 (전송방법) => HTML, XML, JSON
		response.setContentType("text/HTML; charset=UTF-8");
		// 2. 브라우저에서 읽어갈 위치의 메모리 설정
		PrintWriter out = response.getWriter();
		String no = request.getParameter("no");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.boardDetail(Integer.parseInt(no), 2);
		
		out.println("<html>");
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
		out.println("<h1>수정하기</h1>");
		
		out.println("<form method=post action=BoardUpdate>");
		out.println("<table width=700 class=table_content>");
		out.println("<tr>");
		out.println("<th align=right width=15%>이름</th>");
		out.println("<td width=85%><input type=text name=name size=15 value="+vo.getName()+">");
		out.println("<input type=hidden name=no value="+no+">");
		out.println("</td>");
		out.println("</tr>");
		/*
		 * <input type=text name=subject size=45 value=Hello Java>
		 * 	=> Hello
		 * 공백이 있으면 공백전까지만 출력
		 * 
		 * <input type=text name=subject size=45 value="Hello Java">
		 *  => Hello Java
		 * 공백도 출력 
		 */
		out.println("<tr>");
		out.println("<th align=right width=15%>제목</th>");
		out.println("<td width=85%><input type=text name=subject size=45 value=\""+vo.getSubject()+"\"></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th align=right width=15%>내용</th>");
		out.println("<td width=85%><textarea rows=10 cols=50 name=content>"+vo.getContent()+"</textarea></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th align=right width=15%>비밀번호</th>");
		out.println("<td width=85%><input type=password name=pwd size=10></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type=submit value=수정>");
		out.println("<input type=button value=취소 onclick=\"javascript:history.back()\">");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	//submit 누르면 값 넘겨받음
	/*
	 *  Servlet(C) => Java(M) + HTML(V)
	 *  JSP => 화면출력 (View)	MVC
	 *  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post => 요청 처리
		// JSP/ Servlet / Spring / Spring-Boot => 한글 변환
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String name = request.getParameter("name"); // 유효성 검사(JQuery)
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String no = request.getParameter("no");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		
		// Oracle전송
		BoardDAO dao = new BoardDAO();
		dao.boardUpdate(vo);
		
		// 화면이동 => BoardDetail
		response.sendRedirect("BoardDetail?no="+no);
	}

}
