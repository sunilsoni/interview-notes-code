package com.interview.notes.code.year.y2025.June.common.test17;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharArrayTransformer {

    // Method to transform array based on problem conditions
    public static List<Character> transformArray(char[] chars) {
        // Convert array to stream for easy manipulation
        return IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i]) // Convert int index to Character
                .flatMap(c -> {          // flatMap helps replace one char with multiple chars if needed
                    if (c == 'x')        // Check if char is 'x'
                        return Stream.of('a', 'a'); // Replace 'x' with two 'a'
                    else if (c == 'y')   // Check if char is 'y'
                        return Stream.empty();      // Delete 'y' by returning an empty stream
                    else
                        return Stream.of(c);        // Else keep the char as it is
                })
                .collect(Collectors.toList());      // Collect resulting stream into a list
    }

    // Test method using a simple main method to avoid JUnit
    public static void main(String[] args) {

        // Test case 1
        char[] input1 = {'x', 'x', 'y', 'z', 'x'};
        List<Character> expected1 = Arrays.asList('a', 'a', 'a', 'a', 'z', 'a', 'a');
        List<Character> result1 = transformArray(input1);
        System.out.println("Test Case 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));

        // Test case 2
        char[] input2 = {'x', 'y', 'y', 'x', 'y'};
        List<Character> expected2 = Arrays.asList('a', 'a', 'a', 'a');
        List<Character> result2 = transformArray(input2);
        System.out.println("Test Case 2: " + (result2.equals(expected2) ? "PASS" : "FAIL"));

        // Edge case: Empty input
        char[] input3 = {};
        List<Character> expected3 = Collections.emptyList();
        List<Character> result3 = transformArray(input3);
        System.out.println("Test Case 3 (Empty Input): " + (result3.equals(expected3) ? "PASS" : "FAIL"));

        // Edge case: Large input
        char[] input4 = new char[1000000];
        Arrays.fill(input4, 'x'); // Filling large array with 'x'
        List<Character> result4 = transformArray(input4);
        System.out.println("Test Case 4 (Large Input): " + (result4.size() == 2000000 ? "PASS" : "FAIL"));
    }
}