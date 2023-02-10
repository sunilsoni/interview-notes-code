package com.interview.notes.code.java8;

import java.util.ArrayList;
import java.util.List;

public class Increasessalary {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John Doe", 75000, "IT"));
        employees.add(new Employee(2, "Jane Doe", 80000, "IT"));
        employees.add(new Employee(3, "Jim Smith", 65000, "Marketing"));

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .forEach(employee -> employee.setSalary(employee.getSalary() * 1.2));

        System.out.println("Updated Employees: " + employees);
    }
}
