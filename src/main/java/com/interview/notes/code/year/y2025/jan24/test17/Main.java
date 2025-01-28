package com.interview.notes.code.year.y2025.jan24.test17;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Assume Employee class and Employee objects (e1, e2, e3) are properly defined.
        Map<String, Employee> employeeMap = new HashMap<>();
//        employeeMap.put("1", new Employee("John"));
//        employeeMap.put("2", new Employee("Doe"));
//        employeeMap.put("3", new Employee("Jane"));

        // Printing all employee names
        for (Employee employee : employeeMap.values()) {
            System.out.println(employee.getName());
        }

        employeeMap.values().forEach(System.out::println);
    }
}

