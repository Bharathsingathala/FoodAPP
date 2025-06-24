package com.foodapp.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private int orderID;
	private int userId;
	private int restaurantId;
	private Timestamp orderDate;
	private double totalAmount;
	private String status;
	private String paymentMethod;
	private List<OrderItem> orderItems;

	public Order(int userId, int restaurantId, double totalAmount, String status, String paymentMethod) {
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.orderDate = new Timestamp(System.currentTimeMillis());
		this.orderItems = new ArrayList<>();
	}

	public Order(int orderID, int userId, int restaurantId, Timestamp orderDate, double totalAmount, String status,
			String paymentMethod) {
		this.orderID = orderID;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.orderItems = new ArrayList<>();
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getUserId() {
		return userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userId=" + userId + ", restaurantId=" + restaurantId + ", orderDate="
				+ orderDate + ", totalAmount=" + totalAmount + ", status=" + status + ", paymentMethod=" + paymentMethod
				+ ", orderItems=" + orderItems + "]";
	}
}
