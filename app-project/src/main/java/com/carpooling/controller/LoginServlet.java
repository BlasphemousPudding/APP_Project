package com.carpooling.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.carpooling.dao.UserDAO;
import com.carpooling.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login JSP page
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the 'action' parameter to determine the request type
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            // Handle registration request
            handleUserRegistration(request, response);
        } else {
            // Handle login request
            handleUserLogin(request, response);
        }
    }

    private void handleUserRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve registration parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create a new User object with the provided parameters
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        // Attempt to register the user
        boolean registrationSuccessful = UserDAO.registerUser(newUser);

        if (registrationSuccessful) {
            // Registration successful, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login?message=Registration successful. Please log in.");
        } else {
            // Registration failed, redirect back to register page with error message
            response.sendRedirect(request.getContextPath() + "/register?error=Registration failed. Please try again.");
        }
    }

    private void handleUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve login parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate user credentials and get the userId
        Integer userId = UserDAO.validateUser(email, password);

        if (userId != null) {
            // Login successful, store userId in session
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("userEmail", email);

            // Redirect to the dashboard page
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Login failed, redirect back to login page with error message
            response.sendRedirect(request.getContextPath() + "/login?error=Invalid email or password");
        }
    }
}
