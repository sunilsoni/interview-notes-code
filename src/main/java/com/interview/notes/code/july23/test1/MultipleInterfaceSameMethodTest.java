package com.interview.notes.code.july23.test1;

interface Interface1 {
    void abstractMethod();
}

interface Interface2 {
    void abstractMethod();
}

class MyClass implements Interface1, Interface2 {
    @Override
    public void abstractMethod() {
        System.out.println("Implementation of abstractMethod()");
    }
}

public class MultipleInterfaceSameMethodTest {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.abstractMethod();
    }
}
