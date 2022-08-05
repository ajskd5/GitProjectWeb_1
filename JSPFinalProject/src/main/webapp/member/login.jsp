<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodePen - Wavy login form</title>
<link href="https://fonts.googleapis.com/css?family=Asap" rel="stylesheet"><link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="../style.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ // window.onload => mounted(Vue), componentDidMount(React)
	$('#logBtn').on("click", function(){
		let id = $('#id').val();
		if(id.trim()==""){
			$('#id').focus();
			return;
		}
		let pwd = $('#pwd').val();
		if(pwd.trim()==""){
			$('#pwd').focus();
			return;
		}
		// id, pwd 서버 전송
		$.ajax({
			type:'post',
			url:'../member/login_ok.do',
			data:{"id":id, "pwd":pwd},
			success:function(result){
				let res = result.trim();
				if(res==='NOID'){ // 아이디 없음
					alert("아이디가 존재하지 않습니다!!");
					$('#id').val("").focus();
					$('#pwd').val("");
				} else if(res==='NOPWD'){ // 비밀번호 틀림
					alert("비밀번호가 틀립니다!!");
					$('#pwd').val("").focus();
				} else { // 로그인 성공
					parent.location.href="../main/main.do";
				}
			}
		})
	})
})
</script>
</head>
<body>
<!-- partial:index.partial.html -->
<form class="login">
  <input type="text" placeholder="ID" id="id">
  <input type="password" placeholder="Password" id="pwd">
  <input type="button" value="로그인" id="logBtn" class="button">
</form>

</body>
</html>