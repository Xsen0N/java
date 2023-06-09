<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<%-- Проверяем, есть ли сообщение об ошибке --%>
<c:if test="${param.error == 'missing_data'}">
    <div style="color: red;">Please enter your username and password.</div>
</c:if>

<h1>Login</h1>

<%-- Форма для ввода логина и пароля --%>
<form method="post" action="FormServlet">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>