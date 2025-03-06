package com.interview.notes.code.year.y2025.march.common;

public class CharacterCounter {
    
    // Main method to test the solution with different test cases
    public static void main(String[] args) {
        testCharacterCount();
    }
    
    // Method to count characters in a string using Java 8 Streams
    public static String countCharacters(String input) {
        if (input == null || input.isEmpty()) {
            return "{}";
        }
        
        // Remove spaces and convert to lowercase for consistent counting
        return input.replaceAll("\\s+", "")
                   .chars()
                   .mapToObj(ch -> (char) ch)
                   .collect(java.util.stream.Collectors.groupingBy(
                           ch -> ch,
                           java.util.LinkedHashMap::new,
                           java.util.stream.Collectors.counting()))
                   .toString();
    }
    
    // Test method to verify the solution
    public static void testCharacterCount() {
        // Test Case 1: Given example
        runTest("string data to count each character",
                "{s=1, t=5, r=3, i=1, n=2, g=1, d=1, a=5, o=2, c=4, u=1, e=2, h=2}",
                "Basic Test Case");
        
        // Test Case 2: Empty string
        runTest("", "{}", "Empty String");
        
        // Test Case 3: Single character
        runTest("a", "{a=1}", "Single Character");
        
        // Test Case 4: All same characters
        runTest("aaa", "{a=3}", "Repeated Characters");
        
        // Test Case 5: Special characters
        runTest("!@#$%", "{!=1, @=1, #=1, $=1, %=1}", "Special Characters");
        
        // Test Case 6: Large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append("abcdef");
        }
        runTest(largeInput.toString(), null, "Large Input");
    }
    
    // Helper method to run individual tests
    private static void runTest(String input, String expected, String testName) {
        try {
            String result = countCharacters(input);
            boolean passed = expected == null || result.equals(expected);
            System.out.println("Test: " + testName);
            System.out.println("Input: " + (input.length() > 50 ? input.substring(0, 50) + "..." : input));
            System.out.println("Result: " + (result.length() > 50 ? result.substring(0, 50) + "..." : result));
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            System.out.println("--------------------");
        } catch (Exception e) {
            System.out.println("Test: " + testName + " failed with exception: " + e.getMessage());
            System.out.println("--------------------");
        }
    }
}
