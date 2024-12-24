package com.interview.notes.code.months.dec24.amazon.test13;

import java.util.Map;
import java.util.stream.Collectors;

public class FirstNonRepeatingChar {
    
    public static Character findFirstNonRepeating(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        
        // Using Java 8 to count frequencies
        Map<Character, Long> charFrequency = input.toLowerCase()
            .chars()
            .mapToObj(ch -> (char) ch)
            .collect(Collectors.groupingBy(
                ch -> ch,
                Collectors.counting()
            ));
        
        // Find first char with frequency 1
        return input.toLowerCase()
            .chars()
            .mapToObj(ch -> (char) ch)
            .filter(ch -> charFrequency.get(ch) == 1)
            .findFirst()
            .orElse(null);
    }
    
    public static void testCase(String input, Character expected) {
        Character result = findFirstNonRepeating(input);
        boolean passed = (result == null && expected == null) || 
                        (result != null && result.equals(expected));
        
        System.out.printf("Test Case: '%s'\n", input);
        System.out.printf("Expected: %s\n", expected);
        System.out.printf("Result: %s\n", result);
        System.out.printf("Status: %s\n\n", passed ? "PASS" : "FAIL");
    }
    
    public static void main(String[] args) {
        // Basic test cases
        testCase("ganapathy", 'g');
        testCase("karthik", 't');
        testCase("aabbcc", null);
        
        // Edge cases
        testCase("", null);
        testCase(null, null);
        testCase("a", 'a');
        
        // Case sensitivity test
        testCase("GaNaPaThY", 'g');
        
        // Large input test
        String largeInput = "a".repeat(100000) + "b";
        testCase(largeInput, 'b');
        
        // Special characters
        testCase("hello123world!", '1');
        
        // Mixed case with numbers
        testCase("aA123bB", '1');
    }
}
