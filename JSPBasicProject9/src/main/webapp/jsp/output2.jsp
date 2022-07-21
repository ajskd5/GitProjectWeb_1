<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%-- output 에서 값 받은거랑 똑같음 --%>
<%--
	JSP 액션태그
	<jsp:~> => xml태그 	=> 지정된 태그만 사용 가능, 속성 지정된 내용만 사용 가능
						=> 대소문자 구분
						=> 속성값을 사용시 ""
						=> 여는 태그와 닫는 태그가 동일
							-------------------------- Empty 태그 사용 가능 <a/>
							
	*** 목적 : 최대한 자바코딩 제거
				<% %> <%! %> <%= %>  ===> 제거 ( Front, Back 분리) => MV구조
	<jsp:useBean> : 객체 메모리 할당
		<jsp:useBean id="mb" class="com.sist.dao.MemberBean">
			=> MemberBean mb = new MemberBean() 이거랑 같음
						  --- 객체명 
	<jsp:setProperty>
		<jsp:setProperty name="객체명" property="" value="">
						 -----		   ---------   -----
					   객체명 동일(id)	변수명		값
		ex> <jsp:setProperty name="mb" property="name' value="박문수">
				=> mb.setName("박문수")
			<jsp:setProperty name="mb" property="*"/> => 전체 값 저장
				=> 	String name = request.getParameter("name");
					String sex = request.getParameter("sex");
					String age = request.getParameter("age");
					String address = request.getParameter("address");
					String tel = request.getParameter("tel");
					
					mb.setName(name);
					mb.setSex(sex);
					mb.setAge(Integer.parseInt(age));
					mb.setAddress(address);
					mb.setTel(tel);
					
			<jsp:setProperty name="mb" property="name">
				=> mb.setName(request.getParameter("name"))
				
	<jsp:getProperty> // 잘 안씀
		=> <jsp:getProperty name="객체명" property="name">
				<%= mb.getName()%>
		
	<jsp:include> : JSP안에 특정부분에 다른 JSP 추가  ==> Spring에도 나옴 => 조립식
	------------ => pageContext.include()
	
	<jsp:forward> : 오버라이드 => 화면을 변경 (URL 변경 없음) => request보전
 --%>
<jsp:useBean id="mb" class="com.sist.dao.MemberBean">
	<jsp:setProperty name="mb" property="*"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>Insert title here</title>
</head>
<body>
	이름 : <%=mb.getName() %><br>
	이름 : <jsp:getProperty property="name" name="mb"/><br>
	성별 : <%=mb.getSex() %><br>
	나이 : <%=mb.getAge() %><br>
	주소 : <%=mb.getAddress() %><br>
	전화번호 : <%=mb.getTel() %><br>
</body>
</html>