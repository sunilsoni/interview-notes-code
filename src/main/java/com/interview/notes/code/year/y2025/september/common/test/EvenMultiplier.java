package com.interview.notes.code.year.y2025.september.common.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EvenMultiplier {

    // Method to multiply even numbers by 2 and return modified list
    public static List<Integer> multiplyEvenByTwo(List<Integer> inputList) {
        return inputList.stream() // Stream over the list
                .map(num -> num % 2 == 0 ? num * 2 : num) // if even, multiply by 2, else keep same
                .collect(Collectors.toList()); // Collect back to list
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> test1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> result1 = multiplyEvenByTwo(test1);
        System.out.println("Input: " + test1);
        System.out.println("Output: " + result1);
        System.out.println(result1.equals(Arrays.asList(1, 4, 3, 8, 5, 12)) ? "PASS" : "FAIL");

        // Test Case 2 - All even
        List<Integer> test2 = Arrays.asList(2, 4, 6, 8);
        List<Integer> result2 = multiplyEvenByTwo(test2);
        System.out.println(result2.equals(Arrays.asList(4, 8, 12, 16)) ? "PASS" : "FAIL");

        // Test Case 3 - All odd
        List<Integer> test3 = Arrays.asList(1, 3, 5, 7);
        List<Integer> result3 = multiplyEvenByTwo(test3);
        System.out.println(result3.equals(Arrays.asList(1, 3, 5, 7)) ? "PASS" : "FAIL");

        // Test Case 4 - Empty List
        List<Integer> test4 = new ArrayList<>();
        List<Integer> result4 = multiplyEvenByTwo(test4);
        System.out.println(result4.equals(Collections.emptyList()) ? "PASS" : "FAIL");

        // Test Case 5 - Large List
        List<Integer> largeList = new ArrayList<>();
        for (int i = 1; i <= 1_000_000; i++) {
            largeList.add(i);
        }
        List<Integer> result5 = multiplyEvenByTwo(largeList);
        boolean pass = true;
        for (int i = 0; i < 10; i++) {
            int original = largeList.get(i);
            int expected = original % 2 == 0 ? original * 2 : original;
            if (result5.get(i) != expected) {
                pass = false;
                break;
            }
        }
        System.out.println(pass ? "PASS (Large Input Sample Check)" : "FAIL (Large Input Sample Check)");
    }
}