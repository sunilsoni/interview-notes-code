package com.interview.notes.code.year.y2025.july.codesignal.test2;

import java.util.*;
import java.util.stream.*;

public class ResourceExchangeCycles {
    public static int solution(String[] resources, int conversionRate) {
        // Use counts instead of manipulating lists for speed/memory.
        int n = resources.length;
        // Count number of A's at the beginning
        int numA = 0;
        while (numA < n && resources[numA].equals("A")) numA++;
        // Count number of P's at the end
        int numP = n - numA;

        int cycles = 0;
        while (true) {
            // Step 1: Can we convert Ps to an A?
            if (numP >= conversionRate) {
                numP -= conversionRate;
                numA += 1;
                cycles++;
            }
            // Step 2: Else, can we convert last A to P?
            else if (numA > 0) {
                numA -= 1;
                numP += 1;
                cycles++;
            }
            // Step 3: Stop
            else {
                break;
            }
        }
        return cycles;
    }

    // Simple main method for manual tests
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            new TestCase(new String[]{"A", "A", "A", "P", "P", "P"}, 2, 13),
            new TestCase(new String[]{"A", "A"}, 2, 4),
            new TestCase(new String[]{"P", "P", "P"}, 3, 2),
            new TestCase(new String[]{"A", "A", "A", "A", "A", "A"}, 2, 11), // All A's
            new TestCase(new String[]{"P", "P"}, 2, 1), // All P's
            new TestCase(generateResources(250, 250), 10, 374) // Large input
        );

        int passCount = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase test = tests.get(i);
            int result = solution(test.resources, test.conversionRate);
            boolean pass = result == test.expected;
            System.out.println("Test #" + (i+1) + ": " + (pass ? "PASS" : "FAIL") + " (Expected: " + test.expected + ", Got: " + result + ")");
            if (pass) passCount++;
        }
        System.out.println("Total Passed: " + passCount + "/" + tests.size());
    }

    // Helper for large test case
    static String[] generateResources(int numA, int numP) {
        return Stream.concat(
                Collections.nCopies(numA, "A").stream(),
                Collections.nCopies(numP, "P").stream()
        ).toArray(String[]::new);
    }

    // Test case class
    static class TestCase {
        String[] resources;
        int conversionRate;
        int expected;

        TestCase(String[] resources, int conversionRate, int expected) {
            this.resources = resources;
            this.conversionRate = conversionRate;
            this.expected = expected;
        }
    }
}
