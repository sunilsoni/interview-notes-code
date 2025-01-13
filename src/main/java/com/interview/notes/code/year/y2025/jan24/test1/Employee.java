package com.interview.notes.code.year.y2025.jan24.test1;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructors
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Employee {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}