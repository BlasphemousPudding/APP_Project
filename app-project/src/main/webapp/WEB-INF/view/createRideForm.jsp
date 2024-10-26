<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create a New Ride</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            margin-top: 50px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #007bff;
            margin-bottom: 30px;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
    <script>
        window.onerror = function(message, source, lineno, colno, error) {
            console.error("JavaScript error:", message, "at", source, ":", lineno);
            return false;
        };
    </script>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Create a New Ride</h1>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        <form id="createRideForm" action="${pageContext.request.contextPath}/createRide" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="origin" class="form-label">Origin:</label>
                <input type="text" class="form-control" id="origin" name="origin" required>
            </div>
            <div class="mb-3">
                <label for="destination" class="form-label">Destination:</label>
                <input type="text" class="form-control" id="destination" name="destination" required>
            </div>
            <div class="mb-3">
                <label for="date_time" class="form-label">Date and Time:</label>
                <input type="datetime-local" class="form-control" id="date_time" name="date_time" required>
            </div>
            <div class="mb-3">
                <label for="available_seats" class="form-label">Available Seats:</label>
                <input type="number" class="form-control" id="available_seats" name="available_seats" required>
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price:</label>
                <input type="number" class="form-control" id="price" name="price" step="0.01" required>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Create Ride</button>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        console.log("Bootstrap script loaded successfully");
        
        document.getElementById('createRideForm').addEventListener('submit', function(e) {
            e.preventDefault();
            fetch(this.action, {
                method: 'POST',
                body: new FormData(this)
            })
            .then(response => {
                console.log('Response status:', response.status);
                console.log('Response headers:', response.headers);
                return response.text();
            })
            .then(text => {
                console.log('Response body:', text);
                if (text.includes('error.jsp')) {
                    console.error('Redirected to error.jsp');
                    alert('An error occurred while creating the ride. Please try again.');
                } else {
                    window.location.href = '${pageContext.request.contextPath}/myRides';
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
                alert('An error occurred while submitting the form.');
            });
        });
    </script>
</body>
</html>
