package com.cafeconsole.models;

public class User {
	private String name;
	private String email;
	private String password;
	private boolean isEmployee;
	
	public User() {}
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String email, String password, boolean isEmployee) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.isEmployee = isEmployee;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}


	
	
	public String toString() {
		String output = "";
		if(isEmployee) {
			output += "\nUser: Employee";
		} else {
			output += "\nUser: Customer";
		}
		output += "\nName: " + this.name +
				  "\nEmail: " + this.email +
				  "\n";
		for(int i = 0; i < password.length(); i++) {
			output += "*";
		}
				
		return output;
	}
}
