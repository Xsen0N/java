<%--
  Created by IntelliJ IDEA.
  User: Ksenia Zhuk
  Date: 26.04.2023
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="register" method="get">
    <h2>Register Form</h2>
    <h3 style="color: red">${message}</h3>
    <p><input type="text" name="username" placeholder="Enter userName" /> </p>
    <p><input type="password" name="password" placeholder="Enter password" /> </p>
    <p><input type="text" name="role" placeholder="Enter role" /> </p>
    <p><input type="submit" value="Register" /> </p>
</form>
<a href="${pageContext.request.contextPath}/LoginPass.jsp">login</a>
</body>
</html>
