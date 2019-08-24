package com.group1.model;

public class ActorModel {
	
		private String image;
		private String name;
	
	public ActorModel() {

	}

	public ActorModel(String image,String name) {
		super();
		
		this.image = image;
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	
	public void setImage(String image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

}
