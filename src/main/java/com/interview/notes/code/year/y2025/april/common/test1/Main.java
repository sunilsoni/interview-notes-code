package com.interview.notes.code.year.y2025.april.common.test1;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal("JJ");
        System.out.println(dog.name);
    }
}

class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }
}
