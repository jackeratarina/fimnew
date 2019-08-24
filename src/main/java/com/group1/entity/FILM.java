package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FILM")
public class FILM {
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="date", nullable=true)
	private String date;

	@Column(name="describe", nullable=true)
	private String describe;

	@Column(name="duration", nullable=true)
	private float duration;

	@Column(name="image", nullable=true)
	private String image;

	@Column(name="IMDB", nullable=true)
	private String IMDB;

	@Column(name="info", nullable=true)
	private String info;

	@Column(name="name", nullable=true)
	private String name;

	@Column(name="name2", nullable=true)
	private String name2;

	@Column(name="resolution", nullable=true)
	private String resolution;

	@Column(name="status", nullable=true)
	private String status;


	
	
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