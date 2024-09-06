package com.interview.notes.code.months.sept24.test1;

import lombok.Data;

import java.time.LocalDate;

@Data
class Employees {
    private int id;
    private String name;
    private double salary;
    private LocalDate joiningDate;

    // Constructor, getters, and setters
    // ...

    public Employees(int id, String name, double salary, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }


}