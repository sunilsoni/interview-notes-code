package com.interview.notes.code.year.y2024.dec24.oracle.test1;

public class WordFilter {

    // Method to filter out the target string from the array
    public static String[] wordsWithout(String[] words, String target) {
        int validCount = 0;

        // First, count how many elements are not equal to target
        for (String word : words) {
            if (!word.equals(target)) {
                validCount++;
            }
        }

        // Create a new array of the correct size
        String[] result = new String[validCount];
        int index = 0;

        // Copy the valid elements to the new array
        for (String word : words) {
            if (!word.equals(target)) {
                result[index++] = word;
            }
        }

        return result;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Test case 1
        String[] words1 = {"a", "b", "c", "a"};
        String[] result1 = wordsWithout(words1, "a");
        System.out.print("Test Case 1: ");
        for (String word : result1) {
            System.out.print(word + " ");
        }
        System.out.println(); // Expected output: ["b", "c"]

        // Test case 2
        String[] words2 = {"a", "b", "c", "a", "b"};
        String[] result2 = wordsWithout(words2, "b");
        System.out.print("Test Case 2: ");
        for (String word : result2) {
            System.out.print(word + " ");
        }
        System.out.println(); // Expected output: ["a", "c", "a"]

        // Test case 3
        String[] words3 = {"a", "b", "c", "a", "c"};
        String[] result3 = wordsWithout(words3, "c");
        System.out.print("Test Case 3: ");
        for (String word : result3) {
            System.out.print(word + " ");
        }
        System.out.println(); // Expected output: ["a", "b", "a"]

        // Edge case 1: Empty array
        String[] words4 = {};
        String[] result4 = wordsWithout(words4, "a");
        System.out.print("Edge Case 1: ");
        for (String word : result4) {
            System.out.print(word + " ");
        }
        System.out.println(); // Expected output: []

        // Edge case 2: No occurrences of target
        String[] words5 = {"x", "y", "z"};
        String[] result5 = wordsWithout(words5, "a");
        System.out.print("Edge Case 2: ");
        for (String word : result5) {
            System.out.print(word + " ");
        }
        System.out.println(); // Expected output: ["x", "y", "z"]

        // Edge case 3: All elements are the target
        String[] words6 = {"a", "a", "a"};
        String[] result6 = wordsWithout(words6, "a");
        System.out.print("Edge Case 3: ");
        for (String word : result6) {
            System.out.print(word + " ");
        }
        System.out.println(); // Expected output: []

        // Edge case 4: Large input case
        String[] words7 = new String[1000000];
        for (int i = 0; i < 1000000; i++) {
            words7[i] = (i % 2 == 0) ? "a" : "b";
        }
        long startTime = System.nanoTime();
        String[] result7 = wordsWithout(words7, "a");
        long endTime = System.nanoTime();
        System.out.print("Edge Case 4 (Large Input): ");
        System.out.println("Execution Time: " + (endTime - startTime) + " ns"); // Measure execution time
    }
}
