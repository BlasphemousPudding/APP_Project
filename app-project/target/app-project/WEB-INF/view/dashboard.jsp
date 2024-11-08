<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        :root {
            --primary-color: #3498db;
            --secondary-color: #2c3e50;
            --background-color: #f8f9fa;
            --text-color: #333;
            --card-background: #ffffff;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: var(--text-color);
            background-color: var(--background-color);
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            flex-grow: 1;
        }
        header {
            background-color: var(--card-background);
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 20px 0;
        }
        h1 {
            color: var(--secondary-color);
            text-align: center;
            margin: 0;
        }
        nav {
            background-color: var(--card-background);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            margin-top: 30px;
        }
        nav ul {
            list-style-type: none;
            padding: 0;
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 20px;
        }
        nav ul li a {
            display: inline-block;
            padding: 12px 24px;
            background-color: var(--primary-color);
            color: white;
            text-decoration: none;
            border-radius: 30px;
            transition: all 0.3s ease;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1px;
            font-size: 14px;
        }
        nav ul li a:hover {
            background-color: var(--secondary-color);
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        .content {
            text-align: center;
            margin-top: 50px;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        .fade-in {
            animation: fadeIn 0.5s ease-out forwards;
        }
    </style>
</head>
<body>
    <header>
        <div class="container">
            <h1>Your Dashboard</h1>
        </div>
    </header>
    <div class="container">
        <nav class="fade-in">
            <ul>
                <li><a href="${pageContext.request.contextPath}/createRide"><i class="fas fa-plus"></i> New Ride</a></li>
                <li><a href="${pageContext.request.contextPath}/myRides"><i class="fas fa-list"></i> My Rides</a></li>
                <li><a href="${pageContext.request.contextPath}/searchAvailableRides">Search Rides</a></li>
                <li><a href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
        <div class="content fade-in">
            <p>Use the navigation menu to manage your rides and explore new opportunities.</p>
        </div>
    </div>
</body>
</html>
