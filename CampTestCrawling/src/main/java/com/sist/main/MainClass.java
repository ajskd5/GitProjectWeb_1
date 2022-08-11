 package com.sist.main;

import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainClass {

	
	// 캠핑 링크 크롤링
	public void linkData() {
		try {
			CampDAO dao = new CampDAO();
			int a = 1;
			for(int k=1; k<=321; k++) {
				Document doc = Jsoup.connect("https://www.gocamping.or.kr/bsite/camp/info/list.do?pageUnit=10&searchKrwd=&listOrdrTrget=last_updusr_pnttm&pageIndex=" + k).get();
				Elements link = doc.select("div.c_list h2.camp_tt a");
				Elements addr = doc.select("li.addr");
				for(int i=0; i<link.size(); i++) {
					CampLinkVO vo = new CampLinkVO();
					System.out.println("link : " + link.get(i).attr("href"));
					System.out.println("title : " + link.get(i).text());
					System.out.println("addr : " + addr.get(i).text());
					vo.setLink(link.get(i).attr("href"));
					vo.setTitle(link.get(i).text());
					vo.setAddr(addr.get(i).text());
					System.out.println();
					dao.linkInsert(vo);
					System.out.println(a);
					a++;
				}
				
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	//캠핑 상세보기 크롤링
	public void campDetail() {
		try {
			CampDAO dao = new CampDAO();
			List<CampLinkVO> list = dao.linkList();
			CampVO vo2 = new CampVO();
			int z = 1;
			for(CampLinkVO vo : list) {
				try {
					Document doc = Jsoup.connect("https://www.gocamping.or.kr"+vo.getLink()).get();
//					Document doc = Jsoup.connect("https://www.gocamping.or.kr/bsite/camp/info/read.do?c_no=1776&viewType=read01&listOrdrTrget=c_rdcnt").get();
					Elements title = doc.select("p.camp_name");
					Elements poster = doc.select("div.img_b img");
					Elements inf = doc.select("div.cont_tb"); // 테이블 읽기  <div짜르기
					
					Elements imgs = doc.select("div.camp_intro li img"); // ^로 구분
					Element content = doc.selectFirst("p.camp_intro_txt span");
					Elements info2 = doc.select("div.camp_item_g"); // 시설정보
					Elements etc = doc.select("div.table_w");
					Elements gallery = doc.select("div#gallery img"); // ^로 구분
					
					String info = inf.html();
					info = info.substring(0, info.indexOf("<div"));
					String etcinfo = etc.html();

					String img = "";
					for(int j=0; j<imgs.size(); j++) {
						img = img + "https://gocamping.or.kr" +imgs.get(j).attr("src") + "^";
					}


					String gal = "";
					for(int i=0; i<gallery.size(); i++) {
						gal = gal + gallery.get(i).attr("src") + "^";
					}
					
					int hit = (int)(Math.random()*10)+1;
					
					String addr = vo.getAddr();
					System.out.println(title.text());
					System.out.println("https://gocamping.or.kr" + poster.attr("src"));
					System.out.println(info);
					System.out.println(img);
					System.out.println(content.text());
					System.out.println(info2.text());
					System.out.println(etcinfo);
					System.out.println(gal);
					
					System.out.println("===========================================");
					vo2.setTitle(title.text());
					vo2.setPoster("https://gocamping.or.kr" + poster.attr("src"));
					vo2.setInfo(info);
					vo2.setImgs(img);
					vo2.setContent(content.text());
					vo2.setInfo2(info2.text());
					vo2.setEtcinfo(etcinfo);
					vo2.setGallery(gal);
					vo2.setHit(hit);
					vo2.setAddr(addr);
					dao.campInsert(vo2);
					System.out.println(z);
					z++;
				} catch (Exception e) {
					System.out.println("try~catch 안에서 오류");
					e.printStackTrace();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public void asdf() {
		try {
			int count = 0;
			int i = 0;
			int a = 0;
			GoodsVO vo = new GoodsVO();
			CampDAO dao = new CampDAO();
			String category = "";
			for(i=1; i<=14; i++) {
				switch(i){
				case 1:
					category = "텐트/타프";
					count = 85;
					break;
				case 2:
					category = "침낭/매트";
					count = 45;
					break;
				case 3:
					category = "의자/테이블/가구";
					count = 46;
					break;
				case 4:
					category = "코펠/취사도구";
					count = 82;
					break;
				case 5:
					category = "버너/랜턴/난로";
					count = 57;
					break;
				case 6:
					category = "화로대/바베큐/연료";
					count = 21;
					break;
				case 7:
					category = "의류/모자/소품";
					count = 28;
					break;
				case 8:
					category = "등산화/운행장비";
					count = 25;
					break;
				case 9:
					category = "아웃도어푸드";
					count = 2;
					break;
				case 10:
					category = "배낭/가방/케이스";
					count = 61;
					break;
				case 11:
					category = "소품/악세사리";
					count = 87;
					break;
				case 12:
					category = "선글라스/고글";
					count = 5;
					break;
				case 13:
					category = "배터리/파워뱅크";
					count = 2;
					break;
				case 14:
					category = "에코파워백";
					count = 2;
					break;
				}
				System.out.println("카테고리번호 : " + i);
				try {
					for(a=1; a<=count; a++) {
						//https://www.campingon.co.kr/goods/goods_list.php?page=1&cateCd=093001
						Document doc = Jsoup.connect("https://www.campingon.co.kr/goods/goods_list.php?page="+a+"&cateCd=09300"+i).get();
						System.out.println(a + "페이지 / 총페이지 : " + count);
						
						Elements list = doc.select("div.item_photo_box a"); // 상품 링크
						try {
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						for(int j=0; j<list.size(); j++) {
							Document doc2 = Jsoup.connect("https://www.campingon.co.kr"+list.get(j).attr("href").substring(2)).get();
							System.out.println(j);
							Elements imgs = doc2.select("div.txt-manual img"); // src
							Elements title = doc2.select("div.item_detail_tit h3");
							Element poster = doc2.selectFirst("span.img_photo_big img"); // src 이미지 |로 구분
							System.out.println(category);
							System.out.println(title.text());
							String img = "";
							for(int k=0; k<imgs.size(); k++) {
//								System.out.println(poster.get(k).attr("src"));
								img = img + "https://www.campingon.co.kr" +imgs.get(k).attr("src")+"^";
							}
							System.out.println("poster : "+ "https://www.campingon.co.kr" + poster.attr("src"));
							System.out.println("img : " + img);
							int price = (int) (Math.random()*9 + 1)*100;
							System.out.println("가격 : "+ price);
							
							vo.setType(category);
							vo.setTitle(title.text());
							vo.setPoster("https://www.campingon.co.kr" + poster.attr("src"));
							vo.setPrice(price);
							vo.setImgs(img);
//							dao.goodsInsert(vo);
							System.out.println("===========================================");
							
						}
						System.out.println("상품 20개 끝");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("다음 카테고리");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		MainClass m = new MainClass();
//		m.linkData();
		m.campDetail();
//		m.asdf();
	}

}
