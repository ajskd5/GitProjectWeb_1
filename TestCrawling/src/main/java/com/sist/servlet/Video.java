package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@WebServlet("/Video")
public class Video extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 변환 (전송방법) => HTML, XML, JSON
		response.setContentType("text/HTML; charset=UTF-8");
		// 2. 브라우저에서 읽어갈 위치의 메모리 설정
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		
		try {
			Document doc = Jsoup.connect("https://sll.seoul.go.kr/lms/requestCourse/doDetailInfo.do?course_id=ASP00001S100120220104151&class_no=01&course_gubun=1&asp_id=ASP00001&course_category_id=20210129754735&category_status=&page=1&mnid=202102376233").get();
			// 1번
			Element a1 = doc.select("ul.date_area strong:eq(0)").first();
			Element a2 = a1.nextElementSibling();
			System.out.println(a1.text());
			System.out.println(a2.text());
			
			Elements a = doc.select("ul.date_area span");
			String q = "";
			String w = "";
			String e = "";
			String r = "";
			String t = "";
			String y = "";
			for(int i=0; i<a.size(); i++) {
				q = a.get(1).text(); 
				w = a.get(2).text();
				e = a.get(3).text();
				r = a.get(4).text();
				t = a.get(5).text();
				y = a.get(6).text();
			}
			System.out.println(q);
			System.out.println(w);
			System.out.println(e);
			System.out.println(r);
			System.out.println(t);
			System.out.println(y);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
