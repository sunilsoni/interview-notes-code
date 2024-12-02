package com.interview.notes.code.year.y2024.april24.test12;

public class Student {
    private int id;
    private String firstname;
    private String lastname;

    // Constructor
    public Student(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", First Name: " + firstname + ", Last Name: " + lastname;
    }
}