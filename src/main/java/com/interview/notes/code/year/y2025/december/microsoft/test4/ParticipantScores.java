package com.interview.notes.code.year.y2025.december.microsoft.test4;

import java.util.stream.IntStream;

public class ParticipantScores {

    // Method to calculate score for a single character (difficulty level)
    // E=2, M=5, H=7 points based on problem difficulty
    public static int getPoints(char difficulty) {
        // Switch expression to map difficulty to points
        switch (difficulty) {
            case 'E':
                return 2;  // Easy problem gives 2 points
            case 'M':
                return 5;  // Medium problem gives 5 points
            case 'H':
                return 7;  // Hard problem gives 7 points
            default:
                return 0;  // Invalid character gives 0 points
        }
    }

    // Method to calculate total score for a participant's string
    // Uses Stream API to process each character and sum points
    public static int calculateScore(String problems) {
        // Convert string to IntStream of characters
        // Map each character to its point value
        // Sum all points to get total score
        return problems.chars()                    // Create IntStream from string characters
                .map(ch -> getPoints((char) ch))   // Convert each char to its point value
                .sum();                            // Add all points together
    }

    // Main method to find the winner among three participants
    // Returns winner name or "Tie" if all scores are equal
    public static String findWinner(String alex, String bob, String eve) {
        // Calculate score for Alex using Stream API
        int alexScore = calculateScore(alex);

        // Calculate score for Bob using Stream API
        int bobScore = calculateScore(bob);

        // Calculate score for Eve using Stream API
        int eveScore = calculateScore(eve);

        // Check if all three scores are equal - it's a Tie
        if (alexScore == bobScore && bobScore == eveScore) {
            return "Tie";  // All participants have same score
        }

        // Find the maximum score among all three participants
        int maxScore = IntStream.of(alexScore, bobScore, eveScore)  // Create stream of scores
                .max()                                               // Find maximum value
                .getAsInt();                                         // Get the integer result

        // Check if Alex has the highest score (and it's unique)
        if (alexScore == maxScore && alexScore > bobScore && alexScore > eveScore) {
            return "Alex";  // Alex wins with highest unique score
        }

        // Check if Bob has the highest score (and it's unique)
        if (bobScore == maxScore && bobScore > alexScore && bobScore > eveScore) {
            return "Bob";   // Bob wins with highest unique score
        }

        // Check if Eve has the highest score (and it's unique)
        if (eveScore == maxScore && eveScore > alexScore && eveScore > bobScore) {
            return "Eve";   // Eve wins with highest unique score
        }

        // If we reach here, two or more have same max score
        return "Tie";  // Multiple participants share highest score
    }

    // Helper method to run a single test case and print result
    // Compares actual output with expected output
    public static void runTest(int testNum, String alex, String bob, String eve, String expected) {
        // Call findWinner to get actual result
        String actual = findWinner(alex, bob, eve);

        // Compare actual result with expected result
        boolean passed = actual.equals(expected);

        // Print test result with details
        System.out.println("Test " + testNum + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("  Input: alex=\"" + alex + "\", bob=\"" + bob + "\", eve=\"" + eve + "\"");
        System.out.println("  Expected: " + expected + ", Actual: " + actual);
        System.out.println();  // Empty line for readability
    }

    // Main method to run all test cases
    public static void main(String[] args) {
        System.out.println("=== Running Test Cases ===\n");

        // Test 1: Sample test case from problem
        // Alex: M(5)+H(7)+H(7)=19, Bob: E(2)+M(5)+H(7)=14, Eve: E(2)+M(5)+M(5)=12
        runTest(1, "MHH", "EMH", "EMM", "Alex");

        // Test 2: Bob wins with highest score
        // Alex: E(2)+E(2)=4, Bob: H(7)+H(7)=14, Eve: M(5)+M(5)=10
        runTest(2, "EE", "HH", "MM", "Bob");

        // Test 3: Eve wins with highest score
        // Alex: E(2)=2, Bob: M(5)=5, Eve: H(7)=7
        runTest(3, "E", "M", "H", "Eve");

        // Test 4: All three have same score - Tie
        // Alex: M(5)=5, Bob: M(5)=5, Eve: M(5)=5
        runTest(4, "M", "M", "M", "Tie");

        // Test 5: Two participants tie for highest
        // Alex: H(7)=7, Bob: H(7)=7, Eve: E(2)=2
        runTest(5, "H", "H", "E", "Tie");

        // Test 6: Empty strings - all have 0 score
        runTest(6, "", "", "", "Tie");

        // Test 7: Single day with all Hard problems
        runTest(7, "H", "H", "H", "Tie");

        // Test 8: Mixed difficulties - Alex wins
        // Alex: H(7)+H(7)+H(7)=21, Bob: E(2)+E(2)+E(2)=6, Eve: M(5)+M(5)+M(5)=15
        runTest(8, "HHH", "EEE", "MMM", "Alex");

        // Test 9: Long string test for performance
        // Creating large input strings
        String largeAlex = generateLargeString('H', 10000);  // 10000 Hard = 70000 points
        String largeBob = generateLargeString('M', 10000);   // 10000 Medium = 50000 points
        String largeEve = generateLargeString('E', 10000);   // 10000 Easy = 20000 points
        runTest(9, largeAlex, largeBob, largeEve, "Alex");

        // Test 10: Very large data test
        String veryLargeAlex = generateLargeString('M', 100000);  // 100000 Medium = 500000
        String veryLargeBob = generateLargeString('H', 100000);   // 100000 Hard = 700000
        String veryLargeEve = generateLargeString('E', 100000);   // 100000 Easy = 200000
        runTest(10, veryLargeAlex, veryLargeBob, veryLargeEve, "Bob");

        // Test 11: Two-way tie between Alex and Eve
        // Alex: H(7)=7, Bob: E(2)=2, Eve: H(7)=7
        runTest(11, "H", "E", "H", "Tie");

        // Test 12: Bob and Eve tie
        runTest(12, "E", "H", "H", "Tie");

        System.out.println("=== All Tests Completed ===");
    }

    // Helper method to generate large test strings
    // Creates a string with repeated characters for stress testing
    public static String generateLargeString(char ch, int length) {
        // Use StringBuilder for efficient string building
        StringBuilder sb = new StringBuilder(length);  // Pre-allocate capacity

        // Use IntStream to repeat character 'length' times
        IntStream.range(0, length)          // Generate numbers from 0 to length-1
                .forEach(i -> sb.append(ch)); // Append character for each number

        // Return the built string
        return sb.toString();
    }
}