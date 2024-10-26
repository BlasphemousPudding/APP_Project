package com.carpooling.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.carpooling.dao.RideDAO;
import com.carpooling.model.Ride;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from the session and handle if it's not present
        Object userIdObj = request.getSession().getAttribute("userId");
        
        // Check if userId is null (user is not logged in or session expired)
        if (userIdObj == null) {
            // Redirect to login page if the userId is not found in session
            response.sendRedirect("login.jsp?error=Please%20login%20first");
            return;
        }
        
        // Cast userIdObj to int
        int driverId = (int) userIdObj;

        // Fetch rides created by the logged-in user
        List<Ride> rides = RideDAO.getRidesByDriverId(driverId);

        // Pass rides to the JSP page for display
        request.setAttribute("rides", rides);

        // Forward the request to dashboard.jsp
        request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request, response);
    }
}