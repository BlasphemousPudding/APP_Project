package com.carpooling.controller;

import jakarta.servlet.ServletException;  // Change from javax.servlet to jakarta.servlet
import jakarta.servlet.annotation.WebServlet;  // Change from javax.servlet to jakarta.servlet
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test")  // URL pattern for accessing the servlet
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h1>Hello from TestServlet using Jakarta!</h1>");
    }
}
