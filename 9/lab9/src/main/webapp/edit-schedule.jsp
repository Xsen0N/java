<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ksenia Zhuk
  Date: 04.05.2023
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Добавить нового пользователя</h1>
<h1>Edit Schedule</h1>

<h2>User Information</h2>
<p>ID: ${user.id}</p>
<p>Name: ${user.name}</p>
<p>Average Mark: ${user.avgmark}</p>
<p>Skips: ${user.skips}</p>

<h2>User Journal</h2>
<table>
    <tr>
        <th>Mark</th>
        <th>Skip</th>
    </tr>
    <c:forEach var="journal" items="${journal}">
        <tr>
            <td>${journal.mark}</td>
            <td>${journal.skip}</td>
        </tr>
    </c:forEach>
</table>

<h2>Add New Entry</h2>
<form method="post" action="edit-schedule">
    <input type="hidden" name="id" value="${user.id}">
    <label for="date">Date:</label>
    <input type="date" name="date" id="date" required><br>
    <label for="mark">Mark:</label>
    <input type="text" name="mark" id="mark" required><br>
    <label for="skip">Skip:</label>
    <input type="text" name="skip" id="skip" required><br>
    <input type="submit" value="Add Entry">
</form>
</body>
</html>
