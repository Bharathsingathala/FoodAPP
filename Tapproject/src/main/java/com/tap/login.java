package com.tap;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class login  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
	
		
		RequestDispatcher rd = req.getRequestDispatcher("Register");
		
		rd.include(req, reps);
		
		
		
		
		
		
	}
	
	
	
	
	
}
