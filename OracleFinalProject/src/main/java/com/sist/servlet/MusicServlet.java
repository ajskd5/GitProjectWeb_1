package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
// => 수동 호출(X)
// => 브라우저 => URL주소 => /MusicServlet => 톰캣에 의해 호출
// => service를 호출 => 출력된 HTML을 브라우저로 전송 후 실행
@WebServlet("/MusicServlet")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// main
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request => 사용자가 보내준 데이터를 받는 역할 => getParameter
		// response => 응답 (HTML, Cookie) => 우리는 HTML 전송, 화면 변경
		// => JSP에서는 내장 객체
		
		// 1. 응답 타입 => text/html, text/xml, text/plain
		response.setContentType("text/html;charset=UTF-8");		// 브라우저 전송 => HTML (한글 포함)
		
		// 누구한테 전송
		PrintWriter out = response.getWriter(); //요청한 사용자에게 => HTML출력 위치 알려줌
		
		// 1. 사용자 요청 받기 => 장르, 페이지 => (request)
		String cno = request.getParameter("cno");
		String page = request.getParameter("page");
		// => 실행 페이지는 사용자가 선택할 수 없다 => Main page
		// => 웹에서는 파일이 아니라 페이지
		// 디폴트
		if(cno == null) {
			cno = "1"; // Top 100
		}
		if(page == null) {
			page = "1";
		}
		
		// 2. 요청 => 데이터베이스 값 받기 => 매개변수는 사용자가 요청한 값
		int curpage = Integer.parseInt(page); //현재 페이지 지정
		// 2-2. 현재 페이지에 대한 데이터 읽기 시작
		MusicDAO dao = new MusicDAO();
		List<MusicVO> list = dao.musicListData(Integer.parseInt(cno), curpage);
		int totalpage = dao.musicTotalPage(Integer.parseInt(cno));
		
		// 3. HTML 만들어서 브라우저로 전송 => out.println()
		String[] genre = {
				"",
				"Top 100",
				"가요",
				"POP",
				"OST",
				"트롯",
				"JAZZ",
				"CLASSIC"
		};
		out.println("<html>");
		out.println("<head>"); // style, script
		out.println("<link rel = stylesheet href=table.css>");
		out.println("</head>");
		out.println("<body>"); // 화면 출력
		out.println("<center>");
		/* 해당 파일에 데이터 전송
		 * URL => 파일명?변수=값
		 * 파일명?변수=값&변수=값
		 * MusicServlet?cno=1&page=2		=====> 중간에 공백이 있으면 에러 발생!!!!
		 * String cno = request.getParameter("cno");
		 * String page = request.getParameter("page");
		 */
		out.println("<a href =MusicServlet?cno=1>Top100</a>&nbsp;");
		out.println("<a href =MusicServlet?cno=2>가요</a>&nbsp;");
		out.println("<a href =MusicServlet?cno=3>POP</a>&nbsp;");
		out.println("<a href =MusicServlet?cno=4>OST</a>&nbsp;");
		out.println("<a href =MusicServlet?cno=5>트롯</a>&nbsp;");
		out.println("<a href =MusicServlet?cno=6>JAZZ</a>&nbsp;");
		out.println("<a href =MusicServlet?cno=7>CLASSIC</a>&nbsp;");
		// 요청 => service()호출 (HTML)변경
		// 재요청 => 이전화면은 메모리에서 해제 => 새로운 파일 생성
		// 				이 과정에서 destroy()호출
		// 	service() => destroy()  => WAS (톰캣) (Web Application Server)
		// 	WebServer(송수신), WAS(자바 번역 ...)
		out.println("<h1>"+ genre[Integer.parseInt(cno)]+"</h1>");
		/*	table	-	tr	-	th/td
		 *  			한줄	제목/데이터
		 */
		out.println("<table id=table_content width=960>");
		out.println("<tr>");
		out.println("<th>순위</th>");
		out.println("<th></th>");
		out.println("<th>곡명</th>");
		out.println("<th>가수명</th>");
		out.println("<th>앨범</th>");
		out.println("</tr>");
		for(MusicVO vo : list) {
			out.println("<tr class=dataTr>");
			out.println("<td>"+ vo.getMno() +"</td>");
			out.println("<td>");
			out.println("<img src=" + vo.getPoster() + "width=30 height=30>");
			out.println("</td>");
			out.println("<td>" + vo.getTitle() + "</td>");
			out.println("<td>" + vo.getSinger() + "</td>");
			out.println("<td>" + vo.getAlbum() + "</td>");
			out.println("</tr>");
		}
		out.println("<tr>");
		out.println("<td colspan=5 align=center>");
		out.println("<a href=MusicServlet?cno="+cno+"&page="+(curpage>1?curpage-1:curpage)+">이전</a>");
		out.println(curpage + " " + "page / " + totalpage + " pages");
		out.println("<a href=MusicServlet?cno="+cno+"&page="+(curpage<totalpage?curpage+1:curpage)+">다음</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
