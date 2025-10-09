import java.util.*;
import java.util.stream.Collectors;

public class DuplicateStringFinder {

    // Method to find duplicates using Stream API
    public static List<String> findDuplicates(String[] input) {
        // Handle null input case
        if (input == null) {
            return new ArrayList<>();
        }

        // Using Stream API to group strings by their occurrence and filter duplicates
        return Arrays.stream(input)
                // Group strings by their value and count occurrences
                .collect(Collectors.groupingBy(
                        str -> str,    // Key is the string itself
                        Collectors.counting()  // Value is the count of occurrences
                ))
                // Convert to entry set for processing
                .entrySet()
                .stream()
                // Filter entries where count > 1 (duplicates)
                .filter(entry -> entry.getValue() > 1)
                // Extract only the duplicate strings
                .map(Map.Entry::getKey)
                // Collect results to a list
                .collect(Collectors.toList());
    }

    // Main method containing test cases
    public static void main(String[] args) {
        // Test Case 1: Basic duplicate test
        testFindDuplicates(
                new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Sunday", "Monday"},
                Arrays.asList("Sunday", "Monday"),
                "Test Case 1: Basic duplicate test"
        );

        // Test Case 2: No duplicates
        testFindDuplicates(
                new String[]{"Sunday", "Monday", "Tuesday"},
                Collections.emptyList(),
                "Test Case 2: No duplicates"
        );

        // Test Case 3: Empty array
        testFindDuplicates(
                new String[]{},
                Collections.emptyList(),
                "Test Case 3: Empty array"
        );

        // Test Case 4: Null input
        testFindDuplicates(
                null,
                Collections.emptyList(),
                "Test Case 4: Null input"
        );

        // Test Case 5: Large data set
        testLargeDataSet();
    }

    // Helper method to test and verify results
    private static void testFindDuplicates(String[] input, List<String> expected, String testName) {
        // Get actual result
        List<String> result = findDuplicates(input);

        // Sort both lists for comparison (since order doesn't matter)
        result.sort(String::compareTo);
        expected.sort(String::compareTo);

        // Compare results and print outcome
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
        }
    }

    // Test method for large data set
    private static void testLargeDataSet() {
        // Create large array with known duplicates
        int size = 100000;
        String[] largeArray = new String[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = "String" + (i % 1000); // Creates duplicates
        }

        // Measure execution time
        long startTime = System.currentTimeMillis();
        List<String> result = findDuplicates(largeArray);
        long endTime = System.currentTimeMillis();

        // Verify results
        boolean passed = result.size() == 999; // Should have 999 duplicates
        System.out.println("Test Case 5: Large data set (" + size + " elements): " +
                (passed ? "PASS" : "FAIL"));
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
