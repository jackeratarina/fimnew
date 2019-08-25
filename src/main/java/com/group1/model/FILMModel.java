package com.group1.model;

public class FILMModel {
	
		private String date;
		private String describe;
		private float duration;
		private String image;
		private String IMDB;
		private String info;
		private String name;
		private String name2;
		private String resolution;
		private String status;
	
	public FILMModel() {

	}

	public FILMModel(String date,String describe,float duration,String image,String IMDB,String info,String name,String name2,String resolution,String status) {
		super();
		
		this.date = date;
		this.describe = describe;
		this.duration = duration;
		this.image = image;
		this.IMDB = IMDB;
		this.info = info;
		this.name = name;
		this.name2 = name2;
		this.resolution = resolution;
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}

	public String getDescribe() {
		return describe;
	}

	public float getDuration() {
		return duration;
	}

	public String getImage() {
		return image;
	}

	public String getIMDB() {
		return IMDB;
	}

	public String getInfo() {
		return info;
	}

	public String getName() {
		return name;
	}

	public String getName2() {
		return name2;
	}

	public String getResolution() {
		return resolution;
	}

	public String getStatus() {
		return status;
	}

	
	public void setDate(String date) {
		this.date = date;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setIMDB(String IMDB) {
		this.IMDB = IMDB;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
