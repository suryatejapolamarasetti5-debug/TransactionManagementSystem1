
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.transaction.model.Transaction" %>

<%
    if (session.getAttribute("rollNumber") == null) {
        response.sendRedirect("login.html");
        return;
    }

    Transaction t = (Transaction) request.getAttribute("transaction");
    String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Transaction</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="includes/navbar.jsp" %>

    <div class="form-container">

        <% if (t != null) { %>

            <h1>Confirm Deletion</h1>

            <p>Are you sure you want to delete transaction
                #<strong><%= t.getTransactionId() %></strong>?
            </p>

            <p style="margin:15px 0;">
                Amount: ₹ <%= t.getAmount() %>
                <br>
                Type: <%= t.getType() %>
            </p>

            <form action="delete" method="post">

                <input type="hidden" name="transactionId"
                       value="<%= t.getTransactionId() %>">

                <button type="submit"
                        style="background:#dc2626;">
                    Yes, Delete
                </button>

            </form>

            <a href="view" class="back-link">Cancel</a>

        <% } else { %>

            <h1>Deletion Status</h1>

            <p style="text-align:center; margin:20px;">
                <%= message == null ? "No transaction selected." : message %>
            </p>

            <a href="view" class="btn">View Transactions</a>
            <a href="home.jsp" class="btn">Home</a>

        <% } %>

    </div>

</body>
</html>
