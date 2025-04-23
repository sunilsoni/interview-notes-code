package com.interview.notes.code.year.y2025.april.common.test9;

class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal("JJ");
        System.out.println(dog.name);
    }
}
