package com.foodapp.model;

public class OrderItem {

	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private double totalamount;

	public OrderItem() {
	}

	public OrderItem(int orderId, int menuId, int quantity, double totalamount) {
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalamount = totalamount;
	}

	public OrderItem(int orderItemId, int orderId, int menuId, int quantity, double totalamount) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalamount = totalamount;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId + ", quantity="
				+ quantity + ", totalamount=" + totalamount + "]";
	}

}
