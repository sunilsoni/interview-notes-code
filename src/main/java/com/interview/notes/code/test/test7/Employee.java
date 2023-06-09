package com.interview.notes.code.test.test7;

import java.util.TreeSet;

public class Employee {
    private int employeeId;
    private String name;

    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setName("John Doe");

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setName("Jane Smith");

        Employee employee3 = new Employee();
        employee3.setEmployeeId(3);
        employee3.setName("Mark Johnson");

        System.out.println("Employee 1 - ID: " + employee1.getEmployeeId() + ", Name: " + employee1.getName());
        System.out.println("Employee 2 - ID: " + employee2.getEmployeeId() + ", Name: " + employee2.getName());
        System.out.println("Employee 3 - ID: " + employee3.getEmployeeId() + ", Name: " + employee3.getName());

        TreeSet<Employee> treeset = new TreeSet<>();

        Employee e1 = new Employee();

        Employee e2 = new Employee();

        e1.setEmployeeId(1);
        e1.setName("B");

        e2.setEmployeeId(2);
        e2.setName("A");

        treeset.add(e1);//Exception in thread "mai
        treeset.add(e2);
        System.out.println(treeset);


    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}