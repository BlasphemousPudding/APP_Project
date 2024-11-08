<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Rides</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .status-available {
            color: blue;
            font-weight: bold;
        }
        .status-confirmed {
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1>My Rides</h1>
        <c:if test="${not empty rides}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Origin</th>
                        <th>Destination</th>
                        <th>Date & Time</th>
                        <th>Available Seats</th>
                        <th>Price</th>
                        <th>Status</th>
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
                                <c:choose>
                                    <c:when test="${ride.status eq 'Confirmed'}">
                                        <span class="status-confirmed">Confirmed</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-available">Available</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty rides}">
            <p class="alert alert-info">You have no rides yet.</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/createRide" class="btn btn-primary">Create a New Ride</a>
    </div>
</body>
</html>
