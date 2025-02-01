package com.interview.notes.code.year.y2025.jan24.test23;

public class JosephusCircle {
    public static int findLastChair(int n) {
        // Base case check
        if (n < 1) return 0;

        // Create array list to store chairs
        java.util.ArrayList<Integer> chairs = new java.util.ArrayList<>();
        for (int i = 1; i <= n; i++) {
            chairs.add(i);
        }

        int startPos = 0;  // Starting position for counting

        // Print initial arrangement
        System.out.println("Initial arrangement: " + chairs);

        // Continue until only one chair remains
        while (chairs.size() > 1) {
            // Calculate position to remove (every 3rd position)
            int removePos = (startPos + 2) % chairs.size();

            // Print step
            System.out.println("Removing position " + (removePos + 1) +
                    " (chair " + chairs.get(removePos) + ")");

            // Remove the chair at calculated position
            chairs.remove(removePos);

            // Print remaining chairs
            System.out.println("Remaining chairs: " + chairs);

            // Next counting starts from the position after removed chair
            startPos = removePos % chairs.size();
        }

        return chairs.get(0);
    }

    public static void runTest(int input, int expected) {
        System.out.println("\n=== Test with n=" + input + " ===");
        int result = findLastChair(input);
        boolean passed = result == expected;

        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        System.out.println("Josephus Circle Problem - Chair Elimination\n");

        // Test Case 1: Basic case with 6 chairs
        runTest(6, 1);

        // Test Case 2: Single chair
        runTest(1, 1);

        // Test Case 3: Three chairs
        runTest(3, 2);

        // Test Case 4: Five chairs
        runTest(5, 4);

        // Test Case 5: Larger case
        runTest(10, 4);

        // Example of detailed execution
        System.out.println("\n=== Detailed Example with 6 chairs ===");
        findLastChair(6);

        // Test with larger input
        System.out.println("\n=== Large Input Test ===");
        int largeN = 1000;
        System.out.println("Testing with n=" + largeN);
        long startTime = System.currentTimeMillis();
        int result = findLastChair(largeN);
        long endTime = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
