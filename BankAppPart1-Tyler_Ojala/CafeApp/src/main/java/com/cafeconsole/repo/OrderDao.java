package com.cafeconsole.repo;

import com.cafeconsole.models.Order;
import com.cafeconsole.models.User;

public interface OrderDao {
	void getOrder(User user);
	void getAllOrders();
	void countOrders(String email);
	void addOrder(Order o);
	void addOrder(String email, String item, double cost);
	void deleteOrder(Order o);
	void deleteOrder(String email);
}
