<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/3/2024
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.rolson.employeemanagement.models.Employee" %>
<%@ page import="java.io.PrintWriter" %>
<html>
    <head>
        <title>Employee Welcome Page</title>
    </head>
    <body>

        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //Proxies

            if(session.getAttribute("username") == null){
                request.setAttribute("error", "You have not logged in!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        %>
        <h4 style="font-size: 17px">${message}</h4><br>

        <h2 style="font-size: 17px">Hello ${employeeName.name}, Welcome to your account!</h2><br>
        <h2>${employeeInfo}</h2>

        <form action="redirect" method="post">
            <input type="hidden" name="username" value=${username}>
            <input type="hidden" name="password" value=${password}>

            <input type="submit" value="Post A Message">
        </form>

        <form action="logout">
            <input type="submit" value="Logout">
        </form>
    </body>
</html>
