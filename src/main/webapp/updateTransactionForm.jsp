
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.transaction.model.Transaction" %>

<%
    if (session.getAttribute("rollNumber") == null) {
        response.sendRedirect("login.html");
        return;
    }

    Transaction t = (Transaction) request.getAttribute("transaction");

    if (t == null) {
        response.sendRedirect("view");
        return;
    }

    String description = t.getDescription() == null ? "" : t.getDescription();
    description = description.replace("&", "&amp;")
                             .replace("<", "&lt;")
                             .replace(">", "&gt;");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Transaction</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="includes/navbar.jsp" %>

    <div class="form-container">

        <h1>Update Transaction</h1>

        <form action="update" method="post">

            <input type="hidden" name="transactionId"
                   value="<%= t.getTransactionId() %>">

            <label>Date</label>
            <input type="date" name="date"
                   value="<%= t.getDate() %>" required>

            <label>Amount (₹)</label>
            <input type="number" name="amount"
                   step="0.01" min="0.01"
                   value="<%= t.getAmount() %>" required>

            <label>Description</label>
            <textarea name="description" rows="4"
                      required><%= description %></textarea>

            <label>Transaction Type</label>
            <select name="type" required>
                <option value="Income"
                    <%= "Income".equals(t.getType()) ? "selected" : "" %>>
                    Income
                </option>

                <option value="Expense"
                    <%= "Expense".equals(t.getType()) ? "selected" : "" %>>
                    Expense
                </option>
            </select>

            <label>Related Entity ID</label>
            <input type="number" name="relatedEntityId" min="1"
                   value="<%= t.getRelatedEntityId() == null
                       ? "" : t.getRelatedEntityId() %>">

            <button type="submit">Update Transaction</button>

        </form>

        <a href="view" class="back-link">Cancel</a>

    </div>

</body>
</html>
