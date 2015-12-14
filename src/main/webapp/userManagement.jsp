<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.total.interview.server.service.UserService" %>
<%@ page import="org.total.interview.server.model.User" %>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>User Management</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/Function.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1 align="center">User Management</h1>
        <%
            UserService service = new UserService();
            List<User> users = service.findAll();
            pageContext.setAttribute("users", users);
        %>
        <label>Size is <%= users.size() %></label><br>
        <c:forEach items="${users}" var="user">
            <c:out value="${user.getUserName()}" /><br>
        </c:forEach>
    </body>
</html>