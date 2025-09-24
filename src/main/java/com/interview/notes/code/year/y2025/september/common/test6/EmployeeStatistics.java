package com.interview.notes.code.year.y2025.september.common.test6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmployeeStatistics {                    // class containing data model and methods

    public static Map<String, Map<String, Double>> calculateAverageAgeByGenderPerDept(List<Employee> employees) { // method to compute averages
        return employees.stream()                    // convert list to stream for processing
                .collect(Collectors.groupingBy(Employee::department, // group by department
                        Collectors.groupingBy(Employee::gender,         // within each department, group by gender
                                Collectors.averagingInt(Employee::age)      // calculate average of ages
                        )                                              // end inner groupingBy
                ));                                               // end outer groupingBy and return result
    } // end calculateAverageAgeByGenderPerDept

    public static void main(String[] args) {        // main method to run tests
        // Test case 1: small sample dataset
        List<Employee> test1 = List.of(               // create test list of employees
                new Employee("Alice", 30, 70000, "Female", "HR"), // add Alice in HR
                new Employee("Bob", 40, 80000, "Male", "HR"), // add Bob in HR
                new Employee("Carol", 25, 60000, "Female", "IT"), // add Carol in IT
                new Employee("David", 35, 90000, "Male", "IT")  // add David in IT
        );                                             // end of test1 list

        Map<String, Map<String, Double>> result1 =
                calculateAverageAgeByGenderPerDept(test1); // compute averages for test1

        Map<String, Map<String, Double>> expected1 = new HashMap<>(); // prepare expected results
        expected1.put("HR", Map.of("Female", 30.0, "Male", 40.0));   // expected averages for HR
        expected1.put("IT", Map.of("Female", 25.0, "Male", 35.0));   // expected averages for IT

        System.out.println("Test 1: " + (result1.equals(expected1) ? "PASS" : "FAIL")); // print PASS or FAIL for test1

        // Test case 2: empty list should result in empty map
        List<Employee> test2 = List.of();           // empty test list
        Map<String, Map<String, Double>> result2 =
                calculateAverageAgeByGenderPerDept(test2); // compute for empty list
        System.out.println("Test 2: " + (result2.isEmpty() ? "PASS" : "FAIL"));  // expect PASS if result2 is empty

        // Test case 3: large dataset to check performance and correctness
        int N = 100_000;                             // number of employees for large dataset
        List<Employee> largeList = IntStream.range(0, N) // generate indices from 0 to N-1
                .mapToObj(i -> {                         // map each index to an Employee object
                    String department = "Dept" + (i % 5); // assign 5 possible departments
                    String gender = (i % 2 == 0) ? "Male" : "Female"; // alternate gender
                    int age = (i % 2 == 0) ? 20 : 30; // assign age based on gender
                    return new Employee("Emp" + i, age, 50000 + i, gender, department); // create new Employee
                })                                       // end mapToObj
                .collect(Collectors.toList());          // collect into a list

        Map<String, Map<String, Double>> result3 =
                calculateAverageAgeByGenderPerDept(largeList); // compute averages for largeList
        boolean largeTestPass = result3.values().stream()   // stream over department maps
                .allMatch(map ->                             // verify each map has correct averages
                        map.get("Male") == 20.0 &&
                                map.get("Female") == 30.0
                );                                           // end allMatch

        System.out.println("Test 3 (large data): " + (largeTestPass ? "PASS" : "FAIL")); // print PASS or FAIL for large data test
    } // end main

    record Employee(                                 // record to hold employee data with less boilerplate
                                                     String name,                             // employee name
                                                     int age,                                 // employee age
                                                     double salary,                           // employee salary
                                                     String gender,                           // employee gender
                                                     String department                        // employee department
    ) {
    }                                             // end of record
} // end class EmployeeStatistics