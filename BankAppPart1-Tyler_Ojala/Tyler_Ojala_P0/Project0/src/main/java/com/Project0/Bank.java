package com.Project0;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import com.Connections.AccountDataHandler;
import com.Connections.UserDataHandler;

public class Bank {
	private Set<Account> listOfAccessibleAccounts = new HashSet<Account>();
	private Set<User> listOfUsers = new HashSet<User>();
	private Set<Account> listOfPendingAccounts = new HashSet<Account>();
	private User currentUser;
	private Set<Account> listOfAccounts = new HashSet<Account>();
	ConsoleHandler handler;
	private String userFilePath = "C:\\Users\\Yler\\Documents\\Revature Training\\Projects\\Project 0\\Project0\\src\\main\\resources\\users.dat";
	private String accountsFilePath = "C:\\Users\\Yler\\Documents\\Revature Training\\Projects\\Project 0\\Project0\\src\\main\\resources\\accounts.dat";
	private String pendingAccountsFilePath = "C:\\Users\\Yler\\Documents\\Revature Training\\Projects\\Project 0\\Project0\\src\\main\\resources\\pendingAccounts.dat";
	private UserDataHandler userHandler = new UserDataHandler();
	private AccountDataHandler accountHandler = new AccountDataHandler();
	//Constructor for bank. Calls the load method to populate lists.
	public Bank() {
		
		load();
	}
	
	//Gets the admin level of the current user.
	public int getAdminLevel() {
		return currentUser.getAdminLevel();
	}
	
	//Moves the account with the given account number from the listOfPendingAccounts to listOfAccounts. It then refreshes accessibility.
	public boolean approveAccount(int accountNumber) {
		for (Account acc : listOfPendingAccounts) {
			if (acc.equals(new Account(accountNumber))) {
				listOfAccounts.add(acc);
				listOfPendingAccounts.remove(acc);
				refreshAccessibility(currentUser);
				save();
				return true;
			}
		}
		return false;
	}
	
	//Removes the given account from listOfPendingAccounts
	public boolean denyAccount(int accountNumber) {
		for (Account acc : listOfPendingAccounts) {
			if (acc.equals(new Account(accountNumber))) {
				listOfPendingAccounts.remove(acc);
				save();
				return true;
			}
		}
		return false;
	}

	//Adds a new user to the bank system. Can only add customers not employees or admins.
	public boolean addUser(String username, String password, String name, String address) {
		User check = findUser(username);
		if (check == null) {
			check = findUser(username);
			if (check == null) {
				listOfUsers.add(new User(username, password, 0, name, address));
				save();
				return true;
			}
		}
		return false;
	}

	//Searches and returns the user with the given username.
	public User findUser(String username) {
		for (User user : listOfUsers) {
			if (user.equals(new User(username))) {
				return user;
			}
		}
		return null;
	}

	//Removes the account from listOfAccounts with the given account number.
	public boolean closeAccount(int accountNumber) {
		for (Account acc : listOfAccessibleAccounts) {
			if (acc.equals(new Account(accountNumber))) {
				listOfAccounts.remove(acc);
				refreshAccessibility(currentUser);
				save();
				return true;
			}
		}
		return false;
	}

	//Searches for and returns the account with the given account number
	public Account findAccount(int accountNumber) {
		for (Account acc : listOfAccessibleAccounts) {
			if (acc.equals(new Account(accountNumber))) {
				return acc;
			}
		}
		return null;
	}
	
	//Searches all possible accounts for the given account number and returns that account.
	public Account findOtherAccount(int accountNumber) {
		for (Account acc : listOfAccounts) {
			if (acc.equals(new Account(accountNumber))) {
				return acc;
			}
		}
		return null;
	}
	
	//Searches for a pending account with the given account number.
	public Account findPendingAccount(int accountNumber) {
		for (Account acc : listOfPendingAccounts) {
			if (acc.equals(new Account(accountNumber))) {
				return acc;
			}
		}
		return null;
	}

	//Returns the balance of the given account number
	public double getAccountBalance(int accountNumber) {
		if (findAccount(accountNumber) == null)
			return -1;
		else
			return findAccount(accountNumber).getBalance();
	}
	
	//Adds an account to the set of pending accounts.
	public boolean requestAccount(int accountNumber) {
		Account check = findAccount(accountNumber);
		if (check == null) {
			check = findPendingAccount(accountNumber);
			if (check == null) {
				listOfPendingAccounts.add(new Account(currentUser, 0.0, accountNumber));
				save();
				return true;
			}
		}
		return false;
	}
	
	//Adds an account with a co-owner to the set of pending accounts.
	public boolean requestAccount(User coOwner, int accountNumber) {
		Account check = findAccount(accountNumber);
		if (check == null) {
			check = findPendingAccount(accountNumber);
			if (check == null) {
				listOfPendingAccounts.add(new Account(currentUser, coOwner, 0.0, accountNumber));
				save();
				return true;
			}
		}
		return false;
	}

	//Withdraws and amount from a given account number
	public boolean withdraw(int accountNumber, double amount) {
		Account account = findAccount(accountNumber);
		if (account != null) {
			return account.withdraw(amount);
		}
		else
			return false;
	}

	//Deposits an amount to a given account number
	public boolean deposit(int accountNumber, double amount) {
		Account account = findAccount(accountNumber);
		if (account != null) {
			return account.deposit(amount);
		}
		else
			return false;
	}

	//Returns the account numbers the current user has access to.
	public String getAccountNumbers() {
		String toReturn = "";
		for (Account acc : listOfAccessibleAccounts) {
			toReturn += acc.getAccountNumber() + "\n";
		}
		return toReturn;
	}

	//Transfers funds from one account to another. Returns if it was successful or not.
	public boolean transfer(int sourceAccount, int destinationAccount, double amount) {
		Account source = findAccount(sourceAccount);
		Account destination = findOtherAccount(destinationAccount);
		if (source != null && destination != null) {
			if (source.withdraw(amount)) {
				destination.deposit(amount);
				save();
				return true;
			}
		}
		return false;
	}

	//Returns the info of the given user.
	public String getUserInfo(String username) {
		User check = findUser(username);
		if (check == null)
			return null;
		return check.getPersonalInfo();
	}

	//Returns the account numbers of all accounts in the set.
	public String getAllAccountNumbers() {
		String toReturn = "";
		for (Account acc : listOfAccounts) {
			toReturn += acc.getAccountNumber() + "\n";
		}
		return toReturn;
	}
	
	//Returns the account numbers in the pending set.
	public String getPendingAccountNumbers() {
		String toReturn = "";
		for (Account acc : listOfPendingAccounts) {
			toReturn += acc.getAccountNumber() + "\n";
		}
		return toReturn;
	}

	//Sets the accessible accounts based on the admin level of the given user.
	public void refreshAccessibility(User user) {
		listOfAccessibleAccounts = new HashSet<Account>();
		if (user.getAdminLevel() < 2) {
			for (Account acc : listOfAccounts) {
				if (acc.isOwnedBy(user.getUsername())) {
					listOfAccessibleAccounts.add(acc);
				}
			}
		} else {
			listOfAccessibleAccounts = listOfAccounts;
		}

	}

	//Logs in, returns false if given info is incorrect.
	public boolean login(String username, String password) {
		for (User user : listOfUsers) {
			if (user.equals(new User(username))) {
				if (user.getPassword().equals(password)) {
					currentUser = user;
					refreshAccessibility(currentUser);
					return true;
				}
			}
		}
		return false;
	}

	//Returns the username of the current user.
	public String getCurrentUsername() {
		return currentUser.getUsername();
	}

	//Saves the sets to files.
	public void save() {
		userHandler.clear();
		userHandler.saveUsers(listOfUsers);
		accountHandler.clear();
		writeUsersToFile(listOfUsers, userFilePath);
		accountHandler.saveAccounts(listOfAccounts);
		writeAccountsToFile(listOfAccounts, accountsFilePath);
		accountHandler.savePendingAccounts(listOfPendingAccounts);
		writeAccountsToFile(listOfPendingAccounts, pendingAccountsFilePath);
	}
	
	//Loads the sets from files.
	public void load() {
		listOfUsers = userHandler.getUsers();
		listOfAccounts = accountHandler.getAccounts();
		listOfPendingAccounts = accountHandler.getPendingAccounts();
	}

	//Writes a set of users to a file.
	public void writeUsersToFile(Set<User> list, String file) {
		ObjectOutputStream outStream = null;
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(file));
			for (User u : list) {
				outStream.writeObject(u);
			}

		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("Invalid input.");
		} finally {
			try {
				if (outStream != null)
					outStream.close();
			} catch (IOException ioException) {
				System.err.println("Error closing file.");
			}
		}
	}

	//Writes a set of accounts to a file.
	public void writeAccountsToFile(Set<Account> list, String file) {
		ObjectOutputStream outStream = null;
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(file));
			for (Account a : list) {
				outStream.writeObject(a);
			}

		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("Invalid input.");
		} finally {
			try {
				if (outStream != null)
					outStream.close();
			} catch (IOException ioException) {
				System.err.println("Error closing file.");
			}
		}
	}

	//Uses serializable to get a set of accounts from a given file.
	public Set<Account> readAccountsFromFile(String file) {
		Set<Account> set = new HashSet<Account>();
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				Account a = (Account) inputStream.readObject();
				set.add(a);
			}
		} catch (EOFException eofException) {
			return set;
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Object creation failed.");
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException ioException) {
				System.err.println("Error closing file.");
			}
		}
		return set;
	}

	//Uses serializable to get a set of users from a given file.
	public Set<User> readUsersFromFile(String file) {
		Set<User> set = new HashSet<User>();
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				User u = (User) inputStream.readObject();
				set.add(u);
			}
		} catch (EOFException eofException) {
			return set;
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Object creation failed.");
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException ioException) {
				System.err.println("Error closing file.");
			}
		}
		return set;
	}

	
	//Returns a string of all usernames.
	public String getAllUsers() {
		String toReturn = "";
		for (User user : listOfUsers) {
			toReturn += user.getUsername() + "\n";
		}
		return toReturn;
	}
}
