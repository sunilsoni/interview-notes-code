package com.interview.notes.code.months.nov23.hackerearth;

abstract class MyClass {
    int var = 1;

    public MyClass() {
        show();
        var++;
    }

    abstract void myMethod();

    void show() {
        System.out.println(var);
    }
}