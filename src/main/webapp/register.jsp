<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div align="right">
                <form action="/registration" method="POST" class="form-inline">
                    Login: <input type="text" class="input-large" name="login"><br/>
                    Password: <input type="password" class="input-large" name="password"/><br/>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
        </div>
    </body>
</html>
