package com.example.lab9.zad3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/SetCookieServlet")
public class SetCookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Установка времени и даты последнего сеанса пользователя
        String lastSessionTime = new Date().toString();
        Cookie lastSessionTimeCookie = new Cookie("lastSessionTime", lastSessionTime);
        lastSessionTimeCookie.setMaxAge(24 * 60 * 60); // Время жизни cookie в секундах (здесь - 1 день)
        response.addCookie(lastSessionTimeCookie);

        // Установка количества посещений
        Cookie visitsCountCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("visitsCount".equals(cookie.getName())) {
                    int visitsCount = Integer.parseInt(cookie.getValue());
                    visitsCount++;
                    cookie.setValue(Integer.toString(visitsCount));
                    visitsCountCookie = cookie;
                    break;
                }
            }
        }
        if (visitsCountCookie == null) {
                visitsCountCookie = new Cookie("visitsCount", "1");
        }
        visitsCountCookie.setMaxAge(24 * 60 * 60); // Время жизни cookie в секундах (здесь - 1 день)
        response.addCookie(visitsCountCookie);

        // Установка типа пользователя
        String userType = "admin"; // Здесь предполагается, что тип пользователя - "admin"
        Cookie userTypeCookie = new Cookie("userType", userType);
        userTypeCookie.setMaxAge(24 * 60 * 60); // Время жизни cookie в секундах (здесь - 1 день)
        response.addCookie(userTypeCookie);

        // Отправка ответа
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cookie Example</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Cookie Example</h1>");
        out.println("<p>Last Session Time: " + lastSessionTime + "</p>");
        out.println("<p>Visits Count: " + visitsCountCookie.getValue() + "</p>");
        out.println("<p>User Type: " + userType + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
