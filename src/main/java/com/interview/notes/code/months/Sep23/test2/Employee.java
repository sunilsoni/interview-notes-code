package com.interview.notes.code.months.Sep23.test2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Employee {
    int id;
    String name;
    int age;
    String gender;
    String departmentName;
    int yearOfJoining;
    double salary;

    // Assuming getters and a constructor for all fields
    public Employee(int id, String name, int age, String gender, String departmentName, int yearOfJoining, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.departmentName = departmentName;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 28, "Male", "Sales", 2019, 50000),
                new Employee(2, "Doe", 25, "Male", "Product Development", 2020, 60000),
                new Employee(3, "Anna", 24, "Female", "Product Development", 2022, 65000),
                new Employee(4, "Mike", 23, "Male", "Product Development", 2021, 62000)
        );

        Employee youngestMaleInProductDevelopment = employees.stream()
                .filter(emp -> "Male".equals(emp.getGender()) && "Product Development".equals(emp.getDepartmentName()))
                .min(Comparator.comparingInt(Employee::getAge))
                .orElse(null);

        System.out.println(youngestMaleInProductDevelopment);
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", yearOfJoining=" + yearOfJoining +
                ", salary=" + salary +
                '}';
    }
}
