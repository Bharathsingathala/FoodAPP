package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;
import com.foodapp.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static final String INSERT_RESTAURANT_QUERY = "INSERT INTO Restaurant (name, address, phonenumber, cuisine, deliveryTime, adminUserId, rating, isActive, imagepath) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String GET_RESTAURANT_QUERY = "SELECT * FROM Restaurant WHERE restaurantId = ?";
	private static final String UPDATE_RESTAURANT_QUERY = "UPDATE Restaurant SET name = ?, address = ?, phonenumber = ?, cuisine = ?, deliveryTime = ?, adminUserId = ?, rating = ?, isActive = ?, imagepath = ? WHERE restaurantId = ?";
	private static final String DELETE_RESTAURANT_QUERY = "DELETE FROM Restaurant WHERE restaurantId = ?";
	private static final String GET_ALL_RESTAURANTS_QUERY = "SELECT * FROM Restaurant";

	// ADD RESTAURANT
	@Override
	public void addRestaurant(Restaurant restaurant) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT_QUERY)) {

			preparedStatement.setString(1, restaurant.getName());
			preparedStatement.setString(2, restaurant.getAddress());
			preparedStatement.setString(3, restaurant.getPhonenumber());
			preparedStatement.setString(4, restaurant.getCuisine());
			preparedStatement.setInt(5, restaurant.getDeliveryTime());
			preparedStatement.setInt(6, restaurant.getAdminUserId());
			preparedStatement.setDouble(7, restaurant.getRating());
			preparedStatement.setBoolean(8, restaurant.isActive());
			preparedStatement.setString(9, restaurant.getImagepath());

			int res = preparedStatement.executeUpdate();
			System.out.println("Rows affected: " + res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GET RESTAURANT
	@Override
	public Restaurant getRestaurantById(int restaurantId) {
		Restaurant restaurant = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_QUERY)) {

			preparedStatement.setInt(1, restaurantId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				restaurant = extractRestaurant(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurant;
	}

	// UPDATE RESTAURANT
	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY)) {

			preparedStatement.setString(1, restaurant.getName());
			preparedStatement.setString(2, restaurant.getAddress());
			preparedStatement.setString(3, restaurant.getPhonenumber());
			preparedStatement.setString(4, restaurant.getCuisine());
			preparedStatement.setInt(5, restaurant.getDeliveryTime());
			preparedStatement.setInt(6, restaurant.getAdminUserId());
			preparedStatement.setDouble(7, restaurant.getRating());
			preparedStatement.setBoolean(8, restaurant.isActive());
			preparedStatement.setString(9, restaurant.getImagepath());
			preparedStatement.setInt(10, restaurant.getRestaurantId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DELETE RESTAURANT
	@Override
	public void deleteRestaurant(int restaurantId) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY)) {

			preparedStatement.setInt(1, restaurantId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// GET ALL RESTAURANTS
	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurantsList = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(GET_ALL_RESTAURANTS_QUERY)) {

			while (resultSet.next()) {
				Restaurant restaurant = extractRestaurant(resultSet);
				restaurantsList.add(restaurant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantsList;
	}

	// EXTRACT RESTAURANT DETAILS
	private Restaurant extractRestaurant(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("restaurantId");
		String name = resultSet.getString("name");
		String address = resultSet.getString("address");
		String phonenumber = resultSet.getString("phonenumber");
		String cuisine = resultSet.getString("cuisine");
		int deliveryTime = resultSet.getInt("deliveryTime");
		int adminUserId = resultSet.getInt("adminUserId");
		double rating = resultSet.getDouble("rating");
		boolean isActive = resultSet.getBoolean("isActive");
		String imagepath = resultSet.getString("imagepath");

		return new Restaurant(id, name, address, phonenumber, cuisine, deliveryTime, adminUserId, rating, isActive,
				imagepath);
	}

}