<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1장
		웹 프로그램 이해 p.19
			= 요청 request	=> 사용자가 요청 (GET / POST)  => 데이터 전송
			= 응답 response	=> 서버에서 응답 => HTML / Cookie
				한개의 JSP에서는 HTML / Cookie 중 한개만 실행 가능
	3장
		동작 원리 ===> p.82
			= JSP 파일 요청 (URL => 파일을 요청 => 파일 찾기(404) => 실행(500) => HTML)
			= JSP 번역 (웹서버 : 요청 받기, 응답하기  /// WAS(톰캣 => 실행 => HTML추출)
			= 서블릿 코드 생성 (work)
			= 인터프리터 
			= 메모리에 저장된 HTML을 브라우저가 읽어 간다
	4장
		JSP 기초 문법 => 자바문법 (변수, 연산자, 제어문, 객체지향, 메소드, 예외처리)
	5장
		제어문 사용법 =>
			<%
				for(){
			%>
					HTML 태그
			<%
				}
			%>
	6장
		JSP 지시자 / 태그
			= page / taglib(18장) => JSTL
	7장
		내장 객체
			request : 요청 정보
				= getParameter() : 단일값 받기
				= getParameterValues()
				= setCharacterEncoding()
				= setAttribute()
				= getAttribute()
				= getRequestURI()
				= getContextPath()
				
			response : 응답 정보
				= setHeader()
				= sendRedirect() : 페이지 이동
			session : 서버에 저장 (클라이언트 일부 정보)
				= setAttribute()
				= getAttribute()
				= invalidate()
				= removeAttribute()
			application : 서버 정보
				= getRealPath()
		기타
			Cookie 
				= new Cookie(key, value)
				= getName() => key
				= getValue() => value
				= setMaxAge() => 기간 설정 (초단위)
				= setPath()
				= 전송 : response.addCookie()
				= 읽기 : request.getCookies()
				
	9장
		자바빈즈 : Bean (데이터 모아서 전송할 목적)
			변수 : private
				읽기 / 쓰기 (setXxx() => setter)
				--- => getXxx(), boolean => isXxx()
			= 객체 생성
				<jsp:useBean id="객체명" class="생성할 클래스이름(패키지부터)">
												com.sist.dao.LocationDAO
			= 값 설정
				<jsp:setProperty name="객체명" property="name"> setName()
														"*" => 객체가 가지고 있는 모든 setXxx() 호출
			= 값 읽기
				<jsp:getProperty name="객체명" property="변수명">
				= <%=객체명.getXxx() %>
	10장
		Connection Pool => Connection 제한, 재사용 가능 => MyBatis / JPA
			JDBC : 데이터베이스 연결, Connection / Statement / ResultSet
										연결	/	문장	 / 전송
				단점 : 연결할 때 Connection 객체가 생성되는데 바로 해제가 안됨 (메모리 누수) => 서버에 부하 발생
			Server => server.xml context사이에 설정
		
	11장
		데이터베이스 연동
	12장
		세션(서버) / 쿠키(클라이언트)
	13장 ~ 예제
	18장
		EL / JSTL => 스프링
		
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>