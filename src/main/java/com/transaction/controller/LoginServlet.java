package com.transaction.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String ROLL_NUMBER = "24VV1A1249";

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        if (username != null && password != null
                && username.equals(ROLL_NUMBER)
                && password.equals(ROLL_NUMBER)) {

            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("rollNumber", ROLL_NUMBER);
            session.setMaxInactiveInterval(30 * 60);

            String cookiePath = request.getContextPath().isEmpty()
                    ? "/" : request.getContextPath();

            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("rememberedUser", ROLL_NUMBER);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setHttpOnly(true);
                cookie.setPath(cookiePath);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("rememberedUser", "");
                cookie.setMaxAge(0);
                cookie.setPath(cookiePath);
                response.addCookie(cookie);
            }

            response.sendRedirect("home.jsp");

        } else {
            response.sendRedirect("login.html?error=invalid");
        }
    }
}