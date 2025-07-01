package com.interview.notes.code.year.y2025.June.common.test13;

import java.util.*;
import java.util.stream.Collectors;

public class AnagramGrouper {
    
    public static List<List<String>> groupAnagrams(String[] words) {
        // Using HashMap to group words by their sorted characters
        return Arrays.stream(words)
            .collect(Collectors.groupingBy(
                word -> sortString(word),
                Collectors.toList()))
            .values()
            .stream()
            .collect(Collectors.toList());
    }
    
    private static String sortString(String word) {
        // Convert word to char array, sort it, and convert back to string
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    public static void main(String[] args) {
        // Test Case 1: Basic test
        String[] test1 = {"act", "pots", "tops", "cat", "stop", "hat"};
        testAndPrint("Test 1 - Basic test", test1, 
            Arrays.asList("hat"), 
            Arrays.asList("act", "cat"),
            Arrays.asList("stop", "pots", "tops"));
            
        // Test Case 2: Empty array
        String[] test2 = {};
        testAndPrint("Test 2 - Empty array", test2);
        
        // Test Case 3: Single word
        String[] test3 = {"hello"};
        testAndPrint("Test 3 - Single word", test3, 
            Arrays.asList("hello"));
            
        // Test Case 4: Large input
        String[] test4 = generateLargeInput();
        System.out.println("Test 4 - Large input (1000 words): " + 
            (groupAnagrams(test4).size() > 0 ? "PASS" : "FAIL"));
    }
    
    private static void testAndPrint(String testName, String[] input, List<String>... expectedGroups) {
        List<List<String>> result = groupAnagrams(input);
        boolean pass = true;
        
        if (expectedGroups.length > 0) {
            Set<Set<String>> expectedSets = Arrays.stream(expectedGroups)
                .map(HashSet::new)
                .collect(Collectors.toSet());
                
            Set<Set<String>> resultSets = result.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());
                
            pass = expectedSets.equals(resultSets);
        }
        
        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL"));
        System.out.println("Result: " + result);
        System.out.println();
    }
    
    private static String[] generateLargeInput() {
        // Generate large test data
        String[] largeInput = new String[1000];
        for (int i = 0; i < 1000; i++) {
            largeInput[i] = "word" + i;
        }
        return largeInput;
    }
}
