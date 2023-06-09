package DB;

import java.sql.*;

public class DB {
    private Connection connection;
    public DB() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlserver://Host;database=Users;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
        String user = "sa";
        String pass = "12344321";
        connection = DriverManager.getConnection(url, user,pass);
    }
    public void addUser(String username, String password, String role) throws SQLException {
        Statement myStmt = connection.createStatement();
        myStmt.execute("INSERT INTO `Users` (`username`, `password`, `role`) " +
                "VALUES ('"+username+"', '"+password.hashCode()+"', '"+role+"');");
    }
    public boolean isUser(String username, String password, String role) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT `username`, `password`, `role` FROM `Users` WHERE `username` = '"+username+
                "' AND `password` = '"+password.hashCode()+"' AND `role` = '"+role+"'");

        ResultSet resultSet = st.executeQuery();
        return resultSet.next();
    }
    public void closeConnection() throws SQLException {
        //ClearTableUsers();
        connection.close();
    }
    public void FillTableUsers() throws SQLException {
        Statement myStmt = connection.createStatement();
        myStmt.execute("INSERT INTO `Users` (`Login`, `password`, `Role`) " +
                "VALUES ('Kate', '"+"1234".hashCode()+"', 'admin'),"+
                "       ('User', '"+"12345".hashCode()+"', 'user'),"+
                "       ('Hun', '"+"123".hashCode()+"', 'user')");
    }
    public void ClearTableUsers() throws SQLException {
        Statement myStmt = connection.createStatement();
        myStmt.execute("DELETE FROM `Users`");
    }
}
