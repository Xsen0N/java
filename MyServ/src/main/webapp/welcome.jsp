<%--
  Created by IntelliJ IDEA.
  User: Ksenia Zhuk
  Date: 26.04.2023
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="Components.List" %>

<%@ page import="Components.Journal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DB.ConnectorDb" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <style>
        table{
            border: 1px solid black;
            text-align: center;
            border-collapse: collapse;
        }
        td{
            border: 1px solid black;
            text-align: center;
            padding: 5px;
        }
        div > input{
            margin-top: 10px;
            display: inline-block;
        }
        .Info > input{
            margin-top: 10px;
            display: block;
        }
    </style>
</head>
<body>
<h1>Welcome ${username}</h1>
<p>role: ${role}</p>
<p>current date: <%= LocalDate.now() %></p>
<form action="AddServlet" method="get">
    <div class="Info">
        <h3>Refactor Form</h3>
        <input type="text" name="ID" placeholder="Enter id" />
        <input type="text" name="name" placeholder="Enter name" />
        <input type="text" name="skips" placeholder="Enter your skips" />
        <input type="text" name="grade" placeholder="Enter grade " />
        <input type="submit" value="Add" />
    </div>
</form>

<h3>Students:</h3>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Skips</td>
        <td>Grade</td>
    </tr>
    <%
        try {
            ConnectorDb connectorDb = new ConnectorDb();
            List subjects = new List(connectorDb.getConnection("Users"));
            ArrayList<Journal> listChannel = subjects.getList();
            for (Journal subject : listChannel) {
    %>
    <tr>
        <td><%=subject.getID()%></td>
        <td><%=subject.getName()%></td>
        <td><%=subject.getSkips()%></td>
        <td><%=subject.getGrad()%></td>
    </tr>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
<a href="${pageContext.request.contextPath}/index.jsp">Выход</a>
</body>
</html>
