package com.interview.notes.code.year.y2025.november.ibm.test1;

import java.util.List;

class PackagingSystemOptimizer {
    
    private static final int MOD = 1000000007;
    
    public static int ways(int total, int k) {
        var dp = new long[total + 1];
        dp[0] = 1;
        
        for (int item = 1; item <= k; item++) {
            for (int sum = item; sum <= total; sum++) {
                dp[sum] = (dp[sum] + dp[sum - item]) % MOD;
            }
        }
        
        return (int) dp[total];
    }
    
    public static void main(String[] args) {
        record TestScenario(int total, int k, int expected) {}
        
        var scenarios = List.of(
            new TestScenario(5, 3, 5),
            new TestScenario(4, 2, 3),
            new TestScenario(8, 2, 5),
            new TestScenario(10, 5, 30),
            new TestScenario(1, 1, 1),
            new TestScenario(100, 50, 190569292),
            new TestScenario(500, 100, 688158622),
            new TestScenario(1000, 100, 222372164),
            new TestScenario(1000, 1, 1),
            new TestScenario(50, 25, 196888),
            new TestScenario(75, 30, 7667288),
            new TestScenario(200, 75, 989390804),
            new TestScenario(999, 99, 320774968),
            new TestScenario(3, 3, 3),
            new TestScenario(7, 4, 11),
            new TestScenario(15, 7, 134),
            new TestScenario(20, 10, 420),
            new TestScenario(0, 5, 1),
            new TestScenario(2, 2, 2),
            new TestScenario(6, 3, 7)
        );
        
        var startTime = System.currentTimeMillis();
        
        var testResults = scenarios.stream()
            .map(test -> {
                var result = ways(test.total, test.k);
                var passed = result == test.expected;
                System.out.printf("Total=%d, K=%d: Expected=%d, Got=%d [%s]%n",
                    test.total, test.k, test.expected, result, 
                    passed ? "PASS" : "FAIL");
                return passed;
            })
            .toList();
        
        var elapsedTime = System.currentTimeMillis() - startTime;
        var passCount = testResults.stream().filter(Boolean::booleanValue).count();
        
        System.out.printf("%n=== Test Summary ===%n");
        System.out.printf("Passed: %d/%d%n", passCount, scenarios.size());
        System.out.printf("Execution Time: %dms%n", elapsedTime);
        System.out.printf("Status: %s%n", 
            passCount == scenarios.size() ? "ALL TESTS PASSED ✓" : "SOME TESTS FAILED ✗");
        
        System.out.printf("%n=== Stress Test ===%n");
        var stressStart = System.nanoTime();
        var stressResult = ways(1000, 100);
        var stressTime = (System.nanoTime() - stressStart) / 1_000_000.0;
        System.out.printf("Maximum constraint (1000, 100): %d in %.2fms%n", 
            stressResult, stressTime);
        
        System.out.printf("%n=== Edge Cases ===%n");
        System.out.printf("Empty box (0, 10): %d%n", ways(0, 10));
        System.out.printf("Single item (1, 100): %d%n", ways(1, 100));
        System.out.printf("Max constraints (1000, 100): %d%n", ways(1000, 100));
    }
}