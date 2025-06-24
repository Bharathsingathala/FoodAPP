package com.tap;


import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import com.foodapp.dao.UserDAO;
import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String role = request.getParameter("role");

		if (name == null || username == null || email == null || password == null || phone == null || address == null
				|| role == null || name.trim().isEmpty() || username.trim().isEmpty() || email.trim().isEmpty()
				|| password.trim().isEmpty()) {

			response.sendRedirect("registration.jsp?message=Please fill in all required fields");
			return;
		}

		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setAddress(address);
		user.setRole(role);
		user.setCreateDate(Timestamp.from(Instant.now()));
		user.setLastLoginDate(null); // Initially null

		boolean success = userDAO.addUser(user);

		if (success) {
			response.sendRedirect("registration.jsp?message=Registration successful! Please login.");
		} else {
			response.sendRedirect("registration.jsp?message=Username or email already exists");
		}
	}
}