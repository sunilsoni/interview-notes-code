package com.interview.notes.code.months.july24.Test1;

public class Main2 {
    public static void main(String[] args) {
        /* List<Employee> employees = // Your list of Employee objects

        // 1. Find all employees in the "Sales" department
        List<Employee> salesEmployees = employees.stream()
                .filter(employee -> employee.getDepartment().equals("Sales"))
                .collect(Collectors.toList());

        // 2. Find the highest paid employee
        Employee highestPaidEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);

        // 3. Find the employee who has worked for the longest time in the company
        Employee longestWorkingEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getJoiningDate))
                .orElse(null);

        // 4. Increase the salary of all employees in the "HR" department by 10%
        employees.stream()
                .filter(employee -> employee.getDepartment().equals("HR"))
                .forEach(employee -> employee.setSalary(employee.getSalary() * 1.10));

        // Displaying the results
        System.out.println("Employees in Sales department: ");
        salesEmployees.forEach(System.out::println);

        System.out.println("Highest paid employee: " + highestPaidEmployee);

        System.out.println("Employee who has worked for the longest time: " + longestWorkingEmployee);

        System.out.println("Employees in HR department after salary increase: ");
        employees.stream()
                .filter(employee -> employee.getDepartment().equals("HR"))
                .forEach(System.out::println);*/
    }
}

