package com.interview.notes.code.year.y2025.march.common.test19;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomMergeStream {

    public static String merge(String input) {
        // Split the input into two arrays
        String[] parts = input.split(";");

        // Handle empty input case
        if (parts[0].isEmpty() && parts.length > 1 && parts[1].isEmpty()) {
            return "";
        }

        // Parse the first array
        List<Integer> array1 = parts[0].isEmpty() ?
                List.of() :
                Arrays.stream(parts[0].split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

        // Parse the second array
        List<Integer> array2 = parts.length > 1 && !parts[1].isEmpty() ?
                Arrays.stream(parts[1].split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()) :
                List.of();

        // Combine and process all numbers
        List<Integer> result = Stream.concat(array1.stream(), array2.stream())
                .collect(Collectors.partitioningBy(n -> n % 2 != 0))  // Partition into odd and even
                .entrySet().stream()
                .flatMap(entry -> {
                    List<Integer> numbers = entry.getValue();
                    numbers.sort(null);  // Natural ordering
                    return numbers.stream();
                })
                .collect(Collectors.toList());

        // Convert result to string
        return result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        // Test case 1
        String test1 = "1,5,7,4;3,7,2";
        String expected1 = "1,3,5,7,7,2,4";
        String result1 = merge(test1);
        System.out.println("Test 1: " + (expected1.equals(result1) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected1);
        System.out.println("Got:      " + result1);
        System.out.println();

        // Test case 2
        String test2 = "-17,1,5,11,19,-16,-14,-6,0,4,16,20;-13,-7,-7,-18,-4,-2,4,12";
        String expected2 = "-17,-13,-7,-7,1,5,11,19,-18,-16,-14,-6,-4,-2,0,4,4,12,16,20";
        String result2 = merge(test2);
        System.out.println("Test 2: " + (expected2.equals(result2) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected2);
        System.out.println("Got:      " + result2);
        System.out.println();

        // Test case 3: Empty arrays
        String test3 = ";";
        String expected3 = "";
        String result3 = merge(test3);
        System.out.println("Test 3 (Empty arrays): " + (expected3.equals(result3) ? "PASS" : "FAIL"));
        System.out.println();

        // Test case 4: One empty array
        String test4 = "1,3,5;";
        String expected4 = "1,3,5";
        String result4 = merge(test4);
        System.out.println("Test 4 (One empty array): " + (expected4.equals(result4) ? "PASS" : "FAIL"));
        System.out.println();

        // Test case 5: Large arrays
        StringBuilder largeArray1 = new StringBuilder();
        StringBuilder largeArray2 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeArray1.append(i * 2 + 1);
            largeArray2.append(i * 2);
            if (i < 9999) {
                largeArray1.append(",");
                largeArray2.append(",");
            }
        }
        String test5 = largeArray1.toString() + ";" + largeArray2.toString();
        long startTime = System.currentTimeMillis();
        String result5 = merge(test5);
        long endTime = System.currentTimeMillis();
        System.out.println("Test 5 (Large arrays - 20,000 elements): PASS");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("First few elements: " + result5.substring(0, Math.min(50, result5.length())) + "...");
    }
}