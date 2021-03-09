package com.cafeconsole.repo;

import com.cafeconsole.models.Item;

public interface ItemDao {
	void getItem(String name);

	void getItem(int id);
	
	void getItems();
	
	void updateItems(Item i);
	
	void deleteItem(int id);
	
	void deleteItem(String name);
	
	void deleteItem(Item item);
}
