package com.tap.Lanch;
import java.util.Scanner;

import com.foodapp.dao.EmployeeDAO;
import com.foodapp.daoimpl.EmployeeDAOimpl;
import com.foodapp.model.Employee;

public class RegLaunch {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Fill the Employee Details");
		System.out.println("-------------------------");
		System.out.println();

		System.out.print("Enter full name: ");
		String fullname = scan.nextLine();

		System.out.print("Enter username: ");
		String username = scan.nextLine();

		System.out.print("Enter phone number: ");
		String phoneNumber = scan.nextLine();

		System.out.print("Enter email: ");
		String email = scan.nextLine();

		System.out.print("Enter password: ");
		String password = scan.nextLine();

		System.out.print("Confirm password: ");
		String confirmPassword = scan.nextLine();

		Employee employee = new Employee(fullname, username, phoneNumber, email, password, confirmPassword);

		EmployeeDAO dao = new EmployeeDAOimpl();
		dao.addEmployee(employee);

		System.out.println();
		System.out.println("Employee registered successfully!");
		System.out.println(employee);

		scan.close();
	}

}
