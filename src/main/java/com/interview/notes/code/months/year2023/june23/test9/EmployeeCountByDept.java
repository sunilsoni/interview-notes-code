package com.interview.notes.code.months.year2023.june23.test9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeCountByDept {
    public static void main(String[] args) {
        List<Employee1> employees = new ArrayList<>();
        // Assuming employees list is populated with Employee objects

        Map<Integer, Long> employeeCountByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee1::getDeptId, Collectors.counting()));

        // Displaying the employee count by department
        for (Map.Entry<Integer, Long> entry : employeeCountByDept.entrySet()) {
            System.out.println("Department ID: " + entry.getKey() + ", Employee Count: " + entry.getValue());
        }
    }
}

class Employee1 {
    private int id;
    private String name;
    private double salary;
    private int deptId;

    // Constructor, getters, and setters

    public int getDeptId() {
        return deptId;
    }
}
