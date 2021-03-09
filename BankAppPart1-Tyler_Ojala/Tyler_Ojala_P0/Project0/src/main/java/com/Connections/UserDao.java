package com.Connections;

import java.util.Set;

import com.Project0.User;

public interface UserDao {
	Set<User> getUsers();
	void saveUsers(Set<User> users);
	void clear();
}
