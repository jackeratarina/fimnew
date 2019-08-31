package com.group1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/film", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String film(Model model, Integer page, Integer mount) {
		if(page == null || mount == null) {
			return null;
		}
		List<FILM> list = fimdao.listFILMInfoPage(mount, page);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(list);
		System.out.print(json);
		return json;
	}
}
