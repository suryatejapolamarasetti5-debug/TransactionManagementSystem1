
package com.transaction.controller;

import java.io.IOException;

import com.transaction.dao.TransactionDAO;
import com.transaction.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteTransactionServlet extends HttpServlet {

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
            request.getRequestDispatcher("deleteTransaction.jsp")
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

            if (id <= 0) {
                response.sendRedirect("view?error=invalid");
                return;
            }

            boolean deleted = new TransactionDAO().deleteTransaction(id);

            request.setAttribute("message",
                    deleted ? "Transaction deleted successfully!"
                            : "Transaction could not be deleted.");

            request.getRequestDispatcher("deleteTransaction.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("view?error=invalid");
        }
    }
}
