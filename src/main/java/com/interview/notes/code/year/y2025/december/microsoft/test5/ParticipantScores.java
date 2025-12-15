package com.interview.notes.code.year.y2025.december.microsoft.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParticipantScores {
    
    // Method to get points for each difficulty level
    // E=2, M=5, H=7 based on problem statement
    public static int getPoints(char difficulty) {
        switch (difficulty) {
            case 'E': return 2;  // Easy problem worth 2 points
            case 'M': return 5;  // Medium problem worth 5 points
            case 'H': return 7;  // Hard problem worth 7 points
            default: return 0;  // Invalid character gives 0
        }
    }
    
    // Calculate total score for a participant using Stream API
    public static int calculateScore(String problems) {
        // Handle null or empty string
        if (problems == null || problems.isEmpty()) {
            return 0;  // No problems means 0 score
        }
        
        // Stream: convert string to char stream, map to points, sum all
        return problems.chars()                    // Convert string to IntStream of chars
                .map(ch -> getPoints((char) ch))   // Map each char to its point value
                .sum();                            // Sum all points
    }
    
    // Find winner(s) among three participants
    // Returns single name, two names, or "Tie"
    public static String findWinner(String alex, String bob, String eve) {
        // Calculate score for each participant
        int alexScore = calculateScore(alex);  // Alex's total score
        int bobScore = calculateScore(bob);    // Bob's total score
        int eveScore = calculateScore(eve);    // Eve's total score
        
        // Find maximum score using Stream API
        int maxScore = IntStream.of(alexScore, bobScore, eveScore)  // Create stream of 3 scores
                .max()                                               // Find maximum
                .getAsInt();                                         // Get integer value
        
        // List to store names of participants with max score
        List<String> winners = new ArrayList<>();  // Stores winner names
        
        // Check if Alex has max score
        if (alexScore == maxScore) {
            winners.add("Alex");  // Add Alex to winners list
        }
        
        // Check if Bob has max score
        if (bobScore == maxScore) {
            winners.add("Bob");   // Add Bob to winners list
        }
        
        // Check if Eve has max score
        if (eveScore == maxScore) {
            winners.add("Eve");   // Add Eve to winners list
        }
        
        // Decide output based on number of winners
        if (winners.size() == 1) {
            // Only one winner - return that name
            return winners.get(0);
        } else if (winners.size() == 2) {
            // Two winners - return both names separated by comma
            return winners.get(0) + ", " + winners.get(1);
        } else {
            // All three have same score - return Tie
            return "Tie";
        }
    }
    
    // Helper method to run a single test case
    public static void runTest(int testNum, String alex, String bob, String eve, String expected) {
        // Get actual result
        String actual = findWinner(alex, bob, eve);
        
        // Calculate scores for display
        int aScore = calculateScore(alex);
        int bScore = calculateScore(bob);
        int eScore = calculateScore(eve);
        
        // Check if test passed
        boolean passed = actual.equals(expected);
        
        // Print test details
        System.out.println("Test " + testNum + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Alex=\"" + alex + "\" Score=" + aScore);
        System.out.println("  Bob=\"" + bob + "\" Score=" + bScore);
        System.out.println("  Eve=\"" + eve + "\" Score=" + eScore);
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual:   " + actual);
        System.out.println();
    }
    
    // Generate large string for stress testing
    public static String generateLargeString(char ch, int length) {
        // Use StringBuilder for efficient string building
        StringBuilder sb = new StringBuilder(length);
        
        // Append character 'length' times using Stream
        IntStream.range(0, length)           // Generate 0 to length-1
                .forEach(i -> sb.append(ch)); // Append char each time
        
        return sb.toString();  // Return built string
    }
    
    // Main method with all test cases
    public static void main(String[] args) {
        System.out.println("=== Participant Scores Test Cases ===\n");
        
        // Test 1: Sample from problem - Alex wins alone
        // Alex: M(5)+H(7)+H(7)=19, Bob: E(2)+M(5)+H(7)=14, Eve: E(2)+M(5)+M(5)=12
        runTest(1, "MHH", "EMH", "EMM", "Alex");
        
        // Test 2: Bob wins alone
        // Alex: E(2)+E(2)=4, Bob: H(7)+H(7)=14, Eve: M(5)+M(5)=10
        runTest(2, "EE", "HH", "MM", "Bob");
        
        // Test 3: Eve wins alone
        // Alex: E(2)=2, Bob: M(5)=5, Eve: H(7)=7
        runTest(3, "E", "M", "H", "Eve");
        
        // Test 4: All three equal - Tie
        // Alex: M(5)=5, Bob: M(5)=5, Eve: M(5)=5
        runTest(4, "M", "M", "M", "Tie");
        
        // Test 5: Alex and Bob tie for highest - return both names
        // Alex: H(7)=7, Bob: H(7)=7, Eve: E(2)=2
        runTest(5, "H", "H", "E", "Alex, Bob");
        
        // Test 6: Bob and Eve tie for highest - return both names
        // Alex: E(2)=2, Bob: H(7)=7, Eve: H(7)=7
        runTest(6, "E", "H", "H", "Bob, Eve");
        
        // Test 7: Alex and Eve tie for highest - return both names
        // Alex: H(7)=7, Bob: E(2)=2, Eve: H(7)=7
        runTest(7, "H", "E", "H", "Alex, Eve");
        
        // Test 8: Empty strings - all zero - Tie
        runTest(8, "", "", "", "Tie");
        
        // Test 9: All Hard problems - Tie
        runTest(9, "H", "H", "H", "Tie");
        
        // Test 10: Mixed - Alex wins
        // Alex: H(7)+H(7)+H(7)=21, Bob: E(2)+E(2)+E(2)=6, Eve: M(5)+M(5)+M(5)=15
        runTest(10, "HHH", "EEE", "MMM", "Alex");
        
        // Test 11: Two participants with same mixed problems
        // Alex: E(2)+H(7)=9, Bob: E(2)+H(7)=9, Eve: M(5)+M(5)=10
        runTest(11, "EH", "EH", "MM", "Eve");
        
        // Test 12: Complex tie between Alex and Bob
        // Alex: M(5)+M(5)=10, Bob: E(2)+H(7)+E(2)-wait let me recalc
        // Alex: M(5)+M(5)=10, Bob: H(7)+E(2)+E(2)-no
        // Let's make: Alex: H(7)+E(2)=9, Bob: M(5)+E(2)+E(2)=9, Eve: E(2)=2
        runTest(12, "HE", "MEE", "E", "Alex, Bob");
        
        // Test 13: Large data - Alex wins
        System.out.println("Test 13: Large Data (10000 chars each)");
        String largeAlex = generateLargeString('H', 10000);  // 70000 points
        String largeBob = generateLargeString('M', 10000);   // 50000 points
        String largeEve = generateLargeString('E', 10000);   // 20000 points
        String result13 = findWinner(largeAlex, largeBob, largeEve);
        System.out.println("  Alex: 10000 H = 70000 points");
        System.out.println("  Bob: 10000 M = 50000 points");
        System.out.println("  Eve: 10000 E = 20000 points");
        System.out.println("  Expected: Alex");
        System.out.println("  Actual:   " + result13);
        System.out.println("  Result: " + (result13.equals("Alex") ? "PASS ✓" : "FAIL ✗") + "\n");
        
        // Test 14: Large data - Bob and Eve tie
        System.out.println("Test 14: Large Data Tie (50000 chars each)");
        String largeBob2 = generateLargeString('H', 50000);   // 350000 points
        String largeEve2 = generateLargeString('H', 50000);   // 350000 points
        String largeAlex2 = generateLargeString('M', 50000);  // 250000 points
        String result14 = findWinner(largeAlex2, largeBob2, largeEve2);
        System.out.println("  Alex: 50000 M = 250000 points");
        System.out.println("  Bob: 50000 H = 350000 points");
        System.out.println("  Eve: 50000 H = 350000 points");
        System.out.println("  Expected: Bob, Eve");
        System.out.println("  Actual:   " + result14);
        System.out.println("  Result: " + (result14.equals("Bob, Eve") ? "PASS ✓" : "FAIL ✗") + "\n");
        
        // Test 15: Very large data - single winner
        System.out.println("Test 15: Very Large Data (100000 chars each)");
        String veryLargeBob = generateLargeString('H', 100000);   // 700000 points
        String veryLargeAlex = generateLargeString('M', 100000);  // 500000 points
        String veryLargeEve = generateLargeString('E', 100000);   // 200000 points
        String result15 = findWinner(veryLargeAlex, veryLargeBob, veryLargeEve);
        System.out.println("  Alex: 100000 M = 500000 points");
        System.out.println("  Bob: 100000 H = 700000 points");
        System.out.println("  Eve: 100000 E = 200000 points");
        System.out.println("  Expected: Bob");
        System.out.println("  Actual:   " + result15);
        System.out.println("  Result: " + (result15.equals("Bob") ? "PASS ✓" : "FAIL ✗") + "\n");
        
        System.out.println("=== All Tests Completed ===");
    }
}