package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;
import com.foodapp.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	private static final String INSERT_QUERY = "INSERT INTO Menu (restaurantId, itemName, description, price, isAvailable, imagePath, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM Menu WHERE menuId = ?";
	private static final String UPDATE_QUERY = "UPDATE Menu SET itemName = ?, description = ?, price = ?, isAvailable = ?, imagePath = ?, rating = ? WHERE menuId = ?";
	private static final String DELETE_QUERY = "DELETE FROM Menu WHERE menuId = ?";
	private static final String SELECT_ALL = "SELECT * FROM Menu";
	private static final String SELECT_BY_RESTAURANT = "SELECT * FROM Menu WHERE restaurantId = ?";

	// ADD MENU ITEM
	@Override
	public void addMenu(Menu menu) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {

			stmt.setInt(1, menu.getRestaurantId());
			stmt.setString(2, menu.getItemName());
			stmt.setString(3, menu.getDescription());
			stmt.setDouble(4, menu.getPrice());
			stmt.setBoolean(5, menu.isAvailable());
			stmt.setString(6, menu.getImagePath());
			stmt.setDouble(7, menu.getRating());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GET MENU ITEM BY ID
	@Override
	public Menu getMenu(int menuId) {
		Menu menu = null;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, menuId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				menu = extractMenu(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menu;
	}

	// ALIAS: GET MENU BY ID
	@Override
	public Menu getMenuById(int menuId) {
		Menu menu = null;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM menu WHERE menuId = ?")) {

			stmt.setInt(1, menuId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				menu = new Menu(rs.getInt("menuId"), rs.getString("itemName"), rs.getString("description"),
						rs.getDouble("price"), rs.getBoolean("isAvailable"), rs.getString("imagePath"),
						rs.getDouble("rating"), rs.getInt("restaurantId"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	// UPDATE MENU ITEM
	@Override
	public void updateMenu(Menu menu) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {

			stmt.setString(1, menu.getItemName());
			stmt.setString(2, menu.getDescription());
			stmt.setDouble(3, menu.getPrice());
			stmt.setBoolean(4, menu.isAvailable());
			stmt.setString(5, menu.getImagePath());
			stmt.setDouble(6, menu.getRating());
			stmt.setInt(7, menu.getMenuId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DELETE MENU ITEM
	@Override
	public void deleteMenu(int menuId) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {

			stmt.setInt(1, menuId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GET ALL MENU ITEMS
	@Override
	public List<Menu> getAllMenuItems() {
		List<Menu> list = new ArrayList<>();

		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL)) {

			while (rs.next()) {
				list.add(extractMenu(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// GET ALL MENU ITEMS BY RESTAURANT ID
	@Override
	public List<Menu> getAllMenuByRestaurant(int restaurantId) {
		List<Menu> menuList = new ArrayList<>();

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_RESTAURANT)) {

			stmt.setInt(1, restaurantId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				menuList.add(extractMenu(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}

	// EXTRACT MENU FROM RESULTSET
	private Menu extractMenu(ResultSet res) throws SQLException {
		int menuId = res.getInt("menuId");
		int restaurantId = res.getInt("restaurantId");
		String itemName = res.getString("itemName");
		String description = res.getString("description");
		double price = res.getDouble("price");
		boolean isAvailable = res.getBoolean("isAvailable");
		String imagePath = res.getString("imagePath");
		double rating = res.getDouble("rating");

		return new Menu(menuId, restaurantId, itemName, description, price, isAvailable, imagePath, rating);
	}


}