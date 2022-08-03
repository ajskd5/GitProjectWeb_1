<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row3">
	  <div id="breadcrumb" class="clear"> 
	    <!-- ################################################################################################ -->
	    <ul>
	      <li><a href="#">Home</a></li>
	      <li><a href="#">커뮤니티</a></li>
	      <li><a href="#">상세보기</a></li>
	    </ul>
	    <!-- ################################################################################################ --> 
	  </div>
	</div>
	<!-- ################################################################################################ --> 
	<!-- ################################################################################################ --> 
	<!-- ################################################################################################ -->
	<div class="wrapper row3">
	  <main class="container clear"> 
	    <!-- main body --> 
	    <!-- ################################################################################################ -->
	    <h2 class="sectiontitle">내용보기</h2>
	    <div class="two_third first">
	      <table class="table">
	        <tr>
	          <th width="20%" class="text-center">번호</th>
	          <td width="30%" class="text-center">${vo.no }</td>
	          <th width="20%" class="text-center">작성일</th>
	          <td width="30%" class="text-center">${vo.dbday }</td>
	        </tr>
	        <tr>
	          <th width="20%" class="text-center">이름</th>
	          <td width="30%" class="text-center">${vo.name }</td>
	          <th width="20%" class="text-center">조회수</th>
	          <td width="30%" class="text-center">${vo.hit }</td>
	        </tr>
	        <tr>
	          <th width="20%" class="text-center">제목</th>
	          <td colspan="3">${vo.subject }</td>
	        </tr>
	        <tr>
	          <td colspan="4" height="200" valign="top">
	            <pre style="white-space: pre-wrap; background-color: white; border: none">${vo.content }</pre>
	          </td>
	        </tr>
	        <tr>
	          <td colspan="4" class="text-right">
	            <a href="#" class="btn btn-xs btn-danger">수정</a>
	            <a href="#" class="btn btn-xs btn-info">삭제</a>
	            <a href="../freeboard/list.do" class="btn btn-xs btn-warning">목록</a>
	          </td>
	        </tr>
	      </table>
	    </div>
	    <div class="one_third">2/3</div>
	  </main>
	</div>
</body>
</html>