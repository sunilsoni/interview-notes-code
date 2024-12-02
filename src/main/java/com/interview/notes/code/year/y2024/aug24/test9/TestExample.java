package com.interview.notes.code.year.y2024.aug24.test9;

import java.util.Arrays;
import java.util.Optional;

public class TestExample {
    private static void arrayNumber() {
        int[] intArray = {0, 1, 2, 3, 4, 5};
        Optional<Integer> element = Arrays.stream(intArray).boxed().reduce((first, second) -> second);

        // Print the last element if present
        element.ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        arrayNumber();
    }
}
