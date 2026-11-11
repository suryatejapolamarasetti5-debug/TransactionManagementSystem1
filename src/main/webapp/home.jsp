
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
    <title>Home - Transaction Manager</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="includes/navbar.jsp" %>

    <div class="home-container">

        <h1>Welcome to Transaction Management System</h1>

        <p>Manage your income and expenses easily.</p>

        <div class="dashboard-cards">

            <div class="dashboard-card">
                <h2>Record Transaction</h2>
                <p>Add a new income or expense record.</p>
                <a href="recordTransactionForm.html"
                   class="btn">Add Transaction</a>
            </div>

            <div class="dashboard-card">
                <h2>View Transactions</h2>
                <p>View all your recorded transactions.</p>
                <a href="view" class="btn">View Records</a>
            </div>

        </div>

    </div>

</body>
</html>
