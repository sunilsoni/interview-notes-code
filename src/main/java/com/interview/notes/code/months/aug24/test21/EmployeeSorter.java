package com.interview.notes.code.months.aug24.test21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int id;
    private double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + ", salary=" + salary + '}';
    }
}

public class EmployeeSorter {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Doe", 1, 50000.0));
        employees.add(new Employee("Jane Smith", 2, 60000.0));
        employees.add(new Employee("Bob Johnson", 3, 55000.0));
        employees.add(new Employee("Alice Brown", 4, 65000.0));
        employees.add(new Employee("Charlie Davis", 5, 52000.0));

        // Sort employees by salary in ascending order (one-line stream)
        List<Employee> sortedEmployees = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).collect(Collectors.toList());

        System.out.println("Employees sorted by salary (ascending order):");
        sortedEmployees.forEach(System.out::println);

        // Sort employees by salary in descending order (one-line stream)
        List<Employee> sortedEmployeesDesc = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());

        System.out.println("\nEmployees sorted by salary (descending order):");
        sortedEmployeesDesc.forEach(System.out::println);
    }
}
