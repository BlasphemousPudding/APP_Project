package com.carpooling.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/rideManagement", "/admin", "/review"})
public class ViewController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        String view = "";
        switch (path) {
            case "/rideManagement":
                view = "/WEB-INF/view/rideManagement.jsp";
                break;
            case "/admin":
                view = "/WEB-INF/view/admin.jsp";
                break;
            case "/review":
                view = "/WEB-INF/view/review.jsp";
                break;
            default:
                view = "/WEB-INF/view/index.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
