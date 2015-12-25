<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="org.total.interview.server.model.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>Management</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/Function.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <div align="right">
                        <%@ include file="authForm.jsp" %>
                    </div>
                    <div align="right">
                        <a href="register.jsp">Registration</a>
                    </div>
                </c:when>
                <c:when test="${sessionScope.user != null}">
                    <div align="center">
                        <h1>Management</h1>
                    </div>
                    <div>
                        <c:forEach items="${requestScope.users}" var="user">
                            <c:out value="${user.getUserName()}" /><br>
                        </c:forEach>
                    </div>
                    <div align="right">
                        <a href="index.jsp">Start page</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </body>
</html>