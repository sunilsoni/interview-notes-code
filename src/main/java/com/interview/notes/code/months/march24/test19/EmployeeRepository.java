package com.interview.notes.code.months.march24.test19;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query method to search for employees by department name and designation
    List<Employee> findByDepartmentNameAndDesignation(String departmentName, String designation);
}
