package com.interview.notes.code.test.test3;

public interface MyInterface {
    default void defaultMethod1() {
        System.out.println("Default method 1");
    }

    default void defaultMethod2() {
        System.out.println("Default method 2");
    }
}

 class MyClass implements MyInterface {
    @Override
    public void defaultMethod1() {
        System.out.println("Overridden default method 1");
    }
}

 class Main1 {
    public static void main(String[] args) {
        MyClass myObject = new MyClass();
        myObject.defaultMethod1(); // Output: Overridden default method 1
        myObject.defaultMethod2(); // Output: Default method 2

        MyInterface myInterface = myObject;
        myInterface.defaultMethod1(); // Output: Overridden default method 1
        myInterface.defaultMethod2(); // Output: Default method 2 (using interface name)
    }
}
