package com.interview.notes.code.year.y2026.march.common.test6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicates {

    // Method to find duplicates
    public static List<Integer> findDuplicates(List<Integer> list) {

        return list.stream() // convert list to stream
                .collect(Collectors.groupingBy( // group elements
                        Function.identity(),     // key = element itself
                        Collectors.counting()   // value = count
                ))
                .entrySet().stream() // convert map to stream
                .filter(e -> e.getValue() > 1) // keep only duplicates
                .map(Map.Entry::getKey) // extract keys
                .collect(Collectors.toList()); // convert to list
    }

    public static void main(String[] args) {

        // Test Case 1 (Given input)
        List<Integer> input1 = Arrays.asList(1,2,4,5,3,1,2,78,4,59,8,6,4,51,2,3);
        List<Integer> expected1 = Arrays.asList(1,2,4,3);

        runTest("Test1", input1, expected1);

        // Test Case 2 (No duplicates)
        List<Integer> input2 = Arrays.asList(10,20,30);
        List<Integer> expected2 = Collections.emptyList();

        runTest("Test2", input2, expected2);

        // Test Case 3 (All duplicates)
        List<Integer> input3 = Arrays.asList(5,5,5,5);
        List<Integer> expected3 = List.of(5);

        runTest("Test3", input3, expected3);

        // Test Case 4 (Large data)
        List<Integer> large = new ArrayList<>();
        for(int i=0;i<100000;i++) large.add(i);
        large.add(999); // duplicate
        List<Integer> expected4 = List.of(999);

        runTest("Test4", large, expected4);
    }

    // Test method
    private static void runTest(String name, List<Integer> input, List<Integer> expected) {

        List<Integer> result = findDuplicates(input); // get result

        // sort both for comparison
        Collections.sort(result);
        Collections.sort(expected);

        // check PASS/FAIL
        if(result.equals(expected)) {
            System.out.println(name + " : PASS");
        } else {
            System.out.println(name + " : FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got     : " + result);
        }
    }
}