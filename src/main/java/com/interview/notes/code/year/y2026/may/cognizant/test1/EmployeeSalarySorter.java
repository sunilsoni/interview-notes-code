package com.interview.notes.code.year.y2026.may.cognizant.test1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class EmployeeSalarySorter {

    static List<Employee> getEmployeesBelow10000(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.salary() < 10000)
                .sorted(Comparator.comparingDouble(Employee::salary))
                .toList();
    }

    static void test(String name, List<Employee> input, List<Employee> expected) {
        List<Employee> actual = getEmployeesBelow10000(input);
        System.out.println(name + " : " + (actual.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("A", 12000),
                new Employee("B", 8000),
                new Employee("C", 5000),
                new Employee("D", 10000),
                new Employee("E", 9000)
        );

        test("Normal Case",
                employees,
                List.of(
                        new Employee("C", 5000),
                        new Employee("B", 8000),
                        new Employee("E", 9000)
                )
        );

        test("No Match",
                List.of(new Employee("A", 10000), new Employee("B", 15000)),
                List.of()
        );

        test("Empty List",
                List.of(),
                List.of()
        );

        List<Employee> largeData = IntStream.rangeClosed(1, 100000)
                .mapToObj(i -> new Employee("Emp" + i, i % 20000))
                .toList();

        System.out.println("Large Data : " +
                (getEmployeesBelow10000(largeData).stream().allMatch(e -> e.salary() < 10000)
                        ? "PASS"
                        : "FAIL"));
    }

    record Employee(String name, double salary) {}
}