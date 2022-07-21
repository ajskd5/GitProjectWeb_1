<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*, com.sist.vo.*" %>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
	List<CategoryBean> list = dao.categoryListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>믿고보는 맛집 리스트</h3>
	<hr>
	<div class="row">
		<%
			for(int i=0; i<12; i++){
				CategoryBean vo = list.get(i);
		%>
				<div class="col-md-3"> <%-- 한줄 총 길이 => 3칸씩이면 한줄에 4개 --%>
					<div class="thumbnail">
						<a href="#">
							<img src="<%=vo.getPoster()%>" alt ="lights" style="width:100%">
							<div class="caption">
								<p><%=vo.getTitle() %></p>
							</div>
						</a>
					</div>
				</div>
		<%
			}
		%>
	</div>
	
	<h3>지역별 맛집 리스트</h3>
	<hr>
	<div class="row">
		<%
			for(int i=12; i<18; i++){
				CategoryBean vo = list.get(i);
		%>
				<div class="col-md-4">
					<div class="thumbnail">
						<a href="#">
							<img src="<%=vo.getPoster()%>" alt ="lights" style="width:100%">
							<div class="caption">
								<p><%=vo.getTitle() %></p>
							</div>
						</a>
					</div>
				</div>
		<%
			}
		%>
	</div>
	<h3>메뉴별 맛집 리스트</h3>
	<hr>
	<div class="row">
		<%
			for(int i=18; i<30; i++){
				CategoryBean vo = list.get(i);
		%>
				<div class="col-md-3">
					<div class="thumbnail">
						<a href="#">
							<img src="<%=vo.getPoster()%>" alt = "lights" style="width:100%">
							<div class="caption">
								<p><%=vo.getTitle() %></p>
							</div>
						</a>
					</div>
				</div>
		<%
			}
		%>
	</div>
</body>
</html>