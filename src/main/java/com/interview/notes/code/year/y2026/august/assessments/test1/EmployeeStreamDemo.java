package com.interview.notes.code.year.y2026.august.assessments.test1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EmployeeStreamDemo {

    public static void main(String[] args) {
        // 1. Thread-safe map to avoid ConcurrentModificationException during concurrent updates
        Map<String, PersonalDetails> employeeMap = new ConcurrentHashMap<>();

        // Preload some sample data
        employeeMap.put("Alice", new PersonalDetails(101, "HR", "alice@company.com", 75000));
        employeeMap.put("Bob", new PersonalDetails(102, "IT", "bob@company.com", 95000));
        employeeMap.put("Charlie", new PersonalDetails(103, "HR", "charlie@company.com", 72000));
        employeeMap.put("David", new PersonalDetails(104, "Finance", "david@company.com", 88000));

        // 2. Simulate concurrent writer thread
        Thread writerThread = new Thread(() -> {
            for (int i = 105; i <= 120; i++) {
                String dept = (i % 2 == 0) ? "HR" : "IT";
                employeeMap.put("Emp_" + i, new PersonalDetails(i, dept, "emp" + i + "@company.com", 60000 + i));
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        writerThread.start();

        // 3. Filter HR employees using Java Streams while updates are occurring
        Map<String, PersonalDetails> hrEmployees = getEmployeesByDepartment(employeeMap, "HR");

        // Display results
        System.out.println("HR Employees found (" + hrEmployees.size() + "):");
        hrEmployees.forEach((name, details) ->
            System.out.println(name + " -> " + details)
        );
    }

    /**
     * Filters employees by department using Streams safely in a concurrent environment.
     */
    public static Map<String, PersonalDetails> getEmployeesByDepartment(
            Map<String, PersonalDetails> map,
            String targetDept) {

        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() != null && targetDept.equalsIgnoreCase(entry.getValue().department()))
                .collect(Collectors.toUnmodifiableMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing // Merge rule for key collisions
                ));
    }

    // Record for immutability and clean syntax
    public record PersonalDetails(int id, String department, String email, double salary) {}
}