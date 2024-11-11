package com.rolson.employeemanagement.servlets;


import com.rolson.employeemanagement.DAO.UserDAO;
import com.rolson.employeemanagement.models.Employee;
import com.rolson.employeemanagement.models.EmployeeMessage;
import com.rolson.employeemanagement.utils.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Message", urlPatterns = "/message")
public class Message extends HttpServlet{

    UserDAO dao = new UserDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Employee employee = dao.checkEmployeeTable(username, password);

        String message = req.getParameter("message");
        String status = "Pending";

        int saved = dao.SaveEmployeeMessage(message, status, username, password);

        if (saved < 1) {
            req.setAttribute("error", "Error saving message!");
            req.getRequestDispatcher("createMessage.jsp").forward(req, resp);
        } else {
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("employeeName", new Employee(employee.getName()));
            req.setAttribute("message", "Message saved successfully!");
            req.getRequestDispatcher("userHome.jsp").forward(req, resp);
        }
    }
}
