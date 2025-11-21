package com.interview.notes.code.year.y2024.march24.test14;

class Base {
    public static void display() {
        System.out.println("Static method from Base");
    }
}

class Derived extends Base {
    public static void display() {
        System.out.println("Static method from Derived");
    }
}

public class Test {

    public static void main(String[] args) {
        Base obj1 = new Derived();
        Base.display(); // Calls Base's static method
    }
}
