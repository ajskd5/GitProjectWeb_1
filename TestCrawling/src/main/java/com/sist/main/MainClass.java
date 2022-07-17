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
					BookVO vo = new BookVO();
					System.out.println(a);
					System.out.println(title.get(i).attr("alt"));
					System.out.println("http://www.yes24.com" + link.get(i).attr("href"));
					vo.setTitle(title.get(i).attr("alt"));
					vo.setLink(link.get(i).attr("href"));
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
			List<BookVO> list = dao.bookLinkData();
			for(BookVO vo : list) {
				Document doc = Jsoup.connect("http://www.yes24.com"+vo.getLink()).get();
				Elements title = doc.select("div.gd_titArea");
				Elements image = doc.select("span.gd_img em.imgBdr img[src*=/goods/]");
				Elements author = doc.select("span.gd_pubArea");
				Element price = doc.selectFirst("div.gd_infoTb em.yes_m");
				Elements content = doc.select("div.infoWrap_txtInner");
				Elements index = doc.select("div#infoset_toc textarea.txtContentText");
				
				String content2 = content.html().toString();
				content2 = content2.substring(content2.indexOf("class=\"txtContentText\">"), content2.indexOf("</textarea>"));
				content2 = content2.replaceAll("&lt\\;", "<");
				content2 = content2.replaceAll("&gt\\;", ">");		
				content2 = "<div " + content2 + "</div>";
				
				String index2 = index.html().toString();
				index2 = index2.replaceAll("&lt\\;", "<");
				index2 = index2.replaceAll("&gt\\;", ">");
				
				System.out.println(title.text());
				System.out.println(image.attr("src"));
				System.out.println(author.text());
				System.out.println(price);
				System.out.println(content2);
				System.out.println(index2);
				System.out.println("===========================================");
				

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		MainClass m = new MainClass();
		m.bookDetail();

	}

}
