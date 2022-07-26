<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSLT / EL
	EL
		${}
		${일반 변수가 아님}
		${request.getAttribute()}
		${session.getAttribute()}
		${application.getAttribute()}
		${request.getParameter()}
		${request.getParameterValues()}
		=> request, session, application => JSP 내장 객체
			=> 기존의 데이터 + 추가 데이터 (setAttribute) 첨부 가능
		=============================================================
		MVC(MV) => 재사용, 확장기능 (자바, HTML분리)
		JSP 단점 => 1회용, 보안취약(JSP는 파일 전체 전송)
		
		EL 연산자
			산술연산자 : +, -, *, /(div), %(mod)
					정수 / 정수 = 실수
					"10" + "20" = 30
					null + 1 => 1
					문자열 결합 => += 사용
					
			논리연산자 : &&(and), ||(or)
			
			비교연산자 : ==, !=, <, >, <=, >=
						모든 데이터 비교 가능
			
	JSLT : 제어문, 변수선언, 화면 이동, 날짜/숫자 변환, String메소드
			------------------------------------------------------- 태그형으로 제작
		
		HTML/CSS JavaScript Java SQL 분리
		JSP는 자바 코딩 없이 태그형 프로그램만 제어
			XML로 제작
			----------- 대소문자 구분, 여는 태그와 닫는 태그 동일
						MyBatis / JPA / Spring / Spring-Boot
						VueJs, ReactJS => Jsx
						JSON / Annotation
						RestFul
		1. <c:set>
					<c:set var="" value=""> : 변수 설정 => MVC에서 거의 사용 안함
							변수명	값  => ${var변수명}
							
		2. <c:out> : 화면 출력 (JavaScript) => Jquery => $
					$('태그, id,class').click()		=> JavaScript연동에 많이 씀
					<c:out value="">
							출력할 내용
							
	***	3. <c:forEach> : for문
				일반 for문
								int i =  1    i<10     i++
					<c:forEach var="" begin="" end="" step="">		step="1"이면 생략 가능
							  루프변수	초기값  조건  증가값	step은 증가만 가능 감소 불가
				forEach 문
					<c:forEach var="" items="" varStatus="">
							var : 받는변수
							items : 배열/컬렉션일 경우 사용
							varStatus : 인덱스번호
						for(String name : list)
						var = name
						item = list
						varStatus = "s"
						
	***	4. <c:if> : if
				<c:if test="조건문">
						=> else가 없음
						
		5. <c:choose> : 다중 조건문
				<c:choose>
					<c:when test="조건문">출력</c:when>			if
					<c:when test="조건문">출력</c:when>			else if
					<c:when test="조건문">출력</c:when>			else if
					<c:otherwise> else 문장 </c:otherwise>		else
				</c:choose>
				
	***	6. <c:redirect> : 화면 이동 => sendRedirect("url")
					<c:redirect url="">
					
		7. <fmt:formatDate> : simpleDateFormat
				==> 화면 출력 ${}를 사용하지 않아도 바로 출력됨
					<fmt:formatDate value="${today}" pattern="yyyy-MM-dd">
										날짜					형식
			오라클에서 하는게 더 편함
				TO_CHAR(regdate, 'YYYY-MM-DD')
				
		8. <fmt:formatNumber> : NumberFormat
				==> <fmt:formatNumber value="10000" pattern="99,999">
					=> , 위치 정하기
			오라클에서 하는게 더 편함
				TO_CHAR(won, 'L000,000')
		
	
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