package com.cafeconsole;

import com.cafeconsole.cafe.Cafe;
import com.cafeconsole.models.User;
import com.cafeconsole.repo.UserDataHandler;

public class MainDriver {
	public static boolean isLoggedIn = false;
	static Cafe cafe = new Cafe();
	
	public static void startCafe() {
		UserDataHandler handler = new UserDataHandler();
		handler.deleteUser("Craig@Craig.Craig");
		//cafe.load();
	}
	
	public static void main(String[] args) {
//		com.cafeconsole.repo.UserDataHandler handler = new com.cafeconsole.repo.UserDataHandler();
//		System.out.println(handler.checkEmail("Jim@Jim.Jim"));
		startCafe();
	}

}
