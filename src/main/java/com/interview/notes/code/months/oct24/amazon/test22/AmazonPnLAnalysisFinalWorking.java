package com.interview.notes.code.months.oct24.amazon.test22;

import java.util.*;

/**
 * WORKING
 * <p>
 * <p>
 * *2. Code Question 2**
 * You are analyzing the market trends of Amazon stocks. An AWS financial service model returned an array of integers, `PnL` (Profit and Loss), for your portfolio representing that in the ith month, you will either gain or lose `PnL[i]`. All reported PnL values are positive, representing gains.
 * <p>
 * As part of the analysis, you will perform the following operation on the `PnL` array any number of times:
 * - Choose any month \( i \) (where \( 0 \leq i < n \)) and multiply `PnL[i]` by `-1`
 * <p>
 * Find the maximum number of months you can afford to face a loss, i.e., have a negative `PnL`, such that the cumulative `PnL` for each of the \( n \) months remains strictly positive (i.e., remains greater than 0).
 * <p>
 * *Note**: The cumulative `PnL` for the ith month is defined as the sum of `PnL` from the starting month up to the ith month. For example, the cumulative `PnL` for the `PnL` = `[3, -2, 5, -6, 1]` is `[3, 1, 6, 0, 1]`.
 * <p>
 * *Example**
 * Consider, \( n = 4 \), and `PnL = [5, 3, 1, 2]`
 * <p>
 * Some of the possible arrays after performing the given operation some number of times:
 * <p>
 * <p>
 * *Some of the possible arrays after performing the given operation some number of times:**
 * <p>
 * | Modified PnL       | Cumulative PnL | Number of negatives | Is Valid | Comments                                                                                 |
 * |--------------------|----------------|----------------------|----------|------------------------------------------------------------------------------------------|
 * | `[5, -3, -1, 2]`   | `[5, 2, 1, 3]` | 2                    | Yes      | The operation was performed on the second and third months (in bold).                    |
 * | `[5, -3, -1, -2]`  | `[5, 2, 1, -1]`| 3                    | No       | The last cumulative PnL is negative, hence this is not valid.                            |
 * | `[5, -3, 1, -2]`   | `[5, 2, 3, 1]` | 2                    | Yes      | All the cumulative PnL are positive                                                     |
 * | `[-5, 3, 1, 2]`    | `[-5, -2, -1, 1]` | 1                 | No       | The cumulative PnL for the first three months are negative.                              |
 * <p>
 * There are many more ways to perform the operations but the maximum number of negative PnLs there can be, maintaining a positive cumulative PnL, is `2`. Report `2` as the answer.
 * <p>
 * <p>
 * ---
 * <p>
 * *Function Description**
 * Complete the function `getMaxNegativePnL` in the editor below.
 * <p>
 * `getMaxNegativePnL` has the following parameter:
 * - `int PnL[n]`: an array of integers
 * <p>
 * *Constraints**
 * - \(1 \leq n \leq 10^5\)
 * - \(1 \leq PnL[i] \leq 10^9\)
 * <p>
 * <p>
 * <p>
 * *Input Format For Custom Testing**
 * The first line contains an integer, \( n \), the number of elements in `PnL`.
 * Each line \( i \) of the \( n \) subsequent lines (where \( 0 \leq i < n \)) contains an integer, `PnL[i]`.
 * <p>
 * *Sample Case 0**
 * Sample Input For Custom Testing
 * <p>
 * STDIN        FUNCTION
 * ```
 * 5            → PnL[] size n = 4
 * 1            → PnL = [1, 1, 1, 1]
 * 1
 * 1
 * 1
 * 1
 * ```
 * Sample Output
 * ```
 * 2
 * ```
 * Explanation:
 * There are multiple possible PnLs such as `[1, -1, -1, 1, 1]`, `[-1, 1, -1, 1, -1]`, etc.
 * However, it is optimal to modify the PnL to be `[1, 1, -1, 1]` or `[1, 1, 1, -1]`.
 * <p>
 * ---
 * <p>
 * *Sample Case 1**
 * Sample Input For Custom Testing
 * <p>
 * STDIN        FUNCTION
 * ```
 * 6            → PnL[] size n = 6
 * 5            → PnL = [5, 2, 3, 5, 2, 3]
 * 2
 * 3
 * 5
 * 2
 * 3
 * ```
 * Sample Output
 * ```
 * 3
 * ```
 * Explanation:
 * The possible PnLs such that all the cumulative PnLs are positive are `[5, 2, -3, 5, 2, -3]`, `[5, 2, 3, 5, -2, -3]`, etc.
 * <p>
 * The maximum number of negatives we can have ensuring that all the cumulative PnLs are positive is `3` corresponding to the case `[5, -2, 3, 5, -2, -3]`. Note that `[5, 2, 3, 5, -5, -2, -3]` is not a valid case as the cumulative PnLs are `[5, 7, 10, 5, 3, 0]` but they must be strictly positive.
 */
public class AmazonPnLAnalysisFinalWorking {

    /**
     * Function to find the maximum number of PnL elements that can be made negative
     * while keeping cumulative sums strictly positive.
     *
     * @param PnL list of positive integers representing profit and loss
     * @return maximum number of PnL elements that can be negative
     */
    public static int getMaxNegativePnL(List<Integer> PnL) {
        long cumulativeSum = PnL.get(0); // Initialize cumulative sum with the first month's PnL
        int totalNegatives = 0; // Counter for negative PnL elements

        // Max-heap to store negative PnL elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 1; i < PnL.size(); i++) {
            int currentPnL = PnL.get(i);
            cumulativeSum += -currentPnL; // Assume flipping PnL[i] to negative
            maxHeap.add(currentPnL);
            totalNegatives++;

            // If cumulative sum becomes non-positive, undo flips starting with the largest PnL
            while (cumulativeSum <= 0 && !maxHeap.isEmpty()) {
                int largestPnL = maxHeap.poll();
                cumulativeSum += 2L * largestPnL; // Undo the flip
                totalNegatives--;
            }
        }

        return totalNegatives;
    }

    /**
     * Main method for testing the getMaxNegativePnL function.
     */
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase(
                Arrays.asList(1, 1, 1, 1, 1),
                2,
                "Sample Case 0"
        ));

        // Sample Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(5, 2, 3, 5, 2, 3),
                3,
                "Sample Case 1"
        ));

        // Edge Case: All PnLs are the same
        testCases.add(new TestCase(
                Arrays.asList(5, 5, 5, 5, 5),
                2,
                "Edge Case: All PnLs are the same"
        ));

        // Edge Case: Large PnLs
        testCases.add(new TestCase(
                Arrays.asList(1000000000, 1000000000, 1000000000),
                1,
                "Edge Case: Large PnLs"
        ));

        // Corrected Large Input Case
        int n = 100000;
        List<Integer> largePnL = new ArrayList<>(Collections.nCopies(n, 1));
        testCases.add(new TestCase(
                largePnL,
                (n - 1) / 2,
                "Large Input Case"
        ));

        // Run and test each case
        for (TestCase testCase : testCases) {
            int result = getMaxNegativePnL(testCase.PnL);
            if (result == testCase.expectedOutput) {
                System.out.println(testCase.description + ": PASS");
            } else {
                System.out.println(testCase.description + ": FAIL (Expected " + testCase.expectedOutput + ", Got " + result + ")");
            }
        }
    }

    /**
     * Class to represent a test case.
     */
    static class TestCase {
        List<Integer> PnL;
        int expectedOutput;
        String description;

        TestCase(List<Integer> PnL, int expectedOutput, String description) {
            this.PnL = PnL;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }
}
