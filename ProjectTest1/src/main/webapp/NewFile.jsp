<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*,com.sist.vo.*, org.jsoup.Jsoup, org.jsoup.nodes.Document, org.jsoup.select.Elements;"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%

	try {
		LicenseDAO dao = new LicenseDAO();
		List<LicenseLinkVO> list = dao.licenseListData();
		for(LicenseLinkVO vo : list) {
		//Document doc = Jsoup.connect("https://www.studyon.co.kr"+vo.getLink()).get();
		Document doc = Jsoup.connect("https://www.studyon.co.kr/d0005").get();
		Elements info = doc.select("div.bg");
		Elements schedule = doc.select("div.widcontent strong");
		String schedule2 = schedule.text().toString();
		schedule2 = schedule2.substring(1, schedule2.length()-1); // 시험일정 앞뒤 <>자르기

		Elements img = doc.select("div.widcontent img");
		Elements detail = doc.select("div.widcontent");
		String content = detail.html().toString();
		String info2 = content;
		info2 = info2.substring(0, info2.indexOf("<strong>"));
		
		out.println(info.text());
		out.println(info2);
		out.println(schedule2);
		out.println("https://www.studyon.co.kr/" + img.attr("src"));
		out.println("상세설명");

		if(content.indexOf("<p><br> 1.")==-1) {
			content = content.substring(content.indexOf("<p>1."), content.indexOf("<table"));
		} else {
			content = content.substring(content.indexOf("<p><br> 1."), content.indexOf("<table"));
		}
		out.println(content);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	%>
</body>
</html>