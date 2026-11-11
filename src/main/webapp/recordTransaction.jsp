
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
    <title>Transaction Result</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="includes/navbar.jsp" %>

    <div class="form-container">
        <h1>Transaction Status</h1>

        <p style="text-align:center;">
            <%= request.getAttribute("message") %>
        </p>

        <a href="recordTransactionForm.html" class="btn">
            Add Another Transaction
        </a>

        <a href="view" class="btn">
            View Transactions
        </a>
    </div>

</body>
</html>
