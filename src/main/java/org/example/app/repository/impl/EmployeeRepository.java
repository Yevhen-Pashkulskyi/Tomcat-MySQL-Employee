package org.example.app.repository.impl;

import org.example.app.database.DBConn;
import org.example.app.entity.Employee;
import org.example.app.repository.AppRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeRepository implements AppRepository<Employee> {
    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    @Override
    public void create(Employee employee) {
        String sql = "INSERT INTO employee (name, position, phone) VALUES (?, ?, ?)";
        try (Connection connection = DBConn.connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getPosition());
            ps.setString(3, employee.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection connection = DBConn.connect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getString("phone")
                ));
            }
            return employees;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Employee readById(Long id) {
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (Connection connection = DBConn.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee(id, resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getString("phone"));
            }
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return employee;
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, position = ?, phone = ? WHERE id = ?";
        try (Connection connection = DBConn.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setString(3, employee.getPhone());
            preparedStatement.setLong(4, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection connection = DBConn.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
