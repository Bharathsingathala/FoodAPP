package com.tap;

import java.io.IOException;

import com.foodapp.dao.UserDAO;
import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserDAO userDAO = new UserDAOImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usernameOrEmail = request.getParameter("usernameOrEmail");
		String password = request.getParameter("password");

		// Attempt login
		User user = userDAO.login(usernameOrEmail, password);

		if (user != null) {
			// Set session attributes
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// Optional: Set last login date update logic here (if needed)

			// Redirect to main page
			response.sendRedirect("first");
		} else {
			// Login failed – send error back to login.jsp
			request.setAttribute("error", "Invalid username/email or password.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}

