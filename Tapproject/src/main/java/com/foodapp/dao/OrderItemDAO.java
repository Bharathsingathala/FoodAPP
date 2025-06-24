package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.OrderItem;

public interface OrderItemDAO {

	void addOrderItem(OrderItem orderItem);

	OrderItem getOrderItemById(int orderItemId);

	List<OrderItem> getOrderItemsByOrderId(int orderId);

	void updateOrderItem(OrderItem orderItem);

	void deleteOrderItem(int orderItemId);

	List<OrderItem> getAllOrderItems();
}
