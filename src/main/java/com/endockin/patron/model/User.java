package com.endockin.patron.model;

public class User {
	
	public static final int USER_ROLE_ADMIN = 0;
	public static final int USER_ROLE_CLIENT = 1;

	private int userid;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private int userRole;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}

}
