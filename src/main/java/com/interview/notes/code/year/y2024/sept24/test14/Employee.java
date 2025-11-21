package com.interview.notes.code.year.y2024.sept24.test14;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final int id;
    private final int age;

    // Constructor
    public Employee(String firstName, String lastName, int id, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
