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
<form action="loginPass" method="get" >
  <h1>Authorization</h1>
  <p> <input type="text" name="username" placeholder="Enter userName" /></p>
  <p> <input type="password" name="password" placeholder="Enter password" /></p>
  <p> <input type="text" name="role" placeholder="Enter role" /></p>
  <p>  <input type="submit" value="Login" /></p>
</form>
<a href="${pageContext.request.contextPath}/registerForm.jsp">register</a>
</body>
</html>