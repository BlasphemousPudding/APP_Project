package com.carpooling.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.carpooling.dao.RideDAO;
import com.carpooling.model.Ride;

@WebServlet("/viewRideMap")
public class ViewRideMapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rideId = Integer.parseInt(request.getParameter("rideId"));
        Ride ride = RideDAO.getRideById(rideId);
        
        if (ride != null) {
            // Ensure that ride.status is set correctly
            System.out.println("Ride status: " + ride.getStatus()); // Add this line for debugging
            request.setAttribute("ride", ride);
            request.getRequestDispatcher("/WEB-INF/view/rideMap.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ride not found");
        }
    }
}
