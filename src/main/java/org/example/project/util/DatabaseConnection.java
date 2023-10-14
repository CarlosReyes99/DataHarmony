package org.example.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String pass = "toor";
    private static Connection myConn;

    public static Connection getInstance() throws SQLException {
        if(myConn == null){
            myConn = DriverManager.getConnection(url, user, pass);
        }
        return myConn;
    }



}
