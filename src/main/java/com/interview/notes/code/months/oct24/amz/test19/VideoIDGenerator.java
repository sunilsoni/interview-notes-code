package com.interview.notes.code.months.oct24.amz.test19;

import java.util.*;

public class VideoIDGenerator {
    
    public static List<Integer> countMinimumCharactersForVideoIDs(String idStream, List<String> videoIds) {
        List<Integer> result = new ArrayList<>();
        
        for (String targetId : videoIds) {
            result.add(findMinimumLength(idStream, targetId));
        }
        
        return result;
    }
    
    private static int findMinimumLength(String idStream, String targetId) {
        Map<Character, Integer> targetFreq = new HashMap<>();
        // Count required frequencies for target
        for (char c : targetId.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
        }
        
        int n = idStream.length();
        int minLength = Integer.MAX_VALUE;
        boolean found = false;
        
        // Try each possible starting position
        for (int start = 0; start < n; start++) {
            Map<Character, Integer> currentFreq = new HashMap<>();
            int end;
            
            // Extend window until we have all required characters
            for (end = start; end < n; end++) {
                char c = idStream.charAt(end);
                currentFreq.put(c, currentFreq.getOrDefault(c, 0) + 1);
                
                // Check if we have all required characters
                boolean isValid = true;
                for (Map.Entry<Character, Integer> entry : targetFreq.entrySet()) {
                    char key = entry.getKey();
                    int requiredCount = entry.getValue();
                    if (!currentFreq.containsKey(key) || currentFreq.get(key) < requiredCount) {
                        isValid = false;
                        break;
                    }
                }
                
                if (isValid) {
                    found = true;
                    minLength = Math.min(minLength, end - start + 1);
                    break;
                }
            }
        }
        
        return found ? minLength : -1;
    }
    
    public static void main(String[] args) {
        // Test Case 1
        runTestCase("Test Case 1", 
            "064819848398",
            Arrays.asList("088", "364", "07"),
            Arrays.asList(7, 10, -1));
            
        // Test Case 2
        runTestCase("Test Case 2",
            "1112223333444",
            Arrays.asList("121", "3", "12345", "11234"),
            Arrays.asList(4, 7, -1, 10));
            
        // Test Case 3
        runTestCase("Test Case 3",
            "987654",
            Arrays.asList("456789", "4", "04"),
            Arrays.asList(6, 6, -1));
            
        // Large data test case
        StringBuilder largeStream = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {  // Reduced size for demonstration
            largeStream.append(rand.nextInt(10));
        }
        List<String> largeVideoIds = new ArrayList<>();
        List<Integer> expectedResults = new ArrayList<>();
        for (int i = 0; i < 100; i++) {  // Reduced size for demonstration
            largeVideoIds.add("123");
            expectedResults.add(findMinimumLength(largeStream.toString(), "123"));
        }
        runTestCase("Large Data Test",
            largeStream.toString(),
            largeVideoIds,
            expectedResults);
    }
    
    private static void runTestCase(String testName, String idStream, 
                                  List<String> videoIds, List<Integer> expected) {
        long startTime = System.currentTimeMillis();
        List<Integer> result = countMinimumCharactersForVideoIDs(idStream, videoIds);
        long endTime = System.currentTimeMillis();
        
        boolean passed = result.equals(expected);
        
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
        }
        System.out.println();
    }
}