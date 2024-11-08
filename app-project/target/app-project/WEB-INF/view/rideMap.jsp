<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ride Map</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://apis.mapmyindia.com/advancedmaps/v1/5e975e028257b71504b08d88c4ebb693/map_load?v=1.5"></script>
    <style>
        #map { height: 400px; width: 100%; margin-bottom: 20px; }
        .status-available { color: blue; }
        .status-confirmed { color: green; }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1>Ride Map</h1>
        <div id="map"></div>
        <div class="mt-3">
            <p><strong>Origin:</strong> ${ride.origin}</p>
            <p><strong>Destination:</strong> ${ride.destination}</p>
            <p><strong>Date & Time:</strong> 
                <custom:formatDateTime dateTime="${ride.dateTime}" pattern="yyyy-MM-dd HH:mm" />
            </p>
            <p><strong>Available Seats:</strong> ${ride.availableSeats}</p>
            <p><strong>Price:</strong> $${ride.price}</p>
            <p><strong>Status:</strong> <span id="rideStatus" class="status-${ride.status.toLowerCase()}">${ride.status}</span></p>
            
            <button id="confirmRideBtn" class="btn btn-primary">Confirm Ride</button>
        </div>
    </div>

    <script>
        var map;
        function initMap() {
            map = new MapmyIndia.Map('map', {center: [20.5937, 78.9629], zoom: 5});
            
            MapmyIndia.search({search: "${ride.origin}"}, function(originResponse) {
                if (originResponse.results.length > 0) {
                    var originCoords = [originResponse.results[0].latitude, originResponse.results[0].longitude];
                    
                    MapmyIndia.search({search: "${ride.destination}"}, function(destResponse) {
                        if (destResponse.results.length > 0) {
                            var destCoords = [destResponse.results[0].latitude, destResponse.results[0].longitude];
                            
                            MapmyIndia.direction({
                                map: map,
                                start: originCoords.join(','),
                                end: destCoords.join(','),
                                callback: function(data) {
                                    if (data.status === 'success') {
                                        var routeGeometry = data.results.routes[0].geometry.coordinates;
                                        var routeLayer = L.polyline(routeGeometry, {color: 'blue', weight: 5}).addTo(map);
                                        
                                        L.marker(originCoords).addTo(map).bindPopup("Start: ${ride.origin}").openPopup();
                                        L.marker(destCoords).addTo(map).bindPopup("End: ${ride.destination}").openPopup();
                                        
                                        map.fitBounds(routeLayer.getBounds());
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }

        document.addEventListener('DOMContentLoaded', function() {
            initMap();
            
            var confirmButton = document.getElementById('confirmRideBtn');
            if (confirmButton) {
                confirmButton.addEventListener('click', function() {
                    confirmRide(${ride.rideId});
                });
            }
        });

        function confirmRide(rideId) {
            fetch('${pageContext.request.contextPath}/confirmRide', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: 'rideId=' + rideId
            })
            .then(function(response) { return response.json(); })
            .then(function(data) {
                if (data.success) {
                    alert('Ride confirmed successfully!');
                    if (data.redirectUrl) {
                        window.location.href = data.redirectUrl;
                    }
                } else {
                    alert('Failed to confirm ride: ' + data.message);
                }
            })
            .catch(function(error) {
                console.error('Error:', error);
                alert('An error occurred while confirming the ride.');
            });
        }
    </script>
</body>
</html>
