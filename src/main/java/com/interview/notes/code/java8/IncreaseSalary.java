package com.interview.notes.code.java8;

import java.util.Arrays;
import java.util.List;


//employee class id name salary increase all salary in IT department by 20% using java 8
class Employee {
    private int id;
    private int age;
    private String name;
    private String city;
    private double salary;
    private String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}

public class IncreaseSalary {
    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer("aaaaa");
        //  System.out.println(sb.insert(3, false));
        // System.out.println(sb.insert(5, 3.3).insert(7, "One"));
        System.out.println(sb.insert(3, false).insert(5, 3.3).insert(7, "One"));


        List<Employee1> employees = Arrays.asList(
                new Employee1(1, "John Doe", 75000, "IT"),
                new Employee1(2, "Jane Doe", 80000, "IT"),
                new Employee1(3, "Jim Smith", 65000, "HR"),
                new Employee1(4, "Lisa Smith", 90000, "IT")
        );

        employees.stream()
                .filter(e -> e.getDepartment().equals("IT"))
                .forEach(e -> e.setSalary(e.getSalary() * 1.2));

        // employees.forEach(e -> System.out.println(e.getName() + ": $" + e.getSalary()));
    }
}
