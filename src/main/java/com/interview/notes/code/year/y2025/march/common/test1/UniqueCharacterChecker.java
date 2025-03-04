package com.interview.notes.code.year.y2025.march.common.test1;

public class UniqueCharacterChecker {
    
    /**
     * Checks if all characters in a string are unique.
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(min(n, k)) where k is the size of the character set
     * 
     * @param input The string to check
     * @return true if all characters are unique, false otherwise
     */
    public static boolean hasAllUniqueCharacters(String input) {
        // Edge case: empty string has all unique characters
        if (input == null || input.isEmpty()) {
            return true;
        }
        
        // Using a HashSet to track characters we've seen
        java.util.Set<Character> seenChars = new java.util.HashSet<>();
        
        // Check each character in the string
        for (char c : input.toCharArray()) {
            // If we've seen this character before, return false
            if (seenChars.contains(c)) {
                return false;
            }
            // Otherwise, add it to our set of seen characters
            seenChars.add(c);
        }
        
        // If we get here, all characters were unique
        return true;
    }
    
    /**
     * Alternative implementation using Java 8 Stream API
     */
    public static boolean hasAllUniqueCharactersStream(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }
        
        // Count distinct characters and compare with string length
        return input.chars().distinct().count() == input.length();
    }
    
    public static void main(String[] args) {
        // Test cases
        testCase("ABC", true);                // All unique
        testCase("ABA", false);               // Duplicate 'A'
        testCase("ABCDE", true);              // All unique
        testCase("ABCDEA", false);            // Duplicate 'A' at beginning and end
        testCase("", true);                   // Empty string
        testCase("A", true);                  // Single character
        testCase("  ", false);                // Duplicate spaces
        testCase("!@#$%^&*()", true);         // All unique special characters
        testCase("!@#$%^&*()!", false);       // Duplicate '!'
        
        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append((char)('A' + i % 26));
        }
        testCase(largeInput.toString(), false); // Large input with duplicates
        
        // Test with all ASCII characters (should be unique)
        StringBuilder allAscii = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            allAscii.append((char)i);
        }
        testCase(allAscii.toString(), true);
        
        // Compare performance of both implementations
        System.out.println("\n--- Performance Test ---");
        long startTime, endTime;
        
        startTime = System.nanoTime();
        hasAllUniqueCharacters(largeInput.toString());
        endTime = System.nanoTime();
        System.out.println("HashSet implementation: " + (endTime - startTime) / 1000000.0 + " ms");
        
        startTime = System.nanoTime();
        hasAllUniqueCharactersStream(largeInput.toString());
        endTime = System.nanoTime();
        System.out.println("Stream implementation: " + (endTime - startTime) / 1000000.0 + " ms");
    }
    
    /**
     * Helper method to test and print results
     */
    private static void testCase(String input, boolean expected) {
        boolean result = hasAllUniqueCharacters(input);
        boolean streamResult = hasAllUniqueCharactersStream(input);
        
        String displayInput = input.length() > 20 ? 
                input.substring(0, 17) + "..." : input;
        
        System.out.println("Input: \"" + displayInput + "\"");
        System.out.println("  HashSet Result: " + result + 
                (result == expected ? " ✓" : " ✗"));
        System.out.println("  Stream Result: " + streamResult + 
                (streamResult == expected ? " ✓" : " ✗"));
    }
}