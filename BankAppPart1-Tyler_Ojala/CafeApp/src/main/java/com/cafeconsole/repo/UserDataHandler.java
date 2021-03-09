package com.cafeconsole.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cafeconsole.models.User;

public class UserDataHandler implements UserDao {

	@Override
	public void addUser(String name, String email, String password) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO cafe_user (username, email, is_employee, password) values" + "(?,?,?,?);";

		try (Connection conn = Connector.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setBoolean(3, false);
			ps.setString(4, password);

			ps.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO cafe_user (username, email, is_employee, password) values" + "(?,?,?,?);";

		try (Connection conn = Connector.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setBoolean(3, user.isEmployee());
			ps.setString(4, user.getPassword());

			ps.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkName(String email, String name) {
		// TODO Auto-generated method stub
		try (Connection conn = Connector.getConnection()) {
			String sql = " SELECT * FROM cafe_user WHERE email = ? AND username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, name);

			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		try (Connection conn = Connector.getConnection()) {
			String sql = "SELECT * FROM cafe_user WHERE email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(2).equals(email)) {
					return true;
				}
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return false;
	}

	@Override
	public boolean checkPass(String email, String pass) {
		// TODO Auto-generated method stub
		try (Connection conn = Connector.getConnection()) {
			String sql = " SELECT * FROM cafe_user WHERE email = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public List<User> getValuedCustomers() {
		//TODO do the whole thing
		return null;
	}

	@Override
	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		Connection conn = Connector.getConnection();

		String sql = "DELETE FROM cafe_user where email = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = Connector.getConnection();

		String sql = "DELETE FROM cafe_user where email = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User user) {
		changeName(user.getEmail(), user.getName());
		changePass(user.getEmail(), user.getPassword());
	}

	@Override
	public void changeName(String email, String name) {
		Connection conn = Connector.getConnection();

		String sql = "UPDATE cafe_user set username = ? where email = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void changePass(String email, String password) {
		Connection conn = Connector.getConnection();

		String sql = "UPDATE cafe_user set password = ? where email = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, email);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = " SELECT * FROM cafe_user WHERE email = ?"; // sql statement

		try (Connection conn = Connector.getConnection()) {
			PreparedStatement s = conn.prepareStatement(sql); // Allows us to send SQL statements

			ResultSet rs = s.executeQuery(sql); // We are executing our SQL statement

			while (rs.next()) {
				users.add(new User(rs.getString("username"), rs.getString("email"), rs.getString("password"),
						rs.getBoolean("is_employee")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return users;
	}

	@Override
	public User getUser(String email) {
		User user = null;
		String sql = " SELECT * FROM cafe_user Where email = ?"; // sql statement

		try (Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql); // Allows us to send SQL statements

			ps.setString(1, email);
			ps.execute();
			ResultSet rs = ps.executeQuery(); // We are executing our SQL statement

			while (rs.next()) {
				user = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"),
						rs.getBoolean("is_employee"));
			}

			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return user;
	}

	@Override
	public User getUser(User user) {
		return user;
	}

}
