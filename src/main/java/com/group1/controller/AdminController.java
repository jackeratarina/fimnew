package com.group1.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group1.dao.FILMDAO;
import com.group1.entity.Actor;
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

	@RequestMapping(value = "/admin/Category/load", method = RequestMethod.GET)
	public String cateManager(Model model) {
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}

	
	@RequestMapping(value = "/admin/Category/filterActive", method = RequestMethod.GET)
	public String filterActive(Model model, String id) {
		if(id.contains("2")) {
			List<Category> cate = fimdao.getlistCategory();
			model.addAttribute("categories", cate);
			return "filter_active_category";
		}
		else {
			List<Category> cate = fimdao.filterActiveCategory(id);
			model.addAttribute("categories", cate);
			return "filter_active_category";
		}	
	}
	
	@Transactional
	@RequestMapping(value = "/admin/Category/setActive")
	public String setActiveCategory(Model model, String id) {
		fimdao.activeCategory(id);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/Category/setDisable")
	public String setDisableCategory(Model model, String id) {
		fimdao.disableCategory(id);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}

	@RequestMapping(value = "/admin/Category/findById", method = RequestMethod.GET)
	public String editCategory(Model model, String id) {
		Category cate = fimdao.getCategory(id);
		model.addAttribute("cate", cate);
		return "edit_category";
	}

	@Transactional
	@RequestMapping(value = "/admin/Category/editCategory", method = RequestMethod.POST)
	public String editCategory(Model model, String id, String name, String is_active) {
		fimdao.updateCategory(id, name, is_active);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}

	@Transactional
	@RequestMapping(value = "/admin/Category/addCategory", method = RequestMethod.POST)
	public String addCate(Model model, String name, String is_active) {
		UUID uuid = UUID.randomUUID();
		fimdao.addCategory(uuid.toString(), name, is_active);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}

	@RequestMapping(value = "/admin/actor", method = RequestMethod.GET)
	public String actorManager(Model model) {
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);
		return "actor_manager";
	}

	@Transactional
	@RequestMapping(value = "/admin/actor/inactiveActor")
	public String inactiveActor(Model model, String id) {
		fimdao.inactiveActor(id);
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);
		return "actor_manager";
	}

	@Transactional
	@RequestMapping(value = "/admin/actor/activeActor")
	public String activeActor(Model model, String id) {
		fimdao.activeActor(id);
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);
		return "actor_manager";
	}

	@RequestMapping(value = "/admin/actor/editActor", method = RequestMethod.GET)
	public String editActor(Model model, String id) {
		Actor actor = fimdao.getActor(id);
		model.addAttribute("actor", actor);
		return "edit_actor";
	}
	@RequestMapping(value = "/admin/actor/create", method = RequestMethod.GET)
	public String create(Model model) {		
		return "create_actor";
	}
	@RequestMapping(value = "/admin/actor/findByName", method = RequestMethod.GET)
	public String findByName(Model model,String name) {	
		List<Actor> actors = fimdao.findByName(name);
		model.addAttribute("actors", actors);
		return "actor_manager";
		
	}
	@Transactional
	@RequestMapping(value = "/admin/actor/createActor", method = RequestMethod.GET)
	public String editActor(Model model, String id,String name,String is_active) {
		fimdao.createActor(id, name, is_active);
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);

		return "actor_manager";
	}
	@Transactional
	@RequestMapping(value = "/admin/actor/updateActor", method = RequestMethod.GET)
	public String editActor2(Model model, String id,String name) {
		fimdao.updateActor(id, name);
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);

		return "actor_manager";
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
