package com.interview.notes.code.year.y2024.june24.test5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 25, "Male"),
                new Employee("Jane", 30, "Female"),
                new Employee("Mike", 40, "Male"),
                new Employee("Emily", 35, "Female"),
                new Employee("Tom", 50, "Male")
        );

        Map<String, Long> genderCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        System.out.println(genderCount);
/*

        List<Employee> employees = Arrays.asList(
                new Employee("John", Arrays.asList("123-456-7890", "987-654-3210")),
                new Employee("Jane", Arrays.asList("555-123-4567")),
                new Employee("Mike", Arrays.asList("333-222-1111", "444-555-6666"))
        );

        List<String> allPhoneNumbers = employees.stream()
                .flatMap(employee -> employee.getPhoneNumbers().stream())
                .collect(Collectors.toList());

        System.out.println(allPhoneNumbers);*/
    }
}