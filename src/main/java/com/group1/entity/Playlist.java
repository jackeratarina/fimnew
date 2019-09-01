package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="Playlist")
public class Playlist implements Serializable{
	
	@Column(name="id", nullable=false)
	private String id;
	
	
	
	@Column(name="created_date", nullable=true)
	private java.sql.Timestamp created_date;

	@Id
	@Column(name="id_user", nullable=false)
	private String id_user;

	
	@Column(name="is_public", nullable=true)
	private int is_public;

	
	@Column(name="name", nullable=true)
	private String name;

	
	@Column(name="is_active", nullable=true)
	private String is_active;


	
	
	public String getId() {
		return id;
	}

	public java.sql.Timestamp getCreated_date() {
		return created_date;
	}

	public String getId_user() {
		return id_user;
	}

	public int getIs_public() {
		return is_public;
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

	public void setCreated_date(java.sql.Timestamp created_date) {
		this.created_date = created_date;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public void setIs_public(int is_public) {
		this.is_public = is_public;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}


}