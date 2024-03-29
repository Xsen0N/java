import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class WrapperConnector {
    private Connection connection;
    public WrapperConnector() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("db.Сувениры");
            String url = resource.getString("");
            String user = resource.getString("user");
            String pass = resource.getString("password");
            Properties prop = new Properties();
            prop.put("user", user);
            prop.put("password", pass);
            connection = DriverManager.getConnection(url, prop);
        } catch (MissingResourceException e) {
            System.err.println("properties file is missing " + e);}
        catch (SQLException e) {
            System.err.println("not obtained connection " + e);
        }
    }
    public Statement getStatement() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }
}
