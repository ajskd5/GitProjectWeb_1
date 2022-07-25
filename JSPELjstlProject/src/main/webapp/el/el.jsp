<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	18장 (JSP)
	EL / JSTL => MVC, Spring, Spring-Boot
	--
	화면에 데이터 출력 <%= %> 대신 사용 ===> ${데이터}
	==== Java / HTML 분리 (MV)
	==== Java / HTML 분리/연결 (MVC)
	프로그램은 태그형 프로그램으로 변경 => 유지보수, CSS 작업이 쉬워짐
	EL (523page)
		=> JSP 페이지에서 자바코드를 최소화하기 위해 만든 표현식(출력식)
		=> EL (표현식) <%= %> out.println() => ${ } => 단점 (Javascript => Jquery => $)충돌 될 수 있음
		=> EL
			1) 연산자 (++, -- 존재하지 않음)
				이항연산자
					= 산술연산자 (+, -, *, /, %)
						+ : 순수하게 산술만 가능 (문자열 결합 X)
							${10+20} => ${"10" + "20"} => 30
							자바 에서 => "Java" + "Hello"=> JavaHello
							${"Java" + "Hello"} => 오류 발생
							${"Java" += "Hello"} => += 쓰면 문자열 결합
							${null + 10} => null을 0으로 취급 => 10
						/ : 0으로 나누면 에러 발생
							정수 / 정수 => 실수
							${10/3} => 3.3333... => ${10 div 3} 으로 써도됨
						% : ${10%3} => 1   => ${10 mod 3}
						
					= 비교연산자 (==, !=, <, >, <=, >=) => true/false
						== (eq)
						!= (ne)
						<  (lt)
						>  (gt)
						<= (le)
						>= (ge)
						==========> 문자열, 날짜 비교가 가능
							${id=='admin'}
							
					= 논리연산자 (&&, ||, !) (and, or로 써도됨)
						&& => 직렬 연산자 (두개의 조건이 true일 경우 => true) => and
						|| => 병렬 연산자 (두개의 조건중 1개 이상이 true => true) => or
						!  => 부정연산자 (not)
						
					= 삼항연산자
						(조건)?값1:값2 => 조건이 true면 값1
										조건이 false면 값2
					
					= [], () => 배열, List ==> []로 출력
			2) 내장 객체
			3) 객체 출력 => 메소드
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>산술연산자 (+, -, *, /(div), %(mod))</h3>
	&#36{10+20} = ${10+20 }<br>
	&#36{"10"+"20"}=${"10"+"20"}, <%="10"+"20" %><br>
	&#36{null+10} = ${null+10 }<br>
	<%-- &#36{"Java"+"Hello"} = ${"Java"+"Hello" }<br> 오류 발생 코드 NumberFormatException--%>
	&#36{"Java"+="Hello"} = ${"Java"+="Hello" }<br>
	&#36{10/3} = ${10/3}<br>
	&#36{10 div 3} = ${10 div 3 }<br>
	&#36{10 % 3} = ${10 % 3 }<br>
	&#36{10 mod 3} = ${10 mod 3 }<br>
	
	<h3>비교연산자 (==(eq), !=(ne), <(lt), >(gt), <=(le), >=(ge)) => true/false</h3>
	&#36{100 == 200} = ${100 == 200 }<br>
	&#36{100 eq 200} = ${100 eq 200 }<br>
	&#36{"admin" == "admin"} = ${"admin"== "admin" }<br>
	&#36{"admin" eq "admin"} = ${"admin" eq "admin" }<br>
	&#36{100 != 200} = ${100 != 200 }<br>
	&#36{100 ne 200} = ${100 ne 200 }<br>
	&#36{100 < 200} = ${100 < 200 }<br>
	&#36{100 lt 200} = ${100 lt 200 }<br>
	&#36{100 > 200} = ${100 > 200 }<br>
	&#36{100 gt 200} = ${100 gt 200 }<br>
	&#36{100 <= 200} = ${100 <= 200 }<br>
	&#36{100 le 200} = ${100 le 200 }<br>
	&#36{100 >= 200} = ${100 >= 200 }<br>
	&#36{100 ge 200} = ${100 ge 200 }<br>
	
	<h3>논리연산자 (&&(and), ||(or))</h3>
	&#36{(10 < 8) && (10 != 8)} = ${(10 < 8) && (10 != 8)}<br>
	&#36{(10 < 8) and (10 != 8)} = ${(10 < 8) and (10 != 8)}<br>
	&#36{(10 < 8) || (10 != 8)} = ${(10 < 8) || (10 != 8)}<br>
	&#36{(10 < 8) or (10 != 8)} = ${(10 < 8) or (10 != 8)}<br>
	&#36{!(10 < 8) || (10 != 8)} = ${!(10 < 8) || (10 != 8)}<br>
	&#36{not(10 < 8) or (10 != 8)} = ${not(10 < 8) or (10 != 8)}<br>
	
	<h3>삼항연산자 ((조건)?값1:값2)</h3>
	&#36{(10%2==0)?"짝수":"홀수"} = ${(10%2==0)?"짝수":"홀수"}
</body>
</html>












