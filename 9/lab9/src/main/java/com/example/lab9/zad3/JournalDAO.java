package com.example.lab9.zad3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {
    String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Users;user=sa;password=12344321";

    public List<Journal> getJournalByUserLogin(String userLogin) {
        List<Journal> journal = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433/accounts?verifyServerCertificate=false&useSSL=false&requireSSL=false", "sa", "12344321");
             PreparedStatement statement = connection.prepareStatement("SELECT *\n" +
                     "FROM JOURNAL us\n" +
                     "INNER JOIN Users u ON u.Id = us.id\n" +
                     "WHERE u.username = ?\n")) {
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Journal item = new Journal(
                        resultSet.getInt("avgmark"),
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("skips")
                );
                journal.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journal;
    }

    ///////////////////////////////////////////////////////////////////////
    public List<Command> getUserCommand(int user_id)
    {
        List<Command> command = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433/accounts?verifyServerCertificate=false&useSSL=false&requireSSL=false", "sa", "12344321");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_info WHERE user_id=?")) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Command item = new Command(
                        resultSet.getInt("user_id"),
                        resultSet.getInt("age")
                );
                command.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return command;
    }
    public List<Journal> getUserJournal(int user_id) {
        List<Journal> journal = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433/accounts?verifyServerCertificate=false&useSSL=false&requireSSL=false", "sa", "12344321");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_info WHERE user_id=?")) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Journal item = new Journal(
                        resultSet.getInt("avgmark"),
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("skips")

                );
                journal.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journal;
    }
    public List<Journal> getAllJournals() {
        List<Journal> JournalList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement("SELECT *\n" +
                     "FROM JOURNAL\n")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Journal item = new Journal(
                        resultSet.getInt("avgmark"),
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("skips")
                );
                JournalList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return JournalList;
    }



    public boolean updateJournal(List<Journal> journals) {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE JOURNAL SET avgmark=? WHERE id=?")) {
            for (Journal item : journals) {
                statement.setInt(1, item.getAvgmark());
                statement.setInt(2, item.getId());
                statement.addBatch();
            }
            int[] rowsUpdated = statement.executeBatch();
            for (int row : rowsUpdated) {
                if (row == 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void saveJournal(List<Journal> journals) {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM JOURNAL WHERE id = ?");
             PreparedStatement insertStatement = connection.prepareStatement(
                     "INSERT INTO JOURNAL (id, name, skips, avgmark) " +
                             "VALUES (?, ?, ?, ?)")) {
            // Удаляем все записи из таблицы JOURNAL для данного пользователя
            statement.setInt(1, journals.get(0).getId());
            statement.executeUpdate();

            // Добавляем новое расписание
            for (Journal journal : journals) {
                insertStatement.setInt(1, journal.getId());
                insertStatement.setString(2, journal.getName());
                insertStatement.setInt(3, journal.getSkips());
                insertStatement.setInt(4, journal.getAvgmark());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
