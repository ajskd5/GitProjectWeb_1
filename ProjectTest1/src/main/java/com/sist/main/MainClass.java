package com.sist.main;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class MainClass {
	// 각 링크, 이름
	public void linkData() {
		try {
			LicenseDAO dao = new LicenseDAO();
			Document doc = Jsoup.connect("https://www.studyon.co.kr/index.php?mid=home3").get();
			Elements name = doc.select("div.xe_content a[target=\"_parent\"]");
			Elements link = doc.select("div.xe_content a[target=\"_parent\"]");
//			Elements category = doc.select("tr td[colspan=\"4\"] strong");
//			for(int i=0; i<category.size(); i++) {
//				System.out.println(category.get(i).text());
//				LicenseCategoryVO vo = new LicenseCategoryVO();
//				vo.setL_cname(category.get(i).text());
//				dao.licenseCategory(vo);
//			}
			for(int i=0; i<name.size(); i++) {
				System.out.println(name.get(i).text().substring(1));
				System.out.println(link.get(i).attr("href"));
				System.out.println();
				LicenseLinkVO vo = new LicenseLinkVO();
				
				vo.setLl_name(name.get(i).text().substring(1));
				vo.setLl_link(link.get(i).attr("href"));
				
				dao.linkInsert(vo);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	// 카테고리 테이블 (번호, 카테고리)
	public void CategoryInsert() {
		try {
			LicenseDAO dao = new LicenseDAO();
			Document doc = Jsoup.connect("https://www.studyon.co.kr/index.php?mid=home3").get();
//			Elements name = doc.select("div.xe_content a[target=\"_parent\"]");
//			Elements link = doc.select("div.xe_content a[target=\"_parent\"]");
			Elements category = doc.select("tr td[colspan=\"4\"] strong");
			for(int i=0; i<category.size(); i++) {
				System.out.println(category.get(i).text().substring(2));
				LicenseCategoryVO vo = new LicenseCategoryVO();
				vo.setL_cname(category.get(i).text().substring(2));
				dao.licenseCategory(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void licenseDetail() {
		try {
			LicenseDAO dao = new LicenseDAO();
			List<LicenseLinkVO> list2 = dao.licenseListData();
			
			int i=1;
			for(LicenseLinkVO vo2 : list2) {
				try {
					LicenseVO vo = new LicenseVO();
					Document doc = Jsoup.connect("https://www.studyon.co.kr"+vo2.getLl_link()).get();
					Document doc2 = Jsoup.connect("https://www.studyon.co.kr/index.php?mid=home3").get();
					Elements info = doc.select("div.bg");
					Elements schedule = doc.select("div.widcontent strong");
					String schedule2 = schedule.text().toString();
					schedule2 = schedule2.substring(1, schedule2.length()-1); // 시험일정 앞뒤 <>자르기

					Elements img = doc.select("div.widcontent img");
					Elements detail = doc.select("div.widcontent");
					
					String content = detail.html().toString();
					String info2 = content;
					
					System.out.println(vo2.getLl_name());
					System.out.println(info.text());
					info2 = info2.substring(0, info2.indexOf("<strong>"));
					System.out.println(info2);
					System.out.println(schedule2);
					System.out.println("https://www.studyon.co.kr/" + img.attr("src"));
					System.out.println("상세설명");
//					if(content.indexOf("<p><br> 1.")==-1) {
//						content = content.substring(content.indexOf("<p>1."), content.indexOf("<table"));
//					} else {
//						content = content.substring(content.indexOf("<p><br> 1."), content.indexOf("<table"));
//					}
					System.out.println(vo2.getL_cno());
//					System.out.println(content);
					
					if(content.indexOf("<p><br> 1.")==-1) {
						if(content.indexOf("<table")==-1) {
							content = content.substring(content.indexOf("1."), content.indexOf("<div id=\"footer\""));
						} else {
							content = content.substring(content.indexOf("1."), content.indexOf("<table"));
						}
					} else {
						if(content.indexOf("<table")==-1) {
							content = content.substring(content.indexOf("<p><br> 1."), content.indexOf("<div id=\"footer\""));
						} else {
							content = content.substring(content.indexOf("<p><br> 1."), content.indexOf("<table"));
						}
					}

					String name = vo2.getLl_name();
					vo.setL_name(name);
					vo.setL_info(info.text());
					vo.setL_info2(info2);
					vo.setL_schedule(schedule2);
					vo.setL_img("https://www.studyon.co.kr/" + img.attr("src"));
					vo.setL_content(content);
					vo.setL_cno(vo2.getL_cno());
					List<PosterVO> list3 = dao.PosterData(i);
					for(PosterVO vo3 : list3) {
						i++;
						vo.setLp_no(vo3.getLp_no());
						System.out.println(vo3.getLp_no());
						break;
					}
					if(i>20) {
						i=1;
					}
					dao.licenseInsert(vo);
				} catch (Exception e) {
					System.out.println("반복문 안에서 try~catch 에러");
					e.printStackTrace();
				}


			}
		} catch (Exception e) {
			System.out.println("반복문 밖에서 try~catch 에러");
			e.printStackTrace();
		}
	}
	
	public void posterInsert() {
		LicenseDAO dao = new LicenseDAO();
		try {
			String[] img = new String[20];
			img[0] = "https://cdn-icons.flaticon.com/png/128/2110/premium/2110328.png?token=exp=1658054428~hmac=5b8b323c3149a3d76400a75061782237";
			img[1] = "https://cdn-icons-png.flaticon.com/128/1573/1573442.png";
			img[2] = "https://cdn-icons.flaticon.com/png/128/3160/premium/3160638.png?token=exp=1658054428~hmac=6a4844278dc013db2bca99e8ce7c7423";
			img[3] = "https://cdn-icons.flaticon.com/png/128/2110/premium/2110357.png?token=exp=1658054428~hmac=4140f28b41d04e196a874f0876d08ce2";
			img[4] = "https://cdn-icons-png.flaticon.com/128/5966/5966881.png";
			img[5] = "https://cdn-icons.flaticon.com/png/128/4529/premium/4529752.png?token=exp=1658054428~hmac=fe3e2b88655893f9becc28a6b4ace83b";
			img[6] = "https://cdn-icons-png.flaticon.com/128/6158/6158293.png";
			img[7] = "https://cdn-icons.flaticon.com/png/128/3447/premium/3447263.png?token=exp=1658054428~hmac=35f3c9489521bbd583b7e0674dfe689e";
			img[8] = "https://cdn-icons.flaticon.com/png/128/3769/premium/3769461.png?token=exp=1658054428~hmac=9ca7a72a1e01664119c620627aaf28a5";
			img[9] = "https://cdn-icons-png.flaticon.com/128/2867/2867758.png";
			img[10] = "https://cdn-icons-png.flaticon.com/128/5983/5983508.png";
			img[11] = "https://cdn-icons-png.flaticon.com/128/1470/1470294.png";
			img[12] = "https://cdn-icons-png.flaticon.com/128/7793/7793678.png";
			img[13] = "https://cdn-icons-png.flaticon.com/128/3814/3814280.png";
			img[14] = "https://cdn-icons.flaticon.com/png/128/4216/premium/4216724.png?token=exp=1658054428~hmac=79411d9755bce0186580f5852fd27ab2";
			img[15] = "https://cdn-icons-png.flaticon.com/128/1049/1049592.png";
			img[16] = "https://cdn-icons-png.flaticon.com/128/6041/6041027.png";
			img[17] = "https://cdn-icons-png.flaticon.com/128/3088/3088283.png";
			img[18] = "https://cdn-icons.flaticon.com/png/128/2045/premium/2045215.png?token=exp=1658054428~hmac=e77fb591e5b310020f39dd75859f1b07";
			img[19] = "https://cdn-icons-png.flaticon.com/128/8017/8017842.png";
			for(int i=0; i<img.length; i++) {
				PosterVO vo = new PosterVO();
				vo.setLp_poster(img[i]);
				dao.posterInsert(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MainClass m = new MainClass();
//		m.linkData();
		m.licenseDetail();
//		m.CategoryInsert();
//		m.posterInsert();
	}

}
