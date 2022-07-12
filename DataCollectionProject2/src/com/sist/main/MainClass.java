package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainClass {

	public static void main(String[] args) {
		String html = "<script id=sc>[{name:'hong1',sex:'man'},{name:'hong2',sex:'man'},{name:'hong3',sex:'man'}]</script>" 
				+ " <div class=\"row\">"
				+ "  <div class=\"col-md-4\">"
				+ "    <div class=\"thumbnail\">"
				+ "      <a href=\"/w3images/lights.jpg\">"
				+ "        <img src=\"/w3images/lights.jpg\" alt=\"Lights\" style=\"width:100%\">"
				+ "        <div id=\"caption\">"
				+ "          <p>Lorem ipsum...1</p>" // 이것만 가져오고 싶을 때 
													// select First() / select("div.capion p").get(0) 이렇게도 씀
				+ "        </div>"
				+ "      </a>"
				+ "    </div>"
				+ "  </div>"
				+ "  <div class=\"col-md-4\">"
				+ "    <div class=\"thumbnail\">"
				+ "      <a href=\"/w3images/nature.jpg\">"
				+ "        <img src=\"/w3images/nature.jpg\" alt=\"Nature\" style=\"width:100%\">"
				+ "        <div class=\"caption\">"
				+ "          <p>Lorem ipsum...2</p>"
				+ "        </div>"
				+ "      </a>"
				+ "    </div>"
				+ "  </div>"
				+ "  <div class=\"col-md-4\">"
				+ "    <div class=\"thumbnail\">"
				+ "      <a href=\"/w3images/fjords.jpg\">"
				+ "        <img src=\"/w3images/fjords.jpg\" alt=\"Fjords\" style=\"width:100%\">"
				+ "        <div class=\"caption\">"
				+ "          <p>Lorem ipsum...3</p>"
				+ "        </div>"
				+ "      </a>"
				+ "    </div>"
				+ "  </div>"
				+ "</div>"
				+ "<p>Hello</p>";
		/*
		 * text() => 태그 사이의 값 출력 => <p>Lorem ipsum...</p>
		 * attr() => 태그 속성의 값 출력 attr("href") => <a href=\"/w3images/fjords.jpg\">
		 * 		  	 attr(String s) => attr("href")
		 * html() => 태그에 속한 html을 긁어옴 =>  <div class=\"col-md-4\"> => 여기에 속하는 html 다 긁어옴
		 * data() => javascript값을 가져옴
		 * 
		 * Element / Elements
		 * 
		 * => CSS 선택자 이용 => id(태그명#id명), class(태그명.class명)
		 *  	selectFirst() 하면 여러 값 가져오는게 아니라 처음 값만 가져옴
		 *  	selectFirst("div.captionj p")
		 *  	select("div.capion p").get(0) 이렇게도 씀
		 */
		try {
			Document doc = Jsoup.parse(html); // 내부
			// connect() => 외부 (웹사이트 주소)
			Element p = doc.selectFirst("div#caption p");
			System.out.println(p.text());
			
			Elements ps = doc.select("div.caption p");
			System.out.println(ps); // 태그 전체 가져옴
			//text() 해야 값만 가져옴
			for(int i=0; i<ps.size(); i++) {
				System.out.println(ps.get(i).text());
			}
			
			Elements img = doc.select("div.thumbnail img");
			System.out.println(img);
			for(int i=0; i<img.size(); i++) {
				System.out.println(img.get(i).attr("src"));
				System.out.println(img.get(i).attr("alt"));
			}
			
			Elements img2 = doc.select("div.thumbnail");
			for(int i=0; i<img.size(); i++) {
				System.out.println(img2.get(i).html());
			}
			
			Element sc = doc.selectFirst("#sc");
			System.out.println(sc);
			System.out.println(sc.data());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
