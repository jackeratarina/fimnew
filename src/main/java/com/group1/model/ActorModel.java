package com.group1.model;

public class ActorModel {
	
		private String id;
		private String image;
		private String is_active;
		private String name;
	
	public ActorModel() {

	}

	public ActorModel(String id,String image,String is_active,String name) {
		super();
		
		this.id = id;
		this.image = image;
		this.is_active = is_active;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getIs_active() {
		return is_active;
	}

	public String getName() {
		return name;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getAllVar() {
		return "id,image,is_active,name";
	}
}
