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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.group1.entity.Link;
import com.group1.model.CategoryModel;
import com.group1.model.CountryModel;
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
		if(search == null) {
			search = "%";
		}
		List<FILM> list = fimdao.listFILMWithSearch(mount, page, search);
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
		gson = new GsonBuilder().disableHtmlEscaping().create();
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
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/link", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String link_film(Model model, String id_film) {
		List<Link> list = fimdao.getLinkOfFilm(id_film);
		String json = gson.toJson(list);
		return json;
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/link/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String link_film(Model model, Link link) {
		fimdao.updateLink(link);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/link/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String link_film_add(Model model, Link link) {
		fimdao.addLink(link);
		return "{}";
	}
	@Transactional
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/admin/link/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String link_film_delete(Model model, String id_link) {
		fimdao.deleteLink(id_link);
		return "{}";
	}
	

	public void showCountry(Model model) {
		List<Country> listCountry = fimdao.listAllCountry();
		model.addAttribute("listCountry", listCountry);
	}
	
	@RequestMapping(value = "/admin/country", method = RequestMethod.GET)
	public String manageCountry(Model model) {
		showCountry(model);
		return "manageCountry";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/country/delete/{id}", method = RequestMethod.GET)
	public String deleteCountry(Model model, @PathVariable String id) {
		fimdao.disableCountry(id);
		showCountry(model);
		return "manageCountry";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/country/disableCoutry/{id}", method = RequestMethod.GET)
	public String disableCountry(Model model, @PathVariable String id) {
		fimdao.disableCountry(id);
		showCountry(model);
		return "manageCountry";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/country/enableCoutry/{id}", method = RequestMethod.GET)
	public String enableCountry(Model model, @PathVariable String id) {
		fimdao.enableCountry(id);;
		showCountry(model);
		return "manageCountry";
	}
	
	@RequestMapping(value = "/admin/country/find/{id}", method = RequestMethod.GET)
	public String findCountry(Model model, @PathVariable String id) {
		Country country = fimdao.findCountry(id);
		model.addAttribute("country", country);
		return "editCountry";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/country/update", method = RequestMethod.POST)
	public String updateCountry(Model model, @ModelAttribute("countrymodel") CountryModel countryModel) {
		fimdao.updateCountry(countryModel.getId(), countryModel.getName());
		
		showCountry(model);
		return "manageCountry";
	}
}
