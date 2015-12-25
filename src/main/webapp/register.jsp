<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Registration</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/Function.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">
            <c:if test="${requestScope.error != null}">
                <c:out value="${requestScope.error}" /><br>
            </c:if>
            <div align="center">
                <h1>Registration</h1>
            </div>
            <div align="right">
                <%@ include file="registerForm.jsp" %>
            </div>
            <div align="right">
                <a href="index.jsp">Start page</a>
            </div>
        </div>
    </body>
</html>
