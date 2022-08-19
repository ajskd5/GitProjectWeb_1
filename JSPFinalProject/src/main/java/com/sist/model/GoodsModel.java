package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class GoodsModel {
	@RequestMapping("goods_main/goods_main.do")
	public String goods_main(HttpServletRequest request, HttpServletResponse response) {
		// 베스트 best
		List<GoodsVO> bList = GoodsDAO.goodsMainData("goods_best");
		// 특가 special
		List<GoodsVO> sList = GoodsDAO.goodsMainData("goods_special");
		// 신상품 new
		List<GoodsVO> nList = GoodsDAO.goodsMainData("goods_new");
		// goods_home.jsp로 request를 전송 => EL/JSTL로 jsp에서 출력
		request.setAttribute("bList", bList);
		request.setAttribute("sList", sList);
		request.setAttribute("nList", nList);
		request.setAttribute("goods_jsp", "../goods_main/goods_home.jsp");
		return "../goods_main/goods_main.jsp";
	}
	
	// 전체상품
	@RequestMapping("goods_main/goods_all.do")
	public String goods_all(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		// DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curpage) - (rowSize-1);
		int end = rowSize * curpage;
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_all");
		
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalpage = GoodsDAO.goodsTotalPage("goods_all");
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_all.jsp");
		return "../goods_main/goods_main.jsp";
	}
	
	// 베스트
	@RequestMapping("goods_main/goods_best.do")
	public String goods_best(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		// DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curpage) - (rowSize-1);
		int end = rowSize * curpage;
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_best");
		
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalpage = GoodsDAO.goodsTotalPage("goods_best");
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_best.jsp");
		return "../goods_main/goods_main.jsp";
	}
	
	// 특가상품
	@RequestMapping("goods_main/goods_special.do")
	public String goods_special(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		// DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curpage) - (rowSize-1);
		int end = rowSize * curpage;
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_special");
		
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalpage = GoodsDAO.goodsTotalPage("goods_special");
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_special.jsp");
		return "../goods_main/goods_main.jsp";
	}
	
	// 신상품
	@RequestMapping("goods_main/goods_new.do")
	public String goods_new(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		// DAO 연결
		Map map = new HashMap();
		int rowSize = 9;
		int start = (rowSize*curpage) - (rowSize-1);
		int end = rowSize * curpage;
		map.put("start", start);
		map.put("end", end);
		map.put("table_name", "goods_new");
		
		List<GoodsVO> list = GoodsDAO.goodsListData(map);
		int totalpage = GoodsDAO.goodsTotalPage("goods_new");
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("goods_jsp", "../goods_main/goods_new.jsp");
		return "../goods_main/goods_main.jsp";
	}
	
	// 상세보기
	@RequestMapping("goods_main/goods_detail.do")
	public String goods_detail(HttpServletRequest request, HttpServletResponse response) {
		String strNo = request.getParameter("no");
		String strCno = request.getParameter("cno");
		int no = Integer.parseInt(strNo);
		int cno = Integer.parseInt(strNo);
		String table_name = "";
		if(cno == 1) {
			table_name = "goods_all";
		} else if(cno == 2) {
			table_name = "goods_best";
		} else if(cno == 3) {
			table_name = "goods_special";
		} else if(cno == 4) {
			table_name = "goods_new";
		}
		
		Map map = new HashMap();
		map.put("table_name", table_name);
		map.put("no", no);
		GoodsVO vo = GoodsDAO.goodsDetailData(map);
		
		request.setAttribute("vo", vo);
		request.setAttribute("goods_jsp", "../goods_main/goods_detail.jsp");
		return "../goods_main/goods_main.jsp";
	}
	
	
}