package com.interview.notes.code.months.oct24.test30;

public class Solution1 {

    /**
     * The main method runs a series of test cases to verify the correctness of the solution method.
     * It outputs "PASS" for each test case that meets the expected result and "FAIL" otherwise.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Solution1 sol = new Solution1();

        // Define test cases as an array of TestCase objects
        TestCase[] testCases = new TestCase[]{
                new TestCase("", 0),
                new TestCase("-", 0),
                new TestCase("H", -1),
                new TestCase("HHH-", -1),
                new TestCase("-HHH", -1),
                new TestCase("---HHH---", -1),
                new TestCase("-H-H-H-H", 2),
                new TestCase("-H-HH--", 2),
                new TestCase("HH-HH", -1),
                new TestCase("-H-H-H-H-H", 3),
                new TestCase("H-H-H-H-H-H-", 3),
                new TestCase("-H--H--H--H--H-", 3),
                new TestCase("----", 0),
                new TestCase("H-H-H", 2),
                new TestCase("-H-HHH-H-", -1),
                // Large input test case
                new TestCase(generateLargeTestCase(100000), 50000)
        };

        // Iterate through each test case and check the result
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int result = sol.solution(tc.input);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expected + ", Got " + result + ")");
            }
        }
    }

    /**
     * Generates a large test case string with alternating houses and empty plots.
     * For example, "-H-H-H-..." up to the specified size.
     *
     * @param size The desired length of the test case string.
     * @return A string representing a large street with houses and empty plots.
     */
    private static String generateLargeTestCase(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append((i % 2 == 0) ? '-' : 'H');
        }
        return sb.toString();
    }

    /**
     * This method calculates the minimum number of water tanks needed to ensure that every house
     * on the street has at least one adjacent water tank. If it's impossible to cover all houses,
     * the method returns -1.
     *
     * @param S A string representing the street, where 'H' denotes a house and '-' denotes an empty plot.
     * @return The minimum number of water tanks needed, or -1 if it's impossible to cover all houses.
     */
    public int solution(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }

        int N = S.length();
        int tanks = 0;
        int lastTankPosition = -1;

        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == 'H') {
                // Check if the current house is already covered by a tank placed at i-1
                if (lastTankPosition == i - 1) {
                    continue;
                }

                // Try to place a tank at i+1 to cover current house and possibly the next one
                if (i + 1 < N && S.charAt(i + 1) == '-') {
                    tanks++;
                    lastTankPosition = i + 1;
                }
                // If placing at i+1 is not possible, try placing at i-1
                else if (i - 1 >= 0 && S.charAt(i - 1) == '-') {
                    tanks++;
                    lastTankPosition = i - 1;
                }
                // If neither position is available, it's impossible to cover this house
                else {
                    return -1;
                }
            }
        }

        return tanks;
    }

    /**
     * A simple class to represent a test case with an input string and the expected result.
     */
    static class TestCase {
        String input;
        int expected;

        TestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
