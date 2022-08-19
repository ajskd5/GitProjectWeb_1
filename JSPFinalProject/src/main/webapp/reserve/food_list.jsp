<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//if else 
	$('.food_info').hover(function(){
		$(this).css("backgroundColor", "#FC6").css("cursor", "pointer"); // css가 여러개면 뒤에 붙여서 처리
	}, function(){
		$(this).css("backgroundColor", "#FFF").css("cursor", "none");
	})
	
	// 클릭하면 화면 이동
	$('.food_info').click(function(){
		let poster = $(this).attr("data-poster");
		let fno = $(this).attr("data-no");
		let name = $(this).attr("data-name");
		$('#f_name_lab').show();
		$('#f_name').text(name);
		$('#rImg').attr("src", poster);
		$('#re_fno').val(fno); // reserve.jsp에서 value를 주지 않고 여기서 fno를 넣음
		// 달력
		$.ajax({
			type:'post',
			url:'../reserve/reserve_date.do',
			data:{"fno":fno},
			success:function(result){
				$('#r_date').html(result);
			}
		})
	})
})
</script>
</head>
<body>
<table class="table">
  <c:forEach var="vo" items="${list }">
    <tr class="food_info" data-poster="${vo.poster }" data-no="${vo.fno }" data-name="${vo.name }">
      <td>
        <img src="${vo.poster }" style="width: 30px; height: 30px;">
      </td>
      <td>${vo.name }</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>