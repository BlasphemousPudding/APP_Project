package com.carpooling.model;

public class Ride {
    private int rideId;  // Add rideId field for identification
    private int driverId;
    private String origin;
    private String destination;
    private String dateTime;
    private int availableSeats;
    private String comments;
    private double originLatitude;
    private double originLongitude;
    private double destinationLatitude;
    private double destinationLongitude;
    private String status;  // Add status field for ride status

    // Full Constructor (with rideId)
    public Ride(int rideId, int driverId, String origin, String destination, String dateTime, int availableSeats, String comments, double originLatitude, double originLongitude, double destinationLatitude, double destinationLongitude, String status) {
        this.rideId = rideId;
        this.driverId = driverId;
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        this.availableSeats = availableSeats;
        this.comments = comments;
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.status = status;  // Initialize status
    }

    // Constructor without rideId (for creating new rides)
    public Ride(int driverId, String origin, String destination, String dateTime, int availableSeats, String comments, double originLatitude, double originLongitude, double destinationLatitude, double destinationLongitude) {
        this.driverId = driverId;
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        this.availableSeats = availableSeats;
        this.comments = comments;
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.status = "scheduled";  // Set default status as 'scheduled'
    }

    // Getters and Setters for each field
    public int getRideId() { return rideId; }
    public void setRideId(int rideId) { this.rideId = rideId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public double getOriginLatitude() { return originLatitude; }
    public void setOriginLatitude(double originLatitude) { this.originLatitude = originLatitude; }

    public double getOriginLongitude() { return originLongitude; }
    public void setOriginLongitude(double originLongitude) { this.originLongitude = originLongitude; }

    public double getDestinationLatitude() { return destinationLatitude; }
    public void setDestinationLatitude(double destinationLatitude) { this.destinationLatitude = destinationLatitude; }

    public double getDestinationLongitude() { return destinationLongitude; }
    public void setDestinationLongitude(double destinationLongitude) { this.destinationLongitude = destinationLongitude; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
