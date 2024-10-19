<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Carpool Buddy</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
            font-weight: bold;
        }
        input {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            margin-top: 20px;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #45a049;
        }
        .login-link {
            text-align: center;
            margin-top: 20px;
        }
        .error-message {
            color: #ff0000;
            text-align: center;
            margin-top: 10px;
        }
        .success-message {
            color: #4CAF50;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Join Carpool Buddy</h1>
        
        <c:if test="${not empty param.error}">
            <p class="error-message">${param.error}</p>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/login" method="post" id="registerForm">
            <input type="hidden" name="action" value="register">
            <label for="name">Full Name:</label>
            <input type="text" name="name" id="name" required>
            
            <label for="email">Email Address:</label>
            <input type="email" name="email" id="email" required>
            
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>
            
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" name="confirmPassword" id="confirmPassword" required>
            
            <button type="submit">Create Account</button>
        </form>
        
        <p class="login-link">
            Already have an account? <a href="${pageContext.request.contextPath}/login">Log in</a>
        </p>
    </div>

    <jsp:include page="/WEB-INF/view/footer.jsp" />

    <script>
        document.getElementById('registerForm').addEventListener('submit', function(e) {
            var password = document.getElementById('password');
            var confirmPassword = document.getElementById('confirmPassword');
            if (password.value !== confirmPassword.value) {
                e.preventDefault();
                alert('Passwords do not match.');
            }
        });
    </script>
</body>
</html>
