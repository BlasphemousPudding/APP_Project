package com.carpooling.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.carpooling.dao.RideDAO;

@WebServlet("/confirmRide")
public class ConfirmRideServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rideId = Integer.parseInt(request.getParameter("rideId"));
        
        boolean success = RideDAO.updateRideStatus(rideId, "Confirmed");
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        
        if (success) {
            out.print(gson.toJson(new JsonResponse(true, "Ride confirmed successfully", request.getContextPath() + "/dashboard")));
        } else {
            out.print(gson.toJson(new JsonResponse(false, "Failed to confirm ride", null)));
        }
        out.flush();
    }
    
    private static class JsonResponse {
        boolean success;
        String message;
        String redirectUrl;
        
        JsonResponse(boolean success, String message, String redirectUrl) {
            this.success = success;
            this.message = message;
            this.redirectUrl = redirectUrl;
        }
    }
}
