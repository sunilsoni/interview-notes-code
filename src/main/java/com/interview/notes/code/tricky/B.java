package com.interview.notes.code.tricky;

class A {
    public A(String s) {
        System.out.print("A");
    }
}

public class B extends A {
    public B(String s) {
        super(s);
        System.out.print("B");
    }

    public static void main(String[] args) {
        new B("C");
        System.out.println(" ");
    }
}