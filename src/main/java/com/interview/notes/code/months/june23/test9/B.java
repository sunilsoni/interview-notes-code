package com.interview.notes.code.months.june23.test9;

public class B extends A {

    @Override
    protected void dispose() {
        super.dispose();
        System.out.println("Disposing object of type B");
    }
}
