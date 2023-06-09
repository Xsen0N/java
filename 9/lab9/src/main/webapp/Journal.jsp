<%@ page import="com.example.lab9.zad3.JournalDAO" %>
<%@ page import="com.example.lab9.zad3.Journal" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ksenia Zhuk
  Date: 04.05.2023
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%
    String userIdParam = request.getParameter("user_id");
    int userId = -1;
    if (userIdParam != null && !userIdParam.isEmpty()) {
        userId = Integer.parseInt(userIdParam);
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update and Save Journal</title>
</head>
<body>
<h1> Список студентов</h1>
<%
    JournalDAO journalDAO = new JournalDAO();

    if(request.getMethod().equals("POST")) {
        // Получаем данные из формы
        List<Journal> journal = (List<Journal>) request.getAttribute("journal");

        // Обновляем и сохраняем расписание
        boolean updateSuccess = journalDAO.updateJournal(journal);
        journalDAO.saveJournal(journal);

        if(updateSuccess) {
            System.out.println("<p>Журнал успешно обновлен и сохранен!</p>");
        } else {
            System.out.println("<p>Ошибка при обновлении!</p>");
        }
    } else {
        // Получаем текущее расписание пользователя
        List<Journal> journals = journalDAO.getUserJournal(userId);
        List<Journal> journals1 = journalDAO.getAllJournals();
%>

<form method="post">
    <table>
        <tr>
            <th>Id</th>
            <th>Имя</th>
            <th>Пропуски</th>
            <th>Оценка</th>
        </tr>
        <% for(Journal item : journals1) { %>
        <tr>
            <td><input type="text" name="id" value="<%= item.getId() %>"></td>
            <td><input type="text" name="name" value="<%= item.getName() %>"></td>
            <td><input type="text" name="skips" value="<%= item.getSkips() %>"></td>
            <td><input type="text" name="avgmark" value="<%= item.getAvgmark() %>"></td>
        </tr>
        <% } %>
    </table>
    <h2>Добавить студента</h2>
    <form method="post" action="edit-schedule">
        <label for="id">Id:</label>
        <input type="text" name="id" id="id" required><br>
        <label for="name">name:</label>
        <input type="text" name="name" id="name" required><br>
        <label for="mark">Mark:</label>
        <input type="text" name="mark" id="mark" required><br>
        <label for="skip">Skip:</label>
        <input type="text" name="skip" id="skip" required><br>
        <input type="submit" value="Add Entry">
    </form>
    <input type="submit" value="Обновить и сохранить расписание">
    <%
            // Сохраняем в атрибут запроса
            request.setAttribute("journal", journals1);
        }
    %>
</form>
</body>
</html>
