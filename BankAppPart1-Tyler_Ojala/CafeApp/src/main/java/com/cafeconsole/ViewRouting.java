package com.cafeconsole;

import com.cafeconsole.views.*;
import com.cafeconsole.views.HomeView;
import com.cafeconsole.views.LoginView;
import com.cafeconsole.views.RegisterView;

public class ViewRouting {
 private static RegisterView registerView = new RegisterView();
 private static LoginView loginView = new LoginView();
 private static HomeView homeView = new HomeView();
 private static IntroView introView = new IntroView();
 private static OrderView orderView = new OrderView();
 
 
 public static IntroView getIntroView() {
	 return introView;
 }
 
 public static RegisterView getRegisterView(){
	 return registerView;
 }
 public static LoginView getLoginView(){
	 return loginView;
 }
 public static HomeView getHomeView(){
	 return homeView;
 }
 public static OrderView getOrderView() {
	 return orderView;
 }
}
