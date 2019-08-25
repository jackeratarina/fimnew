package com.group1.model;

public class UserModel {
	
		private String id;
		private String email;
		private String password;
		private String role;
		private String username;
	
	public UserModel() {

	}

	public UserModel(String id,String email,String password,String role,String username) {
		super();
		
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.username = username;
	}
	
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

	public static String getAllVar() {
		return "id,email,password,role,username";
	}
}
