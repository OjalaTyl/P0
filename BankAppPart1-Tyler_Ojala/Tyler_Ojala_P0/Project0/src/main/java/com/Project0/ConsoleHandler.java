package com.Project0;

import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConsoleHandler {
	private Bank bank;
	private Scanner sc = new Scanner(System.in);
	final static Logger logg = Logger.getLogger(ConsoleHandler.class);

	// Constructor makes a new bank then calls login.
	public ConsoleHandler() {
		bank = new Bank();
	}

	// Method that starts the loop.
	public void run() {
		while (goToInterface()) {

		}
	}

	// Enters the interface for the logged in user based on their admin level.
	private boolean goToInterface() {
		login();
		int adminLevel = bank.getAdminLevel();
		switch (adminLevel) {
		case 0:
			return customerLoop();

		case 1:
			return employeeLoop();

		case 2:
			return adminLoop();
		}
		return false;
	}

	// Reads the inputs for and call the operations for employees.
	private boolean employeeLoop() {
		boolean running = true;
		String command;
		while (running) {
			System.out.print("Please enter a command: ");
			command = sc.next().toLowerCase();
			switch (command) {
			default:
				System.out.println("Invalid Command");
				break;
			case "deposit":
				deposit();
				break;

			case "withdraw":
				withdraw();
				break;

			case "transfer":
				transfer();
				break;

			case "requestaccount":
				requestAccount();
				break;

			case "requestjointaccount":
				requestJointAccount();
				break;

			case "approveaccount":
				approveAccount();
				break;

			case "denyaccount":
				denyAccount();
				break;

			case "viewaccountinfo":
				viewAccountInfo();
				break;

			case "viewuserinfo":
				viewUserInfo();
				break;

			case "viewaccountbalance":
				veiwAccountBalance();
				break;

			case "logout":
				return true;

			case "exit":
				running = false;
				System.out.println("exiting");
				break;
			}
		}
		return false;
	}

	// Reads the inputs for and call the operations for admins.
	private boolean adminLoop() {
		boolean running = true;
		String command;
		while (running) {
			System.out.print("Please enter a command: ");
			command = sc.next().toLowerCase();
			switch (command) {
			default:
				System.out.println("Invalid Command");
				break;
			case "deposit":
				deposit();
				break;

			case "withdraw":
				withdraw();
				break;

			case "transfer":
				transfer();
				break;

			case "requestaccount":
				requestAccount();
				break;

			case "requestjointaccount":
				requestJointAccount();
				break;

			case "approveaccount":
				approveAccount();
				break;

			case "denyaccount":
				denyAccount();
				break;

			case "cancelaccount":
				cancelAccount();
				break;

			case "viewaccountinfo":
				viewAccountInfo();
				break;

			case "viewuserinfo":
				viewUserInfo();
				break;

			case "viewaccountbalance":
				veiwAccountBalance();
				break;

			case "help":
				System.out.println(
						"The list of available commands are: \ndeposit\nwithdraw\ntransfer\nrequestaccount\nrequestjointaccount"
								+ "\napproveaccount\ndenyaccount\ncancelaccount\nviewaccountinfo\nviewuserinfo\nviewaccountbalance\nlogout\nexit");
				break;

			case "logout":
				return true;

			case "exit":
				running = false;
				System.out.println("exiting");
				break;
			}
		}
		return false;
	}

	// Shows the account balance of a given account the current user has access to.
	private void veiwAccountBalance() {
		System.out.println("Please input an account number to view. \nThe list of used numbers are:");
		System.out.println(bank.getAccountNumbers());
		try {
			int accNumber = sc.nextInt();
			double balance = bank.getAccountBalance(accNumber);
			if (balance < 0) {
				System.out.println("That account is not available");
			} else
				System.out.println("The balance is $" + balance);

		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Shows the user info of a given username.
	private void viewUserInfo() {
		System.out.println("Please input a username to view. \nThe list of usernames are:");
		System.out.println(bank.getAllUsers());
		String output = bank.getUserInfo(sc.next());
		if (output == null) {
			System.out.println("That username does not exist");
		} else
			System.out.println(output);
	}

	// Shows the info of a given account.
	private void viewAccountInfo() {
		System.out.println("Please input an account number to approve. \nThe list of used numbers are:");
		System.out.println(bank.getAllAccountNumbers());
		try {
			int accNumber = sc.nextInt();
			Account acc = bank.findAccount(accNumber);
			if (acc == null) {
				System.out.println("That account does not exist");
			} else
				System.out.println(acc);
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Deletes an account from the system.
	private void cancelAccount() {
		System.out.println("Please input an account number to delete. \nThe list of used numbers are:");
		System.out.println(bank.getAllAccountNumbers());
		try {
			int accNumber = sc.nextInt();
			if (bank.closeAccount(accNumber)) {
				System.out.println("Successfully closed.");
			} else
				System.out.println("Close not successful check the given number.");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Denies a pending account.
	private void denyAccount() {
		System.out.println("Please input an account number to deny. \nThe list of used numbers are:");
		System.out.println(bank.getPendingAccountNumbers());
		try {
			int accNumber = sc.nextInt();
			if (bank.denyAccount(accNumber)) {
				System.out.println("Successfully denied.");
			} else
				System.out.println("Denial not successful check the given number.");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.next();
		}
	}

	// Approves a pending account.
	private void approveAccount() {
		System.out.println("Please input an account number to approve. \nThe list of pending accounts are:");
		System.out.println(bank.getPendingAccountNumbers());
		try {
			int accNumber = sc.nextInt();
			if (bank.approveAccount(accNumber)) {
				System.out.println("Successfully approved.");
			} else
				System.out.println("Approval not successful check the given number.");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Reads the inputs for and call the operations for customers.
	private boolean customerLoop() {
		boolean running = true;
		String command;
		while (running) {
			System.out.print("Please enter a command: ");
			command = sc.next().toLowerCase();
			switch (command) {
			default:
				System.out.println("Invalid Command");
				break;
			case "deposit":
				deposit();
				break;

			case "withdraw":
				withdraw();
				break;

			case "transfer":
				transfer();
				break;

			case "requestaccount":
				requestAccount();
				break;

			case "requestjointaccount":
				requestJointAccount();
				break;

			case "viewaccountbalance":
				veiwAccountBalance();
				break;

			case "help":
				System.out.println(
						"The list of available commands are: \ndeposit\nwithdraw\ntransfer\nrequestaccount\nrequestjointaccount"
								+ "\nviewaccountbalance\nexit");
				break;

			case "logout":
				return true;

			case "exit":
				running = false;
				System.out.println("exiting");
				break;
			}
		}
		return false;
	}

	// Adds an account to the pending set with a co-owner.
	private void requestJointAccount() {
		System.out.println("What is the username of the co-owner?");
		String username = sc.next();
		User coOwner = bank.findUser(username);
		while (coOwner == null) {
			System.out.println("Co-owner is not in the system please input a new user.");
			username = sc.next();
			coOwner = bank.findUser(username);
		}
		System.out.println("Please input an account number. \nThe list of used numbers are:");
		System.out.println(bank.getAllAccountNumbers());
		try {
			int accNumber = sc.nextInt();
			if (bank.requestAccount(coOwner, accNumber)) {
				System.out.println("Request successful waiting approval.");
			} else
				System.out.println("Request not successful there may be a request with that number");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Adds an account to the pending set.
	private void requestAccount() {
		System.out.println("Please input an account number. \nThe list of used numbers are:");
		System.out.println(bank.getAllAccountNumbers());
		try {
			int accNumber = sc.nextInt();
			if (bank.requestAccount(accNumber)) {
				System.out.println("Request successful waiting approval.");
			} else
				System.out.println("Request not successful there may be a request with that number");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Calls the transfer method of the bank.
	private void transfer() {
		System.out.println("Please input the account to transfer from. \nYour accounts are:");
		System.out.println(bank.getAccountNumbers());
		try {
			int source = sc.nextInt();
			System.out.println("Please input the account to transfer to. \nPossible accounts are:");
			System.out.println(bank.getAllAccountNumbers());
			int destination = sc.nextInt();
			System.out.println("How much would you like to transfer?");
			double d = Double.parseDouble(sc.next());
			while (d <= 0) {
				System.out.println("Please enter an amount greater than 0.");
				d = Double.parseDouble(sc.next());
			}
			if (bank.transfer(source, destination, d)) {
				logg.setLevel(Level.ALL);
				logg.info(
						bank.getCurrentUsername() + " has transfered " + d + " from " + source + " to " + destination);
				System.out.println("Transfer successful. Your current balance is $" + bank.getAccountBalance(source));
				bank.save();
			} else
				System.out.println(
						"Transfer failed, make sure that you choose the right account and use a valid amount.");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Calls the withdraw method of the bank.
	public void withdraw() {
		System.out.println("Please input the account to withdraw from. \nYour accounts are:");
		System.out.println(bank.getAccountNumbers());
		try {
			int s = sc.nextInt();
			System.out.println("How much would you like to withdraw?");
			double d = Double.parseDouble(sc.next());
			while (d <= 0) {
				System.out.println("Please enter an amount greater than 0.");
				d = Double.parseDouble(sc.next());
			}
			if (bank.withdraw(s, d)) {
				logg.setLevel(Level.ALL);
				logg.info(bank.getCurrentUsername() + " has withdrawn " + d + " from " + s);
				System.out.println("Withdraw successful. Your current balance is $" + bank.getAccountBalance(s));
				bank.save();
			} else
				System.out.println(
						"Withdraw failed, make sure that you choose the right account and use a valid amount.");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Calls the deposit method of the bank.
	public void deposit() {
		System.out.println("Please input the account to deposit to. \nYour accounts are:");
		System.out.println(bank.getAccountNumbers());
		try {
			int s = sc.nextInt();
			System.out.println("How much would you like to deposit?");
			double d = Double.parseDouble(sc.next());
			while (d <= 0) {
				System.out.println("Please enter an amount greater than 0.");
				d = Double.parseDouble(sc.next());
			}
			if (bank.deposit(s, d)) {
				logg.setLevel(Level.ALL);
				logg.info(bank.getCurrentUsername() + " has deposited " + d + " to " + s);
				System.out.println("Deposit successful. Your current balance is $" + bank.getAccountBalance(s));
				bank.save();
			} else
				System.out
						.println("Deposit failed, make sure that you choose the right account and use a valid amount.");
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
			if(sc.hasNext())
				sc.nextLine();
		}
	}

	// Logs in to the system. Will not exit until login is successful.
	public void login() {
		System.out.print("Please enter your username or enter \"register\" to make a new account: ");
		String username = sc.next();
		if (username.equals("register")) {
			register();
			return;
		}
		System.out.print("Please enter your password: ");
		String password = sc.next();
		while (!bank.login(username, password)) {
			System.out.println("Incorrect credentials please try again.");
			System.out.print("Please enter your username: ");
			username = sc.next();
			System.out.print("Please enter your password: ");
			password = sc.next();
		}
		System.out.println("Login successful!");
	}

	// Registers a new user to the system. Calls addUser in bank.
	private void register() {
		String username;
		String password;
		String name;
		String address = "";
		System.out.print("Please enter a username ");
		username = sc.next();
		System.out.print("Please enter a password ");
		password = sc.next();
		System.out.print("Please enter your name ");
		name = sc.next();
		System.out.print("Please enter your address ");
		while (address.equals(""))
			address = sc.nextLine();
		if (bank.addUser(username, password, name, address)) {
			System.out.println("Registration successful");
			bank.login(username, password);
			System.out.println("Logged in as " + username);
		} else
			System.out.println("Registration failed, username was taken");
	}
}
