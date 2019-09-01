package com.group1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Id
	@Column(name="id", nullable=false)
	private String id;
	
	
	@Column(name="email", nullable=true)
	private String email;

	@Column(name="password", nullable=true)
	private String password;

	@Column(name="role", nullable=true)
	private String role;

	@Column(name="username", nullable=true)
	private String username;

	@Column(name="is_active", nullable=true)
	private String is_active;


	
	
	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public String getIs_active() {
		return is_active;
	}


	
	public void setId(String id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}


}