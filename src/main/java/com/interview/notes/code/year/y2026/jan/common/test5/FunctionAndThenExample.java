package com.interview.notes.code.year.y2026.jan.common.test5;

import java.util.function.Function;

public class FunctionAndThenExample {
    public static void main(String[] args) {

        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;

        int result = f1.andThen(f2).apply(3);

        System.out.println(result); // Output: 8
    }
}
