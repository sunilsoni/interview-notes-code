package com.interview.notes.code.months.Oct23.test12;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        int[] gQ = {4, 7, 2, 9, 6, 1};

        // 1. Find the 3rd largest element
        Optional<Integer> thirdLargest = Arrays.stream(gQ)
                .boxed()
                .sorted((a, b) -> b - a)
                .skip(2)
                .findFirst();

        thirdLargest.ifPresent(value -> System.out.println("3rd Largest Element: " + value));

        // 2. Using lambda, return even numbers from the array
        int[] evenNumbers = Arrays.stream(gQ)
                .filter(x -> x % 2 == 0)
                .toArray();

        System.out.println("Even Numbers: " + Arrays.toString(evenNumbers));
    }
}
