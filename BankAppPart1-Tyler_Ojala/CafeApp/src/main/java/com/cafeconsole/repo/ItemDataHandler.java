package com.cafeconsole.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cafeconsole.cafe.Cafe;
import com.cafeconsole.models.Item;

public class ItemDataHandler implements ItemDao {

	
	@Override
	public void getItem(String name) {
		// TODO Auto-generated method stub
		Cafe cafe = new Cafe();
		
		String sql = "SELECT * FROM cafe_items WHERE item_name = ?";
		
		try (Connection conn = Connector.getConnection()){

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cafe.addItem(rs.getString("item_name"), rs.getDouble("cost"), rs.getBoolean("is_food"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	

	@Override
	public void getItem(int id) {
		// TODO Auto-generated method stub
		Cafe cafe = new Cafe();
		
		String sql = "SELECT * FROM cafe_items WHERE item_id = ?";
		
		try (Connection conn = Connector.getConnection()){

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cafe.addItem(rs.getString("item_name"), rs.getDouble("cost"), rs.getBoolean("is_food"));
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

	@Override
	public void getItems() {
		// TODO Auto-generated method stub
		Cafe cafe = new Cafe();
		
		String sql = "SELECT * FROM cafe_items";
		
		try (Connection conn = Connector.getConnection()){

			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				cafe.addItem(rs.getString("item_name"), rs.getDouble("cost"), rs.getBoolean("is_food"));
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

	@Override
	public void updateItems(Item i) {
		// TODO Auto-generated method stub
			try(Connection conn = Connector.getConnection()){
			
			String sql = "UPDATE cafe_items SET item_name = ?, cost = ?, is_food = ? WHERE item_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i.getId());
			ps.setString(4, i.getName());
			ps.setDouble(3, i.getCost());
			ps.setBoolean(4, i.isFood());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		Connection conn = Connector.getConnection();
		
		String sql = "DELETE FROM planets where item_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteItem(String name) {
		Connection conn = Connector.getConnection();
		
		String sql = "DELETE FROM planets where item_name = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void deleteItem(Item item) {
		// TODO Auto-generated method stub
		Connection conn = Connector.getConnection();
		
		String sql = "DELETE FROM planets where item_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, item.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
