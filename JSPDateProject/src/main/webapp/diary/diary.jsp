<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	width: 600px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<!-- <script type="text/javascript">
$(function(){
	// img, button, span => click
	// hover (mouseover)
	// keyup/keydown
	// 바뀌었을때 <select> = onchange
	$('#year').change(function(){ 
		let year = $('#year').val();
		let month = $('#month').val();
		location.href="diary.do?year="+year+"&month="+month; // sendRedirect와 같은 역할 수행
	})
	$('#month').change(function(){ // 바뀌었을때
		let year = $('#year').val();
		let month = $('#month').val();
		location.href="diary.do?year="+year+"&month="+month; // sendRedirect와 같은 역할 수행
	})

})
</script> -->
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'diary_ok.do',
		success:function(result){
			$('#print').html(result);
		}
	})
	
	$('#year').change(function(){
		let year = $('#year').val();
		let month = $('#month').val();
		$.ajax({
			type:'post',
			url:'diary_ok.do',
			data:{"year":year, "month":month},
			success:function(result){
				$('#print').html(result);
			}
		})
	})
	$('#month').change(function(){
		let year = $('#year').val();
		let month = $('#month').val();
		$.ajax({
			type:'post',
			url:'diary_ok.do',
			data:{"year":year, "month":month},
			success:function(result){
				$('#print').html(result);
			}
		})
	})
})
</script>
</head>
<body>
  <div class="container">
    <h1 class="text-center">${year }년 ${month }월 ${day }일</h1>
    <div class="row">
      <table class="table">
        <tr>
          <td>
            <select name="year" class="input-sm" id="year">
              <c:forEach var="i" begin="2022" end="2030">
                <option ${year==i?"selected":"" }>${i }</option><!-- 선택창에 현재 년/월 기본으로 설정 -->
              </c:forEach>
            </select>년도&nbsp;
			<select name="month" class="input-sm" id="month">
              <c:forEach var="i" begin="1" end="12">
                <option ${month==i?"selected":"" }>${i }</option>
              </c:forEach>
            </select>월<!-- &nbsp;
            <input type="button" value="선택" id="dateBtn" class="btn btn-sm btn-primary"> -->
          </td>
        </tr>
      </table>
      <div style="height: 15px;"></div>
      <div class="row" id="print">
      
      </div>
    </div>
  </div>
</body>
</html>