<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create a Ride</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
        }
        form {
            display: grid;
            gap: 20px;
        }
        label {
            font-weight: bold;
            color: #34495e;
        }
        input[type="text"],
        input[type="datetime-local"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 12px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Create a Ride</h1>
        <form action="${pageContext.request.contextPath}/createRide" method="post" enctype="multipart/form-data">
            <div>
                <label for="origin">Origin:</label>
                <input type="text" id="origin" name="origin" required>
            </div>

            <div>
                <label for="destination">Destination:</label>
                <input type="text" id="destination" name="destination" required>
            </div>

            <div>
                <label for="date_time">Date and Time:</label>
                <input type="datetime-local" id="date_time" name="date_time" required>
            </div>

            <div>
                <label for="available_seats">Available Seats:</label>
                <input type="number" id="available_seats" name="available_seats" required>
            </div>

            <div>
                <label for="price">Price per Seat:</label>
                <input type="number" step="0.01" id="price" name="price" required>
            </div>

            <input type="submit" value="Create Ride">
        </form>
    </div>

    <script>
        document.querySelector('form').addEventListener('submit', function(e) {
            e.preventDefault();
            const form = this;
            const formData = new FormData(form);

            console.log("Form data:");
            for (let [key, value] of formData.entries()) {
                console.log(key + ': ' + value);
            }

            console.log("date_time value:", form.elements['date_time'].value);

            fetch(form.action, {
                method: form.method,
                body: formData,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
            .then(response => response.json())
            .then(data => {
                console.log("Server response:", data);
                if (data.success) {
                    window.location.href = '${pageContext.request.contextPath}/myRides';
                } else {
                    alert('Form submission was not successful: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while submitting the form.');
            });
        });
    </script>
</body>
</html>
