<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 30px;
	width: 350px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 중복체크 버튼
	$('#cBtn').click(function(){
		let id = $('#id').val();
		if(id.trim()==""){
			$('#id').focus();
			return;
		}
		//전송 => 결과값 받기
		/*
			let res = httpRequest.responseText / responseXML
		*/
		$.ajax({
			type:'post',
			url:'../member/idcheck_ok.do',
			data:{"id":id},
			success:function(result){
				let count = parseInt(result.trim()); //정수형 변경
				if(count===0){
					$('#print').html("<span style='color:blue'>" + id + "는(은) 사용 가능합니다!</span>");
					$('#okTr').show();
					$('#id').attr("disable", true);// 사용가능하면 비활성화(안써도됨)
				} else {
					$('#print').html("<span style='color:red'>" + id + "는(은) 이미 사용중인 아이디입니다!<br>다시 입력하세요!</span>");
					$('#id').val("").focus();
					/* $('#id').focus(); */
				}
			}
		})
	})
	// 확인버튼
	$('#okBtn').on("click", function(){
		parent.join_frm.id.value=$('#id').val();// parent = 창 열어준 join.jsp (form태그 안에)
		parent.Shadowbox.close(); // 쉐도우박스 닫기
	})
})
</script>
</head>
<body>
	<div class="container">
	  <div class="row">
	    <table class="table">
	      <tr>
	        <td>
	          입력 : <input type="text" name="id" id="id" size="15" class="input-sm">
	          <input type="button" value="중복체크" class="btn btn-sm btn-primary" id="cBtn">
	        </td>
	      </tr>
	      <tr height="45">
	        <td class="text-center">
	          <span id="print"></span>
	        </td>
	      </tr>
	      <tr id="okTr" style="display: none">
	        <td class="text-center">
	          <input type="button" class="btn btn-sm btn-primary" value="확인" id="okBtn">
	        </td>
	      </tr>
	    </table>
	  </div>
	</div>
</body>
</html>