package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="Link")
public class Link implements Serializable{
	
	@Column(name="id", nullable=false)
	private String id;
	
	
	
	@Column(name="describe", nullable=true)
	private String describe;

	@Id
	@Column(name="id_film", nullable=false)
	private String id_film;

	
	@Column(name="num", nullable=true)
	private int num;

	
	@Column(name="url", nullable=true)
	private String url;

	
	@Column(name="is_active", nullable=true)
	private String is_active;


	
	
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

	public String getIs_active() {
		return is_active;
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

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}


}