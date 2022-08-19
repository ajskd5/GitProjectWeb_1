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
		request.setAttribute("goods_jsp", "../goods_main/goods_all.jsp");
		return "../goods_main/goods_main.jsp";
	}
}
