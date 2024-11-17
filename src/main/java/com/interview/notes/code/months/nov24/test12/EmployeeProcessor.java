package com.interview.notes.code.months.nov24.test12;

import java.util.*;
import java.util.stream.*;
/*

Java8 STream

class Employee {
    String name;
    String department;
    int salary;
    int age;
}


Write a stream operation that:
Filters employees who are in the "Sales" department and earn a salary greater than 50000.
Groups these employees by age.
For each age group, returns the name of the employee with the highest salary.
If two or more employees in an age group have the same salary, include both names.

 */
public class EmployeeProcessor {
    public static Map<Integer, List<String>> processEmployees(List<Employee> employees) {
        return employees.stream()
            // Filter employees in "Sales" department with salary > 50000
            .filter(e -> "Sales".equals(e.department) && e.salary > 50000)
            // Group by age
            .collect(Collectors.groupingBy(
                e -> e.age,
                Collectors.collectingAndThen(
                    Collectors.groupingBy(
                        e -> e.salary,
                        Collectors.mapping(e -> e.name, Collectors.toList())
                    ),
                    salaryMap -> {
                        // Find the highest salary
                        int maxSalary = salaryMap.keySet().stream()
                            .mapToInt(Integer::intValue)
                            .max()
                            .orElse(0);
                        // Return names of employees with the highest salary
                        return salaryMap.get(maxSalary);
                    }
                )
            ));
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("John", "Sales", 60000, 30),
            new Employee("Alice", "Sales", 55000, 28),
            new Employee("Bob", "Sales", 65000, 30),
            new Employee("Charlie", "Marketing", 52000, 35),
            new Employee("David", "Sales", 70000, 35),
            new Employee("Eve", "Sales", 70000, 35)
        );

        Map<Integer, List<String>> result = processEmployees(employees);
        result.forEach((age, names) -> 
            System.out.println("Age " + age + ": " + String.join(", ", names)));
    }
}
