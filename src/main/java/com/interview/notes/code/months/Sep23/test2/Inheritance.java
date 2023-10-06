package com.interview.notes.code.months.Sep23.test2;


//What is the output of the below Java program using Inheritance?
class A {
    int i = 10;

    void show() {
        System.out.println("A is showing. ");
    }
}

class B extends A {
    int i = 20;

    void show() {
        System.out.println("B is showing. ");
    }

    void show1() {
        System.out.println("A is showing new thing. ");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        A a = new B();
        a.show();
        System.out.println(a.i);
        //a.show1();
    }
}
