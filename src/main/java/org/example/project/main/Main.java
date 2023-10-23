package org.example.project.main;

import org.example.project.model.Employee;
import org.example.project.repository.EmployeeRepository;
import org.example.project.repository.Repository;
import org.example.project.util.DatabaseConnection;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection myConn = DatabaseConnection.getInstance()){
            if (myConn.getAutoCommit()){
                myConn.setAutoCommit(false);
            }
            try{
                Repository<Employee> repository= new EmployeeRepository(myConn);
                Employee employee = new Employee();
                employee.setFirst_name("Alexander");
                employee.setPa_surname("Perez");
                employee.setMa_surname("Barbosa");
                employee.setEmail("account@example.com");
                employee.setSalary(3000F);
                employee.setCurp("REJC980514HCSYMR09");
                repository.save(employee);
                myConn.commit();
                System.out.println("Empleado agregado correctamente");

            } catch (SQLException e) {
                myConn.rollback();
                throw new RuntimeException(e);
            }

        }

        }
    }


