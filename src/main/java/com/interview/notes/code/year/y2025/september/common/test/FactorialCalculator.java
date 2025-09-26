package com.interview.notes.code.year.y2025.september.common.test;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class FactorialCalculator {         // 3. Main class definition

    /**
     * Computes n! using BigInteger and Java 8 streams.
     *
     * @param n non-negative integer whose factorial is to be computed
     * @return n! as a BigInteger
     */
    public static BigInteger factorial(int n) {            // 4. Method signature
        // 5. Create a stream of ints from 1 to n (inclusive),
        //    map each int to BigInteger, and multiply them all.
        return IntStream.rangeClosed(1, n)
                .mapToObj(BigInteger::valueOf)     // 6. Convert each int to BigInteger
                .reduce(BigInteger.ONE,           // 7. Start with 1
                        BigInteger::multiply);     // 8. Multiply cumulatively
    }

    /**
     * Runs a suite of test cases and prints PASS/FAIL for each.
     */
    public static void main(String[] args) {               // 9. Main entry point
        // 10. Small test cases with known expected values
        int[] testInputs = {0, 1, 5, 10, 20};
        String[] expectedFacts = {
                "1",                                           // 0! = 1
                "1",                                           // 1! = 1
                "120",                                         // 5! = 120
                "3628800",                                     // 10! = 3,628,800
                "2432902008176640000"                          // 20! fits within 64 bits
        };

        System.out.println("=== Small Test Cases ===");
        for (int i = 0; i < testInputs.length; i++) {
            int n = testInputs[i];
            BigInteger result = factorial(n);             // 11. Compute factorial
            String resStr = result.toString();
            // 12. Compare against expected string
            String status = resStr.equals(expectedFacts[i]) ? "PASS" : "FAIL";
            System.out.printf("n = %2d | %-22s | %s\n",
                    n, "Computed=" + resStr, status);
        }

        // 13. Large test case to verify performance and correctness
        int largeN = 100;
        System.out.println("\n=== Large Test Case ===");
        long start = System.currentTimeMillis();
        BigInteger bigResult = factorial(largeN);
        long elapsed = System.currentTimeMillis() - start;
        // 14. Simple check: result is non-null and timing under threshold
        String largeStatus = (bigResult != null && elapsed < 2000)
                ? "PASS" : "FAIL";
        System.out.printf("n = %3d | digits=%4d | time=%4dms | %s\n",
                largeN,
                bigResult.toString().length(),
                elapsed,
                largeStatus);
    }
}