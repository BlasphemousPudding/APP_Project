<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carpool Buddy</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }
        .header {
            background-color: #4CAF50;
            padding: 20px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .logo {
            font-size: 24px;
            font-weight: bold;
        }
        .search-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 20px;
            margin: 50px auto;
            max-width: 800px;
        }
        .search-form {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }
        .search-form input, .search-form select {
            flex: 1;
            min-width: 200px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        .search-button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 18px;
            margin-top: 10px;
        }
        .right-icons {
            display: flex;
            align-items: center;
        }
        .publish-ride, .profile-icon {
            background-color: white;
            color: #4CAF50;
            border: none;
            padding: 10px 15px;
            margin-left: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
            right: 0;
        }
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }
        .show {
            display: block;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">Carpool Buddy</div>
        <div class="right-icons">
            <button class="publish-ride">Publish a Ride</button>
            <div class="dropdown">
                <button class="profile-icon" onclick="toggleDropdown()">Profile</button>
                <div id="myDropdown" class="dropdown-content">
                    <!-- Use the correct context path for dynamic URL resolution in JSP -->
                    <a href="<%= request.getContextPath() %>/login">Login</a>
                    <a href="<%= request.getContextPath() %>/register">Signup</a>
                </div>
            </div>
        </div>
    </div>
    <div class="search-container">
        <form class="search-form">
            <input type="text" placeholder="Leaving from" required>
            <input type="text" placeholder="Going to" required>
            <input type="date" required>
            <select required>
                <option value="" disabled selected>Number of passengers</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5+</option>
            </select>
            <button type="submit" class="search-button">Search Rides</button>
        </form>
    </div>

    <script>
        function toggleDropdown() {
            document.getElementById("myDropdown").classList.toggle("show");
        }

        window.onclick = function(event) {
            if (!event.target.matches('.profile-icon')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                for (var i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }

        // Set min date for the date input to today
        var today = new Date().toISOString().split('T')[0];
        document.querySelector('input[type="date"]').setAttribute('min', today);
    </script>
</body>
</html>
