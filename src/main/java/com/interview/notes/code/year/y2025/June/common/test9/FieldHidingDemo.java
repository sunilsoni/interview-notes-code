package com.interview.notes.code.year.y2025.June.common.test9;

class Parent {
    int a = 10;
}

class Child extends Parent {
    int a = 20;
}

public class FieldHidingDemo {
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.a);  // prints 10
    }
}
