<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Start page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/Function.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Title</h1>
            </div>
            <div id="formdiv" align="right">
                <form action="/org.total.hibernate/authorization" id="myForm" method="POST" class="form-inline">
                    <label class="title">Login:</label><input type="text" class="input-large" name="login"><br/>
                    <label class="title">Password:</label><input type="password" class="input-large" name="password"/><br/>
                    <input type="submit" id="submitButton" value="Submit"/>
                </form>
                <a href="register.jsp">Registration</a>
            </div>
            <div align="center">
                <br>
                <br>
                <br>
                <h1>Some content</h1>
                <br>
                <br>
                <br>
            </div>
        </div>
    </body>
</html>
