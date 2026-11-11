
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("rollNumber") == null) {
        response.sendRedirect("login.html");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Status</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="includes/navbar.jsp" %>

    <div class="form-container">
        <h1>Update Status</h1>

        <p style="text-align:center; margin:20px;">
            <%= request.getAttribute("message") %>
        </p>

        <a href="view" class="btn">View Transactions</a>
        <a href="home.jsp" class="btn">Home</a>
    </div>

</body>
</html>
