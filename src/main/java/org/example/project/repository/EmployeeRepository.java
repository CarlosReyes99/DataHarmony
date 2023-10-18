package org.example.project.repository;

import org.example.project.model.Employee;
import org.example.project.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (
                Statement mystatm = getConnection().createStatement();
                ResultSet myResult = mystatm.executeQuery("SELECT * FROM employees")) {
            while (myResult.next()) {
                createEmployee(myResult);
                Employee e = createEmployee(myResult);
                employees.add(e);
            }
        }

        return employees;
    }


    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try (PreparedStatement myStatm = getConnection().prepareStatement("SELECT * FROM employees WHERE id= ?")) {
            myStatm.setInt(1, id);
            try (ResultSet myRes = myStatm.executeQuery()) {
                if (myRes.next()){
                    employee = createEmployee(myRes);
                }

            }
        }

        return employee;
    }


    @Override
    /**
     * Save or update an employee in the database.
     *
     * @param employee The object of type Employee to save or update.
     * @throws SQLException If an error occurs when interacting with the database.
     */
    public void save(Employee employee) throws SQLException {

        String sql;
        boolean update= false;
        // Determines whether an update or insert should be performed on the database.
        if(employee.getId() > 0) {
            sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ? WHERE id = ?";
            update= true;
        }else{
            sql ="INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES( ?,?,?,?,?)";
        }

        try(
        PreparedStatement myStamt = getConnection().prepareStatement(sql)){
            // Set the parameters of the SQL query.
            myStamt.setString(1, employee.getFirst_name());
            myStamt.setString(2, employee.getPa_surname());
            myStamt.setString(3, employee.getMa_surname());
            myStamt.setString(4, employee.getEmail());
            myStamt.setFloat(5, employee.getSalary());
            if(update){
                // If it is an update, set the ID value additionally.
                myStamt.setInt(6, employee.getId());
                int rowsUpdated = myStamt.executeUpdate();
                if(rowsUpdated>0){
                    System.out.println("Datos actualizados correctamente: "+rowsUpdated);
                }
            }else{
                // If it is an insert, execute the query without the ID.
                myStamt.executeUpdate();
                System.out.println("Empleado creado correctamente");
            }
        }
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