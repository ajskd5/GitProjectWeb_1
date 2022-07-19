<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, java.net.*"%>
<%
	// 1. 요청값 받기
	try{
		request.setCharacterEncoding("UTF-8");
		String fn = request.getParameter("fn");
		File file = new File("c:\\download\\" + fn);
		
		// 다운로드 창 보여줌
			// 실제 다운로드 X						창 띄움		파일 이름
		response.setHeader("Content-Disposition", "attachement;filename=" + URLEncoder.encode(fn, "UTF-8"));
		
	// 실제 다운로드
		//서버에 존재하는 파일 읽기
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		// 클라이언트로 보내기
		//													클라이언트 정보 가지고 있음
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		int i = 0;
		byte[] buffer = new byte[1024];
		while((i=bis.read(buffer, 0, 1024)) != -1){
			bos.write(buffer, 0, i);
		}
		bis.close();
		bos.close();
		// 객체 초기화, 파일 연결
		out.clear();
		out = pageContext.pushBody();
	} catch(Exception e){
		
	}
	// 2.
%>