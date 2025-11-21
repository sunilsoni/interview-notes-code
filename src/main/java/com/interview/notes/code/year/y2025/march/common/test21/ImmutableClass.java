package com.interview.notes.code.year.y2025.march.common.test21;

import java.util.Date;

record ImmutableClass(String name, int age, Date birthDate) {
    // Constructor to initialize fields
    ImmutableClass(String name, int age, Date birthDate) {
        this.name = name;
        this.age = age;
        this.birthDate = new Date(birthDate.getTime()); // Defensive copy
    }

    @Override
    public Date birthDate() {
        return new Date(birthDate.getTime()); // Defensive copy
    }
}
