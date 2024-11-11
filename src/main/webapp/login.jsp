<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Employee Management</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //Proxies

            session.removeAttribute("username");
        %>

            <form action="login" method="post">
                Username <input type="text" name="username"><br>
                Password <input type="password" name="password">
                <input type="submit" value="Submit">
            </form>
            <h4 style="font-size: 17px">${message}</h4>
            <h4 style="color: red; font-size: 17px">${error}</h4>
    </body>
</html>