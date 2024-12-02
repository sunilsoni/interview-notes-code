package com.interview.notes.code.year.y2024.april24.test14;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static Employee[] employees;

    // Return all employees that work for a given department and earn a given salary
    public static List<Employee> filterEmployees(String department, Double salary) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment().equals(department) && employee.getSalary().equals(salary)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    // Return if any, an employee who's job title is CEO
    public static Employee findCEO() {
        for (Employee employee : employees) {
            if (employee.getTitle().equals("CEO")) {
                return employee;
            }
        }
        return null; // If no CEO found
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
        for (Employee employee : filteredEmployees) {
            System.out.println(employee.getName());
        }

        Employee ceo = findCEO();
        if (ceo != null) {
            System.out.println("CEO: " + ceo.getName());
        } else {
            System.out.println("No CEO found.");
        }
    }

    public static class Employee {
        private String name;
        private String title;
        private String department;
        private Double salary;

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
