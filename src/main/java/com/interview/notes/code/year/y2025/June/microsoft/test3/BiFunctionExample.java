package com.interview.notes.code.year.y2025.June.microsoft.test3;

import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        int result = add.apply(10, 20);
        System.out.println("Sum is: " + result);
    }
}
