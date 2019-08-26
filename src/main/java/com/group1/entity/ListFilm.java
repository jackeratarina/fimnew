package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ListFilm")
public class ListFilm {
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="created_date", nullable=true)
	private java.sql.Timestamp created_date;

	@Column(name="id_film", nullable=true)
	private String id_film;

	@Column(name="id_playlist", nullable=true)
	private String id_playlist;


	
	
	public String getId() {
		return id;
	}

	public java.sql.Timestamp getCreated_date() {
		return created_date;
	}

	public String getId_film() {
		return id_film;
	}

	public String getId_playlist() {
		return id_playlist;
	}


	
	public void setId(String id) {
		this.id = id;
	}

	public void setCreated_date(java.sql.Timestamp created_date) {
		this.created_date = created_date;
	}

	public void setId_film(String id_film) {
		this.id_film = id_film;
	}

	public void setId_playlist(String id_playlist) {
		this.id_playlist = id_playlist;
	}


}