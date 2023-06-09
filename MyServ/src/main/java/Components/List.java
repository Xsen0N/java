package Components;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class List {
       private final Connection connection;
    ArrayList<Journal> students = new ArrayList<>();

    public List(Connection connection) throws SQLException {
        this.connection = connection;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM JOURNAL");
        while (resultSet.next()) {
            {
                students.add(new Journal(resultSet.getInt("Id"),
                        resultSet.getInt("SKIPS"),
                        resultSet.getString("NAME"),
                        resultSet.getInt("AVRGRADE")));
            }
        }
    }
    public ArrayList<Journal> getList() {
        return students;
    }
}
