package com.interview.notes.code.test.test9;

public abstract class A {
    private static int instanceCount = 0;

    protected A() {
        instanceCount++;
    }

    protected void dispose() {
        instanceCount--;
    }

    public static boolean isFirstInstanceCreated() {
        return instanceCount == 1;
    }
}
