package com.cafeconsole.views;

import org.apache.log4j.Logger;

import com.cafeconsole.MainDriver;
import com.cafeconsole.cafe.Cafe;
//import com.cafeconsole.models.Customer;
import com.cafeconsole.models.User;

import com.cafeconsole.MyScanner;
import com.cafeconsole.ViewRouting;

public class RegisterView extends View {
	static Cafe cafe = new Cafe();
	static final Logger logging = Logger.getLogger(MainDriver.class);
	
	public static User authenticateUser(String username, String password) {
		// If password matches the user account associated with user name
		if(cafe.login(username, password)) {
			System.out.println("User Has logged in.");
			cafe.setUser(username);
			//logging.info(cafe.getEmail() + " has logged in.");
			
		}
		return cafe.getUser();
	}
	
	public void start() {
		System.out.println("Choose an option");
		System.out.println("1 - Log In");
		System.out.println("2 - Register");
		int userChoice = MyScanner.scanInt();
		if(userChoice == 1) {
			login();
		}else if(userChoice == 2) {
			register();
		}else {
			System.out.println("invalid action");
			start();
		}
		
	}
	
	public void login() {
		System.out.println("Enter a Username: ");
		String username = MyScanner.scanString();
		if(!cafe.checkEmail(username)) {
			System.out.println("Username does not exist");
			login();
			return;
		}
		System.out.println("Enter your password.");
		String password = MyScanner.scanString();
		User loggedInUser = authenticateUser(username, password);
		if(loggedInUser != null) {
			System.out.println("Logging in");
			MainDriver.isLoggedIn = true;
		}else {
			System.out.println("Wrong password");
			login();
		}
	}
	
	public String validatePasswordMatch() {
		boolean isMatch = false;
		String password = "";
		while(!isMatch) {
			System.out.println("Enter Password.");
			password = MyScanner.scanString();
			System.out.println("Confirm the password.");
			String confirmedPassword = MyScanner.scanString();
			if(!isMatch) {
				System.out.println("Password did not match");
				continue;
			}
			break;
			
			
		}
		return password;
	}
	public void register() {
		System.out.println("Enter your name:");
		String name = MyScanner.scanString();
		System.out.println("Enter your username");
		String username = MyScanner.scanString();
		if(cafe.checkEmail(username)) {
			System.out.println("Username exists");
			register();
			return;
		}
		String newPassword = validatePasswordMatch();
		cafe.signUp(name, username, newPassword);
		//insert new customer
		ViewRouting.getHomeView().start();
		
	}
	public void logout() {
		MainDriver.isLoggedIn = false;
		ViewRouting.getIntroView().start();
	}
	
	
}
