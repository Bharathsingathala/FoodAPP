package com.tap.Lanch;

	import java.util.Scanner;

import com.foodapp.daoimpl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

	public class RestaurantLaunch {

		public static void main(String[] args) {

			Scanner scan = new Scanner(System.in);

			System.out.println("Fill the Below Restaurant Details");
			System.out.println();

			System.out.print("Enter the Restaurant Name: ");
			String name = scan.nextLine();

			System.out.print("Enter the address: ");
			String address = scan.nextLine();

			System.out.print("Enter the phone number: ");
			String phonenumber = scan.nextLine();

			System.out.print("Enter the cuisine: ");
			String cuisine = scan.nextLine();

			System.out.print("Enter the delivery time (in minutes): ");
			int deliveryTime = scan.nextInt();

			System.out.print("Enter the rating (0 to 5): ");
			double rating = scan.nextDouble();

			System.out.print("Enter the adminUserId: ");
			int adminUserId = scan.nextInt();

			scan.nextLine();

			System.out.print("Enter the isActive (true or false): ");
			boolean isActive = scan.nextBoolean();

			scan.nextLine();

			System.out.print("Enter the image path: ");
			String imagepath = scan.nextLine();

			Restaurant restaurant = new Restaurant(name, address, phonenumber, cuisine, deliveryTime, adminUserId, rating,
					isActive, imagepath);

			RestaurantDAOImpl dao = new RestaurantDAOImpl();
			dao.addRestaurant(restaurant);

			System.out.println();

			System.out.println("Restaurant added successfully!");

			System.out.println();

			System.out.println(restaurant);

			scan.close();
		}
	}
