package com.interview.notes.code.year.y2025.september.common.test5;

public class PISeriesCalculator {

    // Main method to calculate PI and run tests
    public static void main(String[] args) {
        // Test cases with different n values
        testPISeries(1);    // Basic test with 1 term
        testPISeries(10);   // Small series test
        testPISeries(1000); // Medium series test
        testPISeries(100000); // Large series test for accuracy
    }

    /**
     * Calculates PI using Gregory-Leibniz series: π = 4(1 - 1/3 + 1/5 - 1/7 + ...)
     *
     * @param n number of terms to calculate
     * @return calculated value of PI
     */
    public static double calculatePI(int n) {
        // Using Java 8 Stream to generate sequence and calculate sum
        return 4 * java.util.stream.IntStream.range(0, n)
                // Map each index i to the corresponding term in series
                .mapToDouble(i -> {
                    // Calculate denominator: 2i + 1
                    double denominator = 2 * i + 1;
                    // Alternate between positive and negative terms using (-1)^i
                    return Math.pow(-1, i) / denominator;
                })
                // Sum all terms
                .sum();
    }

    /**
     * Test method to verify PI calculation accuracy
     *
     * @param n number of terms to use in calculation
     */
    private static void testPISeries(int n) {
        // Calculate PI using our method
        double calculatedPI = calculatePI(n);
        // Get actual PI value from Math constant
        double actualPI = Math.PI;
        // Calculate absolute difference
        double difference = Math.abs(calculatedPI - actualPI);

        // Print test results
        System.out.printf("Test with n=%d:%n", n);
        System.out.printf("Calculated PI: %.10f%n", calculatedPI);
        System.out.printf("Actual PI:     %.10f%n", actualPI);
        System.out.printf("Difference:    %.10f%n", difference);

        // Check if result is within acceptable error margin
        // More terms = higher accuracy expectation
        double acceptableError = 1.0 / n;
        boolean passed = difference < acceptableError;

        System.out.printf("Test Result: %s%n", passed ? "PASS" : "FAIL");
        System.out.println("------------------------");
    }
}
