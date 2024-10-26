package com.carpooling.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String servletName = (String) request.getAttribute("jakarta.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");

        System.out.println("Error details:");
        System.out.println("Status code: " + statusCode);
        System.out.println("Servlet name: " + servletName);
        System.out.println("Request URI: " + requestUri);
        if (throwable != null) {
            System.out.println("Exception: " + throwable.getMessage());
            throwable.printStackTrace();
        }

        response.setContentType("text/html");
        response.getWriter().write("<html><body><h2>An error occurred</h2><p>Please check server logs for details.</p></body></html>");
    }
}
