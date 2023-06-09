package com.example.lab9.zad3;

import com.mysql.jdbc.Driver;
import org.mindrot.jbcrypt.BCrypt;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class UserDAO {


    public User getUserByLogin(String login) {
        User user = null;


        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username=?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setLogin(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public boolean addUser(User user) {

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (username, password, role) VALUES (?, ?, ?)");
            statement.setString(1, user.getLogin());
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(11));
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkUserExists(String login) {

            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
                 PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM Users WHERE username=?");

                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

             catch (Exception e)  {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }


            return false;
    }
    public User getUserById(int id) {

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        User user = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setLogin(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }



    public boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}