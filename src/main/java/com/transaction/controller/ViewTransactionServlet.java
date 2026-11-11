
package com.transaction.controller;

import java.io.IOException;
import java.util.List;

import com.transaction.dao.TransactionDAO;
import com.transaction.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewTransactionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession(false) == null ||
                request.getSession(false).getAttribute("rollNumber") == null) {
            response.sendRedirect("login.html");
            return;
        }

        TransactionDAO dao = new TransactionDAO();

        List<Transaction> transactions = dao.getAllTransactions();

        request.setAttribute("transactions", transactions);

        request.getRequestDispatcher("viewTransaction.jsp")
               .forward(request, response);
    }
}
