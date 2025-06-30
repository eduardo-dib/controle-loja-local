
package com.loja.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String URL = "jdbc:sqlite:loja.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }
}
