package com.carpooling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.carpooling.model.Ride;
import com.carpooling.util.DatabaseUtil;
import com.carpooling.util.LogUtil;

public class RideDAO {
    // Create a new ride
    public static boolean createRide(Ride ride) {
        String query = "INSERT INTO rides (driver_id, origin, destination, date_time, available_seats, price, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            LogUtil.log(Level.INFO, "Preparing to insert ride: " + ride);
            
            pstmt.setInt(1, ride.getDriverId());
            pstmt.setString(2, ride.getOrigin());
            pstmt.setString(3, ride.getDestination());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(ride.getDateTime()));
            pstmt.setInt(5, ride.getAvailableSeats());
            pstmt.setDouble(6, ride.getPrice());
            pstmt.setString(7, ride.getStatus());

            LogUtil.log(Level.INFO, "Executing SQL: " + pstmt);
            
            int rowsAffected = pstmt.executeUpdate();
            LogUtil.log(Level.INFO, "Rows affected: " + rowsAffected);
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            LogUtil.log(Level.SEVERE, "Error creating ride", e);
            return false;
        }
    }

    // Get rides for a specific driver
    public static List<Ride> getRidesByDriverId(int driverId) {
        List<Ride> rides = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM rides WHERE driver_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, driverId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Ride ride = new Ride(
                            rs.getInt("driver_id"),
                            rs.getString("origin"),
                            rs.getString("destination"),
                            rs.getTimestamp("date_time").toLocalDateTime(),
                            rs.getInt("available_seats"),
                            rs.getDouble("price"),
                            rs.getString("status")
                        );
                        ride.setRideId(rs.getInt("ride_id"));
                        ride.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                        ride.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                        ride.setComments(rs.getString("comments"));
                        rides.add(ride);
                    }
                }
            }
        } catch (SQLException e) {
            LogUtil.log(Level.SEVERE, "Error getting rides for driver", e);
        }
        return rides;
    }

    // Add this new method
    public static List<Ride> getRidesForUser(int userId) {
        List<Ride> rides = new ArrayList<>();
        String query = "SELECT * FROM rides WHERE driver_id = ? ORDER BY date_time DESC";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Ride ride = new Ride(
                        rs.getInt("driver_id"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getInt("available_seats"),
                        rs.getDouble("price"),
                        rs.getString("status")
                    );
                    ride.setRideId(rs.getInt("ride_id"));
                    rides.add(ride);
                }
            }
        } catch (SQLException e) {
            LogUtil.log(Level.SEVERE, "Error getting rides for user", e);
        }
        return rides;
    }
}
