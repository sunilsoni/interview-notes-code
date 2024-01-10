package com.interview.notes.code.months.jan24.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

    // Sample list of employees
    private List<Employee> employees = new ArrayList<>();

    // Constructor to initialize employees (you can load data from a database or other source)
    public EmployeeService() {
      /*  employees.add(new Employee(1, "John", 60.0, 55));
        employees.add(new Employee(2, "Alice", 45.0, 40));
        employees.add(new Employee(3, "Bob", 55.0, 60));
        employees.add(new Employee(4, "Eve", 40.0, 35));
        employees.add(new Employee(5, "Charlie", 70.0, 70));*/
    }

    // Method to calculate the sum of salaries for employees over 50 years old
    public double getSumOfSalariesForEmployeesOver50() {
        return 0.0;/*employees.stream()
                .filter(employee -> employee.getAge() > 50)
                .mapToDouble(Employee::getSalary)
                .sum();*/
    }

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();

        double sumOfSalaries = employeeService.getSumOfSalariesForEmployeesOver50();

        System.out.println("Sum of salaries for employees over 50: " + sumOfSalaries);
    }
}
