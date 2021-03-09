package com.Connections;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	// "jdbc:postgresql://[ENDPOINT]/[DATABASE]
	private static final String URL = "jdbc:postgresql://database-2.clfxfw4urext.us-east-2.rds.amazonaws.com/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Wyvernjack44";

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
