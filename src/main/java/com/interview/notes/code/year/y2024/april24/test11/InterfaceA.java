package com.interview.notes.code.year.y2024.april24.test11;

interface InterfaceA {
    void commonMethod();
}

interface InterfaceB {
    void commonMethod();
}

class MyClass implements InterfaceA, InterfaceB {
    @Override
    public void commonMethod() {
        // Call InterfaceA's commonMethod
        // InterfaceA.super.commonMethod();
        // Add your implementation specific to InterfaceB here
        System.out.println("Implementing commonMethod() from InterfaceB");
    }
}
