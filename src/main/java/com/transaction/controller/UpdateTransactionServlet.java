
package com.transaction.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

import com.transaction.dao.TransactionDAO;
import com.transaction.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateTransactionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession(false) != null &&
                request.getSession(false).getAttribute("rollNumber") != null;
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect("login.html");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Transaction t = new TransactionDAO().getTransactionById(id);

            if (t == null) {
                response.sendRedirect("view?error=notfound");
                return;
            }

            request.setAttribute("transaction", t);
            request.getRequestDispatcher("updateTransactionForm.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("view?error=invalid");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendRedirect("login.html");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("transactionId"));
            Date date = Date.valueOf(request.getParameter("date"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            String entityValue = request.getParameter("relatedEntityId");

            if (id <= 0 || amount.compareTo(BigDecimal.ZERO) <= 0 ||
                    (!"Income".equals(type) && !"Expense".equals(type)) ||
                    description == null || description.trim().isEmpty()) {
                response.sendRedirect("update?id=" + id + "&error=invalid");
                return;
            }

            Integer entityId = null;

            if (entityValue != null && !entityValue.trim().isEmpty()) {
                entityId = Integer.parseInt(entityValue);
                if (entityId <= 0) {
                    response.sendRedirect("update?id=" + id + "&error=invalid");
                    return;
                }
            }

            Transaction t = new Transaction();
            t.setTransactionId(id);
            t.setDate(date);
            t.setAmount(amount);
            t.setDescription(description.trim());
            t.setType(type);
            t.setRelatedEntityId(entityId);

            boolean updated = new TransactionDAO().updateTransaction(t);

            request.setAttribute("message",
                    updated ? "Transaction updated successfully!"
                            : "Transaction could not be updated.");

            request.getRequestDispatcher("updateTransaction.jsp")
                   .forward(request, response);

        } catch (IllegalArgumentException e) {
            response.sendRedirect("view?error=invalid");
        }
    }
}
