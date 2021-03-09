package com.Project0;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 123L;
	private String username;
	private String password;
	private int adminLevel;
	public String name;
	public String address;

	//Constructor setting all variables
	public User(String username, String password, int adminLevel, String name, String address) {
		super();
		this.username = username;
		this.password = password;
		this.adminLevel = adminLevel;
		this.name = name;
		this.address = address;
	}
	
	//Constructor only initializing username. Used for checking if a user exists with that username.
	public User(String username) {
		super();
		this.username = username;
	}

	//Gets the username
	public String getUsername() {
		return username;
	}
	
	//Sets the username.
	public void setUsername(String username) {
		this.username = username;
	}

	//Gets the password.
	public String getPassword() {
		return password;
	}

	//Sets the password.
	public void setPassword(String password) {
		this.password = password;
	}

	//Gets the admin level.
	public int getAdminLevel() {
		return adminLevel;
	}

	//Sets the admin level.
	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}

	//Checks the username of this and the given user and returns true if they are equal.
	public boolean equals(User arg0) {
		if (this.username.equals(arg0.username)) {
			return true;
		} else {
			return false;
		}
	}

	//Returns the name and address of the user.
	public String getPersonalInfo() {
		return "Name:" + name + ", " + "Address:" + address;
	}

	//Returns the username of the user.
	@Override
	public String toString() {
		return username;
	}
}
