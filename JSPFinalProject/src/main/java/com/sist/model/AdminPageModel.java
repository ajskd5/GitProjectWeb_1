package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class AdminPageModel {
	   @RequestMapping("adminpage/adminpage.do")
	   public String adminpage_main(HttpServletRequest request,HttpServletResponse response)
	   {
		   request.setAttribute("admin_jsp", "../adminpage/adminhome.jsp");
		   request.setAttribute("main_jsp", "../adminpage/adminpage.jsp");
		   return "../main/main.jsp";
	   }
	   
	   @RequestMapping("adminpage/reply.do")
	   public String adminpage_reply(HttpServletRequest request,HttpServletResponse response) {
		   
		   request.setAttribute("admin_jsp", "../adminpage/reply.jsp");
		   request.setAttribute("main_jsp", "../adminpage/adminpage.jsp");
		   return "../main/main.jsp";
	   }
}