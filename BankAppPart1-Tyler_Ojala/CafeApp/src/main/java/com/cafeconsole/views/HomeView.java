package com.cafeconsole.views;

import com.cafeconsole.repo.ItemDataHandler;

public class HomeView extends View {
	
	public void displayItems() {
		ItemDataHandler idh = new ItemDataHandler();
		idh.getItems();
	}
	
	public void start() {
		System.out.println("Here are the things we offer!");
		System.out.println("Please enter the number of the item that you would like to order.");
		displayItems();
		
		//loop user for input
		
	

		}
	}

