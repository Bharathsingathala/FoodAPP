package com.tap.Lanch;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

public class UserLaunch {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Fill the Below Details");
		System.out.println("----------------------");
		System.out.println();

		System.out.print("Enter the name: ");
		String name = scan.nextLine();

		System.out.print("Enter the Username: ");
		String username = scan.nextLine();

		System.out.print("Enter the Password: ");
		String password = scan.nextLine();

		System.out.print("Enter the email: ");
		String email = scan.nextLine();

		System.out.print("Enter the phonenumber: ");
		String phone = scan.nextLine();

		System.out.print("Enter the address: ");
		String address = scan.nextLine();

		System.out.print("Enter the role: ");
		String role = scan.nextLine();

		User user = new User( name, username, password, email, phone, address, role);

		UserDAOImpl dao = new UserDAOImpl();

		dao.addUser(user);

		System.out.println();
		System.out.println("User registered successfully!");

		System.out.println(user);

		scan.close();

	}
}
