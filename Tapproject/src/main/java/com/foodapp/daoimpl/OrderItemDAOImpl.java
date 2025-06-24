package com.foodapp.daoimpl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.OrderItem;
import com.foodapp.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	private static final String INSERT_QUERY = "INSERT INTO OrderItem (orderId, menuId, quantity, totalAmount) VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM OrderItem WHERE orderItemId = ?";
	private static final String SELECT_BY_ORDER_ID_QUERY = "SELECT * FROM OrderItem WHERE orderId = ?";
	private static final String UPDATE_QUERY = "UPDATE OrderItem SET quantity = ?, totalAmount = ? WHERE orderItemId = ?";
	private static final String DELETE_QUERY = "DELETE FROM OrderItem WHERE orderItemId = ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM OrderItem";

	@Override
	public void addOrderItem(OrderItem orderItem) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {

			stmt.setInt(1, orderItem.getOrderId());
			stmt.setInt(2, orderItem.getMenuId());
			stmt.setInt(3, orderItem.getQuantity());
			stmt.setDouble(4, orderItem.getTotalamount());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItemById(int orderItemId) {
		OrderItem orderItem = null;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

			stmt.setInt(1, orderItemId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				orderItem = extractOrderItem(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItem;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {
		List<OrderItem> orderItems = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ORDER_ID_QUERY)) {

			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				orderItems.add(extractOrderItem(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItems;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {

			stmt.setInt(1, orderItem.getQuantity());
			stmt.setDouble(2, orderItem.getTotalamount());
			stmt.setInt(3, orderItem.getOrderItemId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {

			stmt.setInt(1, orderItemId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> orderItems = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY)) {

			while (rs.next()) {
				orderItems.add(extractOrderItem(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItems;
	}

	private OrderItem extractOrderItem(ResultSet rs) throws SQLException {
		int orderItemId = rs.getInt("orderItemId");
		int orderId = rs.getInt("orderId");
		int menuId = rs.getInt("menuId");
		int quantity = rs.getInt("quantity");
		double totalAmount = rs.getDouble("totalAmount");

		return new OrderItem(orderItemId, orderId, menuId, quantity, totalAmount);
	}
}
