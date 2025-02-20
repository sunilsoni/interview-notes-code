package com.interview.notes.code.year.y2025.feb25.common.test7;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    int age;
    String gender;
    double salary;

    public Employee(int id, String name, int age, String gender, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "Leela", 31, "Female", 5000.00));
        empList.add(new Employee(2, "Reju", 29, "Male", 6000.00));
        empList.add(new Employee(3, "Remo", 44, "Male", 9500.00));
        empList.add(new Employee(4, "Kose", 21, "Male", 7500.00));
        empList.add(new Employee(5, "Rita", 28, "Female", 4000.00));

        // Using Streams to filter employees whose age is greater than 30
        List<String> filteredNames = empList.stream()
            .filter(employee -> employee.getAge() > 30)
            .map(Employee::getName)
            .collect(Collectors.toList());

        // Printing the filtered employee names
        filteredNames.forEach(System.out::println);
    }
}
