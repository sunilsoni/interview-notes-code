package com.interview.notes.code.year.y2023.june23.test9;

public abstract class A {
    private static int instanceCount = 0;

    protected A() {
        instanceCount++;
    }

    public static boolean isFirstInstanceCreated() {
        return instanceCount == 1;
    }

    protected void dispose() {
        instanceCount--;
    }
}
