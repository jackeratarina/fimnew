package com.group1.model;

public class CountriesOfFilmModel {
	
		private String id_country;
		private String id_film;
	
	public CountriesOfFilmModel() {

	}

	public CountriesOfFilmModel(String id_country,String id_film) {
		super();
		
		this.id_country = id_country;
		this.id_film = id_film;
	}
	
	public String getId_country() {
		return id_country;
	}

	public String getId_film() {
		return id_film;
	}

	
	public void setId_country(String id_country) {
		this.id_country = id_country;
	}

	public void setId_film(String id_film) {
		this.id_film = id_film;
	}

}
