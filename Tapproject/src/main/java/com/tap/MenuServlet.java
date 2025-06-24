package com.tap;

import java.io.IOException;
import java.util.List;

import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String restIdStr = req.getParameter("restaurantId");
		if (restIdStr == null || restIdStr.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing restaurantId");
			return;
		}

		int restId = Integer.parseInt(restIdStr);

		// ✅ Store restaurantId in session so cart.jsp can access it later
		HttpSession session = req.getSession();
		session.setAttribute("restaurantId", restId);

		MenuDAOImpl daoImpl = new MenuDAOImpl();
		List<Menu> menuList = daoImpl.getAllMenuByRestaurant(restId);

		req.setAttribute("menuList", menuList);
		req.getRequestDispatcher("Menu.jsp").forward(req, resp);
	}
}