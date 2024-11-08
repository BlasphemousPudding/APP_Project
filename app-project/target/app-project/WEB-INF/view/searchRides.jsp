<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Rides</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://apis.mapmyindia.com/advancedmaps/v1/5e975e028257b71504b08d88c4ebb693/map_load?v=1.5"></script>
</head>
<body>
    <div class="container mt-5">
        <h1>Available Rides</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Date & Time</th>
                    <th>Available Seats</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ride" items="${rides}">
                    <tr>
                        <td>${ride.origin}</td>
                        <td>${ride.destination}</td>
                        <td>${ride.dateTime}</td>
                        <td>${ride.availableSeats}</td>
                        <td>${ride.price}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/viewRideMap?rideId=${ride.rideId}" class="btn btn-primary">View Map</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
