<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<style type="text/css">
.container{
	margin-top: 50px;
	/* width: 960px; */
	width: 100%;
}
.table_content{
	margin: 0px auto;
	width: 450px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>회원 정보</h1>
		<form method="post" action="output2.jsp">
		<table class="table_content">
			<tr>
				<th width="30%">이름</th>
				<td width="70%"><input type="text" name="name" size="15"></td>
			</tr>
			<tr>
				<th width="30%">성별</th>
				<td width="70%">
					<input type="radio" name="sex" value="남자" checked="checked">남자
					<input type="radio" name="sex" value="여자">여자
				</td>
			</tr>
			<tr>
				<th width="30%">나이</th>
				<td width="70%">
					<input type="number" name="age" min=""20" max="50">
				</td>
			</tr>
			<tr>
				<th width="30%">주소</th>
				<td width="70%">
					<input type="text" name="address" size="25">
				</td>
			</tr>
			<tr>
				<th width="30%">전화번호</th>
				<td width="70%">
					<input type="text" name="tel" size="20">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button>전송</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>