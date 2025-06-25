package com.interview.notes.code.year.y2025.June.microsoft.test2;

public class StringFlipper {
    
    /**
     * Flips (reverses) the input string
     * @param input String to be flipped
     * @return Flipped string
     */
    public static String flipString(String input) {
        // Handle null or empty input
        if (input == null || input.isEmpty()) {
            return input;
        }
        
        // Convert string to char array for efficient manipulation
        char[] charArray = input.toCharArray();
        
        // Initialize two pointers for swapping
        int left = 0;
        int right = charArray.length - 1;
        
        // Swap characters from start and end until pointers meet
        while (left < right) {
            // Perform swap using temporary variable
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            
            // Move pointers towards center
            left++;
            right--;
        }
        
        // Convert char array back to string and return
        return new String(charArray);
    }
    
    /**
     * Test method to verify flipString functionality
     * @param testCase Test case description
     * @param input Input string
     * @param expected Expected output
     */
    private static void testCase(String testCase, String input, String expected) {
        System.out.println("\nTest Case: " + testCase);
        String result = flipString(input);
        boolean passed = (result == null && expected == null) || 
                        (result != null && result.equals(expected));
        
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println(passed ? "PASS" : "FAIL");
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        // Test 1: Normal string
        testCase("Normal string", "abc", "cba");
        
        // Test 2: Empty string
        testCase("Empty string", "", "");
        
        // Test 3: Null input
        testCase("Null input", null, null);
        
        // Test 4: Single character
        testCase("Single character", "a", "a");
        
        // Test 5: Special characters
        testCase("Special characters", "!@#$%", "%$#@!");
        
        // Test 6: Large string
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append("abcdefghijklmnopqrstuvwxyz");
        }
        String largeString = largeInput.toString();
        String expectedLarge = new StringBuilder(largeString).reverse().toString();
        
        System.out.println("\nTest Case: Large string (26000 characters)");
        long startTime = System.currentTimeMillis();
        String resultLarge = flipString(largeString);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Large string test: " + 
            (resultLarge.equals(expectedLarge) ? "PASS" : "FAIL"));
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
