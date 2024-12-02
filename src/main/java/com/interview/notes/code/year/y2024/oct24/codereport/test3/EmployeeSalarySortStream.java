package com.interview.notes.code.year.y2024.oct24.codereport.test3;

import java.util.HashMap;
import java.util.Map;

public class EmployeeSalarySortStream {
    public static void main(String[] args) {
        // Create a HashMap with employee ID as key and salary as value
        Map<String, Double> employeeSalaries = new HashMap<>();
        employeeSalaries.put("EMP001", 50000.0);
        employeeSalaries.put("EMP002", 75000.0);
        employeeSalaries.put("EMP003", 60000.0);
        employeeSalaries.put("EMP004", 90000.0);
        employeeSalaries.put("EMP005", 55000.0);

        // Sort and display salaries and corresponding employee IDs using streams
        System.out.println("Sorted Salaries (Descending) and Employee IDs:");
        employeeSalaries.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("Salary: $%.2f, Employee ID: %s%n",
                        entry.getValue(), entry.getKey()));
    }
}
