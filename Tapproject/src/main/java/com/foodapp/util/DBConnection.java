package com.foodapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String url = "jdbc:mysql://localhost:3306/foodapp";
	private static final String un = "root";
	private static final String pwd = "Bharath@1603";
	private static Connection Connection = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection = DriverManager.getConnection(url, un, pwd);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Connection;
	}
}