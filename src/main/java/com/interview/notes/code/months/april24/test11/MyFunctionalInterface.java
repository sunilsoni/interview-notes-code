package com.interview.notes.code.months.april24.test11;

@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod(); // Single abstract method

    // Other default or static methods are allowed
    default void anotherMethod() {
        System.out.println("This is a default method");
    }
}