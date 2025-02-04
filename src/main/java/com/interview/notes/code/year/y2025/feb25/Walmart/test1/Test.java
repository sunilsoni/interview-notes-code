package com.interview.notes.code.year.y2025.feb25.Walmart.test1;

interface I1 {
    default void m() {
        System.out.print("I1 ");
    }
}

interface I2 extends I1 {
    default void m() {
        System.out.print("I2 ");
    }
}

public class Test {
    public static void main(String[] args) {
        B b = new B();
        b.m();
        b.callM();
        b.callSuperM();

        A a = new B();
        a.m();
        a.callM();

        A a1 = new A();
        a1.callM();
        a1.m();
    }
}

class A implements I1 {
    public void callM() {
        m();
    }
}

class B extends A implements I2 {
    public void callSuperM() {
        super.m();
    }
}
