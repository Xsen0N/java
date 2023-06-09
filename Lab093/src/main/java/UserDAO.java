import java.sql.*;

public class UserDAO {

    // Здесь нужно указать параметры для подключения к базе данных
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/Users";
    private static final String USER = "username";
    private static final String PASS = "password";

    public boolean checkLogin(String username, String password) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Регистрируем драйвер JDBC
            Class.forName(JDBC_DRIVER);

            // Устанавливаем соединение с базой данных
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Выполняем запрос на выборку данных из таблицы пользователей
            String sql = "SELECT * FROM users WHERE Login = ? AND Password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            // Если запрос вернул какие-то данные, значит логин и пароль верные
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}