package com.interview.notes.code.year.y2025.march.common.test21;

import java.util.Date;

final class ImmutableClass {
    private final String name;
    private final int age;
    private final Date birthDate;

    // Constructor to initialize fields
    public ImmutableClass(String name, int age, Date birthDate) {
        this.name = name;
        this.age = age;
        this.birthDate = new Date(birthDate.getTime()); // Defensive copy
    }

    // Only getters, no setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthDate() {
        return new Date(birthDate.getTime()); // Defensive copy
    }
}
