package com.interview.notes.code.year.y2025.jan24.test1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SecondHighestSalaryPerDepartment {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.getEmployees();

        Map<String, List<Employee>> secondHighestSalaryEmployees = employees.stream()
                // Group employees by department
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet().stream()
                // Process each department separately
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            List<Employee> empList = entry.getValue();
                            // Get the distinct salaries in descending order
                            List<Double> salaries = empList.stream()
                                    .map(Employee::getSalary)
                                    .distinct()
                                    .sorted(Comparator.reverseOrder())
                                    .collect(Collectors.toList());
                            if (salaries.size() < 2) {
                                // If there is no second highest salary
                                return Collections.emptyList();
                            }
                            double secondHighestSalary = salaries.get(1);
                            // Filter employees having the second highest salary
                            return empList.stream()
                                    .filter(e -> e.getSalary() == secondHighestSalary)
                                    .collect(Collectors.toList());
                        }
                ));

        // Display the result
        secondHighestSalaryEmployees.forEach((department, empList) -> {
            System.out.println("Department: " + department);
            if (empList.isEmpty()) {
                System.out.println("No second highest salary.");
            } else {
                empList.forEach(System.out::println);
            }
            System.out.println();
        });
    }
}