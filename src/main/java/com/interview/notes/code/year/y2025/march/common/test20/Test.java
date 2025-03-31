package com.interview.notes.code.year.y2025.march.common.test20;

import java.util.HashMap;
import java.util.Objects;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // ✅ Override equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && Objects.equals(name, person.name);
    }

    // ✅ Override hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
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

        System.out.println("Profession: " + profession); // ✅ Will print: Software Developer
    }
}
