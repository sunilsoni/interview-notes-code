package com.interview.notes.code.year.y2026.june.common.test9;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main { // main class

    static Optional<Double> sixthHighestSalary(List<Employee> employees) { // method to find 6th highest salary
        return employees.stream() // convert employee list to stream
                .map(Employee::salary) // take salary from each employee
                .distinct() // remove duplicate salaries
                .sorted(Comparator.reverseOrder()) // sort salary from high to low
                .skip(5) // skip first 5 highest salaries
                .findFirst(); // take 6th highest salary
    }

    static List<Employee> employeesWithSixthHighestSalary(List<Employee> employees) { // method to get employees
        var salary = sixthHighestSalary(employees); // find 6th highest salary

        return employees.stream() // convert employee list to stream
                .filter(e -> salary.isPresent() && e.salary() == salary.get()) // keep employees with 6th salary
                .toList(); // collect result as list
    }

    static void test(String name, List<Employee> employees, Optional<Double> expected) { // test method
        var actual = sixthHighestSalary(employees); // get actual result

        System.out.println(actual.equals(expected) ? "PASS" : "FAIL"); // print pass or fail
        System.out.println("Test     : " + name); // print test name
        System.out.println("Expected : " + expected); // print expected output
        System.out.println("Actual   : " + actual); // print actual output
        System.out.println("Employees: " + employeesWithSixthHighestSalary(employees)); // print employees
        System.out.println(); // print empty line
    }

    public static void main(String[] args) { // program starts here

        var employees = List.of( // sample employee list
                new Employee(1, "Amit", 90000, "IT"), // employee 1
                new Employee(2, "Ravi", 80000, "IT"), // employee 2
                new Employee(3, "Priya", 80000, "HR"), // duplicate salary
                new Employee(4, "Neha", 70000, "Finance"), // employee 4
                new Employee(5, "John", 60000, "IT"), // employee 5
                new Employee(6, "Kiran", 50000, "Admin"), // employee 6
                new Employee(7, "Sita", 40000, "HR"), // employee 7
                new Employee(8, "Raj", 30000, "Support") // employee 8
        ); // end list

        test("Normal case", employees, Optional.of(40000.0)); // 6th highest unique salary test

        test("Less than 6 unique salaries", List.of( // list with fewer salaries
                new Employee(1, "A", 90000, "IT"), // employee 1
                new Employee(2, "B", 80000, "IT") // employee 2
        ), Optional.empty()); // no 6th salary expected

        test("Duplicate salaries", List.of( // list with duplicate salaries
                new Employee(1, "A", 90000, "IT"), // salary 1
                new Employee(2, "B", 80000, "IT"), // salary 2
                new Employee(3, "C", 70000, "IT"), // salary 3
                new Employee(4, "D", 60000, "IT"), // salary 4
                new Employee(5, "E", 50000, "IT"), // salary 5
                new Employee(6, "F", 40000, "IT"), // salary 6
                new Employee(7, "G", 40000, "HR") // duplicate 6th salary
        ), Optional.of(40000.0)); // expected 6th unique salary

        var large = IntStream.rangeClosed(1, 100000) // create numbers from 1 to 100000
                .mapToObj(i -> new Employee(i, "Emp" + i, i, "Dept")) // create employee for each number
                .toList(); // collect as list

        test("Large data case", large, Optional.of(99995.0)); // 6th highest from 100000 to 1
    }

    record Employee(int employeeId, String name, double salary, String department) {} // employee object
}