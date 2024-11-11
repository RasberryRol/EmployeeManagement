package com.rolson.employeemanagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Filter;

@WebServlet(name = "RedirectCredentials", urlPatterns = "/redirect")
public class RedirectCredentials extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.getRequestDispatcher("createMessage.jsp").forward(req, resp);
    }
}
