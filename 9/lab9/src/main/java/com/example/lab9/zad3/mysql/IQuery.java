package com.example.lab9.zad3.mysql;

public interface IQuery {
    void Query_1(int orderId);

    void Query_2();

    void Query_3(String product_name);

    void Query_4(String product_name, String date);

    void Query_5(String product_name, int amount);

    void CreatOrder();

    void executeTransaction();
}
