package com.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.Project0.User;

public class UserDataHandler implements UserDao{

	@Override
	public Set<User> getUsers() {
		Set<User> users = new HashSet<User>();
		String sql = " SELECT * FROM bank_user"; // sql statement

		try (Connection conn = Connector.getConnection()) {
			Statement s = conn.createStatement(); // Allows us to send SQL statements

			ResultSet rs = s.executeQuery(sql); // We are executing our SQL statement

			while (rs.next()) {
				users.add(new User(rs.getString("username"), rs.getString("user_password"), rs.getInt("admin_level"),rs.getString("actual_name"),rs.getString("address")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return users;
	}

	@Override
	public void saveUsers(Set<User> users) {
		String sql = "INSERT INTO bank_user (username, user_password, admin_level, actual_name, address) values" + "(?,?,?,?,?);";
		Iterator<User> it = users.iterator();
		
		try (Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			while(it.hasNext())
			{
				User user = it.next();
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getAdminLevel());
				ps.setString(4, user.name);
				ps.setString(5, user.address);

			ps.execute();
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		Connection conn = Connector.getConnection();

		String sql = "TRUNCATE bank_user";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
