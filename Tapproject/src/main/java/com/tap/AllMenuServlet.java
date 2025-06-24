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

@WebServlet("/all-menus")
public class AllMenuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        MenuDAOImpl dao = new MenuDAOImpl();
        List<Menu> allMenus = dao.getAllMenuItems();

        req.setAttribute("menuList", allMenus);

        // Remove specific restaurantId from session if needed
        req.getSession().removeAttribute("restaurantId");

        req.getRequestDispatcher("Menu.jsp").forward(req, resp);
    }
}