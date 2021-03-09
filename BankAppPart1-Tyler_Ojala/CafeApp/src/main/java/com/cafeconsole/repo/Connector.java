package com.cafeconsole.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	// "jdbc:postgresql://[ENDPOINT]/[DATABASE]
	private static final String URL = "jdbc:postgresql://localhost/postgres";
	private static final String USERNAME = "cafe_employee";
	private static final String PASSWORD = "employ33";

	private static Connection conn;

	public static Connection getConnection() {

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
