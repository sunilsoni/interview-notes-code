package com.interview.notes.code.months.nov24.test12;

import lombok.Data;

@Data
public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;

    // Constructor, getters, and setters

    public String getMiddleName() {
        return middleName;
    }
}
