<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	내장 객체 (p.171)
		JSP(라이브러리) => Spring / Spring-Boot
			이미 객체를 생성해서 사용이 가능하게 만든 클래스 (내장 객체 / 내부 객체 / 기본 객체)
			=> 9개 지원
			**	1) request
					클래스명 : HttpServletRequest
					기능)
						= 서버 정보 / 브라우저 정보
						 **	getRemoteAddr() : 사용자의 IP 읽음
							getServerInfo()
							getPort()
						 **	getRequestURL()
						 **	getRequestURI()
						 **	getContextPath()
						 						URL
						 ------------------------------------------------------------
						 http://localhost:8080/JSPBasicProject4/jsp/chapter7_p171.jsp
						 --------------------  --------------------------------------
						 	서버정보					클라이언트 요청 정보 (URI)
						 						--------------- ContextPath
						 						
						= 요청 데이터 정보
							getParameter() : 단일값 받음
								<input type=text>
										type=radio
										type=password
										type=file
								<textarea>
								<select>
								?no=10
							getParameterValues() : 다중값 받음 (체크박스)
								
							=> setCharacterEncoding() => 한글 변환
								브라우저로 전송 ==========> encoding(byte[])
								%EC%9E%90...
								브라우저에서 값 읽기
								%EC%9E%90... => 자바 (decoding)
								
						= 데이터 추가
							setAttribute() => MVC (Spring)
							
			**	2) response
					클래스명 : HttpServletResponse
					응답 정보 => HTML, COOKIE : JSP 한개에서 두가지를 동시 수행하지 못함
							 	-----
							 	  ->contentType="text/html"		=> page지시자에 설정
							 	addCookie() : 쿠키 전송
							 	setHeader() => 파일 다운로드
					화면 변경 정보	=> sendRedirect("이동 파일명")
					
			**	3) out
			**	4) application
				5) pageContext
				6) page
				7) config
				8) exception
			**	9) session
			---------------- 기타 (cookie)(일반 객체)
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