package com.interview.notes.code.year.y2026.feb.GoldmanSachs.test5;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Core Logic: Finds frequent IP using Streams.
     */
    public static String findTopIpaddress(String[] lines) {
        return Arrays.stream(lines)
            .parallel() // faster for large data
            .map(line -> line.substring(0, line.indexOf(' '))) // extract IP
            .collect(Collectors.groupingBy(ip -> ip, Collectors.counting())) // count IPs
            .entrySet().stream()
            .max(Map.Entry.comparingByValue()) // find max
            .map(Map.Entry::getKey) // get IP string
            .orElse(""); // handle empty inputs
    }

    /**
     * Test Runner: Validates multiple scenarios using the requested format.
     */
    public static boolean doTestsPass() {
        // Flag to track if all tests succeed. assumed true initially.
        boolean allTestsPassed = true; 

        // ---------------------------------------------------------
        // Test Case 1: Standard Case (Provided in example)
        // ---------------------------------------------------------
        String[] lines1 = new String[] {
            "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
            "10.0.0.1 - frank [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
            "10.0.0.2 - nancy [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234"
        };
        
        // Check if logic finds 10.0.0.1 (appears twice)
        if (!findTopIpaddress(lines1).equals("10.0.0.1")) {
            System.out.println("Test 1 (Standard) Failed"); // Print failure msg
            allTestsPassed = false; // Mark overall status as failed
        }

        // ---------------------------------------------------------
        // Test Case 2: Tie Scenario (New)
        // ---------------------------------------------------------
        // 1.1.1.1 appears twice, 2.2.2.2 appears once.
        String[] lines2 = new String[] {
            "1.1.1.1 - log", 
            "2.2.2.2 - log", 
            "1.1.1.1 - log"
        };

        // Check if logic correctly identifies the majority IP
        if (!findTopIpaddress(lines2).equals("1.1.1.1")) {
            System.out.println("Test 2 (Tie/Majority) Failed"); 
            allTestsPassed = false; 
        }

        // ---------------------------------------------------------
        // Test Case 3: Empty Input (New - Edge Case)
        // ---------------------------------------------------------
        // Passing an empty array to ensure code doesn't crash
        String[] lines3 = new String[] {};

        // Expecting an empty string result, not null
        if (!findTopIpaddress(lines3).equals("")) {
            System.out.println("Test 3 (Empty) Failed"); 
            allTestsPassed = false; 
        }

        // ---------------------------------------------------------
        // Test Case 4: Single Entry (New)
        // ---------------------------------------------------------
        // Only one line exists
        String[] lines4 = new String[] { "8.8.8.8 - log data" };

        // The only IP present must be the winner
        if (!findTopIpaddress(lines4).equals("8.8.8.8")) {
            System.out.println("Test 4 (Single) Failed"); 
            allTestsPassed = false; 
        }

        // ---------------------------------------------------------
        // Final Result
        // ---------------------------------------------------------
        if (allTestsPassed) {
            System.out.println("All Tests Passed"); // Success message
            return true;
        } else {
            System.out.println("Some Tests Failed"); // Failure message
            return false;
        }
    }

    public static void main(String[] args) {
        doTestsPass();
    }
}