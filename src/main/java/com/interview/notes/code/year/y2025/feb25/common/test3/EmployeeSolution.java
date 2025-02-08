package com.interview.notes.code.year.y2025.feb25.common.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EmployeeSolution {

    // 1) Consumer to print current thread name and employee list
    static Consumer<List<Employee>> listConsumer = list -> {
        System.out.println("Thread: " + Thread.currentThread().getName());
        list.forEach(e -> System.out.println(e.getFullName()));
    };
    // Supplier providing employee info strings
    static Supplier<List<String>> employeeInfoSupplier = () -> {
        List<String> employeeStringList = new ArrayList<>();
        // Format: first name, last name, age, salary, department name, manager name
        employeeStringList.add("John,Doe,30,50000.00,development,Jane Doe");
        employeeStringList.add("Jane,Doe,32,55000.00,development,Jane Doe");
        employeeStringList.add("John,Smith,44,60000.00,testing,Tom Hanks");
        employeeStringList.add("Tom,Hanks,55,65000.00,testing,Tom Hanks");
        employeeStringList.add("Tom,Hanks,55,65000.00,testing,Tom Hanks");
        employeeStringList.add("Kenny,Dalglish,60,99000,R&D,Kenny Dalglish");
        employeeStringList.add("James,Milner,37,100000.00,R&D,Kenny Dalglish");
        employeeStringList.add("Jordan,Henderson,32,120000.00,R&D,Kenny Dalglish");
        employeeStringList.add("Tom,Hanks,55,65000.00,testing,Tom Hanks");
        employeeStringList.add("Seth,Myers,18,20000.00,development,Jane Doe");
        employeeStringList.add("Ulyses,Ustinov,36,86000.00,R&D,Kenny Dalglish");
        return employeeStringList;
    };

    public static void main(String[] args) {
        // 2) Create a list of Employee from employeeInfoSupplier data
        List<Employee> employees = parseEmployees(employeeInfoSupplier.get());
        listConsumer.accept(employees);

        // 6) Group employees by salary range
        Map<EmployeeLevel, List<Employee>> eMap = getEmployeesBySalary(employees);
        System.out.println("employeeMap is: " + eMap);
    }

    // Convert CSV strings into Employee objects
    static List<Employee> parseEmployees(List<String> data) {
        List<Employee> employees = new ArrayList<>();
        for (String s : data) {
            String[] parts = s.split(",");
            if (parts.length >= 6) {
                String firstName = parts[0];
                String lastName = parts[1];
                Integer age = Integer.parseInt(parts[2]);
                Double salary = Double.parseDouble(parts[3]);
                String deptName = parts[4];
                String managerName = parts[5];
                Employee emp = new Employee(firstName, lastName, age, salary);
                Department dept = new Department(deptName, managerName);
                emp.setDepartment(dept);
                employees.add(emp);
            }
        }
        return employees;
    }

    // Group employees based on salary ranges
    static Map<EmployeeLevel, List<Employee>> getEmployeesBySalary(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(emp -> {
            double salary = emp.getSalary();
            if (salary < 50000) return EmployeeLevel.JUNIORS;
            else if (salary <= 60000) return EmployeeLevel.SENIORS;
            else return EmployeeLevel.DIRECTORS;
        }));
    }

    // Enum for salary range levels
    enum EmployeeLevel {
        JUNIORS, SENIORS, DIRECTORS
    }
}

class Employee {
    private String firstName;
    private String lastName;
    private Integer age;
    private Double salary;
    private Department department;
    private List<Employee> subordinates;
    private Employee manager;

    public Employee(String firstName, String lastName, Integer age, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public Integer getAge() {
        return this.age;
    }

    public Double getSalary() {
        return this.salary;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;
        return age.equals(employee.age);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }
}

class Department {
    String name;
    String manager;
    List<Employee> members;

    public Department(String name, String manager) {
        this.name = name;
        this.manager = manager;
    }

    public String getName() {
        return this.name;
    }

    public String getEmployee() {
        return this.manager;
    }

    @Override
    public String toString() {
        return this.name;
    }
}