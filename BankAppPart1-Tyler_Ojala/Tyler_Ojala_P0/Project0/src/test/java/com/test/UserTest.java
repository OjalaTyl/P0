package com.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.Project0.User;

public class UserTest {

	static User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User("Test", "Test", 0, "Test", "Test");
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("Test", user.getUsername());
	}

	@Test
	public void testSetUsername() {
		user.setUsername("Test2");
		assertEquals("Test2", user.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("Test", user.getPassword());
	}

	@Test
	public void testSetPassword() {
		user.setPassword("Test2");
		assertEquals("Test2", user.getPassword());
	}
	
	@Test
	public void testGetAdminLevel() {
		assertEquals(0, user.getAdminLevel());
	}

	@Test
	public void testSetAdminLevel() {
		user.setAdminLevel(1);
		assertEquals(1, user.getAdminLevel());
	}
	
	@Test
	public void testEquals() {
		assertTrue(user.equals(new User("Test", "Test", 0, "Test", "Test")));
	}
	
	@Test
	public void testGetPersonalInfo() {
		assertEquals("Name:Test, Address:Test", user.getPersonalInfo());
	}
}
