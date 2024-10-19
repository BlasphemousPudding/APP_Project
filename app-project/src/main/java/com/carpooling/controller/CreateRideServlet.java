package com.carpooling.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.carpooling.dao.RideDAO;  // Import the RideDAO class
import com.carpooling.model.Ride; 

@WebServlet("/createRide") // URL mapping for the servlet
public class CreateRideServlet extends HttpServlet {

    // Add doGet method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the createRide.jsp page
        request.getRequestDispatcher("/WEB-INF/view/createRide.jsp").forward(request, response);
    }

    // Existing doPost method to handle POST requests for creating a ride
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form parameters from the request
            String origin = request.getParameter("origin");
            String destination = request.getParameter("destination");
            String dateTime = request.getParameter("date_time");
            int availableSeats = Integer.parseInt(request.getParameter("available_seats"));
            String comments = request.getParameter("comments");
            double originLat = Double.parseDouble(request.getParameter("origin_latitude"));
            double originLng = Double.parseDouble(request.getParameter("origin_longitude"));
            double destLat = Double.parseDouble(request.getParameter("destination_latitude"));
            double destLng = Double.parseDouble(request.getParameter("destination_longitude"));

            // Retrieve the driverId from session (ensure session has the userId attribute)
            Object userIdObj = request.getSession().getAttribute("userId");
            if (userIdObj == null) {
                response.sendRedirect("login.jsp?error=Please%20login%20first");
                return;
            }
            int driverId = (int) userIdObj;

            // Create a new Ride object and save it in the database
            Ride ride = new Ride(driverId, origin, destination, dateTime, availableSeats, comments, originLat, originLng, destLat, destLng);
            boolean isCreated = RideDAO.createRide(ride);

            // Redirect to appropriate page based on success or failure
            if (isCreated) {
                response.sendRedirect("dashboard.jsp?message=Ride%20created%20successfully");
            } else {
                response.sendRedirect("createRide.jsp?error=Failed%20to%20create%20ride");
            }
        } catch (NumberFormatException e) {
            // Handle any number format exceptions for parsing integers or doubles
            response.sendRedirect("createRide.jsp?error=Invalid%20input%20format.%20Please%20check%20your%20inputs.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("createRide.jsp?error=An%20unexpected%20error%20occurred.%20Please%20try%20again.");
        }
    }
}
