package com.interview.notes.code.year.y2025.march.common.test21;

import java.util.HashMap;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Test {
    public static void main(String[] args) {
        HashMap<Person, String> personMap = new HashMap<>();

        Person person1 = new Person("Sunil", 30);
        Person person2 = new Person("Alice", 25);
        Person person3 = new Person("Bob", 35);

        personMap.put(person1, "Software Developer");
        personMap.put(person2, "Data Scientist");
        personMap.put(person3, "Product Manager");

        // Try retrieving with a new Person object with same data
        Person searchPerson = new Person("Sunil", 30);
        String profession = personMap.get(searchPerson);

        System.out.println("Profession: " + profession); // ❌ Will print: null
    }
}
