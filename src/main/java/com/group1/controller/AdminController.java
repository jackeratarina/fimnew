package com.group1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group1.dao.FILMDAO;
import com.group1.entity.Category;
import com.group1.entity.FILM;
import com.group1.model.CategoryModel;

@Controller
public class AdminController {
	@Autowired
	private FILMDAO fimdao;
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String index(Model model) {
//		List<FILM> list = fimdao.listFILMInfoPage(16, 0);
//		model.addAttribute("film",list);
//        List<CategoryModel> cate = fimdao.listCategory();
//        model.addAttribute("cate",cate);
		return "admin_";
	}
	
	@RequestMapping(value = "/category_manager", method = RequestMethod.GET)
	public String cateManager(Model model) {
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}
	
	@RequestMapping(value = "/deleteCategory")
	public String deleteCategory(Model model, String id) {
		fimdao.deleteCategory(id);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}
	
	@RequestMapping(value = "/editCategory", method = RequestMethod.GET)
	public String editCategory(Model model, String id) {
		Category cate = fimdao.getCategory(id);
		model.addAttribute("cate", cate);
		return "edit_category";
	}
	
	@RequestMapping(value ="/addCategory", method = RequestMethod.POST)
	public String addCate(@RequestParam String categoryName) {
		fimdao.addCategory("ssss", categoryName);
		return "category_manager";
	}
}
