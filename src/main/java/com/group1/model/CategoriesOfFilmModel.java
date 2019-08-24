package com.group1.model;

public class CategoriesOfFilmModel {
	
		private String id_category;
		private String id_film;
	
	public CategoriesOfFilmModel() {

	}

	public CategoriesOfFilmModel(String id_category,String id_film) {
		super();
		
		this.id_category = id_category;
		this.id_film = id_film;
	}
	
	public String getId_category() {
		return id_category;
	}

	public String getId_film() {
		return id_film;
	}

	
	public void setId_category(String id_category) {
		this.id_category = id_category;
	}

	public void setId_film(String id_film) {
		this.id_film = id_film;
	}

}
