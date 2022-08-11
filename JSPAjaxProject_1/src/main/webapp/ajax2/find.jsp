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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 1200px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#btn').click(function(){
		let fd = $('#fd').val();//fd에 입력한 값 읽어옴(id="fd")
		if(fd.trim()===""){
			$('#fd').focus();
			return;
		}
		$('#frm').submit();
	})
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="text-center">
        <form method="post" action="find.jsp" id="frm">
          Search : <input type="text" size="45" class="input-sm" id="fd" name="fd">
          <input type="button" value="검색" id="btn">
        </form>
      </div>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
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
    </div>
  </div>
</body>
</html>