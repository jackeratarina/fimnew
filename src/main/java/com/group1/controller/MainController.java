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
		List<FILM> list = fimdao.listFILMInfoPage(16, 0);
		model.addAttribute("film",list);
        List<CategoryModel> cate = fimdao.listCategory();
        model.addAttribute("cate",cate);
		return "index";
	}
	@RequestMapping(value = "/watch", method = RequestMethod.GET)
	public String watch(Model model, String id, String ep) {
		if(id == null || id == "") {
			return "redirect:";
		}
		if(ep == null || ep == "") {
			ep="1";
		}
		FILMModel film = fimdao.findById(id);
		model.addAttribute("film", film);
		model.addAttribute("countries", fimdao.getFilmCountry(id));
		model.addAttribute("categories",fimdao.getFilmCategories(id));
		model.addAttribute("actors",fimdao.getFilmActors(id));
		model.addAttribute("links",fimdao.getLinkOfFilm(id));
		model.addAttribute("id",id);
		model.addAttribute("ep",ep);
		
		return "watch";
	}
	@RequestMapping(value = "/sort", method = RequestMethod.GET)
	public String sort(Model model,String page, String country,String cate,String year, String actor, String search) {
		String param = "";
		if(page == null || page == "") {
			page = "0";
		}
		if(country == null || country == "") {
			country = "%";
		}else {
			param += "&country="+country;
		}
		if(cate == null || cate == "") {
			cate = "%";
		}else {
			param += "&cate="+cate;
		}
		if(year == null || year == "") {
			year = "%";
		}else {
			param += "&year="+year;
		}
		if(actor == null || actor == "") {
			actor = "%";
		}else {
			param += "&actor="+actor;
		}
		if(search == null || search == "") {
			search = "%";
		}else {
			param += "&search="+search;
		}
		
		int mypage = Integer.parseInt(page);
		if(mypage < 0) {
			return "redirect:";
		}
		List<FILM> film = fimdao.listFILMInfoPageWithInfo(16, mypage, country, cate, year,actor,search);
		model.addAttribute("film", film);
		model.addAttribute("page", mypage);
		model.addAttribute("country", country);
		model.addAttribute("cate", cate);
		model.addAttribute("year", year);
		model.addAttribute("search", search);
		model.addAttribute("next_page", "/sort?"+param+"&page="+(mypage+1));
		model.addAttribute("pre_page", "/sort?"+param+"&page="+(mypage-1));
		return "show";
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(Model model,String page, String country,String cate,String year) {
		if(page == null || page == "") {
			page = "0";
		}
		
		int mypage = Integer.parseInt(page);
		if(mypage < 0) {
			return "redirect:";
		}
		List<FILM> film = fimdao.listFILMInfoPage(16, mypage);
		model.addAttribute("film", film);
		model.addAttribute("page", mypage);
		model.addAttribute("next_page", "/show?page="+(mypage+1));
		model.addAttribute("pre_page", "/show?page="+(mypage-1));
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
	model.addAttribute("id",id);
		return "detail";
	}
}
