package com.group1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group1.dao.FILMDAO;
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
		return "edit-user";
	}
}
