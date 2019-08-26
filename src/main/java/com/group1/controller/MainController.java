package com.group1.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group1.dao.FILMDAO;
import com.group1.entity.FILM;
import com.group1.model.CategoryModel;
import com.group1.model.FILMModel;


@Controller
public class MainController {
	
	@Autowired
	private FILMDAO fimdao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		List<FILMModel> list = fimdao.listFILMInfo();
		model.addAttribute("film",list);
        List<CategoryModel> cate = fimdao.listCategory();
        model.addAttribute("cate",cate);
		return "index";
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show() {
		return "show";
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model,String id) {
		System.out.println(id);
	if(id == null || id == "") {
		return "redirect:";
	}
	FILMModel film = fimdao.findById(id);
	model.addAttribute("film", film);
	model.addAttribute("countries", fimdao.getFilmCountry(id));
	model.addAttribute("categories",fimdao.getFilmCategories(id));
	model.addAttribute("actors",fimdao.getFilmActors(id));
	
		return "detail";
	}
}
