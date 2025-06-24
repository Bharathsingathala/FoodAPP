package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.foodapp.dao.EmployeeDAO;
import com.foodapp.model.Employee;
import com.foodapp.util.DBConnection;

public class EmployeeDAOimpl implements EmployeeDAO {

	private final String INSERT = "INSERT INTO users (fullname, username, phone_number, email, password, confirm_password) VALUES (?, ?, ?, ?, ?, ?)";
	private final String CHECK_EXIST = "SELECT * FROM users WHERE email = ? OR username = ?";

	@Override
	public boolean addEmployee(Employee e) {
		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				System.err.println("Database connection failed.");
				return false;
			}

			// Check if user already exists
			try (PreparedStatement checkStmt = connection.prepareStatement(CHECK_EXIST)) {
				checkStmt.setString(1, e.getEmail());
				checkStmt.setString(2, e.getUsername());

				ResultSet rs = checkStmt.executeQuery();

				if (rs.next()) {
					System.out.println("User already registered with email or username.");
					return false; // Condition: user already exists
				}
			}

			// Insert new user
			try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
				preparedStatement.setString(1, e.getFullname());
				preparedStatement.setString(2, e.getUsername());
				preparedStatement.setString(3, e.getPhoneNumber());
				preparedStatement.setString(4, e.getEmail());
				preparedStatement.setString(5, e.getPassword());
				preparedStatement.setString(6, e.getConfirmPassword());

				int i = preparedStatement.executeUpdate();
				if (i > 0) {
					System.out.println("User registered successfully.");
					return true;
				} else {
					System.out.println("Failed to register user.");
					return false;
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
