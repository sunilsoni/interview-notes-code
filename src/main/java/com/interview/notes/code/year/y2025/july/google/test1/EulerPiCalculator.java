package com.interview.notes.code.year.y2025.july.google.test1;

import java.util.stream.IntStream;

public class EulerPiCalculator {

    /**
     * Approximate π using the first n terms of:
     *    π²/6 = Σ (1 / k²),  k = 1..∞
     * so  π ≈ sqrt(6 * Σ_{k=1..n} 1/k²).
     */
    public static double calculatePi(int n) {
        double sum = IntStream.rangeClosed(1, n)
                              .mapToDouble(k -> 1.0 / (k * (double) k))
                              .sum();
        return Math.sqrt(6 * sum);
    }

    /**
     * Run one test: compute π for the given n, compare to Math.PI,
     * and print PASS if |approx - PI| <= tol.
     */
    private static void runTest(int n, double tol) {
        double approx = calculatePi(n);
        double error  = Math.abs(approx - Math.PI);
        String result = (error <= tol) ? "PASS" : "FAIL";
        System.out.printf("n=%d  approx=%.10f  error=%.2e  tol=%.1e  %s%n",
                          n, approx, error, tol, result);
    }

    public static void main(String[] args) {
        // --- 1) Hard-coded test cases (you can edit these) ---
        int[]    testNs      = {1_000, 10_000, 100_000, 1_000_000};
        double[] tolerances  = {1e-3, 1e-4,   1e-5,     1e-6    };
        
        System.out.println("=== Running built-in tests ===");
        for (int i = 0; i < testNs.length; i++) {
            runTest(testNs[i], tolerances[i]);
        }

        // --- 2) Optional: allow a custom N from command-line ---
        if (args.length > 0) {
            try {
                int n = Integer.parseInt(args[0]);
                System.out.println("\n=== Custom run for N=" + n + " ===");
                runTest(n, 1e-8);  // you can choose a default tol here
            } catch (NumberFormatException e) {
                System.err.println("Please pass a valid integer for N.");
            }
        }
    }
}