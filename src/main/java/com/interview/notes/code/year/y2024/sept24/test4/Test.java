package com.interview.notes.code.year.y2024.sept24.test4;

class Base {
    public static void display() {
        System.out.println("Static or class method from Base");
    }

    public void print() {
        System.out.println("Non-static or Instance method from Base");
    }
}

class Derived extends Base {
    public static void display() {
        System.out.println("Static or class method from Derived");
    }

    @Override
    public void print() {
        System.out.println("Non-static or Instance method from Derived");
    }
}

public class Test {
    public static void main(String[] args) {
        Base obj1 = new Derived();
        Base.display(); // Calls Base class's static method (class method binding)
        obj1.print();   // Calls Derived class's instance method (dynamic method dispatch)
    }
}
