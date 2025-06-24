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

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO = new UserDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp?message=Please+login+first");
			return;
		}

		User user = (User) session.getAttribute("user");

		// Get updated values
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		// Update user object
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);

		// Update in database
		userDAO.updateUser(user);

		// Refresh the session object with latest DB data (optional but recommended)
		User updatedUser = userDAO.getUserById(user.getUserId());
		session.setAttribute("user", updatedUser); // match key used in login and profile.jsp

		// Redirect back to profile page
		response.sendRedirect("profile.jsp");
	}
}
