package com.interview.notes.code.tricky;


/**
 * The reason for mandatory implementation of default methods in multiple interfaces is to avoid ambiguity
 * when multiple interfaces are implemented by a single class. In Java, if two or more interfaces have methods
 * with the same signature and a class implements both interfaces, there needs to be a way to determine
 * which method implementation should be used. By requiring implementation of the default method, the Java
 * language can ensure that there is a clear implementation and avoid any confusion or ambiguity in this scenario.
 */
public class classAB implements InterfaceA,InterfaceB{
    @Override
    public String getGreeting() {
        return InterfaceA.super.getGreeting();
    }
}
