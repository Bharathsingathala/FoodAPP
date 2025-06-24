package com.tap.Lanch;

import java.util.Scanner;

import com.foodapp.daoimpl.OrderItemDAOImpl;
import com.foodapp.model.OrderItem;


public class OrderItemLaunch {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Fill the Below Order Item Details");
		System.out.println("----------------------------------");
		System.out.println();

		System.out.print("Enter Order ID: ");
		int orderId = scan.nextInt();

		System.out.print("Enter Menu ID: ");
		int menuId = scan.nextInt();

		System.out.print("Enter Quantity: ");
		int quantity = scan.nextInt();

		System.out.print("Enter Total Amount: ");
		double totalAmount = scan.nextDouble();

		OrderItem orderItem = new OrderItem(orderId, menuId, quantity, totalAmount);

		OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();
		orderItemDAO.addOrderItem(orderItem);

		System.out.println();

		System.out.println("Order item added successfully!");

		scan.close();
	}
}
