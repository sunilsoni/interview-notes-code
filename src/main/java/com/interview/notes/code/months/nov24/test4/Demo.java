package com.interview.notes.code.months.nov24.test4;

import java.util.function.BiFunction;

public class Demo {
    BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

    public static void main(String args[]) {
        System.out.print("a");
        try {
            System.out.print("b");
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            System.out.print("c");
        } finally {
            System.out.print("d");
        }
        System.out.print("e");
    }


}
