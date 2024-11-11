<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //Proxies

            session.removeAttribute("username");

            String name = (request.getParameter("name") == null) ? "" : request.getParameter("name");
            String email = (request.getParameter("email") == null) ? "" : request.getParameter("email");
            String phone = (request.getParameter("phone") == null) ? "" : request.getParameter("phone");
            String username = (request.getParameter("username") == null) ? "" : request.getParameter("username");

            /*alternative for the above code
            session.setAttribute("name", request.getParameter("name"));
            String str=(String)session.getAttribute("name");*/

        %>

    Please enter the following information: <br><br>
        <form action="register" method="post">
            <label for="name">Full Name</label>
            <input type="text" name="name" id="name" placeholder="Ex: John Doe" value="<%=name%>" required><br><br>

            <label for="email">Email</label>
            <input type="email" name="email" id="email" placeholder="Ex: john.doe@gmail.com" value="<%=email%>" required><br><br>

            <label for="phone">Phone#</label>
            <input type="text" name="phone" id="phone" placeholder="Ex: 6285437171" value="<%=phone%>" required><br><br>

            <!--Verify that the username and password are not taken -->
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" placeholder="Ex: john111" value="<%=username%>" required><br><br>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="Ex: Egg$5454" required><br><br>

            Confirm Password <input type="password" name="confirm-password" required><br>
            <input type="submit" value="Register">
        </form>
    <h4 style="color: red; font-size: 17px">${error}</h4>
    </body>
</html>