package com.interview.notes.code.year.y2025.august.common.test6;

// Immutable class
record Person(String name, int age) {
    // Constructor initializes values once

    // toString for printing
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        // Create immutable object
        Person p1 = new Person("Sunil", 30);

        System.out.println(p1);

        // Trying to "modify" values is not possible
        // p1.setName("Other"); ❌ (no setter)

        // The object remains unchanged
        System.out.println("Name: " + p1.name());
        System.out.println("Age: " + p1.age());
    }
}