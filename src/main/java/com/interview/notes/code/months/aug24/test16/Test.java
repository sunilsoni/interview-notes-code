package com.interview.notes.code.months.aug24.test16;

@FunctionalInterface
interface MyFunctionalInterface {
    int add(int a, int b);
}

public class Test {
    public static void main(String[] args) {
        MyFunctionalInterface sum = (a, b) -> a + b;
        System.out.println(sum.add(10, 20));
    }
}
