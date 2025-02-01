package com.interview.notes.code.year.y2025.jan25.glider.test3;

class A {
    public void display() {
        System.out.println("Class A display");
    }
}

class B extends A {
    @Override
    public void display() {
        System.out.println("Class B display");
    }
}

public class Test {
    public static void main(String[] args) {
        A obj = new B();
        obj.display();
    }
}
