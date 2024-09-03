package com.interview.notes.code.months.aug24.test32;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OddOccurrences {
    public static List<String> findOddOccurrences(String[] arr) {
        return Arrays.stream(arr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() % 2 != 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Example 1
        String[] arr1 = {"blue", "red", "green", "yellow", "green", "red", "red"};
        System.out.println("Example 1 Result: " + findOddOccurrences(arr1));
        System.out.println("Example 1 " + (findOddOccurrences(arr1).equals(Arrays.asList("blue", "yellow", "red")) ? "PASS" : "FAIL"));

        // Example 2
        String[] arr2 = {"apple", "banana", "apple", "cherry", "date", "banana"};
        System.out.println("Example 2 Result: " + findOddOccurrences(arr2));
        System.out.println("Example 2 " + (findOddOccurrences(arr2).equals(Arrays.asList("apple", "cherry", "date")) ? "PASS" : "FAIL"));

        // Example 3 (Empty array)
        String[] arr3 = {};
        System.out.println("Example 3 Result: " + findOddOccurrences(arr3));
        System.out.println("Example 3 " + (findOddOccurrences(arr3).isEmpty() ? "PASS" : "FAIL"));

        // Example 4 (All elements occur even number of times)
        String[] arr4 = {"a", "b", "a", "b", "c", "c"};
        System.out.println("Example 4 Result: " + findOddOccurrences(arr4));
        System.out.println("Example 4 " + (findOddOccurrences(arr4).isEmpty() ? "PASS" : "FAIL"));
    }
}
