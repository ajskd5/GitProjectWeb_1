<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		Session : 저장 위치 => 서버
					저장되는 내용 => 프로그램 종료시 (logout, 브라우저 종료시까지 저장해야 하는 데이터)
									id, name, ... (장바구니 => 임시 저장 -> 결제시 테이블 저장)
			=> session : 프로젝트에 있는 모든 JSP에서 데이터 공유가 가능
			=> HttpSession 클래스의 객체
				HttpSession session = request.getSession()
									  ------- session, cookie 둘다 생성 가능
			=> 주요기능
				1) 저장 : setAttribute(key, 값) 	=> cookie는 문자열만 저장
											-- => Object 단위로 저장
				2) 저장 값 읽기 : Object getAttribute(key)
								단점 : 리턴형 = Object = 형변환 해야함
				3) 삭제 (일부) : removeAttribute(key) (하나씩 삭제)
				4) 전체 삭제 : invalidate() => 로그아웃
				5) 기간 설정 : setMaxactiveInterval(1800) => 초단위(default = 30분) => 변경 가능
				
			=> getAttribute() =>  null 	=> 로그인 안된 상태
							실제 데이터	=> 로그인 된 상태
	*/
	
	String id = (String)session.getAttribute("id");
	String log_jsp="";
	if(id == null){
		log_jsp = "../member/login.jsp";
	} else{
		log_jsp = "../member/logout.jsp";
	}
	
	String[] jsp_list = {
		"",
		"../food/category.jsp",
		"../food/food_list.jsp",
		"../food/food_detail.jsp"
	};
	
	// 사용자가 요청 => 화면
	String mode = request.getParameter("mode");
	if(mode == null){
		mode = "1";
	}
	int index = Integer.parseInt(mode);
	String jsp = jsp_list[index];
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 20px;
	width: 1200px;
}
/* .col-sm-2{
	height: 800px;
	border: 1px solid red;
}
.col-sm-10{
	height: 800px;
	border: 1px solid green;
} */
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="col-sm-3">
			<jsp:include page="<%=log_jsp %>"></jsp:include>
		</div>
		<div class="col-sm-9">
			<jsp:include page="<%=jsp %>"></jsp:include>
		</div>
	</div>
</body>
</html>