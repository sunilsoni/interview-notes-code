package com.interview.notes.code.year.y2024.june24.test4;

import java.util.List;

public class Employee {
    private String name;
    private List<String> phoneNumbers;

    // Constructor
    public Employee(String name, List<String> phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
}