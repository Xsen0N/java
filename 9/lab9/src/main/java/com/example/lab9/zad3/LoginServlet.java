package com.example.lab9.zad3;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if ("login".equals(action)) {
            // Login existing user

            //////////////////////////////////////////////////////////////////////////////////
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByLogin(login);
            if (user != null) {
                request.getSession().setAttribute("login", user.getLogin());
                request.getSession().setAttribute("role", user.getRole());
                request.getSession().setAttribute("date", LocalDate.now());

                JournalDAO journalDAO = new JournalDAO();

                List<Command> commandList = journalDAO.getUserCommand(user.getId());
                request.setAttribute("commandList", commandList);


                //Cookie time
              Cookie lastSessionTimeCookie = new Cookie("lastSessionTime", LocalDate.now().toString());
              lastSessionTimeCookie.setMaxAge(24 * 60 * 60); // Время жизни cookie в секундах (здесь - 1 день)
              response.addCookie(lastSessionTimeCookie);

              Cookie visitsCountCookie = new Cookie("visitsCount", "1");
              visitsCountCookie.setMaxAge(3600);
              response.addCookie(visitsCountCookie);

              Cookie userTypeCookie = new Cookie("userType", user.getRole());
              userTypeCookie.setMaxAge(3600);
              response.addCookie(userTypeCookie);

                request.getRequestDispatcher("/success.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Неверный логин или пароль");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else if ("register".equals(action)) {

            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String role = "USER";

            UserDAO userDAO = new UserDAO();
            if (userDAO.checkUserExists(login)) {
                request.setAttribute("error", "Пользователь уже существует");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                request.getSession().setAttribute("login", user.getLogin());
                request.getSession().setAttribute("role", user.getRole());
                request.getSession().setAttribute("date", LocalDate.now());



                if (userDAO.addUser(user)) {
                    response.sendRedirect(request.getContextPath() + "/success.jsp");

                } else {
                    request.setAttribute("error", "Ошибка регестрации");

                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
        }
    }
}
