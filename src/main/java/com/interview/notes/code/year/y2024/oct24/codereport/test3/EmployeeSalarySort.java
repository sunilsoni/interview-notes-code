package com.interview.notes.code.year.y2024.oct24.codereport.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeSalarySort {
    public static void main(String[] args) {
        // Create a HashMap with employee ID as key and salary as value
        Map<String, Double> employeeSalaries = new HashMap<>();
        employeeSalaries.put("EMP001", 50000.0);
        employeeSalaries.put("EMP002", 75000.0);
        employeeSalaries.put("EMP003", 60000.0);
        employeeSalaries.put("EMP004", 90000.0);
        employeeSalaries.put("EMP005", 55000.0);

        // Convert the map to a list of map entries
        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(employeeSalaries.entrySet());

        // Sort the list based on salary values (descending order)
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Display sorted salaries and corresponding employee IDs
        System.out.println("Sorted Salaries (Descending) and Employee IDs:");
        for (Map.Entry<String, Double> entry : sortedList) {
            System.out.printf("Salary: $%.2f, Employee ID: %s%n", entry.getValue(), entry.getKey());
        }
    }
}
