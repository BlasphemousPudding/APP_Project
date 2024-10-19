package com.carpooling.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.carpooling.dao.UserDAO;
import com.carpooling.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // doPost method to handle user registration requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user inputs from the registration form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check if any field is empty and forward back to the registration page with an error
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            return;
        }

        // Create a new User object with the provided data
        User user = new User(name, email, password);

        // Check if the user already exists in the database
        if (UserDAO.userExists(email)) {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            return;
        }

        // Register the user using the UserDAO and handle success or failure
        boolean isRegistered = UserDAO.registerUser(user);
        if (isRegistered) {
            // Registration successful, redirect to login page
            response.sendRedirect("login.jsp?success=Registration%20successful!%20Please%20login.");
        } else {
            // Registration failed, forward back to the registration page with an error
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }
    }

    // doGet method to handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
    }
}
