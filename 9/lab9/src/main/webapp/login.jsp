<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login or Register</title>
    <link rel="stylesheet" href="style.css">
</head>
<p><%= request.getAttribute("error") %></p>
<body >
<h1>Login or Register</h1>
<form method="post" action="login">
    <input type="hidden" name="action" value="login">
    <label for="login">Login:</label>
    <input type="text" name="login" id="login" required><br>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required><br>
    <button type="submit">Login</button>
</form>
<br>
<a href="registrarion.jsp">Регистрация</a>
</body>
</html>
