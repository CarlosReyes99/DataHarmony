package org.example.project.repository;

import org.example.project.model.Employee;
import org.example.project.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee>{
   private Connection getConnection() throws SQLException {
       return DatabaseConnection.getInstance();
   }
    @Override
    public List<Employee> findAll() throws SQLException {
       List<Employee> employees = new ArrayList<>();
        try(
            Statement mystatm = getConnection().createStatement();
            ResultSet myResult = mystatm.executeQuery("SELECT * FROM employees")) {
            while(myResult.next()){
                createEmployee(myResult);
            }
        }

        return employees;
    }



    @Override
    public Employee getById(Integer id) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(Integer id) {

    }

    private Employee createEmployee(ResultSet myResult) throws SQLException {
        Employee e = new Employee();
        e.setId(myResult.getInt("id"));
        e.setFirst_name(myResult.getString("first_name"));
        e.setPa_surname(myResult.getString("pa_surname"));
        e.setMa_surname(myResult.getString("ma_surname"));
        e.setEmail(myResult.getString("email"));
        e.setSalary(myResult.getFloat("salary"));
       return e;
    }
}
