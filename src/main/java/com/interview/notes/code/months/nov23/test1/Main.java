package com.interview.notes.code.months.nov23.test1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        // Assuming employees list is populated

        // Find the maximum age among employees
        employees.stream()
                .mapToInt(Employee::getAge)
                .max()
                .ifPresent(oldestAge -> {
                    // Print employees with the oldest age
                    employees.stream()
                            .filter(emp -> emp.getAge() == oldestAge)
                            .forEach(emp -> System.out.println(emp.getName() + " - " + emp.getAge()));
                });
    }
}
