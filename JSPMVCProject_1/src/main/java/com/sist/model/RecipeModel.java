package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class RecipeModel {
	@RequestMapping("recipe/list.do")
	public String recipe_list(HttpServletRequest request, HttpServletResponse response) {
		
		Map map = new HashMap();
		map.put("start", 1);
		map.put("end", 12);
		List<RecipeVO> list = RecipeDAO.recipeListData(map);
		request.setAttribute("list", list);
		return "../recipe/list.jsp";
	}
}
