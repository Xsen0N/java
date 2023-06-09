<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<h1>Current Time</h1>
<form action="CurrentTimeServlet" method="get">
    <input type="submit" value="Get Current Time">
</form>
<a href="${pageContext.request.contextPath}/LoginPass.jsp">login Servlet</a>
</body>
</html>