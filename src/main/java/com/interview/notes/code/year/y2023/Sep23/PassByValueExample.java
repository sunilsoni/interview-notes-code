package com.interview.notes.code.year.y2023.Sep23;

public class PassByValueExample {
    public static void main(String[] args) {
        Person person = new Person("Alice");
        modifyName(person);
        System.out.println(person.getName()); // Output: Bob
    }

    public static void modifyName(Person p) {
        p.setName("Bob");
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
