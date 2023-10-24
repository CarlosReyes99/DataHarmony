package org.example.project.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String pass = "toor";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if(pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);

            //Sets the initial size of the connection pool.
            pool.setInitialSize(3);
            //Sets the minimum number of idle connections that the pool must maintain at all times.
            pool.setMinIdle(3);
            //Sets the maximum number of total connections that the pool can keep active simultaneously.
            pool.setMaxIdle(3);
            //Sets the maximum number of total connections that the pool can keep active simultaneously.
            pool.setMaxTotal(10);
        }
        return pool;
    }
    public static Connection getConnection() throws SQLException {

        return getInstance().getConnection();
    }



}
