package com.tap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.foodapp.dao.MenuDAO;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	private MenuDAO menuDAO = new MenuDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}

		if ("view".equals(action) || action == null) {
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		} else if ("remove".equals(action)) {
			int menuId = Integer.parseInt(request.getParameter("menuId"));
			cart.remove(menuId);
			response.sendRedirect(request.getContextPath() + "/cart?action=view");
		} else if ("add".equals(action)) {
			// You can remove this GET add to rely only on POST, better practice
			int menuId = Integer.parseInt(request.getParameter("menuId"));
			Menu menu = menuDAO.getMenu(menuId);
			if (menu != null) {
				cart.compute(menuId, (k, v) -> {
					if (v == null)
						return new CartItem(menu, 1);
					v.setQuantity(v.getQuantity() + 1);
					return v;
				});
			}
			response.sendRedirect(request.getContextPath() + "/cart?action=view");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}

		if (action == null) {
			response.sendRedirect(request.getContextPath() + "/cart?action=view");
			return;
		}

		int menuId = Integer.parseInt(request.getParameter("menuId"));

		switch (action) {
		case "add":
			Menu menuToAdd = menuDAO.getMenu(menuId);
			if (menuToAdd != null) {
				cart.compute(menuId, (k, v) -> {
					if (v == null)
						return new CartItem(menuToAdd, 1);
					v.setQuantity(v.getQuantity() + 1);
					return v;
				});
			}
			break;

		case "decrease":
			if (cart.containsKey(menuId)) {
				CartItem item = cart.get(menuId);
				int qty = item.getQuantity();
				if (qty <= 1) {
					cart.remove(menuId);
				} else {
					item.setQuantity(qty - 1);
				}
			}
			break;

		case "remove":
			cart.remove(menuId);
			break;
		}

		response.sendRedirect(request.getContextPath() + "/cart?action=view");
	}
}

