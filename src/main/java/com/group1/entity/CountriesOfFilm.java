package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CountriesOfFilm")
public class CountriesOfFilm {
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="id_country", nullable=true)
	private String id_country;

	@Column(name="id_film", nullable=true)
	private String id_film;


	
	
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


}