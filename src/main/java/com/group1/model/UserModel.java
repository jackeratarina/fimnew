package com.group1.model;

public class UserModel {
	
		private String id;
		private String email;
		private String password;
		private String role;
		private String username;
		private String is_active;
	
	public UserModel() {

	}

	public UserModel(String id,String email,String password,String role,String username,String is_active) {
		super();
		
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.username = username;
		this.is_active = is_active;
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

	public static String getAllVar() {
		return "id,email,password,role,username,is_active";
	}
}
