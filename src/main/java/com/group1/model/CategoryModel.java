package com.group1.model;

public class CategoryModel {
	
		private String id;
		private String name;
		private String is_active;
	
	public CategoryModel() {

	}

	public CategoryModel(String id,String name,String is_active) {
		super();
		
		this.id = id;
		this.name = name;
		this.is_active = is_active;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIs_active() {
		return is_active;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public static String getAllVar() {
		return "id,name,is_active";
	}
}
