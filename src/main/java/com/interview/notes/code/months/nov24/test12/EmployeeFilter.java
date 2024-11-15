package com.interview.notes.code.months.nov24.test12;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFilter {
    public static List<Employee> filterEmployeesByMiddleName(List<Employee> employees, String middleName) {
        return employees.stream()
                .filter(employee -> employee.getMiddleName().equalsIgnoreCase(middleName))
                .collect(Collectors.toList());
    }
}
