<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i = 0;
$(function(){
	$('#del').click(function(){
		if(i===0){
			$('#delTr').show("slow"); // 효과
			$('#del').text("취소");
			i=1;
		} else {
			$('#delTr').hide();
			$('#del').text("삭제");
			i=0;
		}
	})
	
	//삭제
	/* 태그명($(태그명)), 아이디명 ($(#아이디명)), 클래스명($(.클래스명))
		인접 ($(태그명 + 태그명)), 후손($(태그명 태그명)), 자손($(태그명 > 태그명))
		
		이벤트 (태그명 | 아이디명)
			onclick
				$('태그명').click(function(){
					처리내용
				})
				$('태그명').on("click", function(){
					처리내용
				})
			onmouseover
			hover
				$('태그명').hover(function(){
					
				})
			onchange
				<select> => 예약 (달력)
				$('태그명').change(function(){
					
				})
			onkeydown,   onkeyup
			 --채팅--   --검색바--
			 
		<input value=> value => val()
		<td>Hello</td> ==> $('td').text()
		<a href="data"><img src="data"> 	=> $('a').attr("href")   $('img').attr("src")
		
		$('ul').html() ==> ul태그 안에 값 통째로 가져옴 (li태그 통째로)
	*/
	$('#delBtn').on("click", function(){
		let pwd = $('#delPwd').val(); // 입력한 비밀번호 가져오기 val() => 입력된 값 읽음
		let no = $(this).attr("data-no");
		if(pwd.trim()==""){
			$('#delPwd').focus();
			return;
		}
		// ajax => 요청 , 데이터 받기
		//alert("password : " + pwd + "\n번호 : "+ no); 값 받는지만 확인
		/*
			type => GET/POST
			url  => 처리할 URL주소 => .do
			data: => ?() 보내줄 데이터
			success:function(result){
				정상 수행 : 200  ====> text, xml, json 결과값을 어떤 형태로 보낼지 선택
									  (html)
				결과값 받아옴
			}
			error:function(ex){
				에러 : 404, 500, 412, 415, 403, ...
			}
				
		  ../freeboard/delete.do?no=1&pwd=1111 이렇게 넘어감
		
			HttpRequest reques; => 브라우저에 존재
			
			request.open("post", "url주소", true(비동기))
											false(동기)
		*/
		$.ajax({
			type: 'post',
			url: '../freeboard/delete.do',
			data: {"no":no, "pwd":pwd},
			success: function(result){
				let res = result.trim();
				if(res=="yes"){ // 정상수행 (비밀번호 일치 여부)
					location.href="../freeboard/list.do"; //sendRedirect()
				} else {
					alert("비밀번호가 틀립니다!!");
					$('#delPwd').val("");//비밀번호 입력란 지우기
					$('#delPwd').focus();
				}
				
			},
			fail:function(err){
				alert(err);
			}
			
		})
	})
	
})
</script>
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
	            <a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-danger">수정</a>
	            <span class="btn btn-xs btn-info" id="del">삭제</span>
	            <a href="../freeboard/list.do" class="btn btn-xs btn-warning">목록</a>
	          </td>
	        </tr>
	        <tr id="delTr" style="display: none">
	          <td colspan="4" class="text-right inline">
	            <span>비밀번호 : </span><input type="password" name="pwd" size="10" class="input-sm" id="delPwd">
	            <input type="button" value="삭제" class="btn btn-sm btn-danger" id="delBtn" data-no="${vo.no }">
	          </td>
	        </tr>
	      </table>
	    </div>
	    <div class="one_third">2/3</div>
	  </main>
	</div>
</body>
</html>