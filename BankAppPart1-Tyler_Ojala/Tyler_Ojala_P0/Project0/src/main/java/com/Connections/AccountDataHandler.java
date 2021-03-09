package com.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.Project0.Account;


public class AccountDataHandler implements AccountDao{

	@Override
	public Set<Account> getAccounts() {
		Set<Account> accounts = new HashSet<Account>();
		String sql = " SELECT * FROM bank_account"; // sql statement

		try (Connection conn = Connector.getConnection()) {
			Statement s = conn.createStatement(); // Allows us to send SQL statements

			ResultSet rs = s.executeQuery(sql); // We are executing our SQL statement

			while (rs.next()) {
				accounts.add(new Account(rs.getString("account_owner"), rs.getString("co_owner"), rs.getDouble("balance"),rs.getInt("account_number")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return accounts;
	}

	@Override
	public void saveAccounts(Set<Account> accounts) {
		String sql = "INSERT INTO bank_account (account_owner, co_owner, balance, account_number) values" + "(?,?,?,?);";
		Iterator<Account> it = accounts.iterator();
		
		try (Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			while(it.hasNext())
			{
				Account account = it.next();
				ps.setString(1, account.getOwner());
				ps.setString(2, account.getCoOwner());
				ps.setDouble(3, account.getBalance());
				ps.setInt(4, account.getAccountNumber());

			ps.execute();
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<Account> getPendingAccounts() {
		Set<Account> accounts = new HashSet<Account>();
		String sql = " SELECT * FROM pending_bank_account"; // sql statement

		try (Connection conn = Connector.getConnection()) {
			Statement s = conn.createStatement(); // Allows us to send SQL statements

			ResultSet rs = s.executeQuery(sql); // We are executing our SQL statement

			while (rs.next()) {
				accounts.add(new Account(rs.getString("account_owner"), rs.getString("co_owner"), rs.getDouble("balance"),rs.getInt("account_number")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return accounts;
	}

	@Override
	public void savePendingAccounts(Set<Account> accounts) {
		String sql = "INSERT INTO pending_bank_account (account_owner, co_owner, balance, account_number) values" + "(?,?,?,?);";
		Iterator<Account> it = accounts.iterator();
		
		try (Connection conn = Connector.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			while(it.hasNext())
			{
				Account account = it.next();
				ps.setString(1, account.getOwner());
				ps.setString(2, account.getCoOwner());
				ps.setDouble(3, account.getBalance());
				ps.setInt(4, account.getAccountNumber());

			ps.execute();
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		Connection conn = Connector.getConnection();

		String sql = "TRUNCATE bank_account";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			
			sql = "TRUNCATE pending_bank_account";
			ps = conn.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
