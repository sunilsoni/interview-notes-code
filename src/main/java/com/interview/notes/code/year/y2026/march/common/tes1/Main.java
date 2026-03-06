package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Employee1> employee1s = Arrays.asList(
                new Employee1("A", 50000),
                new Employee1("B", 70000),
                new Employee1("C", 60000),
                new Employee1("D", 70000)
        );

        Optional<Double> secondHighest =
                employee1s.stream()
                        .map(Employee1::getSalary)
                        .distinct()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst();

        if (secondHighest.isPresent()) {
            System.out.println("Second Highest Salary: " + secondHighest.get());
        } else {
            System.out.println("Not enough data");
        }
    }
}