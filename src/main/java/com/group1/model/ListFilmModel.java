package com.group1.model;

public class ListFilmModel {
	
		private String id;
		private java.sql.Timestamp created_date;
		private String id_film;
		private String id_playlist;
	
	public ListFilmModel() {

	}

	public ListFilmModel(String id,java.sql.Timestamp created_date,String id_film,String id_playlist) {
		super();
		
		this.id = id;
		this.created_date = created_date;
		this.id_film = id_film;
		this.id_playlist = id_playlist;
	}
	
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

	public static String getAllVar() {
		return "id,created_date,id_film,id_playlist";
	}
}
