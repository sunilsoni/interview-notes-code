package com.interview.notes.code.months.year2023.Aug23.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    String firstName;
    String lastName;
    int age;
    double salary;
    String gender;

    // Constructor
    Employee(String firstName, String lastName, int age, double salary, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public double getSalary() {
        return salary;
    }

    public String getGender() {
        return gender;
    }
}

/**
 * complete below in java8 lambda astream
 * List<Employee>
 * <p>
 * Employee has firstName, lastName,age , salary , gender
 * <p>
 * List<String. as an out where string is firstname
 * <p>
 * <p>
 * <p>
 * condition -
 * <p>
 * 1. All female employee whose salary is greater than 50k
 * <p>
 * 2.  create a hasmap  where key is salary  and value is List string
 */
public class FemaleFirstNames {
    public static void main(String[] args) {
        // Sample list of Employee objects
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Johnson", 28, 60000, "female"),
                new Employee("Bob", "Smith", 30, 40000, "male"),
                new Employee("Cathy", "Williams", 35, 55000, "female")
        );

        // Condition 1: All female employees whose salary is greater than 50k
        List<String> femaleFirstNames = employees.stream()
                .filter(e -> e.getGender().equalsIgnoreCase("female") && e.getSalary() > 50000)
                .map(Employee::getFirstName)
                .collect(Collectors.toList());

        System.out.println("Female employees with salary greater than 50k: " + femaleFirstNames);

        // Condition 2: Create a HashMap where key is salary and value is List of first names
        Map<Double, List<String>> salaryToFirstNamesMap = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getSalary,
                        Collectors.mapping(Employee::getFirstName, Collectors.toList())
                ));

        System.out.println("Salary to first names map: " + salaryToFirstNamesMap);
    }
}
