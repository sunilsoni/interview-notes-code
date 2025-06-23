package com.interview.notes.code.year.y2025.June.amazon.test5;

public class VersionComparer {

    // Main method to compare semantic versions
    public static int compareSemVer(String version1, String version2) {
        // Split versions into components using dot as delimiter
        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");

        // Compare each component (major, minor, revision)
        for (int i = 0; i < 3; i++) {
            // Convert string numbers to integers for proper comparison
            int v1 = Integer.parseInt(v1Parts[i]);
            int v2 = Integer.parseInt(v2Parts[i]);

            // If components are different, determine which is greater
            if (v1 < v2) return -1;
            if (v1 > v2) return 1;
        }

        // If we reach here, versions are equal
        return 0;
    }

    // Main method with comprehensive test cases
    public static void main(String[] args) {
        // Test case structure: version1, version2, expected result
        Object[][] testCases = {
                {"1.13.4", "1.11.3", 1},
                {"1.09.4", "1.11.3", -1},
                {"1.10.2", "1.10.2", 0},
                {"2.0.0", "1.9.9", 1},
                {"1.0.0", "1.0.1", -1},
                {"9.9.9", "9.9.9", 0},
                {"0.0.1", "0.0.2", -1}
        };

        // Run all test cases
        int passedTests = 0;
        for (Object[] test : testCases) {
            String v1 = (String) test[0];
            String v2 = (String) test[1];
            int expected = (Integer) test[2];

            int result = compareSemVer(v1, v2);
            boolean passed = result == expected;

            System.out.printf("Test: %s vs %s -> Expected: %d, Got: %d, %s%n",
                    v1, v2, expected, result, passed ? "PASS" : "FAIL");

            if (passed) passedTests++;
        }

        // Print summary
        System.out.printf("%nPassed %d out of %d tests%n",
                passedTests, testCases.length);
    }
}
