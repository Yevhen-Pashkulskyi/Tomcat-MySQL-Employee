package org.example.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.entity.Employee;
import org.example.app.repository.impl.EmployeeRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController extends HttpServlet {
    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/new" -> showNewForm(req, resp);
            case "/insert" -> create(req, resp);
            case "/delete" -> delete(req, resp);
            case "/edit" -> showEditForm(req, resp);
            case "/update" -> update(req, resp);
            default -> read(req, resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String position = req.getParameter("position");
        String phone = req.getParameter("phone");
        Employee employee = new Employee(name, position, phone);
        employeeRepository.create(employee);
        resp.sendRedirect("list");
    }

    private void read(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> listEmployee = employeeRepository.read();
        req.setAttribute("listEmployee", listEmployee);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/employee_list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String position = req.getParameter("position");
        String phone = req.getParameter("phone");
        Employee employee = new Employee(id, name, position, phone);
        employeeRepository.update(employee);
        resp.sendRedirect("list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        employeeRepository.delete(id);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/employee_form.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Employee existingEmployee = employeeRepository.readById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/employee_form.jsp");
        req.setAttribute("employee", existingEmployee);
        dispatcher.forward(req, resp);
    }
}
