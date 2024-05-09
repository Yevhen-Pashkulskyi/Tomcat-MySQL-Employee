package org.example.app.entity;

public class Employee {
    private Long employeeId;
    private String employeeName;
    private String position;
    private String phoneNumber;

    public Employee(String employeeName, String position, String phoneNumber) {
        this.employeeName = employeeName;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    public Employee(Long employeeId, String employeeName, String position, String phoneNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPosition() {
        return position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
