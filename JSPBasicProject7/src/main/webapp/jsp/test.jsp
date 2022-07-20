<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	applicationo 객체
		=> 클래스명 (ServletContext)
		=> 서버 정보 (서버명, 버전명, 로그파일, 실제경로)
												--------
		1. web.xml 	=> 등록된 데이터 읽기 (getInitParameter())
		2. 로그 파일에 message 기록 => log()
		3. 실제경로 : getRealPath() => URL을 로컬 파일 시스템으로 변경하여 반환
		
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>application : ServletContext => 접속 횟수(프로젝트에 있는 모든 JSP에서 접근 가능)</h1>
</body>
</html>