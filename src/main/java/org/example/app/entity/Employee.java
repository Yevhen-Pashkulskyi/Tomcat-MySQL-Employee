package org.example.app.entity;

public class Employee {
    private Long id;
    private String name;
    private String position;
    private String phoneNumber;

    public Employee(String employeeName, String position, String phoneNumber) {
        this.name = employeeName;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    public Employee(Long id, String employeeName, String position, String phoneNumber) {
        this.id = id;
        this.name = employeeName;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phoneNumber;
    }
}
