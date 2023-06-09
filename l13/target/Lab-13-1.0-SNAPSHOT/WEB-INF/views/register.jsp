<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="login-container" align="center">
    <section class="login" id="login">
        <div class="form">
            <p><font color="red">${errorRegister}</font></p>

            <form class="login-form" action="${pageContext.servletContext.contextPath}/controller?command=register_new_user" method="POST">
                <p> <h4>Register new user</h4></p>
                <p>   <input  name="newLoginName" placeholder="Enter new login" type="text" />
                </p>
                <p>    <input name="newPassword" placeholder="Enter new password"  type="password" />
                </p>
                <input class ="button-main-page" type="submit"  value="Register"/>

            </form>
        </div>
    </section>
</div>
</body>
</html>