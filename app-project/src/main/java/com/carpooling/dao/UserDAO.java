package com.carpooling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.carpooling.model.User;
import com.carpooling.util.DatabaseUtil;

public class UserDAO {

    // Method to hash the password using SHA-256
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to check if a user exists by email
    public static boolean userExists(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            String query = "SELECT * FROM users WHERE email = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            return rs.next(); // Return true if a user with the given email is found
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResources(conn, pstmt, rs);
        }
        return false; // Return false if an exception occurred or no user is found
    }

    // Method to register a new user in the database
    public static boolean registerUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String query = "INSERT INTO users (name, email, password, role, profile_picture) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, hashPassword(user.getPassword()));
            pstmt.setString(4, user.getRole() != null ? user.getRole() : "rider");
            pstmt.setString(5, user.getProfilePicture());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResources(conn, pstmt, null);
        }
        return false;
    }

    // Modified validateUser method to return user_id instead of boolean
    public static Integer validateUser(String email, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer userId = null; // Declare userId as Integer to store user_id from the database

        try {
            conn = DatabaseUtil.getConnection();
            String query = "SELECT user_id FROM users WHERE email = ? AND password = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, hashPassword(password));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retrieve the user_id from the result set if the credentials are valid
                userId = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResources(conn, pstmt, rs);
        }

        return userId; // Return the userId if valid, otherwise return null
    }

    // Method to register a new user using Google OAuth
    public static boolean registerGoogleUser(String name, String email, String googleId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String query = "INSERT INTO users (name, email, google_id, role) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, googleId);
            pstmt.setString(4, "rider");

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResources(conn, pstmt, null);
        }
        return false;
    }

    // Method to validate a Google user using their Google ID
    public static boolean validateGoogleUser(String googleId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            String query = "SELECT * FROM users WHERE google_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, googleId);
            rs = pstmt.executeQuery();
            return rs.next(); // Return true if the Google user is found
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResources(conn, pstmt, rs);
        }
        return false;
    }

    // Method to update the profile picture of a user
    public static boolean updateProfilePicture(int userId, String profilePictureUrl) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String query = "UPDATE users SET profile_picture = ? WHERE user_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profilePictureUrl);
            pstmt.setInt(2, userId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResources(conn, pstmt, null);
        }
        return false;
    }
}
