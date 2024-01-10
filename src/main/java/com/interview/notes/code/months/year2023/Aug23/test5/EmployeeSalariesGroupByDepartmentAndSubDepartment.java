package com.interview.notes.code.months.year2023.Aug23.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee1 {
    private String name;
    private String id;
    private double salary;
    private String department;
    private String subDepartment;

    public Employee1(String name, String id, double salary, String department, String subDepartment) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.department = department;
        this.subDepartment = subDepartment;
    }

    public String getDepartment() {
        return department;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeSalariesGroupByDepartmentAndSubDepartment {
    public static void main(String[] args) {
        List<Employee1> Employee1s = new ArrayList<>();
        Employee1s.add(new Employee1("Employee11", "1", 1000.0, "DepartmentA", "SubDepartmentA1"));
        Employee1s.add(new Employee1("Employee12", "2", 1500.0, "DepartmentB", "SubDepartmentB1"));
        Employee1s.add(new Employee1("Employee13", "3", 800.0, "DepartmentA", "SubDepartmentA2"));
        Employee1s.add(new Employee1("Employee14", "4", 2000.0, "DepartmentB", "SubDepartmentB1"));

        Map<String, Map<String, Double>> departmentSubDepartmentSalariesSum = Employee1s.stream()
                .collect(Collectors.groupingBy(Employee1::getDepartment,
                        Collectors.groupingBy(Employee1::getSubDepartment,
                                Collectors.summingDouble(Employee1::getSalary))));

        // Print the sum of salaries for each department and sub-department
        departmentSubDepartmentSalariesSum.forEach((department, subDepartmentMap) -> {
            System.out.println("Department: " + department);
            subDepartmentMap.forEach((subDepartment, sum) ->
                    System.out.println("  Sub-Department: " + subDepartment + ", Total Salary: " + sum));
        });
    }
}
