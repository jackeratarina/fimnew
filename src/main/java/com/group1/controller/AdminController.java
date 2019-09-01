package com.group1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.group1.entity.ActorInFilm;
import com.group1.entity.CategoriesOfFilm;
import com.group1.entity.Category;
import com.group1.entity.CountriesOfFilm;
import com.group1.entity.Country;
import com.group1.entity.FILM;
import com.group1.model.CategoryModel;

@Controller
public class AdminController {
	@Autowired
	private FILMDAO fimdao;
	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
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

	@Transactional
	@RequestMapping(value = "/setActive")
	public String setActiveCategory(Model model, String id) {
		fimdao.activeCategory(id);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}
	
	@Transactional
	@RequestMapping(value = "/setDisable")
	public String setDisableCategory(Model model, String id) {
		fimdao.disableCategory(id);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}

	@RequestMapping(value = "/editCategoryPage", method = RequestMethod.GET)
	public String editCategory(Model model, String id) {
		Category cate = fimdao.getCategory(id);
		model.addAttribute("cate", cate);
		return "edit_category";
	}

	@Transactional
	@RequestMapping(value = "/editCategory", method = RequestMethod.POST)
	public String editCategory(Model model, String id, String name, String is_active) {
		fimdao.updateCategory(id, name, is_active);
		Category cate = fimdao.getCategory(id);
		model.addAttribute("cate", cate);
		return "edit_category";
	}

	@Transactional
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String addCate(Model model, String name, String is_active) {
		UUID uuid = UUID.randomUUID();
		fimdao.addCategory(uuid.toString(), name, is_active);
		List<Category> cate = fimdao.getlistCategory();
		model.addAttribute("categories", cate);
		return "category_manager";
	}

	@RequestMapping(value = "/actor_manager", method = RequestMethod.GET)
	public String actorManager(Model model) {
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);
		return "actor_manager";
	}

	@Transactional
	@RequestMapping(value = "/inactiveActor")
	public String inactiveActor(Model model, String id) {
		fimdao.inactiveActor(id);
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);
		return "actor_manager";
	}

	@Transactional
	@RequestMapping(value = "/activeActor")
	public String activeActor(Model model, String id) {
		fimdao.activeActor(id);
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);
		return "actor_manager";
	}

	@RequestMapping(value = "/editActor", method = RequestMethod.GET)
	public String editActor(Model model, String id) {
		Actor actor = fimdao.getActor(id);

		model.addAttribute("actor", actor);
		return "edit_actor";
	}

	@Transactional
	@RequestMapping(value = "/editActor123", method = RequestMethod.GET)
	public String editActor2(Model model, String id,String name) {
		fimdao.updateActor(id, name);
		List<Actor> actors = fimdao.getListActor();
		model.addAttribute("actors", actors);

		return "actor_manager";
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/film", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String film(Model model, Integer page, Integer mount, String search) {
		if(page == null || mount == null) {
			return null;
		}
		List<FILM> list = fimdao.listFILMInfoPageWithInfo(mount, page, "%", "%", "%", "%", search, false);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(list);
		System.out.print(json);
		return json;
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/film/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String film_update(Model model, FILM film) {
		String json = gson.toJson(film);
		FILM status = fimdao.updateFilm(film);
		json = gson.toJson(status);
		return json;
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/film/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String film_add(Model model, FILM film) {
		String json = gson.toJson(film);
		fimdao.saveFilm(film);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/film/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String film_remove(Model model, String id) {
		fimdao.removeFilm(id);
		return "{}";
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/film/getinfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String film_advance(Model model, String id) {
		List<Category> listCate = fimdao.getlistCategory();
		List<Country> countries = fimdao.listCountry();
		List<Category> filmCateroies = fimdao.getFilmCategories(id);
		List<Country> filmCounties= fimdao.getFilmCountry(id);
		List<Actor> filmActors = fimdao.getFilmActors(id);
		Map<String, Object> response = new HashMap<>();
		response.put("listCate", listCate);
		response.put("countries", countries);
		response.put("filmCateroies", filmCateroies);
		response.put("filmCounties", filmCounties);
		response.put("filmActors", filmActors);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(response);
		return json;
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/actor/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String actor_search(Model model, String name) {
		List<Actor> actors = fimdao.searchActorByName(name);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(actors);
		return json;
	}
	///admin/actor_film/create
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/actor_film/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String actor_add(Model model, ActorInFilm aif) {
		fimdao.addActorForFilm(aif);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/category_film/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String cate_add(Model model, CategoriesOfFilm cof) {
		fimdao.addCategoryForFilm(cof);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/country_film/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String country_add(Model model, CountriesOfFilm countryof) {
		fimdao.addCountryForFilm(countryof);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/actor_film/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String actor_remove(Model model, ActorInFilm aif) {
		fimdao.removeActorForFilm(aif);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/category_film/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String cate_remove(Model model, CategoriesOfFilm cof) {
		fimdao.removeCategoryForFilm(cof);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/country_film/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String country_remove(Model model, CountriesOfFilm countryof) {
		fimdao.removeCountryForFilm(countryof);
		return "{}";
	}
}
