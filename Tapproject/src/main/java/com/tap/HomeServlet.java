package com.tap;

import java.io.IOException;
import java.util.List;

import com.foodapp.daoimpl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/first")
public class HomeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RestaurantDAOImpl dao = new RestaurantDAOImpl();

		List<Restaurant> allRestaurants = dao.getAllRestaurants();

		for (Restaurant restaurant : allRestaurants) {
			System.out.println(restaurant);
		}
		
		
		req.setAttribute("allRestaurants", allRestaurants);
		RequestDispatcher rd = req.getRequestDispatcher("First.jsp");
		rd.forward(req, resp);

	}

}
