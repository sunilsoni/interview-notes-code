package com.interview.notes.code.misc;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//https://java2blog.com/find-duplicate-elements-in-stream-java/
public class FindDuplicateElementsUsingStream {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "john", "doe"),
                new Employee(2, "peter", "parker"),
                new Employee(3, "mary", "public"),
                new Employee(4, "charles", "darwin"),
                new Employee(1, "john", "doe"),
                new Employee(3, "mary", "public")
        );

        Set<Employee> duplicateEmployees = employees.stream()
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(employee -> employee.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        duplicateEmployees.forEach(System.out::println);
        //Employee{firstName='john', lastName='doe'}
        //Employee{firstName='mary', lastName='public'}

        List<String> languages = Arrays.asList(
                "english",
                "chinese",
                "french",
                "spanish",
                "hindi",
                "english",
                "french"
        );
        Set<String> uniqueLanguages = new HashSet<>();
        Set<String> duplicateLanguages = languages.stream()
                .filter(language -> !uniqueLanguages.add(language))
                .collect(Collectors.toSet());

        duplicateLanguages.forEach(System.out::println);
        //english
        //french
    }

    static class Employee {
        private int id;
        private String firstName;
        private String lastName;

        public Employee(int id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return id == employee.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

}
