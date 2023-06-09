import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.*;

public class InsertData {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(InsertData.class);
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:sqlserver://Host:1433;databaseName=Souvenirs;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
        String username = "sa";
        String password = "12344321";

        try {
            LOG.info("Starting program for inserting data_____________________________");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
            LOG.info("Connected to database_____________________________");
         //  String query = "INSERT INTO MANUFACTURER (prodname, Country) VALUES (?, ?)";
         //  PreparedStatement pstmt = conn.prepareStatement(query);

         //  pstmt.setString(1, "Glass_master");
         //  pstmt.setString(2, "France");
            LOG.info("Connected to database_____________________________");
            LOG.info("added values into table_____________________________");
           String query = "INSERT INTO SOUVENIRS (Title, Producer_requisite, Date_of_issue, Producer, Price) VALUES (?, ?, ?,?,?)";
           PreparedStatement pstmt = conn.prepareStatement(query);

           pstmt.setString(1, "glass_ball");
           pstmt.setString(2, "Ltd");
           pstmt.setDate(3, java.sql.Date.valueOf("2013-09-04"));
           pstmt.setString(4, "Glass_master");
           pstmt.setLong(5, 200);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " rows inserted");
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Disconnected from database");
                    LOG.info("Disconnected from database_____________________________");
                    LOG.info("Ending program_____________________________");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
