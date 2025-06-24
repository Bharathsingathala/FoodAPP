package com.tap.Lanch;

import java.util.Scanner;

import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;

public class MenuLaunch {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Menu Details:");
		System.out.println("-------------------");
		System.out.println();

		System.out.print("Enter Restaurant ID: ");
		int restaurantId = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Item Name: ");
		String itemName = scanner.nextLine();

		System.out.print("Enter Description: ");
		String description = scanner.nextLine();

		System.out.print("Enter Price: ");
		double price = scanner.nextDouble();

		System.out.print("Is Available (true/false): ");
		boolean isAvailable = scanner.nextBoolean();
		scanner.nextLine();

		System.out.print("Enter Image Path: ");
		String imagePath = scanner.nextLine();

		System.out.print("Enter Rating (0 to 5): ");
		double rating = scanner.nextDouble();

		Menu menu = new Menu(0, restaurantId, itemName, description, price, isAvailable, imagePath, rating);

		MenuDAOImpl dao = new MenuDAOImpl();
		dao.addMenu(menu);

		System.out.println();

		System.out.println("Menu item added successfully!");

		scanner.close();
	}
}
