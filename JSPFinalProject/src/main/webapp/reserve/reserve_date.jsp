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
	$('.rtds').css("cursor", "pointer");
	// 달력에서 선택한 날 동작
	$('.rtds').click(function(){
		let year = $(this).attr("data-year");
		let month = $(this).attr("data-month");
		let day = $(this).text();
		let days = year + "년 " + month + "월 " + day + "일";
		$('#date_lab').show(); // reserve.jsp에서 hide해논거 보여줌
		$('#date_result').text(days);
		$('#re_rday').val(year + "-" + month + "-" + day);// reserve.jsp에서 value를 주지 않고 여기서 rday를 넣음
		// 시간 읽기
		$.ajax({
			type:'post',
			url:'../reserve/reserve_time.do',
			data:{"day":day},
			success:function(result){
				$('#r_time').html(result);
			}
		})
		
	})
})
</script>
</head>
<body>
	<table class="table">
	  <tr>
	    <td class="text-center">
	      ${year }년 ${month }월 ${day }일
	    </td>
	  </tr>
	</table>
	<table class="table">
        <tr class="success">
          <c:forEach  var="sw" items="${strWeek }" varStatus="s">
            <c:choose>
              <c:when test="${s.index==0 }">
                <c:set var="color" value="red"/>
              </c:when>
              <c:when test="${s.index==6 }">
                <c:set var="color" value="blue"/>
              </c:when>
              <c:otherwise>
                <c:set var="color" value="black"/>
              </c:otherwise>
            </c:choose>
            <th class="text-center"><h3 style="color: ${color}">${sw }</h3></th>
          </c:forEach>
        </tr>
        <c:forEach var="i" begin="1" end="${lastday }">
          <c:if test="${i==1 }">
            <tr style="height:70px">
              <c:forEach var="j" begin="1" end="${week }">
                <td>&nbsp;</td>
              </c:forEach>
              <%-- 요일만큼 공백 --%>
          </c:if>
            <c:choose>
              <c:when test="${days[i]==1 }"> <!-- 예약일에 해당하는 인덱스 값이 1임 -->
                <td class="text-center danger rtds" style="color: black;" data-year="${year }" data-month="${month }">${i }</td><%-- 1일부터 출력 --%>
              </c:when>
              <c:otherwise>
                <td class="text-center">${i }</td><%-- 1일부터 출력 --%>
              </c:otherwise>
            </c:choose>
            <c:set var="week" value="${week+1 }"/>
            <c:if test="${week>6 }"> <%-- 줄바꿈 (일요일이면 다음줄에 출력 <tr> --%>
              </tr>
              <c:set var="week" value="0"/>
              <tr style="height: 70px;">
            </c:if>
        </c:forEach>
        </tr>
      </table>
</body>
</html>