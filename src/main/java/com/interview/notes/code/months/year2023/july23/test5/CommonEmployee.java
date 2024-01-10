package com.interview.notes.code.months.year2023.july23.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class CommonEmployee {
    public static void main(String[] args) {
        List<Employee> oldEmployees = new ArrayList<>();
        oldEmployees.add(new Employee(1, "John"));
        oldEmployees.add(new Employee(2, "Alice"));
        oldEmployees.add(new Employee(3, "Bob"));

        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee(3, "Bob"));
        newEmployees.add(new Employee(4, "Eve"));
        newEmployees.add(new Employee(5, "Mike"));

        Set<Integer> oldEmployeeIds = oldEmployees.stream()
                .map(Employee::getId)
                .collect(Collectors.toSet());

        long commonEmployeesCount = newEmployees.stream()
                .filter(employee -> oldEmployeeIds.contains(employee.getId()))
                .count();

        System.out.println("Count of common employees: " + commonEmployeesCount);
    }
}
