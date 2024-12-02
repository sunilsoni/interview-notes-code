package com.interview.notes.code.year.y2023.nov23.hackerearth;

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