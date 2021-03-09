package com.Project0;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private User owner;
	private User coOwner;
	double balance;
	int accountNumber;

	//Account constructor that has a co-owner.
	public Account(User owner, User coOwner, double balance, int accountNumber) {
		super();
		this.owner = owner;
		this.coOwner = coOwner;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	//Constructor for an account without a co-owner.
	public Account(User owner, double balance, int accountNumber) {
		super();
		this.owner = owner;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	//Constructor for an account with just an account number. Used to check if an account with the given number exists already.
	public Account(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Account(String owner, String coOwner, double balance, int accountNumber) {
		super();
		this.owner = new User(owner);
		if(coOwner != null)
			this.coOwner = new User(coOwner);
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	//Withdraws from the balance. Cannot make balance negative.
	public boolean withdraw(double amount) {

		if (amount > balance)
			return false;

		balance -= amount;
		return true;
	}

	//Deposits into the balance. 
	public boolean deposit(double amount) {
		balance += amount;
		return true;
	}

	//Returns the account number.
	public int getAccountNumber() {
		return this.accountNumber;
	}

	//Returns the balance.
	public double getBalance() {
		return this.balance;
	}

	//Returns info as a string.
	@Override
	public String toString() {
		if (coOwner == null)
			return "Username:" + owner + ", Balance:" + balance + ", Account Number:" + accountNumber;
		else
			return "Username:" + owner + ", Coowner:" + coOwner + ", Balance:" + balance + ", Account Number:" + accountNumber;
	}

	//Compares two accounts returns true if the account numbers are equal
	public boolean equals(Account arg0) {
		if (this.accountNumber == arg0.accountNumber) {
			return true;
		} else {
			return false;
		}
	}

	//Returns true if the account is owned by the given username.
	public boolean isOwnedBy(String username) {
		if (coOwner != null)
			return (username.equals(owner.getUsername()) || username.equals(coOwner.getUsername()));
		else
			return username.equals(owner.getUsername());
	}

	public String getOwner() {
		return owner.getUsername();
	}

	public String getCoOwner() {
		if(coOwner != null)
			return coOwner.getUsername();
		return null;
	}


}
