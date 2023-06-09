package com.interview.notes.code.test.test9;

public class B extends A {

    @Override
    protected void dispose() {
        super.dispose();
        System.out.println("Disposing object of type B");
    }
}
