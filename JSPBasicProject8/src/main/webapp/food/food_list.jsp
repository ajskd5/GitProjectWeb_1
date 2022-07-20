<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*" %>
<% 
	// 1. 데이터 받기 => 검색어, 페이지
	request.setCharacterEncoding("UTF-8");
	String fd = request.getParameter("fd");
	String strPage = request.getParameter("page");
	if(fd == null){
		fd = "역삼";
	}
	if(strPage == null){
		strPage = "1";
	}
	
	//  현재 페이지
	int curpage = Integer.parseInt(strPage);
	// 결과값 읽기
	FoodDAO dao = new FoodDAO();
	List<FoodVO> list = dao.foodListData(fd, curpage);
	for(FoodVO vo : list){
		String poster = vo.getPoster();
		poster = poster.substring(0, poster.indexOf("^"));
		vo.setPoster(poster);
		
		String address = vo.getAddress();
		String addr1 = address.substring(address.indexOf(" "));
		String addr2 = addr1.trim().substring(0, addr1.trim().indexOf(" "));
		vo.setAddress(addr2);
	}
	int totalpage = dao.foodTotalPage(fd);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
	margin-top: 50px;
	width: 100%;
}
.row{
	margin: 0px auto;
	width: 800px;
}
.find{
	text-align: center;
}
.food_list{
	width: 800px;
	margin: 0px auto;
}
.food{
	width: 250px;
	text-align: center;
	display: block;
	float: left;
}
.food_name{
	margin-top: 5px;
	margin-bottom: 4px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="find">
			<form method="post" action="food_list.jsp">
				Search : <input type="text" size="30" name=fd>
				<input type="submit" value="검색">
			</form>
			</div>
		</div>
		<div style="height: 30px"></div> <!-- 위 아래 간격 -->
		<div class="row">
			<div class="food_list">
			<%
				for(FoodVO vo : list){
			%>
			<%-- 
				response => 전송
						1) HTML
						2) Cookie
						=> 한개의 JSP에서는 두개를 동시에 전송 불가능
			 --%>
				<a class="food" href="detail_before.jsp?no=<%=vo.getFno()%>">
					<img src="<%=vo.getPoster() %>" style="width: 230px; height: 150px">
					<div class="food_name"><%=vo.getName() %>&nbsp;<span style="color:orange"><%=vo.getScore() %> </div>
					<div class="food_name"><%=vo.getType() %>-<%=vo.getAddress() %></div>
				</a>
			<%
				}
			%>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
		
	<div style="height: 30px"></div>
	<div style="text-align: center">
		<div style="text-align: center">
			<%
				for(int i=1; i<totalpage; i++){
			%>
				[<a href="food_list.jsp?page=<%=i%>&fd=<%=fd%>"><%=i%></a>]
			<%
				}
			%>
		</div>
	</div>
	<h1>최신 본 맛집 (쿠키)</h1>
	<hr>
	<%--
		php, asp, nodeJs, ... 모두 request, response, session 가지고 있음
		request, response => C (요청) / S (응답)
		session
		application, out
		-------------------------- 내장 객체
		일반객체
		cookie => 자동 로그인, 최신 방문  (로그인 => id, pwd 노출 => 보안 위험) => 쓰려면 암호화
			자신 컴퓨터에 저장 (로컬)
			
			1. 쿠키 사용법		=> 단점 => 저장되는 데이터가 문자열 		session은 Object로 저장
				= 쿠키 생성
					Cookie cookie = new Cookie(key, value)
				= 저장된 위치 (path)
					setPath("/"
				= 기간 설정
					setMaxAge(초단위) => 60*60*24 : 하루
				= 클라이언트로 전송
					addCookie
			
	 --%>
	<div>
		
	</div>
</body>
</html>








