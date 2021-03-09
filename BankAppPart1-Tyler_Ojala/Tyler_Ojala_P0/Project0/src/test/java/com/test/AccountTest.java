package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.Project0.Account;
import com.Project0.User;

public class AccountTest {


	static User user;
	static Account account;
	
	@Before
	public void setUp() throws Exception {
		user = new User("Test", "Test", 0, "Test", "Test");
		account = new Account(user, 50, 1);
	}

	@Test 
	public void testWithdraw() {
		assertTrue(account.withdraw(25));
		assertEquals(25.0, account.getBalance(),0);
	}
	
	@Test 
	public void testDeposit() {
		assertTrue(account.deposit(25));
		assertEquals(75.0, account.getBalance(),0);
	}

	
	@Test
	public void testGetAccountNumber() {
		assertEquals(1, account.getAccountNumber());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(50.0, account.getBalance(),0);
	}
	
	@Test
	public void testToString() {
		assertEquals("Username:Test, Balance:50.0, Account Number:1", account.toString());
	}
	
	@Test
	public void testIsOwnedBy() {
		assertTrue(account.isOwnedBy("Test"));
	}
	
	@Test
	public void testEquals() {
		assertTrue(account.equals(new Account(1)));
	}
}
