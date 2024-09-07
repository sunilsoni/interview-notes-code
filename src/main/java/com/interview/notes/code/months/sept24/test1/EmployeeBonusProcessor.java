package com.interview.notes.code.months.sept24.test1;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeBonusProcessor {

    public static void main(String[] args) {
        // Assume you have fetched this list from a database query
        List<Employees> employees = fetchEmployeesFromDatabase();

        LocalDate twoYearsAgo = LocalDate.now().minusYears(2);

        // Process bonuses using parallel stream
        List<Employees> processedEmployees = employees.parallelStream()
                .filter(employee -> employee.getJoiningDate().isBefore(twoYearsAgo))
                .map(EmployeeBonusProcessor::calculateBonus)
                .collect(Collectors.toList());

        // Print results
        processedEmployees.forEach(e -> System.out.println("Employee " + e.getId() +
                " joined on " + e.getJoiningDate() +
                ", new salary: " + e.getSalary()));

        System.out.println("Total employees processed: " + processedEmployees.size());
    }

    private static Employees calculateBonus(Employees employee) {
        double bonus = employee.getSalary() * 0.10; // 10% bonus
        employee.setSalary(employee.getSalary() + bonus);
        return employee;
    }

    // This method would actually fetch data from your database
    private static List<Employees> fetchEmployeesFromDatabase() {
        // Implement your database query here
        // For now, we'll return a dummy list with various joining dates
        return List.of(
                new Employees(1, "John", 50000, LocalDate.of(2020, 1, 1)),
                new Employees(2, "Alice", 60000, LocalDate.of(2021, 6, 15)),
                new Employees(3, "Bob", 55000, LocalDate.of(2019, 3, 10)),
                new Employees(4, "Carol", 65000, LocalDate.of(2022, 2, 28)),
                new Employees(5, "David", 70000, LocalDate.of(2018, 11, 5))
        );
    }
}

class Employee {
    private int id;
    private String name;
    private double salary;
    private LocalDate joiningDate;

    // Constructor, getters, and setters
    // ...

    public Employee(int id, String name, double salary, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    // Getters and setters
    // ...
}
