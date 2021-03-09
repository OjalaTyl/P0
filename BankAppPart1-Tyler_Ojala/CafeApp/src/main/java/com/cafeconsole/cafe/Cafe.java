package com.cafeconsole.cafe;

import java.util.ArrayList;
import java.util.List;

import com.cafeconsole.ViewRouting;
import com.cafeconsole.models.Item;
import com.cafeconsole.models.Order;
import com.cafeconsole.models.User;
import com.cafeconsole.repo.ItemDao;
import com.cafeconsole.repo.ItemDataHandler;
import com.cafeconsole.repo.OrderDao;
import com.cafeconsole.repo.OrderDataHandler;
import com.cafeconsole.repo.UserDao;
import com.cafeconsole.repo.UserDataHandler;

public class Cafe {
	
	private static ItemDao iDao = new ItemDataHandler();
	private static UserDao uDao = new UserDataHandler();
	private static OrderDao oDao = new OrderDataHandler();
	
	User user;
	
	public void load() {
		ViewRouting.getIntroView().start();
	}
	/**
	 * Logs in the user
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean login(String email, String password) {
		//Query with Email & Password in the database
		//if true then user = ____
		
		return uDao.checkPass(email, password);
	}
	
	/**
	 * Prints the list of items on the menu
	 * @return
	 */
	public List<Item> printMenu() {
		//Query for the list of menu items
		return new ArrayList<Item>();
	}
	
	/**
	 * Retrieves the User logged in
	 * @return
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Runs a query if the user exists in the database
	 * @param username
	 * @return
	 */
	public boolean checkUsernameExists(String username) {
		if(uDao.checkEmail(username)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retrieves username/email (same thing)
	 * @return
	 */
	public String getEmail() {
		return user.getEmail();
	}
	
	/**
	 * Gets Email from database
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email) {
		if(uDao.checkEmail(email)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Queries for all the users
	 * @return
	 */
	public List<User> getUsers() {
		//Queries for all users
		return new ArrayList<User>();
	}
	
	/**
	 * Retrieves users with a custom number of orders at least
	 * @param orderNum
	 * @return
	 */
	public List<User> getUsersWithOrderCount(int orderNum) {
		//Query for all users with at least custom order count
		return new ArrayList<User>();
	}
	
	/**
	 * Retrieves users with at least 10 orders
	 * @return
	 */
	public List<User> getValuedCustomers() {
		//Query for all users with at least 10 orders
		return new ArrayList<User>();
	}
	
	/**
	 * Creates a new order
	 * @param item
	 */
	public void newOrder(Item item) {
		Order o = new Order(item.getName(), user.getEmail(), item.getCost());
		
	}
	
	/**
	 * Adds an item to the menu
	 * @param name
	 * @param cost
	 * @param isFood
	 */
	public void addItem(String name, double cost, boolean isFood) {
		Item newItem = new Item(0,name,cost,isFood);
		//INSERT newItem to the database
	}
	
	/**
	 * Returns the total number of orders in the system
	 * @param email
	 * @return
	 */
	public int numOrders() {
		return 0;
	}
	
	/**
	 * Returns the number of orders a customer has made
	 * @param email
	 * @return
	 */
	public int numOrders(String email) {
		return 0;
	}
	
	/**
	 * Retrieves an order with the given id
	 * @param id
	 * @return
	 */
	public Order getOrder(int id) {
		return new Order();
	}

	/**
	 * Creates a new signed up customer
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public User signUp(String name, String email, String password) {
		user = new User(name,email,password);
		//Save User
		return user;
	}
	
	public boolean editInfo(String field, String newInput) {
		
		return false;
	}
	
	/**
	 * Removes the customer from the database
	 * @param email
	 * @return
	 */
	public boolean removeCustomer(String email) {
		//Queries for customer, if exists, delete else return false
		return true;
	}
	
	/**
	 * Logs the customer out
	 */
	public void logout() {
		user = new User();
	}
	public void setUser(String email) {
		// TODO Auto-generated method stub
		user = uDao.getUser(email);
	}
	
	
}
