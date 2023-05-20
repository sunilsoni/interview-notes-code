package com.interview.notes.code.test.test1;

public interface Interface1 {
    default void myMethod() {
        System.out.println("Default method in Interface1");
    }
}

interface Interface2 {
    default void myMethod() {
        System.out.println("Default method in Interface2");
    }
}

class MyClass implements Interface1 ,Interface2{
    public void myMethod() {
        Interface1.super.myMethod(); // Calling the default implementation from Interface1
    }
}
