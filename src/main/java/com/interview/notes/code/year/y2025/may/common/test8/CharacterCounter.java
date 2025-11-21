package com.interview.notes.code.year.y2025.may.common.test8;

public class CharacterCounter {

    // Main method to demonstrate and test the functionality
    public static void main(String[] args) {
        // Test cases
        testCountCharacters("Hello World");  // Mixed case with space
        testCountCharacters("");             // Empty string
        testCountCharacters("AEIOU");        // All vowels
        testCountCharacters("BCDFG");        // All consonants
        testCountCharacters("123");          // Numbers only
        testCountCharacters("A@#$%");        // Special characters

        // Large data test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("HelloWorld");
        }
        testCountCharacters(largeInput.toString());
    }

    // Method to count vowels and consonants using Java 8 Stream
    public static Result countCharacters(String input) {
        // Convert input to lowercase for easier processing
        String text = input.toLowerCase();

        // Using Java 8 Stream to count vowels
        long vowels = text.chars()
                .mapToObj(ch -> (char) ch)
                .filter(ch -> "aeiou".indexOf(ch) >= 0)
                .count();

        // Using Java 8 Stream to count consonants
        long consonants = text.chars()
                .mapToObj(ch -> (char) ch)
                .filter(ch -> Character.isLetter(ch))
                .filter(ch -> "aeiou".indexOf(ch) < 0)
                .count();

        return new Result(vowels, consonants);
    }

    // Helper method to test and print results
    private static void testCountCharacters(String input) {
        System.out.println("\nTesting input: " +
                (input.length() > 50 ? input.substring(0, 47) + "..." : input));

        Result result = countCharacters(input);
        System.out.println("Vowels: " + result.vowels);
        System.out.println("Consonants: " + result.consonants);

        // Validate results
        boolean isValid = validateResults(input, result);
        System.out.println("Test " + (isValid ? "PASSED" : "FAILED"));
    }

    // Validation method to verify results
    private static boolean validateResults(String input, Result result) {
        // Basic validation rules
        if (input.isEmpty() && result.vowels == 0 && result.consonants == 0) {
            return true;
        }

        return result.vowels + result.consonants <= input.length();
    }

    // Simple class to hold results
    static class Result {
        long vowels;
        long consonants;

        Result(long vowels, long consonants) {
            this.vowels = vowels;
            this.consonants = consonants;
        }
    }
}
