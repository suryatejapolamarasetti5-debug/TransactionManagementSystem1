
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ page import="com.transaction.model.Transaction" %>

<%
    if (session.getAttribute("rollNumber") == null) {
        response.sendRedirect("login.html");
        return;
    }

List<?> transactionList =
(List<?>) request.getAttribute("transactions");

List<Transaction> transactions = new ArrayList<>();

if (transactionList != null) {
for (Object obj : transactionList) {
    if (obj instanceof Transaction) {
        transactions.add((Transaction) obj);
    }
}
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="includes/navbar.jsp" %>

    <div class="table-container">

        <h1>Transaction Records</h1>

        <a href="recordTransactionForm.html" class="btn">
            + Add Transaction
        </a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Related Entity</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>

            <% if (transactions != null && !transactions.isEmpty()) {
                for (Transaction t : transactions) { %>

                <tr>
                    <td><%= t.getTransactionId() %></td>
                    <td><%= t.getDate() %></td>
                    <td>₹ <%= t.getAmount() %></td>
                    <td><%= t.getDescription() %></td>
                    <td><%= t.getType() %></td>
                    <td><%= t.getRelatedEntityId() == null
                            ? "-" : t.getRelatedEntityId() %></td>
                    <td>
                        <a href="update?id=<%= t.getTransactionId() %>">
                            Edit
                        </a>
                        |
                       <a href="delete?id=<%= t.getTransactionId() %>">
    Delete
</a>
                    </td>
                </tr>

            <% }
            } else { %>

                <tr>
                    <td colspan="7">No transactions found.</td>
                </tr>

            <% } %>

            </tbody>
        </table>

    </div>

</body>
</html>
