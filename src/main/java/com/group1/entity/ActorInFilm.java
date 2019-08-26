package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ActorInFilm")
public class ActorInFilm {
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="id_actor", nullable=true)
	private String id_actor;

	@Column(name="id_film", nullable=true)
	private String id_film;

	@Column(name="name_in", nullable=true)
	private String name_in;


	
	
	public String getId() {
		return id;
	}

	public String getId_actor() {
		return id_actor;
	}

	public String getId_film() {
		return id_film;
	}

	public String getName_in() {
		return name_in;
	}


	
	public void setId(String id) {
		this.id = id;
	}

	public void setId_actor(String id_actor) {
		this.id_actor = id_actor;
	}

	public void setId_film(String id_film) {
		this.id_film = id_film;
	}

	public void setName_in(String name_in) {
		this.name_in = name_in;
	}


}