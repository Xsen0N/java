<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Title</title>
    <style>
        table{
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<nav role="navigation" class="navbar navbar-default">

    <div align="center">
        <nav class="menu" >
            <a href="${pageContext.servletContext.contextPath}/controller?command=sign_out">Logout</a>
        </nav>
    </div>

    <div class="container" align="center">
        <span/><h3>Welcome,  ${username}</h3>
        <div  class = "layer1">
            <table class ="container" border="2">
                <tr>
                    <th><h1>Name</h1></th>
                    <th><h1>Phone</h1></th>
                    <th><h1>Email</h1></th>
                </tr>
                <c:forEach items="${group}" var="person">
                    <tr><td>${person.name}</td>
                        <td>${person.phone}</td>
                        <td>${person.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class = "layer2">
            <p><font color="red">${errorMessage}</font></p>
            <form class="login-form" method="POST" action="${pageContext.servletContext.contextPath}/controller?command=add_new_person">
                Add new contact:
                <p>  Enter name <input name="nname" type="text" /> </p>
                <p>   Enter phone <input name="nphone" type="text" /> </p>
                <p>  Enter email <input name="nemail" type="text" /> </p>
                <input class ="button-main-page" value="Add" type="submit" />
            </form>
        </div>
    </div>
    <p> ${lastdate}</p>
</nav>
</body>
</html>