package DB;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDb {
    public ConnectorDb() {
        try {
            DriverManager.registerDriver(new SQLServerDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(String dataBase) throws SQLException {
        String url = String.format("jdbc:sqlserver://Host:1433;database=%s;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false",dataBase);
        String user = "sa";
        String password = "12344321";
        return DriverManager.getConnection(url, user, password);
    }
}
