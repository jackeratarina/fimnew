package com.group1.model;

public class CountryModel {
	
		private String id;
		private String name;
	
	public CountryModel() {

	}

	public CountryModel(String id,String name) {
		super();
		
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getAllVar() {
		return "id,name";
	}
}
