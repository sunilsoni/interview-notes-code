package com.interview.notes.code.months.year2023.june23.test9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Employee {
    private int salary;

    public Employee(int salary) {
        this.salary = salary;
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(50000));
        employees.add(new Employee(60000));
        employees.add(new Employee(70000));
        employees.add(new Employee(80000));
        employees.add(new Employee(90000));

        Optional<Integer> thirdHighestSalary = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2) // Skip the first two highest salaries
                .findFirst();

        if (thirdHighestSalary.isPresent()) {
            System.out.println("Third highest salary: " + thirdHighestSalary.get());
        } else {
            System.out.println("No third highest salary found.");
        }
    }

    public int getSalary() {
        return salary;
    }
}
