package com.carpooling.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import com.carpooling.dao.RideDAO;
import com.carpooling.model.Ride;
import com.carpooling.util.LogUtil;
import com.google.gson.Gson;

@WebServlet("/createRide")
@MultipartConfig
public class CreateRideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/createRideForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CreateRideServlet doPost method called");
        response.setContentType("application/json");
        HttpSession session = request.getSession(false);

        LogUtil.log(Level.INFO, "Received a POST request to CreateRideServlet");
        LogUtil.log(Level.INFO, "Request Content Type: " + request.getContentType());

        if (session == null || session.getAttribute("userId") == null) {
            LogUtil.log(Level.WARNING, "User not logged in");
            sendJsonResponse(response, false, "Please login first");
            return;
        }

        try {
            String origin = request.getParameter("origin");
            String destination = request.getParameter("destination");
            String dateTimeString = request.getParameter("date_time");
            String availableSeatsString = request.getParameter("available_seats");
            String priceString = request.getParameter("price");

            LogUtil.log(Level.INFO, "Received parameters:");
            LogUtil.log(Level.INFO, "origin: " + origin);
            LogUtil.log(Level.INFO, "destination: " + destination);
            LogUtil.log(Level.INFO, "date_time: " + dateTimeString);
            LogUtil.log(Level.INFO, "available_seats: " + availableSeatsString);
            LogUtil.log(Level.INFO, "price: " + priceString);

            if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
                LogUtil.log(Level.WARNING, "Date and time is empty or null");
                throw new IllegalArgumentException("Date and time must be provided");
            }

            int driverId = (int) session.getAttribute("userId");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            int availableSeats = Integer.parseInt(availableSeatsString);
            double price = Double.parseDouble(priceString);
            String status = "scheduled"; // Default status

            Ride ride = new Ride(driverId, origin, destination, dateTime, availableSeats, price, status);
            LogUtil.log(Level.INFO, "Ride object created: " + ride);

            if (RideDAO.createRide(ride)) {
                LogUtil.log(Level.INFO, "Ride created successfully, redirecting to /myRides");
                response.sendRedirect(request.getContextPath() + "/myRides");
            } else {
                LogUtil.log(Level.WARNING, "Failed to create ride in database");
                request.setAttribute("error", "Failed to create ride in database");
                request.getRequestDispatcher("/WEB-INF/view/createRideForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            LogUtil.log(Level.SEVERE, "Exception in CreateRideServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/createRideForm.jsp").forward(request, response);
        }
    }

    private void sendJsonResponse(HttpServletResponse response, boolean success, String message) throws IOException {
        String json = gson.toJson(new JsonResponse(success, message));
        response.getWriter().write(json);
    }

    private static class JsonResponse {
        private final boolean success;
        private final String message;

        JsonResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        @SuppressWarnings("unused")
        public boolean isSuccess() {
            return success;
        }

        @SuppressWarnings("unused")
        public String getMessage() {
            return message;
        }
    }
}
