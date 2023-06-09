package com.example.lab9.zad3.mysql;

/*
import org.apache.log4j.*;
*/

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DAO implements IConnection, IQuery {
    private String url;
/*
    private static final Logger log = Logger.getLogger(DAO.class);
*/
    private String user;
    private String password;
    private Connection con;         // соединение
    private Statement statement;    // стейтмент для запросов

    public boolean getProperties(String fileName)
    {
        /*log.info("getProperties!");*/
        Properties props = new Properties();

        try(InputStream in = Files.newInputStream(Paths.get(fileName))){
            props.load(in);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return false;
        }
        url = props.getProperty("url");
        user = props.getProperty("username");
        password = props.getProperty("password");

        return true;
    }


    @Override
    public Boolean Connect()
    {
        try
        {

/*
            log.info("Connect!");
*/
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            // Устанавливаем соединение с помощью полей юрл юзер пассворд
            con = DriverManager.getConnection(url, user, password);
            // Через стейтмент выполняем любые операции с БД: переменная statement далее используется вкаждом запросе
            statement = con.createStatement();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public Boolean CloseConnect() {
        try
        {
            // В конце закрываем соединение и выводим сообщение
            con.close();
/*
            log.info("Connection closed!");
*/
            return true;
        } catch (Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }


    @Override
    public void Query_1(int orderId) {
        //Вывести полную информацию о заданном заказе

        try
        {
/*
            log.info("Query_1!");
*/

            String preparedQuery =
                            "select order_number, receipt_date, product_name, amount\n" +
                                    "from orders join orders_products on orders.orders_products_id = orders_products.orders_products_id\n" +
                                    "where orders.order_number = ?\n";

            // Для таких изменемых запросов нужен PreparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String order_number = resultSet.getString(1);
                String receipt_date = resultSet.getString(2);
                String product_name = resultSet.getString(3);
                String amount = resultSet.getString(4);
                System.out.println(order_number +" | "+receipt_date+" | "+ product_name+" | "+ amount);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error in query1!");
/*
            log.error("Error in query1!!");
*/
            System.out.println(ex);
        }

    }

    @Override
    public void Query_2() {
        //Вывести номера заказов, сумма которых не превосходит заданную и
        //количество различных товаров равно заданному

        // executeQuery - метод statement для выполнения запросов
        try
        {
/*
            log.info("Query_2!");
*/
            ResultSet resultSet = statement.executeQuery(
                    "select orders.order_number \n" +
                            "from orders join orders_products on\n" +
                            "orders.orders_products_id = orders_products.orders_products_id\n" +
                            "join products on orders_products.product_name = products.product_name\n" +
                            "where orders_products.amount * products.price <= 300\n" +
                            "and orders_products.amount < 10"
            );

            // пока в ResultSet (результирующий набор строк) есть строки, выбираем и выводим как нам угодно нужные столбцы
            while(resultSet.next())
            {
                // Получаем данные по колонкам: в первой колонке - стринги, во второй - даты
                int order_id = resultSet.getInt(1);
                System.out.println(order_id);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error in query2!");
/*
            log.error("Error in query2!");
*/
            System.out.println(ex);
        }
    }

    @Override
    public void Query_3(String product_name) {
        //Вывести номера заказов, содержащих заданный товар
        try
        {
/*
            log.info("Query_3!");
*/

            String preparedQuery =
                    "select orders.order_number \n" +
                            "from orders join orders_products on\n" +
                            "orders.orders_products_id = orders_products.orders_products_id\n" +
                            "where orders_products.product_name = ?";

            // Для таких изменемых запросов нужен PreparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(preparedQuery);
            preparedStatement.setString(1, product_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String order_number = resultSet.getString(1);

                System.out.println(order_number);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error in query3!");
/*
            log.error("Query_3 error");
*/
            System.out.println(ex);
        }
    }

    @Override
    public void Query_4(String product_name, String date) {
        //Вывести номера заказов, не содержащих заданный товар и
        //поступивших в течение текущего дня

        //Вывести номера заказов, содержащих заданный товар
        try
        {
/*
            log.info("Query_4!");
*/

            String preparedQuery =
                    "select orders.order_number \n" +
                            "from orders join orders_products on\n" +
                            "orders.orders_products_id = orders_products.orders_products_id\n" +
                            "where orders.receipt_date = ? " +
                            "and orders_products.product_name not like ?";

            // Для таких изменемых запросов нужен PreparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(preparedQuery);
            preparedStatement.setString(2, product_name);

            LocalDate date_p = LocalDate.parse(date);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date_p);


            preparedStatement.setDate(1, sqlDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String order_number = resultSet.getString(1);

                System.out.println(order_number);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error in query4!");
/*
            log.error("Query_4 error");
*/
            System.out.println(ex);
        }
    }

    /*@Override
    public String Query_5() {
        //Сформировать новый заказ, состоящий из товаров, заказанных в
        //текущий день



        return null;
    }*/

    @Override
    public void Query_5(String product_name, int amount) {
        //Удалить все заказы, в которых присутствует заданное количество
        //заданного товара

        try
        {
/*
            log.info("Query_5");
*/

            String preparedQuery =
                    "delete from orders\n" +
                            "where orders.orders_products_id in (select orders_products.orders_products_id from orders_products \n" +
                            "\t\t\t\t\t\t\t\t\twhere product_name = ? and amount = ?)";

            // Для таких изменемых запросов нужен PreparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(preparedQuery);
            preparedStatement.setString(1, product_name);

            preparedStatement.setInt(2, amount);
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println("Deleted " + affectedRows + " rows");
        }
        catch (Exception ex)
        {
            System.out.println("Error in query6!");
/*
            log.error("Query_5 error");
*/
            System.out.println(ex);
        }
    }

    @Override
    public void CreatOrder() {
        try {
            int rows = statement.executeUpdate("INSERT orders(order_number, receipt_date, orders_products_id) VALUES ('0','2022-01-01','0')");
            System.out.println("Add "+rows+ "rows");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void executeTransaction() {
        try {
/*
            log.info("executeTransaction");
*/

            // начало транзакции
            con.setAutoCommit(false);

            // выполнение операций внутри транзакции
            statement.executeUpdate("UPDATE products SET price = price - 100 WHERE price = 30000");
            statement.executeUpdate("INSERT INTO products (product_name, description, price) VALUES ('Холодильник', 'крутой', 150)");


            // подтверждение транзакции
            con.commit();
/*
            log.info("commit Transaction");
*/

            // обработка ошибок
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());


            try {
                con.rollback();
/*
                log.info("rollback Transaction");
*/
            } catch (SQLException ex2) {
                System.out.println("Rollback Exception: " + ex2.getMessage());
            }

        }


    }
}
