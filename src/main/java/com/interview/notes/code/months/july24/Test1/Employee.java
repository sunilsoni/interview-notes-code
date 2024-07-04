package com.interview.notes.code.months.july24.Test1;

import lombok.Data;

import java.time.LocalDate;

@Data
class Employee {
    private String name;
    private String department;
    private double salary;
    private LocalDate joiningDate;

    // Constructor, getters, setters
}