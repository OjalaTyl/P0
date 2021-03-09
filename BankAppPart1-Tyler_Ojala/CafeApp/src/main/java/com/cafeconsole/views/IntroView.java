package com.cafeconsole.views;

import com.cafeconsole.MainDriver;
import com.cafeconsole.ViewRouting;
import com.cafeconsole.repo.ItemDataHandler;

public class IntroView extends View {
	@Override
	public void start() {
		System.out.println("Welcome to the cafe!!");
		//ItemDataHandler idh = new ItemDataHandler();
		//idh.getItems();
		if(!MainDriver.isLoggedIn) {
			//User not logged in. Send them to register
			ViewRouting.getRegisterView().start();
		}else {
			ViewRouting.getHomeView().start();
		}
	}
}
