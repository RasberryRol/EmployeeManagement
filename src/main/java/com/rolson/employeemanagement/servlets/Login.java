package com.rolson.employeemanagement.servlets;

import com.mysql.cj.Session;
import com.rolson.employeemanagement.DAO.UserDAO;
import com.rolson.employeemanagement.models.Employee;
import com.rolson.employeemanagement.utils.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    UserDAO dao = new UserDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = EncryptionUtil.encode(req.getParameter("password"));
        Employee emp = dao.checkManagerAndEmployeeTables(username, password);

        if(emp == null){
            req.setAttribute("error", "Invalid username or password. Try again!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("username", emp.getUsername());

            req.setAttribute("employeeName", new Employee(emp.getName()));
            req.setAttribute("username", emp.getUsername());
            req.setAttribute("password", emp.getPassword());


            req.setAttribute("employeeInfo", emp);
            req.getRequestDispatcher("userHome.jsp").forward(req, resp);
        }
    }
}