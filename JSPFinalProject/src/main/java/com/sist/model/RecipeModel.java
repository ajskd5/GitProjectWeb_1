package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class RecipeModel {
	// Model 리턴형 => String, void  둘 중 하나
	@RequestMapping("recipe/recipe_list.do")
	public String recipe_list(HttpServletRequest request, HttpServletResponse response) {
		// 1. 페이지 받기
		String page = request.getParameter("page");
		if(page==null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		// List => Map(start, end)
		int rowSize = 9;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list = RecipeDAO.recipeListData(map);
		// 글자수 조정
		for(RecipeVO vo : list) {
			String title = vo.getTitle();
			if(title.length()>30) {
				title = title.substring(0, 30) + "...";
			}
			vo.setTitle(title);
		}
		
		int totalpage = RecipeDAO.recipeTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK * BLOCK) + 1;
		int endPage = ((curpage-1)/BLOCK * BLOCK) + BLOCK;
		
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../recipe/recipe_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/chef_list.do")
	public String chef_list(HttpServletRequest request, HttpServletResponse response) {
		// 1. 페이지 받기
		String page = request.getParameter("page");
		if(page==null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		// List => Map(start, end)
		int rowSize = 20;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<ChefVO> list = RecipeDAO.chefListData(map);
		int totalpage = RecipeDAO.chefTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK * BLOCK) + 1;
		int endPage = ((curpage-1)/BLOCK * BLOCK) + BLOCK;
		
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../recipe/chef_list.jsp");
		return "../main/main.jsp";
	}
}
