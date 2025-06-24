package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Order;

public interface OrderDAO {

	void addOrder(Order order);

	Order getOrderById(int orderId);

	void updateOrder(Order order);

	void deleteOrder(int orderId);

	List<Order> getAllOrders();
}
