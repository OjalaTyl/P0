package com.cafeconsole.models;

import java.util.ArrayList;
import java.util.List;
//import java.util.UUID;

public class Order {
//	List<Integer> itemList = new ArrayList<Integer>();
	private String customerEmail;
	private String item;
	private double cost;
	
	public Order() {}
	
	public Order(String item, String email, double cost) {
//		itemList.add(item);
		this.item = item;
		this.customerEmail = email;
		this.cost = cost;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void changeCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	

	
	public String toString() {
		return "\nOrder has been placed for " + this.customerEmail
				+ ":\n" + this.item + "\nTotal: " + this.cost;
	}
	
}
