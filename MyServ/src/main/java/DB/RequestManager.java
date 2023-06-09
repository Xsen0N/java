package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
public class RequestManager {

    private final Connection connection;

    public RequestManager(Connection connection) {
        this.connection = connection;
    }
    public void addSubject(int ID,int grad, String name, int skips) throws SQLException {
        PreparedStatement st =connection.prepareStatement("INSERT INTO JOURNAL (AVRGRADE,Id,NAME,SKIPS) VALUES (?,?,?,?)");
        st.setInt(1,grad);
        st.setInt(2,ID);
        st.setString(3,name);
        st.setInt(4,skips);
        st.executeUpdate();
    }
    public void addUser(String username, String password, String role) throws SQLException   {
        PreparedStatement st = connection.prepareStatement("INSERT INTO Users (username, password, role) VALUES (?, ?, ?)");
        st.setString(1, username);
        st.setString(2, password);
        st.setString(3, role);
        st.executeUpdate();
    }

    public boolean isUser(String username, String password, String role) throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT username, password, role FROM Users WHERE username = ? AND password = ? AND role = ?");
        st.setString(1, username);
        st.setString(2, password);
        st.setString(3, role);

        ResultSet resultSet = st.executeQuery();
        return resultSet.next();
    }


    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
