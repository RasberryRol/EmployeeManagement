package com.rolson.employeemanagement.servlets;

import com.rolson.employeemanagement.DAO.UserDAO;
import com.rolson.employeemanagement.models.Employee;
import com.rolson.employeemanagement.utils.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Random;

@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    Employee employee = new Employee();
    Random random = new Random();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empID = Integer.parseInt(String.format("%04d", random.nextInt(10000)));

        employee.setEmpID(empID);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String role = employee.getRole();
        String username = req.getParameter("username");
        //password needs to be encoded
        String password = EncryptionUtil.encode(req.getParameter("password"));
        System.out.println("register encode " + password);
        String confirmPassword = req.getParameter("confirm-password");

        String decodedPassword = EncryptionUtil.decode(password);
        System.out.println("register decode " + decodedPassword);

        if(!decodedPassword.equals(confirmPassword)){
            req.setAttribute("error", "Password unmatched. Try again!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }else{
            employee = new Employee(employee.getEmpID(), name, email, phone, role, username, password);
            int userSaved = userDAO.saveUser(employee);

            if(userSaved < 1){
                req.setAttribute("error", "Error saving user!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }else{
                req.setAttribute("message", "User saved successfully!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
