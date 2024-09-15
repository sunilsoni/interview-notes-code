package com.interview.notes.code.months.sept24.test7;

import java.util.ArrayList;

public class Solution1 {
    
    public static int solve(int n) {
        long result = 0;
        // Initialize the list of targets
        ArrayList<Integer> targets = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            targets.add(i);
        }
        
        // Keep removing every alternate target until one is left
        while (targets.size() > 1) {
            ArrayList<Integer> remainingTargets = new ArrayList<>();
            // Iterate to remove every alternate target
            for (int i = 0; i < targets.size(); i++) {
                if (i % 2 == 0) {
                    // Sum the points of the target being removed
                    result += targets.get(i);
                } else {
                    // Collect the alternate (remaining) targets
                    remainingTargets.add(targets.get(i));
                }
            }
            // Update the targets list for the next iteration
            targets = remainingTargets;
        }
        
        // Print total score (result)
        return (int) result;
    }

    public static void main(String[] args) {
        // Example test case
        int n = 5; // You can modify this as per other test cases
        System.out.println(solve(n)); // Should print 11 for the sample input
    }
}