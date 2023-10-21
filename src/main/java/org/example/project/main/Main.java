package org.example.project.main;

import org.example.project.model.Employee;
import org.example.project.repository.EmployeeRepository;
import org.example.project.repository.Repository;
import org.example.project.util.DatabaseConnection;
import view.SwingApp;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        SwingApp swingApp = new SwingApp();
        swingApp.setVisible(true);
    }
}

