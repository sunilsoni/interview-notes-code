package com.interview.notes.code.year.y2023.june23.test9;

public class B extends A {

    @Override
    protected void dispose() {
        super.dispose();
        System.out.println("Disposing object of type B");
    }
}
