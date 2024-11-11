<%--
  Created by IntelliJ IDEA.
  User: dief9
  Date: 11/9/2024
  Time: 8:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
</head>
    <body>

        <form action="message" method="post">
            <input type="hidden" name="username" value=${username}>
            <input type="hidden" name="password" value=${password}>
            Type in your message below: <br><br>
            Message cannot be shorter than 10 or longer than 250 characters.<br><br>
            <label for="message">Message</label><br>
            <textarea id="message" name="message" required placeholder="Type your message here"
                      maxlength="250" minlength="10" rows="10" cols="60"></textarea>

            <input type="submit" value="Post"><br><br>
        </form>
        <h4 style="color: red; font-size: 17px">${error}</h4>
    </body>
</html>
