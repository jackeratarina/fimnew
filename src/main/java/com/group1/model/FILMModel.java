package com.group1.model;

public class FILMModel {
	
		private String id;
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
		private String is_active;
		private java.sql.Timestamp created_date;
		private String image_poster;
	
	public FILMModel() {

	}

	public FILMModel(String id,String date,String describe,float duration,String image,String IMDB,String info,String name,String name2,String resolution,String status,String is_active,java.sql.Timestamp created_date,String image_poster) {
		super();
		
		this.id = id;
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
		this.is_active = is_active;
		this.created_date = created_date;
		this.image_poster = image_poster;
	}
	
	public String getId() {
		return id;
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

	public String getIs_active() {
		return is_active;
	}

	public java.sql.Timestamp getCreated_date() {
		return created_date;
	}

	public String getImage_poster() {
		return image_poster;
	}

	
	public void setId(String id) {
		this.id = id;
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

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public void setCreated_date(java.sql.Timestamp created_date) {
		this.created_date = created_date;
	}

	public void setImage_poster(String image_poster) {
		this.image_poster = image_poster;
	}

	public static String getAllVar() {
		return "id,date,describe,duration,image,IMDB,info,name,name2,resolution,status,is_active,created_date,image_poster";
	}
}
