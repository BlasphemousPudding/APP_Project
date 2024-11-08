package com.carpooling.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

import com.carpooling.dao.RideDAO;
import com.carpooling.model.Ride;

@WebServlet("/myRides")
public class MyRidesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyRidesServlet doGet method called");
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        int userId = (int) session.getAttribute("userId");
        
        try {
            List<Ride> rides = RideDAO.getRidesForUser(userId);
            System.out.println("Fetched " + rides.size() + " rides for user " + userId);
            request.setAttribute("rides", rides);
            request.getRequestDispatcher("/WEB-INF/view/myRides.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error in MyRidesServlet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching rides: " + e.getMessage());
        }
    }
}
