package com.carpooling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carpooling.model.Ride;
import com.carpooling.util.DatabaseUtil;

public class RideDAO {

    // Method to create a new ride in the database
    public static boolean createRide(Ride ride) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Establish database connection
            conn = DatabaseUtil.getConnection();
            
            // SQL query to insert a new ride into the 'rides' table
            String query = "INSERT INTO rides (driver_id, origin, destination, date_time, available_seats, comments, status, origin_latitude, origin_longitude, destination_latitude, destination_longitude) "
                         + "VALUES (?, ?, ?, ?, ?, ?, 'scheduled', ?, ?, ?, ?)";
            
            // Prepare the statement with ride details
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, ride.getDriverId());
            pstmt.setString(2, ride.getOrigin());
            pstmt.setString(3, ride.getDestination());
            pstmt.setString(4, ride.getDateTime());
            pstmt.setInt(5, ride.getAvailableSeats());
            pstmt.setString(6, ride.getComments());
            pstmt.setDouble(7, ride.getOriginLatitude());
            pstmt.setDouble(8, ride.getOriginLongitude());
            pstmt.setDouble(9, ride.getDestinationLatitude());
            pstmt.setDouble(10, ride.getDestinationLongitude());
            
            // Execute the query and check if rows are affected
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if a row is inserted successfully
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Ensure database resources are closed properly
            DatabaseUtil.closeResources(conn, pstmt, null);
        }
        return false; // Return false if an exception occurred or no row was inserted
    }

    // Method to get all rides for a specific driver
    public static List<Ride> getRidesByDriverId(int driverId) {
        List<Ride> rides = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            String query = "SELECT * FROM rides WHERE driver_id = ? AND status != 'canceled'";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, driverId);
            rs = pstmt.executeQuery();
            
            // Loop through the result set and create Ride objects
            while (rs.next()) {
                Ride ride = new Ride(
                    rs.getInt("ride_id"), // Assuming the Ride constructor accepts rideId
                    rs.getInt("driver_id"),
                    rs.getString("origin"),
                    rs.getString("destination"),
                    rs.getString("date_time"),
                    rs.getInt("available_seats"),
                    rs.getString("comments"),
                    rs.getDouble("origin_latitude"),
                    rs.getDouble("origin_longitude"),
                    rs.getDouble("destination_latitude"),
                    rs.getDouble("destination_longitude"),
                    rs.getString("status")
                );
                rides.add(ride);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Ensure database resources are closed properly
            DatabaseUtil.closeResources(conn, pstmt, rs);
        }
        return rides;
    }
}
