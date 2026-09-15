package com.interview.notes.code.year.y2026.august.common.test1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

record Employee(String name, String vertical, String email) {}

public class Main {
    public static void main(String[] args) {

        Map<String, Employee> employees = new ConcurrentHashMap<>();

        employees.put("101", new Employee("Raj", "IT", "raj@mail.com"));
        employees.put("102", new Employee("Anu", "HR", "anu@mail.com"));
        employees.put("103", new Employee("Sam", "HR", "sam@mail.com"));

        Map<String, Employee> hrEmployees =
                employees.entrySet()
                        .stream()
                        .filter(e -> "HR".equalsIgnoreCase(e.getValue().vertical()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ));

        hrEmployees.forEach((id, emp) ->
                System.out.println(id + " -> " + emp));
    }
}