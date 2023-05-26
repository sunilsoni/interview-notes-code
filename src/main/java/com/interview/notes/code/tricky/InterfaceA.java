package com.interview.notes.code.tricky;

public interface InterfaceA {
    default String getGreeting() {
        return "Good Morning!";
    }

    //default String getGreeting1();
}