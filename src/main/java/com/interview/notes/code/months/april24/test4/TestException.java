package com.interview.notes.code.months.april24.test4;

public class TestException {
    Object abc;

    public static void main(String[] args) {
        TestException obj = new TestException();
        Object xyz = new Object();
        if (obj == xyz) {
            System.out.println("hello");
        }
    }
}
