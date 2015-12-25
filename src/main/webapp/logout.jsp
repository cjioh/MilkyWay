<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Log out</title>
    </head>
    <body>
        <% session.invalidate(); %>
        <div align="center">
            <h4>You have been successfully logout</h4>
            <a href="index.jsp">Start page</a>
        </div>
    </body>
</html>
