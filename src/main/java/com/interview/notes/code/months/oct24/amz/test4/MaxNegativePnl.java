package com.interview.notes.code.months.oct24.amz.test4;

import java.util.*;

/*
WORKING

You are analyzing the market trends of Amazon stocks. An AWS financial service model returned an array of integers, PnL (Profit and Loss), for your portfolio representing that in the ith month, you will either gain or lose PnL[i]. All reported PnL values are positive, representing gains.

As part of the analysis, you will perform the following operation on the PnL array any number of times:
Choose any month i (0 ≤ i < n) and multiply PnL[i] by -1.

Find the maximum number of months you can afford to face a loss, i.e. have a negative PnL, such that the cumulative PnL for each of the n months remains strictly positive (i.e., remains greater than 0).

Note: The cumulative PnL for the ith month is defined as the sum of PnL from the starting month up to the ith month. For example, the cumulative PnL for the PnL = [3, -2, 5, -6, 1] is [3, 1, 6, 0, 1].

Example:
Consider, n = 4, and PnL = [5, 3, 1, 2].

Some of the possible arrays after performing the given operation some number of times:

Modified PnL  |  Cumulative PnL  |  Number of negatives  |  Is Valid  |  Comments
[5, -3, -1, 2]  |  [5, 2, 1, 3]  |  2  |  Yes  |  The operation was performed on the second and third months (in bold). All the cumulative PnL are positive.
[5, -3, -1, -2]  |  [5, 2, 1, -1]  |  3  |  No  |  The last cumulative PnL is negative, hence this is not valid.
[5, -3, 1, -2]  |  [5, 2, 3, 1]  |  2  |  Yes  |  All the cumulative PnL are positive.
[-5, 3, 1, 2]  |  [-5, -2, -1, 1]  |  1  |  No  |  The cumulative PnL for the first three months are negative.



There are many more ways to perform the operations but the maximum number of negative PnLs there can be, maintaining a positive cumulative PnL is 2. Report 2 as the answer.

Function Description:
Complete the function getMaxNegativePnL in the editor below.

getMaxNegativePnL has the following parameter:
int PnL[n]: an array of integers

Constraints:
* 1 < n ≤ 10⁵
* 1 ≤ PnL[i] ≤ 10⁹

Input Format For Custom Testing:

The first line contains an integer, n, the number of elements in PnL.
Each line j of the n subsequent lines (where 0 ≤ j < n) contains an integer, PnL[j].

Sample Case 0:

Sample Input For Custom Testing:

STDIN FUNCTION

5  ->  PnL[] size n = 4
1  ->  PnL = [1, 1, 1, 1, 2]
1
1
1
1

Sample Output:
2

Explanation:
There are multiple possible PnLs such as [1, -1, -1, 1, 1], [-1, 1, -1, 1, -1], etc.
However, it is optimal to modify the PnL to be [1, 1, -1, 1, -1] or [1, 1, 1, -1, -1].





Sample Case 1

Sample Input For Custom Testing

STDIN FUNCTION

6  ->  PnL[] size n = 6
5  ->  PnL = [5, 2, 3, 5, 2, 3]
2
3
5
2
3

Sample Output:
3

Explanation:
The possible PnLs such that all the cumulative PnLs are positive are [5, 2, -3, 5, 2, -3], [5, 2, 3, 5, -2, -3], [5, -2, 3, 5, -2, -3] etc.

The maximum number of negatives we can have ensuring that all the cumulative PnLs are positive is 3 corresponding to the case [5, -2, 3, 5, -2, -3].
Note that [5, 2, 3, -5, -2, -3] is not a valid case as the cumulative PnLs are [5, 7, 10, 5, 3, 0] but they must be strictly positive.




Complete the 'getMaxNegativePnL' function below.
The function is expected to return an INTEGER.
The function accepts INTEGER_ARRAY PnL as parameter.

public static int getMaxNegativePnl(List<Integer> PnL) {
// Write your code here
}

 */
public class MaxNegativePnl {

    /**
     * Function to find the maximum number of negative PnL entries
     * such that the cumulative PnL remains strictly positive.
     *
     * @param PnL List of positive integers representing PnL values.
     * @return The maximum number of PnL entries that can be negative.
     */
    public static int getMaxNegativePnl(List<Integer> PnL) {
        long cumSum = 0;
        int negCount = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int pnl : PnL) {
            cumSum += pnl;
            // Attempt to negate the current PnL
            cumSum -= 2L * pnl;
            maxHeap.add(pnl);
            negCount++;

            // Ensure cumulative sum remains positive
            while (cumSum <= 0 && !maxHeap.isEmpty()) {
                int largestNegatedPnl = maxHeap.poll();
                cumSum += 2L * largestNegatedPnl;
                negCount--;
            }
        }

        return negCount;
    }

    public static void main(String[] args) {
        // Directly run test cases without using Scanner
        runTests();
    }

    /**
     * Method to run predefined test cases and check for pass/fail.
     */
    private static void runTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase(Arrays.asList(1, 1, 1, 1, 1), 2));

        // Sample Test Case 1
        testCases.add(new TestCase(Arrays.asList(5, 2, 3, 5, 2, 3), 3));

        // Additional Test Cases
        testCases.add(new TestCase(Arrays.asList(5, 3, 1, 2), 2));
        testCases.add(new TestCase(Arrays.asList(1), 0));
        testCases.add(new TestCase(Arrays.asList(10, 1, 1, 1, 1), 4)); // Updated Expected Output
        testCases.add(new TestCase(Arrays.asList(2, 2, 2, 2, 2), 2));
        testCases.add(new TestCase(Arrays.asList(1000000000, 1000000000, 1000000000), 1));

        // Large Input Test Case
        testCases.add(generateLargeTestCase(100000, 1, 49999)); // Updated Expected Output

        boolean allPassed = true;

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int output = getMaxNegativePnl(tc.PnL);
            if (output == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expected + ", Got " + output + ")");
                allPassed = false;
            }
        }

        if (allPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    /**
     * Helper method to generate a large test case.
     *
     * @param size     The size of the PnL list.
     * @param value    The PnL value to use for all entries.
     * @param expected The expected number of negatives.
     * @return A TestCase object with the generated PnL list and expected result.
     */
    private static TestCase generateLargeTestCase(int size, int value, int expected) {
        List<Integer> pnlList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            pnlList.add(value);
        }
        return new TestCase(pnlList, expected);
    }

    /**
     * Helper class to store test cases.
     */
    private static class TestCase {
        List<Integer> PnL;
        int expected;

        TestCase(List<Integer> PnL, int expected) {
            this.PnL = PnL;
            this.expected = expected;
        }
    }
}
