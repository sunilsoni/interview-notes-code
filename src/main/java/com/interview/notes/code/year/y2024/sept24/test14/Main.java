package com.interview.notes.code.year.y2024.sept24.test14;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "Doe", 1, 45));
        employees.add(new Employee("Jane", "Smith", 2, 55));
        employees.add(new Employee("Emily", "Jones", 3, 60));
        employees.add(new Employee("Michael", "Brown", 4, 40));

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getAge() > 50)
                .collect(Collectors.toList());

        filteredEmployees.forEach(System.out::println);
    }
}
