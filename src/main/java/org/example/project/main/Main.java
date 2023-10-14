package org.example.project.main;

import org.example.project.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {



        try(Connection myConn = DatabaseConnection.getInstance();
            Statement mystatm = myConn.createStatement();
            ResultSet myResult = mystatm.executeQuery("SELECT * FROM employees");) {

            System.out.println("Successful Conection");
            while(myResult.next()){
                System.out.println("El nombre es: "+myResult.getString("first_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("There was an error in the connection.");
        }
        }
}

