package com.foodapp.daoimpl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderDAO;
import com.foodapp.model.Order;
import com.foodapp.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

	private static final String INSERT_QUERY = "INSERT INTO orders (userId, restaurantId, orderDate, totalAmount, status, paymentMethod) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM orders WHERE orderId = ?";
	private static final String UPDATE_QUERY = "UPDATE orders SET userId = ?, restaurantId = ?, orderDate = ?, totalAmount = ?, status = ?, paymentMethod = ? WHERE orderId = ?";
	private static final String DELETE_QUERY = "DELETE FROM orders WHERE orderId = ?";
	private static final String SELECT_ALL = "SELECT * FROM orders";

	@Override
	public void addOrder(Order order) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {

			stmt.setInt(1, order.getUserId());
			stmt.setInt(2, order.getRestaurantId());
			stmt.setTimestamp(3, order.getOrderDate());
			stmt.setDouble(4, order.getTotalAmount());
			stmt.setString(5, order.getStatus());
			stmt.setString(6, order.getPaymentMethod());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order getOrderById(int orderId) {
		Order order = null;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				order = extractOrder(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {

			stmt.setInt(1, order.getUserId());
			stmt.setInt(2, order.getRestaurantId());
			stmt.setTimestamp(3, order.getOrderDate());
			stmt.setDouble(4, order.getTotalAmount());
			stmt.setString(5, order.getStatus());
			stmt.setString(6, order.getPaymentMethod());
			stmt.setInt(7, order.getOrderID());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(int orderId) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {

			stmt.setInt(1, orderId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL)) {

			while (rs.next()) {
				orders.add(extractOrder(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	private Order extractOrder(ResultSet rs) throws SQLException {
		int orderId = rs.getInt("orderId");
		int userId = rs.getInt("userId");
		int restaurantId = rs.getInt("restaurantId");
		Timestamp orderDate = rs.getTimestamp("orderDate");
		double totalAmount = rs.getDouble("totalAmount");
		String status = rs.getString("status");
		String paymentMethod = rs.getString("paymentMethod");

		return new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMethod);
	}
}
