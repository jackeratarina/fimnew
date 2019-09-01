package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="CountriesOfFilm")
public class CountriesOfFilm implements Serializable{
	
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Id
	@Column(name="id_country", nullable=false)
	private String id_country;

	@Id
	@Column(name="id_film", nullable=false)
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