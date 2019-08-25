package com.group1.model;

public class PlaylistModel {
	
		private String id;
		private java.sql.Timestamp created_date;
		private String id_user;
		private int is_public;
		private String name;
	
	public PlaylistModel() {

	}

	public PlaylistModel(String id,java.sql.Timestamp created_date,String id_user,int is_public,String name) {
		super();
		
		this.id = id;
		this.created_date = created_date;
		this.id_user = id_user;
		this.is_public = is_public;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public java.sql.Timestamp getCreated_date() {
		return created_date;
	}

	public String getId_user() {
		return id_user;
	}

	public int getIs_public() {
		return is_public;
	}

	public String getName() {
		return name;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setCreated_date(java.sql.Timestamp created_date) {
		this.created_date = created_date;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public void setIs_public(int is_public) {
		this.is_public = is_public;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getAllVar() {
		return "id,created_date,id_user,is_public,name";
	}
}