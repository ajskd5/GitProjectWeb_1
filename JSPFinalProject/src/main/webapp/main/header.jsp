<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">맛집 & 레시피 & 서울여행</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <li><i class="fa fa-phone"></i> +00 (123) 456 7890</li>
        <li><i class="fa fa-envelope-o"></i> info@domain.com</li>
      </ul>
    </div>
    <!-- ################################################################################################ --> 
  </header>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원가입</a>
        <ul>
          <li><a href="pages/sidebar-left.html">회원가입</a></li>
          <li><a href="pages/gallery.html">아이디 찾기</a></li>
          <li><a href="pages/full-width.html">비밀번호 찾기</a></li>
        </ul>
      </li>
     <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="pages/gallery.html">지역별 맛집</a></li>
          <li><a href="pages/full-width.html">맛집 추천</a></li>
          <li><a href="pages/sidebar-left.html">맛집 예약</a></li>
        </ul>
      </li>
      <li><a class="drop" href="../recipe/recipe_list.do">레시피</a>
        <ul>
          <li><a href="../recipe/recipe_list.do">레시피 목록</a></li>
          <li><a href="../recipe/chef_list.do">쉐프</a></li>
          <li><a href="../recipe/list.do">가격 비교</a></li>
          <li><a href="../recipe/list.do">레시피 만들기</a></li>
        </ul>
      </li>
      <li><a class="drop" href="../seoul/location.do">서울여행</a>
        <ul>
          <li><a href="../seoul/location.do">명소</a></li>
          <%--
          	DispatcherServlet : 요청 (URL주소)
          	  |
          	Model 	(~VO, ~DAO, ~Model)
          		FoodModel
          		SeoulModel  --- View
           --%>
          <li><a href="../seoul/nature.do">자연 & 관광</a></li>
          <li><a href="../seoul/shop.do">쇼핑</a></li>
          <li><a href="../seoul/hotel.do">호텔</a></li>
          <li><a href="../seoul/guest.do">게스트 하우스</a></li>
          <li><a href="pages/full-width.html">코스 추천</a></li>
        </ul>
      </li>
      <li><a href="#">레시피 스토어</a></li>
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="pages/gallery.html">자유게시판</a></li>
          <li><a href="pages/full-width.html">묻고 답하기</a></li>
          <li><a href="pages/sidebar-left.html">공지사항</a></li>
        </ul>
      </li>
      <li><a href="#">마이페이지</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </nav>
</div>
</body>
</html>