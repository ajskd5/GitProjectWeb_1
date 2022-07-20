package com.sist.main;

import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MainClass {
	public void linkData() {
		try {
			BookDAO dao = new BookDAO();
			int a = 1;
			for(int k=1; k<=50; k++) {
				Document doc = Jsoup.connect("http://www.yes24.com/24/category/more/001001015?elemno=104&elemseq=1&pagenumber=" + k).get();
				Elements title = doc.select("span.imgBdr img");
				Elements link = doc.select("span.imgBdr a");
				for(int i=0; i<title.size(); i++) {
					BookLinkVO vo = new BookLinkVO();
					System.out.println(a);
					System.out.println(title.get(i).attr("alt"));
					System.out.println("http://www.yes24.com" + link.get(i).attr("href"));
					vo.setGl_title(title.get(i).attr("alt"));
					vo.setGl_link(link.get(i).attr("href"));
					System.out.println();
					a++;
					
					dao.linkInsert(vo);
				}
				
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	public void bookDetail() {
		try {
			BookDAO dao = new BookDAO();
//			List<BookVO> list2 = new ArrayList<BookVO>();
			List<BookLinkVO> list = dao.bookLinkData();
			for(BookLinkVO vo : list) {
				try {
					BookVO vo2 = new BookVO();
					
					Document doc = Jsoup.connect("http://www.yes24.com"+vo.getGl_link()).get();
					Elements title = doc.select("div.gd_titArea");
					Elements poster = doc.select("span.gd_img em.imgBdr img[src*=/goods/]");
					Elements writer = doc.select("span.gd_pubArea");
//					Element price = doc.selectFirst("div.gd_infoTb em.yes_m");
					Element info = doc.selectFirst("tbody.b_size");
					Elements intro = doc.select("div.infoWrap_txtInner");
					Elements index = doc.select("div#infoset_toc textarea.txtContentText");
					
					String intro2 = intro.html().toString();
//					if(intro2.indexOf("class=\"txtContentText\">")!=-1 || intro2.indexOf("</textarea>") != -1) {
//						intro2 = intro2.substring(intro2.indexOf("class=\"txtContentText\">"), intro2.indexOf("</textarea>"));
//						intro2 = intro2.replaceAll("&lt\\;", "<");
//						intro2 = intro2.replaceAll("&gt\\;", ">");		
//						intro2 = "<div " + intro2 + "</div>";
//					}
					intro2 = intro2.substring(intro2.indexOf("class=\"txtContentText\">"), intro2.indexOf("</textarea>"));
					intro2 = intro2.replaceAll("&lt\\;", "<");
					intro2 = intro2.replaceAll("&gt\\;", ">");		
					intro2 = "<div " + intro2 + "</div>";
					
					String index2 = index.html().toString();
					index2 = index2.replaceAll("&lt\\;", "<");
					index2 = index2.replaceAll("&gt\\;", ">");
					
					int price = (int)((Math.random()*9)+1)*100;
					
					System.out.println(title.text());
					System.out.println(poster.attr("src"));
					System.out.println(writer.text());
					System.out.println(price);
					System.out.println(intro2);
					System.out.println(index2);
					System.out.println("===========================================");
					
					vo2.setG_name(title.text());
					vo2.setG_writer(writer.text());
					vo2.setG_poster(poster.attr("src"));
					vo2.setG_price(price);
					vo2.setG_info("<table>" + info.html() + "</table>");
					vo2.setG_introduce(intro2);
					vo2.setG_index(index2);
					
					dao.BookInsert(vo2);
				} catch (Exception e) {
					System.out.println("try~catch 안에서 오류");
					e.printStackTrace();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public static void main(String[] args) {
		MainClass m = new MainClass();
//		m.linkData();
		m.bookDetail();

	}

}
