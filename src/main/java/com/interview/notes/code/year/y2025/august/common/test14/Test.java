package com.interview.notes.code.year.y2025.august.common.test14;

class A {
    A() {
        System.out.println("A called");
    }
}

class B extends A {
    B() {
        System.out.println("B called");
    }
}

class C extends B {
    C() {
        System.out.println("C called");
    }
}

public class Test {
    public static void main(String[] args) {
        //A c1 = new C();  // Case 1
       // A a1 = new A();  // Case 2

        B b = new B();// output?
    }
}