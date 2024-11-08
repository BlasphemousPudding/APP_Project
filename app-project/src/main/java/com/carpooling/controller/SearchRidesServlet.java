package com.carpooling.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.carpooling.model.Ride;
import com.carpooling.dao.RideDAO;

@WebServlet("/searchAvailableRides")
public class SearchRidesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ride> availableRides = RideDAO.getAvailableRides();
        request.setAttribute("rides", availableRides);
        request.getRequestDispatcher("/WEB-INF/view/searchRides.jsp").forward(request, response);
    }
}
