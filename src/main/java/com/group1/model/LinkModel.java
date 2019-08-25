package com.group1.model;

public class LinkModel {
	
		private String id;
		private String describe;
		private String id_film;
		private int num;
		private String url;
	
	public LinkModel() {

	}

	public LinkModel(String id,String describe,String id_film,int num,String url) {
		super();
		
		this.id = id;
		this.describe = describe;
		this.id_film = id_film;
		this.num = num;
		this.url = url;
	}
	
	public String getId() {
		return id;
	}

	public String getDescribe() {
		return describe;
	}

	public String getId_film() {
		return id_film;
	}

	public int getNum() {
		return num;
	}

	public String getUrl() {
		return url;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public void setId_film(String id_film) {
		this.id_film = id_film;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static String getAllVar() {
		return "id,describe,id_film,num,url";
	}
}