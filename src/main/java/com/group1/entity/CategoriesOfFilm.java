package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CategoriesOfFilm")
public class CategoriesOfFilm {
	@Id
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="id_category", nullable=true)
	private String id_category;

	@Column(name="id_film", nullable=true)
	private String id_film;


	
	
	public String getId() {
		return id;
	}

	public String getId_category() {
		return id_category;
	}

	public String getId_film() {
		return id_film;
	}


	
	public void setId(String id) {
		this.id = id;
	}

	public void setId_category(String id_category) {
		this.id_category = id_category;
	}

	public void setId_film(String id_film) {
		this.id_film = id_film;
	}


}