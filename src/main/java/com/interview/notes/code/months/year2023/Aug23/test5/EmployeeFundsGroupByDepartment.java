package com.interview.notes.code.months.year2023.Aug23.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private double funds;

    public Employee(String name, String department, double funds) {
        this.name = name;
        this.department = department;
        this.funds = funds;
    }

    public String getDepartment() {
        return department;
    }

    public double getFunds() {
        return funds;
    }
}

public class EmployeeFundsGroupByDepartment {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Employee1", "DepartmentA", 1000.0));
        employees.add(new Employee("Employee2", "DepartmentB", 1500.0));
        employees.add(new Employee("Employee3", "DepartmentA", 800.0));
        employees.add(new Employee("Employee4", "DepartmentB", 2000.0));

        Map<String, Double> departmentFundsSum = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.summingDouble(Employee::getFunds)));

        // Print the sum of funds for each department
        departmentFundsSum.forEach((department, sum) ->
                System.out.println("Department: " + department + ", Total Funds: " + sum));
    }
}
