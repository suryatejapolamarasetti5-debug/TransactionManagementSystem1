
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

@WebServlet("/record")
public class RecordTransactionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession(false) == null ||
                request.getSession(false).getAttribute("rollNumber") == null) {
            response.sendRedirect("login.html");
            return;
        }

        try {
            String dateValue = request.getParameter("date");
            String amountValue = request.getParameter("amount");
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            String entityValue = request.getParameter("relatedEntityId");

            Date date = Date.valueOf(dateValue);
            BigDecimal amount = new BigDecimal(amountValue);

            if (amount.compareTo(BigDecimal.ZERO) <= 0 ||
                    (!"Income".equals(type) && !"Expense".equals(type))) {
                response.sendRedirect("recordTransactionForm.html?error=invalid");
                return;
            }

            Integer entityId = null;

            if (entityValue != null && !entityValue.trim().isEmpty()) {
                entityId = Integer.parseInt(entityValue);
                if (entityId <= 0) {
                    response.sendRedirect("recordTransactionForm.html?error=invalid");
                    return;
                }
            }

            Transaction t = new Transaction();
            t.setDate(date);
            t.setAmount(amount);
            t.setDescription(description);
            t.setType(type);
            t.setRelatedEntityId(entityId);

            TransactionDAO dao = new TransactionDAO();

            if (dao.addTransaction(t)) {
                request.setAttribute("message",
                        "Transaction recorded successfully!");
                request.getRequestDispatcher("recordTransaction.jsp")
                       .forward(request, response);
            } else {
                request.setAttribute("message",
                        "Unable to record transaction. Please try again.");
                request.getRequestDispatcher("recordTransaction.jsp")
                       .forward(request, response);
            }

        } catch (IllegalArgumentException e) {
            response.sendRedirect("recordTransactionForm.html?error=invalid");
        }
    }
}
