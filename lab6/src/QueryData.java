import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.SQLException;
import java.sql.*;

public class QueryData {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(QueryData.class);
    public static void main(String[] args) {
        LOG.info("Starting program for requests_____________________________");
        Connection conn = null;
        String url = "jdbc:sqlserver://Host:1433;databaseName=Souvenirs;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
        String username = "sa";
        String password = "12344321";
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
            LOG.info("Connected to database_____________________________");
            Statement stmt = conn.createStatement();
            String query = "select Title, Producer_requisite from SOUVENIRS\n" +
                    "where Producer = 'Toy_master'";
            ResultSet rs = stmt.executeQuery(query);
            LOG.info("Created query№1_____________________________");
            while (rs.next()) {
                String column1 = rs.getString("Title");
                String column2 = rs.getString("Producer_requisite");

                System.out.println( "Title: " + column1 + ", Producer_requisite: " + column2 );
            }
            System.out.println( "- - - - - - - - - - - - - - - - - - - - - - - " );
            conn.commit();
//-------------------------------------------------------------------------------------------------
            LOG.info("Created query№2_____________________________");

            String query1 = "SELECT Title, Producer, Price from SOUVENIRS, MANUFACTURER\n" +
                    "where Country = 'France';";

            ResultSet rs1 = stmt.executeQuery(query1);

            while (rs1.next()) {
                String column1 = rs1.getString("Title");
                String column2 = rs1.getString("Producer");
                long column3 = rs1.getLong("Price");

                System.out.println( "Title: " + column1 + ", Producer: " + column2+ ", Price: " + column3 );
            }
            System.out.println( "- - - - - - - - - - - - - - - - - - - - - - - " );
            conn.commit();
//-------------------------------------------------------------------------------------------------
            LOG.info("Created query№3_____________________________");
            String query2 = "select Title, Producer, Price, Country from SOUVENIRS, MANUFACTURER\n" +
                    "where Price < 200;";

            ResultSet rs2 = stmt.executeQuery(query2);

            while (rs2.next()) {
                String column1 = rs2.getString("Title");
                String column2 = rs2.getString("Producer");
                long column3 = rs2.getLong("Price");
                String column4 = rs2.getString("Country");

                System.out.println( "Title: " + column1 + ", Producer: " + column2+ ", Price: " + column3 + ", Country: " + column4  );
            }
            System.out.println( "- - - - - - - - - - - - - - - - - - - - - - - " );
            conn.commit();
//-------------------------------------------------------------------------------------------------
            LOG.info("Created query№4_____________________________");
            String query3 = "select prodname, Country from  MANUFACTURER,SOUVENIRS\n" +
                    "where Title  in ('doll', 'magnet') and Date_of_issue < '2015-12-12';";

            ResultSet rs3 = stmt.executeQuery(query3);

            while (rs3.next()) {
                String column1 = rs3.getString("prodname");
                String column2 = rs3.getString("Country");

                System.out.println( " Producer: " + column1+  ", Country: " + column2  );
            }
            System.out.println( "- - - - - - - - - - - - - - - - - - - - - - - " );
            conn.commit();
//-------------------------------------------------------------------------------------------------
            LOG.info("Created query№5_____________________________");
            String query4 = "delete from SOUVENIRS \n" +
                    "where Producer ='New_master'";

            int rs4 = stmt.executeUpdate(query4);

            while (rs3.next()) {
                System.out.println("Deleted" );
            }
            System.out.println( "- - - - - - - - - - - - - - - - - - - - - - - " );
            conn.commit();
            //-------------------------------------------------------------------------------------------------
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Disconnected from database");
                    LOG.info("Disconnected from database_____________________________");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                        System.out.println("Disconnected from database");
                        LOG.info("Disconnected from database_____________________________");
                        LOG.info("ending program_____________________________");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}