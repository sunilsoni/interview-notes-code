package com.interview.notes.code.months.jan24.test3;

import lombok.Data;

@Data
class Employee {
    private int id;
    private String name;
    private double salary;

    // Constructor, getters, and setters here...

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}
