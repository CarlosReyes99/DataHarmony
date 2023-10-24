package org.example.project.main;

import org.example.project.model.Employee;
import org.example.project.repository.EmployeeRepository;
import org.example.project.repository.Repository;
import org.example.project.util.DatabaseConnection;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("------Listing employees------");
        Repository<Employee> repository= new EmployeeRepository();
        repository.findAll().forEach(System.out::println);

        }
    }


