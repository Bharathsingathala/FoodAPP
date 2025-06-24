package com.tap.Lanch;
import java.util.Scanner;

import com.foodapp.daoimpl.OrderDAOImpl;
import com.foodapp.model.Order;


public class OrderLaunch {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Fill the Below Order Details");
		System.out.println("-----------------------------");
		System.out.println();

		System.out.print("Enter User ID: ");
		int userId = scan.nextInt();

		System.out.print("Enter Restaurant ID: ");
		int restaurantId = scan.nextInt();

		System.out.print("Enter Total Amount: ");
		double totalAmount = scan.nextDouble();

		scan.nextLine();

		System.out.print("Enter Order Status (e.g., Pending, Completed): ");
		String status = scan.nextLine();

		System.out.print("Enter Payment Method (e.g., Card, Cash): ");
		String paymentMethod = scan.nextLine();

		Order order = new Order(userId, restaurantId, totalAmount, status, paymentMethod);

		OrderDAOImpl dao = new OrderDAOImpl();
		dao.addOrder(order);

		System.out.println();

		System.out.println("Order placed successfully!");

		scan.close();
	}
}
