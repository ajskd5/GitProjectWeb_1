package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.ReplyDAO;
import com.sist.vo.ReplyVO;
/*
 * 
 */
@Controller
public class ReplyModel {
	@RequestMapping("reply/reply_insert.do")
	public String reply_insert(HttpServletRequest request, HttpServletResponse response) {
		// bno, type, msg
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String bno = request.getParameter("bno"); // 게시물 번호
		String type = request.getParameter("type"); // 카테고리
		String msg = request.getParameter("msg"); // 내용
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		ReplyVO vo = new ReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		vo.setType(Integer.parseInt(type));
		
		// 나중에 더 추가
		String[] temp = {"", "project_freeboard", "seoul_location", "seoul_nature", "seoul_shop"};
		String table = temp[Integer.parseInt(type)];
		vo.setTable_name(table);
		
		// DAO연결
		ReplyDAO.replyInsertData(vo);
		
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	//댓글 삭제
	@RequestMapping("reply/reply_delete.do")
	public String reply_delete(HttpServletRequest request, HttpServletResponse response) {
		String bno = request.getParameter("bno"); // 다시 상세보기로 돌아가기 위한 게시물 번호
		String no = request.getParameter("no"); // 삭제할 댓글 번호
		request.setAttribute("bno", bno);
		// 삭제(DAO)
		ReplyDAO.replyDelete(Integer.parseInt(no));
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	
	@RequestMapping("reply/reply_update.do")
	   public String reply_update(HttpServletRequest request,HttpServletResponse response){
		   try{
			   request.setCharacterEncoding("UTF-8");
		   }catch(Exception ex) {
			   
		   }
		   String bno=request.getParameter("bno");// 게시물번호 => detail로 이동
		   String no=request.getParameter("no"); // 댓글 번호 => 삭제
		   String msg=request.getParameter("msg");
		   
		   ReplyVO vo=new ReplyVO();
		   vo.setNo(Integer.parseInt(no));
		   vo.setMsg(msg);
		   ReplyDAO.replyUpdate(vo);
		   //DAO연동 
		   return "redirect:../freeboard/detail.do?no="+bno;
		   
	   }
}
