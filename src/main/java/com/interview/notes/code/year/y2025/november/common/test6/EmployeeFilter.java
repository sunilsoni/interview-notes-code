package com.interview.notes.code.year.y2025.november.common.test6;

import java.util.List;
import java.util.stream.Collectors;

// Assuming Employee class has getName(), getGender(), and getSalary() methods.
public class EmployeeFilter {

    /**
     * Filters the list of employees and returns the names of those whose
     * gender is female and salary is greater than 30000.
     * * @param employeeList The pre-populated list of Employee objects.
     * @return A List<String> containing the names of the filtered employees.
     */
    public List<String> getFilteredEmployeeNames(List<Employee> employeeList) {
        
        List<String> employeeNames = employeeList.stream()
            // Filter: Gender is female (case-insensitive) AND salary > 30000
            .filter(e -> "female".equalsIgnoreCase(e.gender()) && e.salary() > 30000)
            // Map: Extract only the name
            .map(Employee::name)
            // Collect: Gather the results into a List<String>
            .collect(Collectors.toList());

        return employeeNames;
    }
}