package com.group1.model;

public class CountriesOfFilmModel {
	
		private String id;
		private String id_country;
		private String id_film;
	
	public CountriesOfFilmModel() {

	}

	public CountriesOfFilmModel(String id,String id_country,String id_film) {
		super();
		
		this.id = id;
		this.id_country = id_country;
		this.id_film = id_film;
	}
	
	public String getId() {
		return id;
	}

	public String getId_country() {
		return id_country;
	}

	public String getId_film() {
		return id_film;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setId_country(String id_country) {
		this.id_country = id_country;
	}

	public void setId_film(String id_film) {
		this.id_film = id_film;
	}

	public static String getAllVar() {
		return "id,id_country,id_film";
	}
}
