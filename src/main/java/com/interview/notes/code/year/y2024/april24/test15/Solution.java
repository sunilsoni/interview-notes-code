package com.interview.notes.code.year.y2024.april24.test15;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Solution {
    public static Employee[] employees;

    // Return all employees that work for a given department and earn a given salary
    public static List<Employee> filterEmployees(String department, Double salary) {
        return Arrays.stream(employees)
                .filter(employee -> employee.getDepartment().equals(department) && employee.getSalary().equals(salary))
                .toList();
    }

    // Return if any, an employee who's job title is CEO
    public static Optional<Employee> findCEO() {
        return Arrays.stream(employees)
                .filter(employee -> employee.getTitle().equals("CEO"))
                .findFirst();
    }

    public static void main(String[] args) {
        // Example usage
        employees = new Employee[]{
                new Employee("John Doe", "CEO", "Management", 100000.0),
                new Employee("Jane Smith", "Manager", "Management", 80000.0),
                new Employee("Alice Johnson", "Engineer", "Engineering", 90000.0)
        };

        List<Employee> filteredEmployees = filterEmployees("Management", 100000.0);
        System.out.println("Employees in Management department earning 100000.0: ");
        filteredEmployees.forEach(employee -> System.out.println(employee.getName()));

        Optional<Employee> ceo = findCEO();
        ceo.ifPresentOrElse(employee -> System.out.println("CEO: " + employee.getName()),
                () -> System.out.println("No CEO found."));
    }

    public static class Employee {
        private final String name;
        private final String title;
        private final String department;
        private final Double salary;

        public Employee(String name, String title, String department, Double salary) {
            this.name = name;
            this.title = title;
            this.department = department;
            this.salary = salary;
        }

        public String getDepartment() {
            return this.department;
        }

        public String getName() {
            return this.name;
        }

        public Double getSalary() {
            return this.salary;
        }

        public String getTitle() {
            return this.title;
        }
    }
}
