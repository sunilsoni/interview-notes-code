package com.interview.notes.code.months.sept24.test14;

public class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private int age;

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
