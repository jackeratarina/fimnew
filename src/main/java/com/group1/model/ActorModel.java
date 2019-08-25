package com.group1.model;

public class ActorModel {
	
		private String id;
		private String image;
		private String name;
	
	public ActorModel() {

	}

	public ActorModel(String id,String image,String name) {
		super();
		
		this.id = id;
		this.image = image;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public String getImage() {
		return image;
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

	public void setName(String name) {
		this.name = name;
	}

	public static String getAllVar() {
		return "id,image,name";
	}
}
