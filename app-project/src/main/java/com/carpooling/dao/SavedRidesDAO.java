package com.carpooling.dao;

import com.carpooling.model.Ride;
import com.carpooling.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavedRidesDAO {
    public static boolean saveRide(int userId, int rideId) {
        String sql = "INSERT INTO saved_rides (user_id, ride_id) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, rideId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Ride> getSavedRides(int userId) {
        List<Ride> savedRides = new ArrayList<>();
        String sql = "SELECT r.* FROM rides r JOIN saved_rides sr ON r.ride_id = sr.ride_id WHERE sr.user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
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
                savedRides.add(ride);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedRides;
    }
}
