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
import com.group1.model.FILMModel;


@Controller
public class MainController {
	
	@Autowired
	private FILMDAO fimdao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String ss() {
		return "show";
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String a() {
		
		return "detail";
	}
}
