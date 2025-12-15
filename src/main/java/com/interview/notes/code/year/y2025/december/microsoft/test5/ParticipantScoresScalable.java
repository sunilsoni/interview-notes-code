package com.interview.notes.code.year.y2025.december.microsoft.test5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParticipantScoresScalable {
    
    // ============================================================
    // METHOD 1: Get points for difficulty level
    // ============================================================
    // Maps character to point value
    // E=2, M=5, H=7
    public static int getPoints(char difficulty) {
        switch (difficulty) {
            case 'E': return 2;  // Easy
            case 'M': return 5;  // Medium
            case 'H': return 7;  // Hard
            default: return 0;  // Invalid
        }
    }
    
    // ============================================================
    // METHOD 2: Calculate score for a single participant
    // ============================================================
    // Input: String of problems like "MHH"
    // Output: Total score (5+7+7=19)
    public static int calculateScore(String problems) {
        // Handle null or empty
        if (problems == null || problems.isEmpty()) {
            return 0;
        }
        
        // Use Stream to process each character
        return problems.chars()                    // Convert to IntStream
                .map(ch -> getPoints((char) ch))   // Map to points
                .sum();                            // Sum all
    }
    
    // ============================================================
    // METHOD 3: Find winner(s) - SCALABLE VERSION
    // ============================================================
    // Input: Map of participant names to their problem strings
    // Output: Winner name(s) or "Tie"
    public static String findWinner(Map<String, String> participants) {
        
        // Step 1: Handle empty input
        if (participants == null || participants.isEmpty()) {
            return "No participants";
        }
        
        // Step 2: Calculate score for each participant
        // Create new map: Name -> Score
        Map<String, Integer> scores = participants.entrySet()  // Get all entries
                .stream()                                       // Create stream
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),                // Key = Name
                        entry -> calculateScore(entry.getValue()) // Value = Score
                ));
        
        // Step 3: Find maximum score
        int maxScore = scores.values()   // Get all scores
                .stream()                 // Create stream
                .mapToInt(Integer::intValue)  // Convert to IntStream
                .max()                    // Find max
                .orElse(0);               // Default 0 if empty
        
        // Step 4: Find all participants with max score
        List<String> winners = scores.entrySet()  // Get all entries
                .stream()                          // Create stream
                .filter(entry -> entry.getValue() == maxScore)  // Keep only max scores
                .map(entry -> entry.getKey())      // Get names only
                .sorted()                          // Sort alphabetically
                .collect(Collectors.toList());     // Collect to list
        
        // Step 5: Return result based on number of winners
        if (winners.size() == 1) {
            // Single winner
            return winners.get(0);
        } else if (winners.size() == participants.size()) {
            // All participants have same score
            return "Tie";
        } else {
            // Multiple winners (but not all)
            // Join names with comma
            return winners.stream()
                    .collect(Collectors.joining(", "));
        }
    }
    
    // ============================================================
    // METHOD 4: Overloaded method for 3 participants (backward compatible)
    // ============================================================
    // This maintains compatibility with original 3-participant calls
    public static String findWinner(String alex, String bob, String eve) {
        // Create map and delegate to main method
        Map<String, String> participants = new HashMap<>();
        participants.put("Alex", alex);
        participants.put("Bob", bob);
        participants.put("Eve", eve);
        
        return findWinner(participants);
    }
    
    // ============================================================
    // METHOD 5: Helper to create participant map easily
    // ============================================================
    // Takes alternating name, problems pairs
    // Example: createParticipants("Alex", "MHH", "Bob", "EMH")
    public static Map<String, String> createParticipants(String... args) {
        Map<String, String> participants = new HashMap<>();
        
        // Process pairs: name1, problems1, name2, problems2, ...
        for (int i = 0; i < args.length - 1; i += 2) {
            String name = args[i];        // First of pair is name
            String problems = args[i + 1]; // Second of pair is problems
            participants.put(name, problems);
        }
        
        return participants;
    }
    
    // ============================================================
    // METHOD 6: Print scores for all participants
    // ============================================================
    public static void printScores(Map<String, String> participants) {
        System.out.println("  Participant Scores:");
        
        participants.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())  // Sort by name
                .forEach(entry -> {
                    String name = entry.getKey();
                    String problems = entry.getValue();
                    int score = calculateScore(problems);
                    System.out.println("    " + name + ": \"" + problems + "\" = " + score + " points");
                });
    }
    
    // ============================================================
    // METHOD 7: Run test case
    // ============================================================
    public static void runTest(int testNum, Map<String, String> participants, String expected) {
        String actual = findWinner(participants);
        boolean passed = actual.equals(expected);
        
        System.out.println("Test " + testNum + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        printScores(participants);
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual:   " + actual);
        System.out.println();
    }
    
    // ============================================================
    // METHOD 8: Generate large data for testing
    // ============================================================
    public static String generateLargeString(char ch, int length) {
        StringBuilder sb = new StringBuilder(length);
        IntStream.range(0, length).forEach(i -> sb.append(ch));
        return sb.toString();
    }
    
    // ============================================================
    // MAIN METHOD: Run all tests
    // ============================================================
    public static void main(String[] args) {
        System.out.println("=== Scalable Participant Scores Tests ===\n");
        
        // ----------------------------------------------------------
        // Test 1: Original 3 participants - Alex wins
        // ----------------------------------------------------------
        Map<String, String> test1 = createParticipants(
                "Alex", "MHH",   // 5+7+7 = 19
                "Bob", "EMH",    // 2+5+7 = 14
                "Eve", "EMM"     // 2+5+5 = 12
        );
        runTest(1, test1, "Alex");
        
        // ----------------------------------------------------------
        // Test 2: 3 participants - Two way tie
        // ----------------------------------------------------------
        Map<String, String> test2 = createParticipants(
                "Alex", "H",     // 7
                "Bob", "H",      // 7
                "Eve", "E"       // 2
        );
        runTest(2, test2, "Alex, Bob");
        
        // ----------------------------------------------------------
        // Test 3: 3 participants - All tie
        // ----------------------------------------------------------
        Map<String, String> test3 = createParticipants(
                "Alex", "M",     // 5
                "Bob", "M",      // 5
                "Eve", "M"       // 5
        );
        runTest(3, test3, "Tie");
        
        // ----------------------------------------------------------
        // Test 4: 4 participants - Single winner
        // ----------------------------------------------------------
        Map<String, String> test4 = createParticipants(
                "Alex", "HH",    // 14
                "Bob", "MM",     // 10
                "Eve", "EE",     // 4
                "Dave", "HHH"    // 21 - Winner!
        );
        runTest(4, test4, "Dave");
        
        // ----------------------------------------------------------
        // Test 5: 4 participants - Two way tie
        // ----------------------------------------------------------
        Map<String, String> test5 = createParticipants(
                "Alex", "HH",    // 14
                "Bob", "HH",     // 14
                "Eve", "MM",     // 10
                "Dave", "EE"     // 4
        );
        runTest(5, test5, "Alex, Bob");
        
        // ----------------------------------------------------------
        // Test 6: 5 participants - Three way tie
        // ----------------------------------------------------------
        Map<String, String> test6 = createParticipants(
                "Alex", "HH",    // 14
                "Bob", "HH",     // 14
                "Eve", "HH",     // 14
                "Dave", "MM",    // 10
                "Frank", "EE"    // 4
        );
        runTest(6, test6, "Alex, Bob, Eve");
        
        // ----------------------------------------------------------
        // Test 7: 5 participants - All tie
        // ----------------------------------------------------------
        Map<String, String> test7 = createParticipants(
                "Alex", "M",     // 5
                "Bob", "M",      // 5
                "Eve", "M",      // 5
                "Dave", "M",     // 5
                "Frank", "M"     // 5
        );
        runTest(7, test7, "Tie");
        
        // ----------------------------------------------------------
        // Test 8: 6 participants - Single winner
        // ----------------------------------------------------------
        Map<String, String> test8 = createParticipants(
                "Alex", "E",     // 2
                "Bob", "M",      // 5
                "Eve", "H",      // 7
                "Dave", "EE",    // 4
                "Frank", "MM",   // 10
                "Grace", "HHH"   // 21 - Winner!
        );
        runTest(8, test8, "Grace");
        
        // ----------------------------------------------------------
        // Test 9: 2 participants only
        // ----------------------------------------------------------
        Map<String, String> test9 = createParticipants(
                "Alex", "HHH",   // 21
                "Bob", "MMM"     // 15
        );
        runTest(9, test9, "Alex");
        
        // ----------------------------------------------------------
        // Test 10: 2 participants - Tie
        // ----------------------------------------------------------
        Map<String, String> test10 = createParticipants(
                "Alex", "HH",    // 14
                "Bob", "HH"      // 14
        );
        runTest(10, test10, "Tie");
        
        // ----------------------------------------------------------
        // Test 11: Single participant
        // ----------------------------------------------------------
        Map<String, String> test11 = createParticipants(
                "Alex", "MHH"    // 19 - Only participant wins
        );
        runTest(11, test11, "Alex");
        
        // ----------------------------------------------------------
        // Test 12: 10 participants - Complex
        // ----------------------------------------------------------
        Map<String, String> test12 = createParticipants(
                "Alex", "H",      // 7
                "Bob", "H",       // 7
                "Charlie", "H",   // 7
                "Dave", "M",      // 5
                "Eve", "M",       // 5
                "Frank", "M",     // 5
                "Grace", "E",     // 2
                "Henry", "E",     // 2
                "Ivy", "E",       // 2
                "Jack", "E"       // 2
        );
        runTest(12, test12, "Alex, Bob, Charlie");
        
        // ----------------------------------------------------------
        // Test 13: Large data - 5 participants
        // ----------------------------------------------------------
        System.out.println("Test 13: Large Data (10000 problems each)");
        Map<String, String> test13 = new HashMap<>();
        test13.put("Alex", generateLargeString('H', 10000));   // 70000
        test13.put("Bob", generateLargeString('M', 10000));    // 50000
        test13.put("Eve", generateLargeString('E', 10000));    // 20000
        test13.put("Dave", generateLargeString('H', 9000));    // 63000
        test13.put("Frank", generateLargeString('H', 10001));  // 70007 - Winner!
        
        String result13 = findWinner(test13);
        System.out.println("  Alex: 10000 H = 70000");
        System.out.println("  Bob: 10000 M = 50000");
        System.out.println("  Eve: 10000 E = 20000");
        System.out.println("  Dave: 9000 H = 63000");
        System.out.println("  Frank: 10001 H = 70007");
        System.out.println("  Expected: Frank");
        System.out.println("  Actual:   " + result13);
        System.out.println("  Result: " + (result13.equals("Frank") ? "PASS ✓" : "FAIL ✗") + "\n");
        
        // ----------------------------------------------------------
        // Test 14: Backward compatibility - Old method signature
        // ----------------------------------------------------------
        System.out.println("Test 14: Backward Compatibility Test");
        String result14 = findWinner("MHH", "EMH", "EMM");  // Old 3-param method
        System.out.println("  Using: findWinner(\"MHH\", \"EMH\", \"EMM\")");
        System.out.println("  Expected: Alex");
        System.out.println("  Actual:   " + result14);
        System.out.println("  Result: " + (result14.equals("Alex") ? "PASS ✓" : "FAIL ✗") + "\n");
        
        // ----------------------------------------------------------
        // Test 15: Empty participants
        // ----------------------------------------------------------
        Map<String, String> test15 = new HashMap<>();
        String result15 = findWinner(test15);
        System.out.println("Test 15: Empty Participants");
        System.out.println("  Expected: No participants");
        System.out.println("  Actual:   " + result15);
        System.out.println("  Result: " + (result15.equals("No participants") ? "PASS ✓" : "FAIL ✗") + "\n");
        
        System.out.println("=== All Tests Completed ===");
    }
}