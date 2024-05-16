package com.interview.notes.code.months.may24.test3;

public class Main {
    public static void changeEmployeeName(Employee emp, String newName) {

        emp.setName(newName);
    }

    public static void main(String[] args) {
        Employee emp = new Employee("John");
        System.out.println("Original Name: " + emp.getName()); // Output: Original Name: John

        changeEmployeeName(emp, "Alice");
        System.out.println("Updated Name: " + emp.getName()); // Output: Updated Name: Alice
    }
}