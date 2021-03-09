package com.cafeconsole.models;

import java.util.UUID;

public class Item {
//	UUID id;
	int id;
	private String name;
	private double cost;
	private boolean isFood = true;
	
	public Item(int id, String name, double cost, boolean isFood) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.isFood = isFood;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public boolean isFood() {
		return isFood;
	}
	public void setFood(boolean isFood) {
		this.isFood = isFood;
	}

	public int getId() {
		return id;
	}
}
