package com.group1.model;

public class ActorInFilmModel {
	
		private String id;
		private String id_actor;
		private String id_film;
		private String name_in;
	
	public ActorInFilmModel() {

	}

	public ActorInFilmModel(String id,String id_actor,String id_film,String name_in) {
		super();
		
		this.id = id;
		this.id_actor = id_actor;
		this.id_film = id_film;
		this.name_in = name_in;
	}
	
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

	public static String getAllVar() {
		return "id,id_actor,id_film,name_in";
	}
}
