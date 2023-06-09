<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sql</title>
</head>
<body>
<sql:setDataSource var="contacts" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                   url="jdbc:sqlserver:Host:1433//;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false"
                   user="sa" password="12344321"/>

<sql:query dataSource="${contacts}" var="myself" >
  SELECT * FROM Users where username = ?
  <sql:param value="Admin" />
</sql:query>

<sql:query dataSource="${contacts}" var="contacts">
  SELECT * FROM Users
</sql:query>

<h1>SQL TAGS</h1>

<table style="border: 1px solid black; width: 40%">
  <tr>
    <th>Name</th>
    <th>Password</th>
    <th>Role</th>
  </tr>

  <c:forEach var = "row" items = "${contacts.rows}">
    <tr>
      <td> <c:out value = "${row.UserName}"/></td>
      <td> <c:out value = "${row.UserPassword}"/></td>
      <td> <c:out value = "${row.UserRole}"/></td>
    </tr>
  </c:forEach>
</table>



<br/><br/><br/>
<table style="border: 1px solid black; width: 25%">
  <tr>
    <th>Name</th>
    <th>Password</th>
    <th>Role</th>
  </tr>

  <c:forEach var = "row" items = "${myself.rows}">
    <tr>
      <td> <c:out value = "${row.username}"/></td>
      <td> <c:out value = "${row.password}"/></td>
      <td> <c:out value = "${row.role}"/></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
