package com.interview.notes.code.year.y2025.august.oracle.test4;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class ExtractNumbers {

    // Function to extract contiguous numbers from array of strings
    public static List<String> extractNumbers(String[] arr) {
        Pattern pattern = Pattern.compile("\\d+"); // regex for one or more digits

        return Arrays.stream(arr) // process each string in the array
                .flatMap(s -> {
                    Matcher matcher = pattern.matcher(s);
                    List<String> nums = new ArrayList<>();
                    while (matcher.find()) { // find all digit sequences
                        nums.add(matcher.group());
                    }
                    return nums.stream(); // flatten into main stream
                })
                .collect(Collectors.toList()); // final result as list
    }

    // Testing
    public static void main(String[] args) {
        String[] input = {"A1B", "C2", "34B", "5F6", "7"};
        List<String> output = extractNumbers(input);

        // Expected result
        List<String> expected = Arrays.asList("1", "2", "34", "5", "6", "7");

        // Print result
        System.out.println("Output  : " + output);
        System.out.println("Expected: " + expected);

        // PASS/FAIL check
        if (output.equals(expected)) {
            System.out.println("Test Case: PASS");
        } else {
            System.out.println("Test Case: FAIL");
        }

        // Large Data Test
        String[] bigInput = new String[100000];
        Arrays.fill(bigInput, "X123Y456Z");
        long start = System.currentTimeMillis();
        List<String> bigOutput = extractNumbers(bigInput);
        long end = System.currentTimeMillis();
        System.out.println("Large Data Processed: " + bigOutput.size() + " numbers in " + (end - start) + " ms");
    }
}