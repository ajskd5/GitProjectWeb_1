<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String mno = request.getParameter("mno");
	MovieVO vo = MovieDAO.movieDetailData(Integer.parseInt(mno));
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 450px;
}
</style>
</head>
<body>
  <div class="row row1">
    <table class="table">
      <tr>
        <td>
          <embed src="http://youtube.com/embed/${vo.getKey() }" style="width: 450px; height: 350px;">
        </td>
      </tr>
    </table>
  </div>
  <div style="height: 10px"></div>
  <div class="row row1">
    <table class="table">
      <tr>
        <td width="30%" class="text-center" valign="top" rowspan="9">
          <img src="${vo.getPoster() }" style="width: 300px; height: 400px;">
        </td>
        <td colspan="2">
          <h3>${vo.getTitle() }<span style="color: orange;">${vo.getScore() }</span></h3>
        </td>
      </tr>
      <tr>
        <td width="15%">감독</td>
        <td width="55%">${vo.getDirector() }</td>
      </tr>
      <tr>
        <td width="15%">출연</td>
        <td width="55%">${vo.getActor() }</td>
      </tr>
      <tr>
        <td width="15%">장르</td>
        <td width="55%">${vo.getGenre() }</td>
      </tr>
      <tr>
        <td width="15%">등급</td>
        <td width="55%">${vo.getGrade() }</td>
      </tr>
      <tr>
        <td width="15%">개봉일</td>
        <td width="55%">${vo.getRegdate() }</td>
      </tr>
      <tr>
        <td width="15%">관람객</td>
        <td width="55%">${vo.getShowuser() }</td>
      </tr>
      <tr>
        <td width="15%">상영시간</td>
        <td width="55%">${vo.getTime() }</td>
      </tr>
      <tr>
        <td width="15%">예매일</td>
        <td width="55%">${vo.getReserve() }</td>
      </tr>
    </table>
  </div>
</body>
</html>