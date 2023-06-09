import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "FormServlet", value = "/FormServlet")
public class FormServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Получаем логин и пароль из формы
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Проверяем, что логин и пароль были введены
        if (username == null || password == null ||
                username.isEmpty() || password.isEmpty()) {
            response.sendRedirect("login.jsp?error=missing_data");
            return;
        }

        // Дальше можно выполнить проверку логина и пароля в базе данных и сделать соответствующие действия
        // ...

        // Перенаправляем пользователя на другую страницу
        response.sendRedirect("home.jsp");
    }
}
