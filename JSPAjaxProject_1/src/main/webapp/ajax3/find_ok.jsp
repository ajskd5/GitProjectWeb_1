<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String fd = request.getParameter("fd");
	if(fd == null){
		fd = "비"; // 자신 JSP에서 처리 => 디폴트
	}
	List<MovieVO> list = MovieDAO.movieFindData(fd);
	// HTML은 출력, 자바는 데이터 읽기
	request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  	<c:forEach var="vo" items="${list }">
		<div class="col-md-3">
	      <div class="thumbnail">
	        <a href="detail_before.do?mno=${vo.mno }">
	          <img src="${vo.poster }" alt="Lights" style="width:300px; height: 300px">
	          <div class="caption">
	            <p>${vo.title }</p>
	          </div>
	        </a>
	      </div>
	    </div>
	</c:forEach>
</body>
</html>