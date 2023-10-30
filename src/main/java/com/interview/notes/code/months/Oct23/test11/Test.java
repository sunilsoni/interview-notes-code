package com.interview.notes.code.months.Oct23.test11;

class BaseClass {
    protected static int counterOne = 1;

    static {
        System.out.println("BaseClass static block");
    }

    protected int counterTwo = 1;

    BaseClass() {
        System.out.println("BaseClass constructor");
    }

    public void demo() {
        System.out.println(counterOne + counterTwo + " - BaseClass demo method - " + (++counterOne + counterTwo++));
    }

    public void anotherDemo() {
        System.out.println("BaseClass another demo method");
    }
}

class DerivedClass extends BaseClass {
    static {
        System.out.println("DerivedClass static block");
    }

    DerivedClass() {
        System.out.println("DerivedClass constructor");
    }

    public void anotherDemo() {
        System.out.println("DerivedClass another demo method");
    }

    public int sum(int x, int y) {
        System.out.println("Called - int sum(int x, int y)");
        return x + y;
    }

    public double sum(double x, double y) {
        System.out.println("Called - double sum(double x, double y)");
        return x + y;
    }
}

public class Test {
    public static void main(String[] args) {
        new BaseClass().demo();
        System.out.println("");

        new BaseClass().demo();
        System.out.println("");

        BaseClass b1 = new DerivedClass();
        b1.anotherDemo();
        System.out.println("");

        DerivedClass d1 = new DerivedClass();
        System.out.println(d1.sum(10, 20));
        System.out.println(d1.sum(10, 10.5));
    }
}
