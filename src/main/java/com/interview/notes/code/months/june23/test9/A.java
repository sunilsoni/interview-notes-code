package com.interview.notes.code.months.june23.test9;

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
