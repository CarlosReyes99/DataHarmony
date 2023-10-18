package org.example.project.main;

import org.example.project.model.Employee;
import org.example.project.repository.EmployeeRepository;
import org.example.project.repository.Repository;
import org.example.project.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {



        try(Connection myConn = DatabaseConnection.getInstance();){
            Repository<Employee> repository = new EmployeeRepository();
            repository.findAll().forEach(System.out::println);
            System.out.println("\n \nThe employee you searched for is: " + repository.getById(2));
            Employee employee = new Employee();

            System.out.println("\n *******UPDATING EMPLOYEE******");
            employee.setId(1);
            employee.setFirst_name("Test Actualizado");
            employee.setPa_surname("Reyes");
            employee.setMa_surname("Jimenez");
            employee.setEmail("test_actualizar@gmail.com");
            employee.setSalary((float)15000);
            repository.save(employee);
            System.out.println("\n *******UPDATING EMPLOYEE******");

            repository.findAll().forEach(System.out::println);

        }
    }
}

