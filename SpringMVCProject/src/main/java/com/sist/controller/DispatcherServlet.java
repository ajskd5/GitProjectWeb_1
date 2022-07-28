package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.net.*;
import java.util.*;
/*
 *  자바 / HTML(태그형 프로그램 사용)  분리
 *  			JSTL
 *  MVC
 *  	Model : 데이터 관리, 요청 처리 후 request/session에 전송값 저장
 *  		~Model(조립기), ~DAO(데이터베이스 연결), ~VO(데이터 모음)  ==> 자바 (재사용, 확장)
 *  		JSP => 첫줄 <% %> 부분
 *  	View : 기능은 없음 (Front : JavaScript) => 데이터 받아서 화면 출력 ==> JSTL/EL  ==> .JSP
 *  	Controller : Model, View 연결하는 역할
 *  			요청받기, 결과 보내기, 화면 이동
 *  								브라우저에서 전송요청 ===> 자바로는 받을 수 없음 (서블릿 , JSP로 받아야 함)
 *  		사용자 요청 (.do) ===> Controller ===> 처리할 Model 찾음  <=====> DAO연결
 *  														|   => request/session에 값 담음 (request/session.setAttribute())
 *  												Controller => JSP 찾기 => JSP로 request/session 전송 => 화면출력(사용자 브라우저에서 읽어감)
 *  		=> 파일  공개(JSP) / 비공개(화면 출력이 아니고 기능만 처리 => 서블릿 제작)
 *  			Controller : 프레임워크 (Spring, Struts) => 이미 만들어져 있음
 *  						=> 포털 (다음, 네이버) 직접 만든다
 *  						프레임워크 VS 라이브러리 차이점
 *  						(레고)			(완제품)
 *  	*** MVC : Controller 를 URL주소로 찾기
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> packList = new ArrayList<String>(); // XML => 패키징 저장
	private List<String> clsList = new ArrayList<String>();// 클래스를 모은다
	public void init(ServletConfig config) throws ServletException {
		try {
			// 현재 프로젝트 경로 읽기
			URL url=this.getClass().getClassLoader().getResource(".");
			File file=new File(url.toURI());
			System.out.println(file.getPath());
			String path=file.getPath();
			// File.separator => \\(window), /(mac)
			path=path.replace("\\", File.separator);
			path=path.substring(0,path.lastIndexOf(File.separator));
			System.out.println(path);
			//XML 파싱
			/*
			 *  JAXP : DOM / SAX (MyBatis, Spring)
			 *  	DOM => 메모리에 트리형태로 저장 후 처리 (수정, 삭제, 추가 가능)
			 *  	SAX => 한줄씩 태그 값 읽어옴 (Read only)
			 *  	=> XML
			 *  		데이터 저장 위치 => <태그>데이터 출력</태그>
			 *  							<태그 속성="데이터">
			 *  	=> XML은 사용자 정의 태그X (속성, 태그를 추가 할 수 없다) => 태그, 속성 외워야 한다
			 *  		마이파티스, 스프링 => 자체에서 제작 후 지원
			 *  XML은 태그 순서가 있음
			 *  
			 *  JAXB : 빅데이터 (클래스 변수 == 태그명 매칭) => 1.8까지만 지원
			 */
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path+File.separator+"application.xml"));
			Element root=doc.getDocumentElement();
			System.out.println(root.getTagName());
			
			NodeList list=root.getElementsByTagName("component-scan");
			for(int i=0;i<list.getLength();i++)
			{
				Element cs=(Element)list.item(i);
				String value=cs.getAttribute("base-package");
				packList.add(value);
			}
			
			// 출력 
			for(String s:packList)
			{
				//System.out.println(s);
				path=path.substring(0,path.lastIndexOf(File.separator));
//				System.out.println(path);
				String ss=path+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+s.replace(".", File.separator);
//				System.out.println(ss);
				File dir=new File(ss);
				File[] files=dir.listFiles();
				for(File f:files)
				{
//					System.out.println(f.getName());
					String sss = s + "." + f.getName().substring(0, f.getName().lastIndexOf("."));
					// com.sist.model.ListModel
					System.out.println(sss);
					clsList.add(sss);
				}
			}
			// Controller => Model 찾기까지 만듦
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// ▲Model 등록
	
	// Model 찾아서 요청 수행 => JSP찾기 => request, session전송
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 사용자 URL => http://localhost:8080/SpringMVCProject/main/main.do => main/main.do 이만큼 잘라서 메소드 찾기
			// @RequestMapping("main/main.do") => 얘랑 자른값이랑 같은지 비교
			String uri = request.getRequestURI(); // /SpringMVCProject/main/main.do 이만큼 가져옴
			uri = uri.substring(request.getContextPath().length()+1); // /SpringMVCProject/이만큼 앞에 자름 (main/main.do)이만큼 남음
			// Model 클래스 찾기
			for(String strCls : clsList) {
				Class clsName = Class.forName(strCls); // 등록된 클래스 정보 읽기
				if(clsName.isAnnotationPresent(Controller.class) == false) {
					continue; // 클래스 위에 @Controller가 있는지 확인 (없으면 제외)
				}
				// 메모리 할당
				Object obj = clsName.getDeclaredConstructor().newInstance();
				// 요청 처리 기능 찾기 => 메소드
				Method[] methods = clsName.getDeclaredMethods(); // Model 클래스에 선언된 모든 메소드 가져옴
				for(Method m : methods) {
					// 메소드 위에 있는 어노테이션 확인
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if(uri.equals(rm.value())) { // 처리 메소드를 찾아서 수행
						String jsp = (String)m.invoke(obj, request, response); // 메소드 호출
						// 메소드명은 마음대로 사용 가능
						/*
						 *  Model 메소드 형식
						 *  @RequestMapping("uri")
						 *  public String main_main(HttpServletRequest request, HttpServletResponse response){
						 *  	return "출력할 JSP";
						 *  }
						 *  	request => 요청값 받는다 => 처리 => setAttribute로 결과값 전송
						 *  	response => Cookie/파일 업로드 (응답)
						 */
						// 이동방식 => sendRedirect(기존에 만들어진 파일로 이동(재호출), forward(화면 출력)
						// 				=> request 초기화									=> request 전송
						if(jsp.startsWith("redirect")) { // sendRedicrect
							// return "redirect:../main/main.do"
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						} else { // forward ( request유지)
							// return "../main/main.jsp"
							RequestDispatcher rd = request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
					
					
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
