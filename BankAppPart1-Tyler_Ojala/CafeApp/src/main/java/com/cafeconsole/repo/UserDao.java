package com.cafeconsole.repo;

import java.util.List;

import com.cafeconsole.models.User;

public interface UserDao {
	void addUser(String name, String email, String password);
	
	void addUser(User user);
	
	
	boolean checkName(String email, String name);
	
	boolean checkEmail(String email);
	
	boolean checkPass(String email, String pass);
	
	List<User> getAllUsers();
	
	User getUser(String email);
	
	User getUser(User user);

	
	List<User> getValuedCustomers();
	
	void deleteUser(String email);

	void deleteUser(User user);
	
	void updateUser(User user);
	
	void changeName(String email, String name);
		
	void changePass(String email, String password);
}
