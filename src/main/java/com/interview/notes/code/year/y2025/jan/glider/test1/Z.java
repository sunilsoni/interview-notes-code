package com.interview.notes.code.year.y2025.jan.glider.test1;

class A {
    public void display() throws Exception {
        System.out.println("Class A display");
    }
}

class B extends A {
    @Override
    public void display() throws Exception {
        System.out.println("Class B display");
    }
}

public class Z {
    public static void main(String[] args) {
        try {
            A obj = new B();
            obj.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
