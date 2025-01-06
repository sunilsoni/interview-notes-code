package com.interview.notes.code.year.y2025.jan24;

import java.util.*;
import java.util.stream.Collectors;

// Employee class
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class SecondHighestSalaryByDepartment {
    public static void main(String[] args) {
        // Sample employee list
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", "IT", 75000),
            new Employee(2, "Jane", "IT", 82000),
            new Employee(3, "Bob", "IT", 65000),
            new Employee(4, "Alice", "HR", 45000),
            new Employee(5, "Mike", "HR", 50000),
            new Employee(6, "Sarah", "HR", 50000),
            new Employee(7, "Tom", "Finance", 65000),
            new Employee(8, "Peter", "Finance", 72000),
            new Employee(9, "Mary", "Finance", 68000)
        );

        // Solution 1: Using groupingBy and custom collector
        Map<String, Optional<Employee>> secondHighestSalaries1 = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    departmentEmployees -> departmentEmployees.stream()
                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                        .skip(1)
                        .findFirst()
                )
            ));

        // Solution 2: Using groupingBy and sorting
        Map<String, List<Employee>> secondHighestSalaries2 = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    departmentEmployees -> departmentEmployees.stream()
                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                        .skip(1)
                        .limit(1)
                        .collect(Collectors.toList())
                )
            ));

        // Solution 3: Using distinct salaries to handle duplicates
        Map<String, Optional<Employee>> secondHighestSalaries3 = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    departmentEmployees -> departmentEmployees.stream()
                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                        .map(Employee::getSalary)
                        .distinct()
                        .skip(1)
                        .findFirst()
                        .flatMap(salary -> departmentEmployees.stream()
                            .filter(emp -> emp.getSalary() == salary)
                            .findFirst())
                )
            ));

        // Solution 4: Using TreeSet
        Map<String, Employee> secondHighestSalaries4 = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                    Collectors.toCollection(() -> 
                        new TreeSet<>(Comparator.comparingDouble(Employee::getSalary).reversed())),
                    TreeSet::stream
                )
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().skip(1).findFirst().orElse(null)
            ));

        // Print results
        System.out.println("Solution 1 Results:");
        secondHighestSalaries1.forEach((dept, emp) -> 
            System.out.println(dept + ": " + emp.orElse(null)));

        System.out.println("\nSolution 2 Results:");
        secondHighestSalaries2.forEach((dept, empList) -> 
            System.out.println(dept + ": " + (empList.isEmpty() ? null : empList.get(0))));

        System.out.println("\nSolution 3 Results:");
        secondHighestSalaries3.forEach((dept, emp) -> 
            System.out.println(dept + ": " + emp.orElse(null)));

        System.out.println("\nSolution 4 Results:");
        secondHighestSalaries4.forEach((dept, emp) -> 
            System.out.println(dept + ": " + emp));
    }
}

// Utility class for handling cases with duplicate salaries
class SalaryRank {
    private final Employee employee;
    private final long rank;

    public SalaryRank(Employee employee, long rank) {
        this.employee = employee;
        this.rank = rank;
    }

    public Employee getEmployee() { return employee; }
    public long getRank() { return rank; }
}

// Additional solution for handling duplicate salaries
class DuplicateSalaryHandler {
    public static Map<String, List<Employee>> getSecondHighestSalaryEmployees(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    departmentEmployees -> {
                        // Group by salary and count occurrences
                        Map<Double, Long> salaryRanks = departmentEmployees.stream()
                            .map(Employee::getSalary)
                            .distinct()
                            .sorted(Comparator.reverseOrder())
                            .skip(1)
                            .limit(1)
                            .collect(Collectors.toList())
                            .stream()
                            .collect(Collectors.toMap(
                                salary -> salary,
                                salary -> departmentEmployees.stream()
                                    .filter(e -> e.getSalary() == salary)
                                    .count()
                            ));

                        // Get employees with second highest salary
                        return departmentEmployees.stream()
                            .filter(emp -> salaryRanks.containsKey(emp.getSalary()))
                            .collect(Collectors.toList());
                    }
                )
            ));
    }
}
