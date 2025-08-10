package com.interview.notes.code.year.y2025.august.common.test12;

import java.util.HashMap;

class Person {
    String name;
    String address;

    Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Uncomment these methods to test custom equality behavior

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && address.equals(person.address);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + address.hashCode();
    }


    @Override
    public String toString() {
        return name + " - " + address;
    }
}

public class HashMapTest {
    public static void main(String[] args) {
        Person p1 = new Person("P1", "p1 address");

        HashMap<Person, String> map = new HashMap<>();

        map.put(p1, "value1");
        map.put(p1, "value2");

        System.out.println("Map size: " + map.size());
        System.out.println("Value for p1: " + map.get(p1));

        // Just for validation: print all entries
        for (Person key : map.keySet()) {
            System.out.println("Key: " + key + " -> Value: " + map.get(key));
        }
    }
}
