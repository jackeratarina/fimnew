package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category {
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="name", nullable=true)
	private String name;

	@Column(name="is_active", nullable=true)
	private String is_active;


	
	
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


}