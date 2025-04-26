package com.interview.notes.code.year.y2025.april.common.tst6;

public class FirstNonRepeatedChar {
    
    public static Character findFirstNonRepeated(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        
        // Using Java 8 streams to count character frequencies
        return str.chars()
                 .mapToObj(ch -> Character.valueOf((char)ch))
                 .filter(ch -> str.chars().filter(c -> c == ch).count() == 1)
                 .findFirst()
                 .orElse(null);
    }

    public static void main(String[] args) {
        // Test cases
        runTest("hello", 'h');
        runTest("aabbccd", 'd');
        runTest("", null);
        runTest(null, null);
        runTest("aabbcc", null);
        runTest("stress", 't');
        
        // Edge case with special characters
        runTest("a!b@c#a", '!');
        
        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("abc");
        }
        largeInput.append('x');
        long startTime = System.currentTimeMillis();
        runTest(largeInput.toString(), 'x');
        long endTime = System.currentTimeMillis();
        System.out.println("Large input processing time: " + (endTime - startTime) + "ms");
    }
    
    private static void runTest(String input, Character expected) {
        Character result = findFirstNonRepeated(input);
        boolean passed = (result == null && expected == null) || 
                        (result != null && result.equals(expected));
        
        System.out.println("Test Case: " + 
            (input == null ? "null" : 
             input.length() > 20 ? input.substring(0, 17) + "..." : input));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
