<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, com.oreilly.servlet.*, com.sist.dao.*"%>
<%@ page import = "com.oreilly.servlet.multipart.*" %>
<!DOCTYPE html>
<%--
	JSP
	1. 화면 출력
	2. 데이터만 받아서 처리 ==> 화면 이동 => sendRedirect()
--%>
<%
	// upload
	try{
		request.setCharacterEncoding("UTF-8");
		// 1. request
		// 2. 파일 경로명
		String path = "c:\\download";
		// 3. 한글
		String enctype="UTF-8";
		// 4. 파일 크기
		int maxsize = 1024*1024*100;
		
		MultipartRequest mr = new MultipartRequest(request, path, maxsize, enctype, new DefaultFileRenamePolicy());
																					// 이름이 같은 파일이면 뒤에 1, 2, ... 자동으로 처리
																					// a.jpg => a1.jpg
		// 데이터베이스 연동
		String name = mr.getParameter("name");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String pwd = mr.getParameter("pwd");
		String filename = mr.getOriginalFileName("upload"); // 파일선택 => 파일 이름
		
		DataBoardVO vo = new DataBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		// 파일 첨부를 했는지 안했는지
		if(filename == null){
			vo.setFilename("");
			vo.setFilesize(0);
		} else {
			File file = new File(path + "\\" + filename);
			vo.setFilename(filename);
			vo.setFilesize((int)file.length());
		}
		DataBoardDAO dao = new DataBoardDAO();
		dao.databoardInsert(vo);
		
		// 화면 이동 (목록으로 이동 시키기)
		response.sendRedirect("list.jsp");
		
	} catch(Exception e){
		
	}
	
	
%>