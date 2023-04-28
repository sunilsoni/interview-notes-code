package com.interview.notes.code.test.test1;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private int id;

    public Student(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    // Override equals() method to check if first name, last name, and id are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return id == student.id &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName);
    }

    // Override hashCode() method to generate a hash code based on first name, last name, and id
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }
}
