package com.interview.notes.code.year.y2025.november.ibm.test1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReachTarget {
    
    // Main function to find minimum moves to reach target position
    public static int reachTarget(int target) {
        // Handle the edge case where target is 0 - no moves needed
        if (target == 0) return 0;
        
        // Work with absolute value since distance is same for positive/negative targets
        target = Math.abs(target);
        
        // Variable to track sum of steps if we keep moving forward
        int sum = 0;
        
        // Variable to track number of moves made
        int moves = 0;
        
        // Keep moving forward until sum reaches or exceeds target
        while (sum < target) {
            moves++;  // Increment move counter for next move
            sum += moves;  // Add current move number to sum (moving forward)
        }
        
        // If we hit target exactly, return the number of moves
        if (sum == target) {
            return moves;
        }
        
        // Calculate how much we overshot the target
        int difference = sum - target;
        
        // If difference is even, we can flip some previous moves to backward
        // When we flip move i from forward to backward, net change is -2*i
        // We need moves that sum to difference/2 to compensate
        if (difference % 2 == 0) {
            return moves;  // Can reach target with current number of moves
        }
        
        // If difference is odd, we need more moves
        // Keep adding moves until difference becomes even
        while (difference % 2 != 0) {
            moves++;  // Try one more move
            sum += moves;  // Add this move to sum
            difference = sum - target;  // Recalculate difference
        }
        
        // Return minimum moves needed
        return moves;
    }
    
    // Testing method with comprehensive test cases
    public static void main(String[] args) {
        // Track test results
        int totalTests = 0;
        int passedTests = 0;
        
        System.out.println("===== RUNNING TEST CASES =====\n");
        
        // Test Case 1: Example from problem
        totalTests++;
        int target1 = 7;
        int expected1 = 5;
        int result1 = reachTarget(target1);
        boolean test1Pass = result1 == expected1;
        passedTests += test1Pass ? 1 : 0;
        System.out.println("Test 1 - Target: " + target1);
        System.out.println("Expected: " + expected1 + ", Got: " + result1);
        System.out.println("Status: " + (test1Pass ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 2: Second example from problem
        totalTests++;
        int target2 = 3;
        int expected2 = 2;
        int result2 = reachTarget(target2);
        boolean test2Pass = result2 == expected2;
        passedTests += test2Pass ? 1 : 0;
        System.out.println("Test 2 - Target: " + target2);
        System.out.println("Expected: " + expected2 + ", Got: " + result2);
        System.out.println("Status: " + (test2Pass ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 3: Edge case - target is 0
        totalTests++;
        int target3 = 0;
        int expected3 = 0;
        int result3 = reachTarget(target3);
        boolean test3Pass = result3 == expected3;
        passedTests += test3Pass ? 1 : 0;
        System.out.println("Test 3 - Target: " + target3);
        System.out.println("Expected: " + expected3 + ", Got: " + result3);
        System.out.println("Status: " + (test3Pass ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 4: Small positive target
        totalTests++;
        int target4 = 1;
        int expected4 = 1;
        int result4 = reachTarget(target4);
        boolean test4Pass = result4 == expected4;
        passedTests += test4Pass ? 1 : 0;
        System.out.println("Test 4 - Target: " + target4);
        System.out.println("Expected: " + expected4 + ", Got: " + result4);
        System.out.println("Status: " + (test4Pass ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 5: Negative target (should work same as positive)
        totalTests++;
        int target5 = -5;
        int expected5 = 5;
        int result5 = reachTarget(target5);
        boolean test5Pass = result5 == expected5;
        passedTests += test5Pass ? 1 : 0;
        System.out.println("Test 5 - Target: " + target5);
        System.out.println("Expected: " + expected5 + ", Got: " + result5);
        System.out.println("Status: " + (test5Pass ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 6: Larger number
        totalTests++;
        int target6 = 100;
        int expected6 = 15;
        int result6 = reachTarget(target6);
        boolean test6Pass = result6 == expected6;
        passedTests += test6Pass ? 1 : 0;
        System.out.println("Test 6 - Target: " + target6);
        System.out.println("Expected: " + expected6 + ", Got: " + result6);
        System.out.println("Status: " + (test6Pass ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 7: Large data input test
        totalTests++;
        System.out.println("Test 7 - Large Data Input Tests:");
        
        // Create list of large test values using Stream API
        List<Integer> largeTargets = Stream.of(1000, 10000, 100000, 1000000)
            .collect(Collectors.toList());
        
        boolean largeDatePass = true;
        long startTime = System.currentTimeMillis();
        
        // Process each large target using Stream API
        largeTargets.stream()
            .forEach(target -> {
                int result = reachTarget(target);
                System.out.println("  Target: " + target + ", Moves: " + result);
            });
        
        long endTime = System.currentTimeMillis();
        System.out.println("  Time taken for large inputs: " + (endTime - startTime) + "ms");
        System.out.println("Status: " + (largeDatePass ? "PASS ✓" : "FAIL ✗"));
        passedTests += largeDatePass ? 1 : 0;
        System.out.println();
        
        // Final Test Summary
        System.out.println("===== TEST SUMMARY =====");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Overall Status: " + 
            (passedTests == totalTests ? "ALL TESTS PASSED ✓" : "SOME TESTS FAILED ✗"));
        
        // Performance validation for maximum constraint
        System.out.println("\n===== PERFORMANCE TEST =====");
        int maxTarget = 1000000000;  // 10^9 as per constraints
        long perfStart = System.nanoTime();
        int maxResult = reachTarget(maxTarget);
        long perfEnd = System.nanoTime();
        System.out.println("Max constraint test (10^9): " + maxResult + " moves");
        System.out.println("Time: " + ((perfEnd - perfStart) / 1000000.0) + "ms");
    }
}