package com.group1.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.group1.entity.Category;
import com.group1.entity.Country;
import com.group1.entity.FILM;
import com.group1.model.CategoryModel;
import com.group1.model.CountryModel;

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
