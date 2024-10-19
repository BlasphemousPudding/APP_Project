<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    /* Header CSS is now in styles.css */
</style>
<div class="header">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo">
        </a>
    </div>
    <nav class="nav">
        <a href="${pageContext.request.contextPath}/searchRides">Search</a>
        <a href="${pageContext.request.contextPath}/publishRide">Publish a Ride</a>
        <a href="${pageContext.request.contextPath}/profile">
            <img src="${pageContext.request.contextPath}/images/profile-icon.png" alt="Profile" class="profile-icon">
        </a>
    </nav>
</div>
