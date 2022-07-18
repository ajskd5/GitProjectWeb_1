<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
	p.19 :
		서버		=> 웹 서버, 톰캣(WAS : Web Application Server) : 자바번역
									-------- 테스트용 Web Server가 있음	(50명정도 접근 가능)
		클라이언트	=> 웹브라우저
		
		C/S (Client/Server)
			사용자 : 요청 => 주소란을 이용해서 요청
				요청 방식 => GET => 요청 데이터를 보낼 때 URL 뒤에 첨부
							POST => 내부 네트워크로 전송 (보안, 데이터가 많을 때)
			서버 : 응답 (응답에 대한 처리 => 웹 프로그래머)
				=> HTML, COOKIE, 이미지, JSON(Rest)
										 ==========> JavaScript(Ajax, Vue, React), kotlin
	p.22 : 
		웹에서 전송 (브라우저) => 일반 자바로는 요청을 받을 수 없음
			=> Servlet / JSP로 받아야 함
		Servlet : Java형태로 되어있음 중간에 HTML을 이용해서 처리
				=> 효율성이 떨어짐
				=> 보안이 좋음 (자바와 관련된 코딩은 Servlet을 이용)
					스프링에서 라이브러릴 제공 (Servlet으로 제공)
		JSP : HTML을 사용하기 편리, CSS, JavaScript 사용이 편리
				View(화면 출력용)
				=> 보안 취약 (JSP : 전체 소스 전송, Java : class파일만 전송)
				
	p.24 : JSP (Java Server Page) 서버에서 실행되는 자바 파일
			=> 파일X => 페이지
			동적 / 정적
			=> 동적 페이지를 생성하기 위한 서버측 스크립트 언어 (단순한 언어)
	p.72 : 동작
		1) 사용자가 요청 (파일 요청) => 브라우저 주소창(유일하게 서버와 연결되는 부분)
		2) 웹 서버, 웹 애플리케이션 서버(WAS)
			-----    ------------------------
			   |
			=> 요청 => 파일 찾기 => 응답
					   ---------
						=> .html, .xml, .json => 웹서버 자체에서 처리 가능
						=> .jsp, .php, .asp => 무조건 HTML로 변경 후 처리
				.jsp를 요청
				---------
					웹서버 => 톰캣으로 전송
							  --------------
							  	1) jsp 파일을 java 파일로 변경
							  	2) 변경된 java파일 => 컴파일 => javac
							  	3) 컴파일된 파일 실행 => java
							  	4) HTML만 메모리에 출력 (출력 버퍼)
							  	5) 요청한 브라우저에서 읽어 감
		3) JSP => 생명주기
			=> 생성 => 화면 출력 => 메모리 해제
			   init()	service()	destroy()=>화면이동시 해제, 새로고침
			   		=> init(), destroy()는 만들어져 있고
			   		=> service()는 만들어야 함 (웹프로그래머가 제작 => 화면(HTML)을 사용자에게 전송)
			=> 자바 기본 문법
				=> 변수, 데이터형, 연산자, 제어문, 배열, 메소드
				=> 객체지향 프로그램(클래스, 캡슐화, 상속, 포함, 다향성(오버라이딩))
									클래스 종류 (추상 클래스, 인터페이스)
				=> 예외처리, 라이브러리
					java.util, java.lang, java.io
						Date, StringTokenizer, List, Map, Set(제네릭스)
						String, Wrapper, Object
				=> java.sql (데이터베이스 연동)
	
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>