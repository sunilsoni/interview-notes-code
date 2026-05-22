package com.interview.notes.code.year.y2026.may.GoldmanSachs.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static int fountainActivation(List<Integer> locations) {
        // 'n' stores the total length of the garden, which is the total number of fountains.
        int n = locations.size();
        
        // 'maxReach' is our array of mailboxes. It tracks the furthest right point we can reach
        // from any given 0-indexed starting position.
        int[] maxReach = new int[n];

        // We walk through the garden using 'i' from 1 to n (representing our physical position).
        for (int i = 1; i <= n; i++) {
            
            // Left Wall (max): Current position minus the range. If it goes past the left wall (0 or lower), snap it to 1.
            // Remember, locations.get(i - 1) looks inside the computer's memory slot for this fountain's range value.
            int leftBound = Math.max(1, i - locations.get(i - 1));
            
            // Right Wall (min): Current position plus the range. If it goes past the right fence (larger than n), snap it to n.
            int rightBound = Math.min(n, i + locations.get(i - 1));
            
            // Go to Mailbox index (leftBound - 1) and store the furthest right boundary found so far.
            // This filters out weaker fountains starting at the same spot.
            maxReach[leftBound - 1] = Math.max(maxReach[leftBound - 1], rightBound);
        }

        // Counter to track how many fountains we must turn on.
        int fountainsActivated = 0;
        
        // The edge of the watered grass from currently turned-on fountains.
        int currentCoveredEnd = 0;
        
        // The absolute furthest point we could reach if we turned on one more fountain.
        int nextPossibleEnd = 0;

        // Walk through the garden position by position (0-indexed computer slots) to execute our jumps.
        for (int i = 0; i < n; i++) {
            
            // Update the furthest possible right point we can reach using any fountain available up to this point.
            nextPossibleEnd = Math.max(nextPossibleEnd, maxReach[i]);
            
            // If our walking feet hit the dry edge of our currently covered watered zone...
            if (i == currentCoveredEnd) {
                
                // We are forced to turn on another fountain to keep the grass green.
                fountainsActivated++;
                
                // Extend our wet zone out to the best possible milestone we tracked.
                currentCoveredEnd = nextPossibleEnd;
                
                // If our updated wet zone reaches or passes the end of the garden, we are completely done!
                if (currentCoveredEnd >= n) {
                    break;
                }
            }
        }

        // Return the minimal total number of activated fountains.
        return fountainsActivated;
    }

    // A simple main method to test your sample cases, edge cases, and massive data sizes
    public static void main(String[] args) {
        System.out.println("--- Running Java Tests ---");

        // Test 1: Sample Case 0 (locations = [1, 1, 1])
        List<Integer> case0 = Arrays.asList(1, 1, 1);
        System.out.println("Test 1 (Sample Case 0): " + (fountainActivation(case0) == 1 ? "PASS" : "FAIL"));

        // Test 2: Sample Case 1 (locations = [2, 0, 0, 0])
        List<Integer> case1 = Arrays.asList(2, 0, 0, 0);
        System.out.println("Test 2 (Sample Case 1): " + (fountainActivation(case1) == 2 ? "PASS" : "FAIL"));

        // Test 3: Edge Case - All ranges are zero (Fountains only cover themselves)
        List<Integer> caseZeros = Arrays.asList(0, 0, 0, 0, 0);
        System.out.println("Test 3 (All Zeros): " + (fountainActivation(caseZeros) == 5 ? "PASS" : "FAIL"));

        // Test 4: Large Input Simulation (100,000 fountains with zero range)
        List<Integer> largeCase = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            largeCase.add(0);
        }
        long startTime = System.currentTimeMillis();
        int largeResult = fountainActivation(largeCase);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Test 4 (Large Data 10^5 elements): " + (largeResult == 100000 ? "PASS" : "FAIL"));
        System.out.println("Large Data execution time: " + (endTime - startTime) + " ms");
    }
}