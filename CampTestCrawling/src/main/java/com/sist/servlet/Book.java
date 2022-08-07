package com.sist.servlet;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 변환 (전송방법) => HTML, XML, JSON
		response.setContentType("text/HTML; charset=UTF-8");
		// 2. 브라우저에서 읽어갈 위치의 메모리 설정
		PrintWriter out = response.getWriter();
		BookDAO dao = new BookDAO();
		List<BookLinkVO> list = dao.bookLinkData();
		out.println("<html>");
		out.println("<body>");
		
		try {
			for(BookLinkVO vo : list) {
				
			}
			Document doc = Jsoup.connect("http://www.yes24.com/Product/Goods/105505636").get();
			Elements title = doc.select("div.gd_titArea");
			Elements image = doc.select("span.gd_img em.imgBdr img[src*=/goods/]");
			Elements author = doc.select("span.gd_pubArea");
			Element price = doc.selectFirst("div.gd_infoTb em.yes_m");
			Elements content = doc.select("div.infoWrap_txtInner");
			Elements index = doc.select("div#infoset_toc textarea.txtContentText"); // html로?
			Element info = doc.selectFirst("tbody.b_size"); 
			
			String content2 = content.html().toString();
			content2 = content2.substring(content2.indexOf("class=\"txtContentText\">"), content2.indexOf("</textarea>"));
			content2 = content2.replaceAll("&lt\\;", "<");
			content2 = content2.replaceAll("&gt\\;", ">");		
			content2 = "<div " + content2 + "</div>";
			
			String index2 = index.html().toString();
//			index2 = index2.replaceAll("<br/>", "<br>");
			index2 = index2.replaceAll("&lt\\;", "<");
			index2 = index2.replaceAll("&gt\\;", ">");
			
			out.println(title.text() + "<br><hr>");
			out.println("<img src=\"" + image.attr("src") + "\"></img><br><hr>");
			out.println(author.text() + "<br><hr>");
			out.println(price.text() + "<br><hr>");
			out.println(content2 + "<br><hr>");
			out.println(index2 + "<br>");
			out.println("<table>" + info.html() + "</table>");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
