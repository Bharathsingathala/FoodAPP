package com.tap;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foodapp.dao.OrderDAO;
import com.foodapp.daoimpl.OrderDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	private OrderDAO orderDAO = new OrderDAOImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

		if (user == null || cart == null || cart.isEmpty()) {
			response.sendRedirect("login.jsp");
			return;
		}

		String paymentMethod = request.getParameter("paymentMethod");
		String address = request.getParameter("address");

		int userId = user.getUserId();
		int restaurantId = cart.values().iterator().next().getMenu().getRestaurantId();
		double totalAmount = cart.values().stream().mapToDouble(item -> item.getMenu().getPrice() * item.getQuantity())
				.sum();

		Order order = new Order(userId, restaurantId, totalAmount, "Pending", paymentMethod);

		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem item : cart.values()) {
			OrderItem orderItem = new OrderItem(0, 0, item.getMenu().getMenuId(), item.getQuantity(),
					item.getMenu().getPrice() * item.getQuantity());
			orderItems.add(orderItem);
		}

		orderDAO.addOrder(order); // Save order & items to DB

		// 1. Pass required attributes to request (BEFORE clearing the session cart)
		request.setAttribute("cart", cart);
		request.setAttribute("order", order);
		request.setAttribute("address", address);
		request.setAttribute("paymentMethod", paymentMethod);

		// Forward to confirmation page
		request.getRequestDispatcher("order-confirmation.jsp").forward(request, response);
	}
}
