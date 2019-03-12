<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New User</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../css/main.css" type="text/css">
    <script src="../../scripts/addUser.js"></script>
</head>
<body>
    <h2>Create New User Profile</h2>
    <form method="post" action="${pageContext.servletContext.contextPath}/addUser.do">
        <label for="name">User Name:</label>
        <input type="text" name="name" id="name"><br>

        <label for="login">Login Name:</label>
        <input type="text" name="login" id="login"><br>

        <label for="email">Email:</label>
        <input type="text" name="email" id="email"><br>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password"><br>

        <label>&nbsp;</label>
        <input type='submit' value='CREATE' onclick="return validateUserInput()">

        <br>
        <br>
        <c:if test="${not empty error}">
            <c:out value='${error}'></c:out>
        </c:if>
    </form>
    <br>
    <br>
    <label>&nbsp;</label>
    <form method='get' action='${pageContext.servletContext.contextPath}/login.do'>
        <input type='submit' value='Cancel'>
    </form>
</body>
</html>
