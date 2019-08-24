package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Actor")
public class Actor {
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="image", nullable=true)
	private String image;

	@Column(name="name", nullable=true)
	private String name;


	
	
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