package com.interview.notes.code.months.jan24.test3;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
