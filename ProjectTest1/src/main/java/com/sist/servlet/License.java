package com.sist.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.*;
import com.sist.vo.*;


@WebServlet("/License")
public class License extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 변환 (전송방법) => HTML, XML, JSON
		response.setContentType("text/HTML; charset=UTF-8");
		// 2. 브라우저에서 읽어갈 위치의 메모리 설정
		PrintWriter out = response.getWriter();
		// 3. 데이터 읽기
		LicenseDAO dao = new LicenseDAO();
		List<LicenseLinkVO> list = dao.licenseListData();
		List<LicenseVO> llist = dao.licenseData(1);
		out.println("<html>");
		out.println("<body>");
		
		try {
			for(LicenseLinkVO vo : list) {
//				Document doc = Jsoup.connect("https://www.studyon.co.kr"+vo.getLl_link()).get();
//				Elements info = doc.select("div.bg");
//				Elements schedule = doc.select("div.widcontent strong");
//				String schedule2 = schedule.text().toString();
//				schedule2 = schedule2.substring(1, schedule2.length()-1); // 시험일정 앞뒤 <>자르기
//
//				Elements img = doc.select("div.widcontent img");
//				Elements detail = doc.select("div.widcontent");
//				String content = detail.html().toString();
//				String info2 = content;
//				out.println(vo.getLl_name()+"<br>");
//				out.println(info.text()+"<br>");
//				info2 = info2.substring(0, info2.indexOf("<strong>"));
//				out.println(info2+"<br>");
//				out.println(schedule2+"<br>");
//				out.println("<img src=\"https://www.studyon.co.kr" + img.attr("src")+"\">"+"</a><br>");
//				out.println("상세설명"+"<br>");
//				if(content.indexOf("<p><br> 1.")==-1) {
//					if(content.indexOf("<table")==-1) {
//						content = content.substring(content.indexOf("1."), content.indexOf("<div id=\"footer\""));
//					} else {
//						content = content.substring(content.indexOf("1."), content.indexOf("<table"));
//					}
//				} else {
//					if(content.indexOf("<table")==-1) {
//						content = content.substring(content.indexOf("<p><br> 1."), content.indexOf("<div id=\"footer\""));
//					} else {
//						content = content.substring(content.indexOf("<p><br> 1."), content.indexOf("<table"));
//					}
//				}
//
//				out.println(content+"<br>");

			}
			for(LicenseVO vo : llist) {
				out.println(vo.getL_no() + "번");
				out.println(vo.getL_cno());
				out.println(vo.getL_name());
				out.println(vo.getL_info());
				out.println(vo.getL_info2());
				out.println("<img href=\"" + vo.getL_img() +"\">");
				out.println(vo.getL_content());
				out.println(vo.getL_cno());
				out.println(vo.getL_pno());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		out.println("</body>");
		out.println("</html>");
		
	}

}
