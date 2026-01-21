package com.interview.notes.code.year.y2024.oct24.amazon.test20;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SecondHighestSalaryFinder {

    // Core method using Stream API
    public static Optional<Double> findSecondHighestSalary(List<Employee> employees) {
        return Optional.ofNullable(employees)
                .filter(list -> list.size() >= 2)
                .map(list -> list.stream()
                        .map(Employee::getSalary)
                        .distinct()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst())
                .orElse(Optional.empty());
    }

    public static void main(String[] args) {
        // Test cases using Stream API
        runAllTests();
    }

    private static void runAllTests() {
        // Test case generator using streams
        Stream.of(
                createTestCase("Normal case test",
                        Stream.of(
                                new Employee("John", 50000),
                                new Employee("Alice", 60000),
                                new Employee("Bob", 45000),
                                new Employee("Carol", 55000)
                        ).collect(Collectors.toList()),
                        55000.0),

                createTestCase("Duplicate salaries test",
                        Stream.of(
                                new Employee("John", 50000),
                                new Employee("Alice", 60000),
                                new Employee("Bob", 60000),
                                new Employee("Carol", 50000)
                        ).collect(Collectors.toList()),
                        50000.0),

                createTestCase("Large dataset test",
                        generateLargeDataset(100000),
                        null),

                createTestCase("Minimum employees test",
                        Stream.of(
                                new Employee("John", 50000),
                                new Employee("Alice", 60000)
                        ).collect(Collectors.toList()),
                        50000.0),

                createTestCase("Empty list test",
                        Collections.emptyList(),
                        null),

                createTestCase("Null test",
                        null,
                        null)
        ).forEach(SecondHighestSalaryFinder::executeTest);
    }

    private static TestCase createTestCase(String name, List<Employee> input, Double expectedOutput) {
        return new TestCase(name, input, expectedOutput);
    }

    private static List<Employee> generateLargeDataset(int size) {
        return new Random().doubles(size, 30000, 150000)
                .mapToObj(salary -> new Employee("Emp" + salary, salary))
                .collect(Collectors.toList());
    }

    private static void executeTest(TestCase testCase) {
        try {
            Optional<Double> result = findSecondHighestSalary(testCase.input);

            // Special handling for large dataset test
            if (testCase.name.contains("Large dataset")) {
                System.out.println(testCase.name + " - " +
                        (result.isPresent() ? "PASS" : "FAIL"));
                return;
            }

            // Normal test case validation
            if (testCase.expectedOutput == null) {
                System.out.println(testCase.name + " - " +
                        (!result.isPresent() ? "PASS" : "FAIL"));
            } else {
                System.out.println(testCase.name + " - " +
                        (result.isPresent() &&
                                Math.abs(result.get() - testCase.expectedOutput) < 0.001 ?
                                "PASS" : "FAIL"));
            }
        } catch (Exception e) {
            System.out.println(testCase.name + " - FAIL (Exception: " +
                    e.getMessage() + ")");
        }
    }

    // Test case class using record (Java 14+) or standard class
    private record TestCase(String name, List<Employee> input, Double expectedOutput) {
    }
}