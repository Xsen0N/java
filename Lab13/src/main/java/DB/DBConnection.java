package DB;


import Models.Users;
import Models.Nation;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;


public class DBConnection {
    private Connection connection;
    private Statement statement;


    public ArrayList<Users> GetAllUsers() throws SQLException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        statement = connection.createStatement();
        String sqlQuery="select * from Users;";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        ArrayList<Users> users = new ArrayList<Users>();
        while(resultSet.next()){
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            String role = resultSet.getString(3);

            Users user = new Users(username,password,role);
            users.add(user);
        }
        return users;
    }

    public boolean AddUser(Users user) throws SQLException {
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        //проверка на существующее имя пользователя
        String select="insert Users(username,password,role) values('"+user.getUsername() +"','"+user.getPassword()+ "','user');";
        // statement = GetConnection().createStatement();
        PreparedStatement pstmt = connection.prepareStatement(select);
        int rowsInserted = pstmt.executeUpdate();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
        return true;
    }

    public Users GetByUsername(String Username) throws SQLException {
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        statement = connection.createStatement();
        String select ="select * from Users where username = '"+Username+"';";

        ResultSet resultSet= statement.executeQuery(select);
        Users user=null;
        if(resultSet != null){
            while(resultSet.next()){
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                String role = resultSet.getString(3);
                user = new Users(username,password,role);
            }
        }
        return  user;
    }

    public Nation GetByname(String names) throws SQLException {
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка с драйвером");
        }
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        statement = connection.createStatement();

        String select ="select * from nation where name = '"+names+"';";
        ResultSet resultSet= statement.executeQuery(select);
        Nation nations=null;
        if(resultSet != null){
            while(resultSet.next()){
                String name = resultSet.getString(1);
                String country = resultSet.getString(2);
                nations = new Nation(name,country);
            }
        }
        return  nations;
    }

    public ArrayList<Nation> GetAllNations() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        statement = connection.createStatement();
        String sqlQuery="select * from nation";
        ResultSet resultSet=statement.executeQuery(sqlQuery);

        ArrayList<Nation> nations = new ArrayList<Nation>();
        while(resultSet.next()){
            String name = resultSet.getString(1);
            String country = resultSet.getString(2);
            Nation nation = new Nation(name,country);
            nations.add(nation);
        }
        return nations;
    }

    public boolean AddNations(Nation nat) throws SQLException {
//        logger.info("Добавление народа");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        statement = connection.createStatement();
        String select="insert nation(name,number ) values('"+nat.getName()+"','"+nat.getNumber()+"')";
        ResultSet resultSet=statement.executeQuery(select);
        return true;
    }

    public boolean Remove(String names) throws SQLException {
//        logger.info("Удаление народа");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        statement = connection.createStatement();
        String select="DELETE FROM nation WHERE name ='"+names+"'";

        ResultSet resultSet=statement.executeQuery(select);

        return true;
    }

    public boolean Update(String names,String number) throws SQLException {
//        logger.info("Изменение народа");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:sqlserver://Host:1433;databaseName=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false", "sa", "12344321");
        statement = connection.createStatement();
        String select="UPDATE nation SET number ='"+number+"' WHERE name ='"+names+"';";
        ResultSet resultSet=statement.executeQuery(select);

        return true;
    }
}