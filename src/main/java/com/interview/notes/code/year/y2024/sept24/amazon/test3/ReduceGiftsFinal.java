package com.interview.notes.code.year.y2024.sept24.amazon.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
WORKING



New Year's Day is around the corner and Amazon is having a sale. They have a list of items they are considering but they may need to remove some of them. Determine the minimum number of items to remove from an array of prices so that the sum of prices of any kitems does not exceed a threshold.
Note: If the number of items in the list is less than k, then there is no need to remove any more items.
Example
prices = [3, 2, 1, 4, 6, 51
k = 3
threshold = 14
$3
$2
$1
$4
$6
$5
Hao
The sum of prices for every k= 3 items must not be more than threshold = 14. The sum of the
prices of the last three items is 6 + 5 + 4 = 15. The item priced $6 can be removed leaving:
$3
$2
$1
$4
$5
No 3 items' prices sum to greater than 14. Only 1 item needs to be removed.
Function Description
Complete the function reduceGifts in the editor below.


reduceGifts has the following parameters: int prices[n]: the prices of each item int k: the number of items to sum
int threshold: the maximum price of kitems

Returns
int: the minimum number of items to remove from the list


• Input Format For Custom Testing
AOT G
The first line contains an integer, n, the number of elements in prices.
The next n lines contain n integers, prices[i].
The next line contains an integer, k, the number of items to sum.
The next line contains an integer, threshold, the maximum allowed sum for any k distinct items.


• Sample Case 0
Sample Input For Custom Testing
STDIN
FUNCTION
879d197
6
9
6
7
2
7
2
2
13
prices] size n = 6
prices = 19, 6, 7, 2, 7, 21

k = 2
→ threshold = 13
Sample Output
2
1070
Explanation
[6, 2, 7, 2].
Items with prices 9 and 7 have a sum larger than 13. After removing these two items, prices' =
No kitems have a sum of prices greater than threshold.



• Sample Case 1
Sample Input For Custom Testing
STDIN
FUNCTION
 8
9
6
3
2
9
10
10
11
4
1
→
→
prices] size n = 8
prices = 19, 6, 3, 2, 9, 10, 10, 111
hook+6087edi
8-
-6629
azon
→
k= 4
→ threshold = 1
Ran
Sample Output
5
hook+608
amazon
Hack
Explanation
Since no price is less than or equal to threshold, the sum of any k elements will always be greater than threshold.
The threshold only applies to groups of kitems. Now the goal is to reduce the array length to k-
1 which requires the removal of 5 elements.



MUST follow Constraints:

1<=k<=n<=10 to the power 5
1<=threashhold <= 10 to the power 9
1<=prices[i]<=0 to the power 9

IMplement below make sure to consider for edges cases

 public static int reduceGifts(List<Integer> prices, int k, int threshold) {

  }


1<=k<=n<=10 to the power 5
1<=threashhold <= 10 to the power 9
1<=prices[i]<=0 to the power 9
 */
public class ReduceGiftsFinal {
    /**
     * Determines the minimum number of items to remove from the list so that the sum of prices
     * of any k items does not exceed the threshold.
     *
     * @param prices    List of item prices.
     * @param k         Number of items to sum.
     * @param threshold Maximum allowed sum for any k items.
     * @return Minimum number of items to remove.
     */
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        int n = prices.size();

        // If the number of items is less than k, no need to remove any items.
        if (n < k) {
            return 0;
        }

        // Create a mutable copy of prices and sort it in descending order.
        List<Integer> mutablePrices = new ArrayList<>(prices);
        Collections.sort(mutablePrices, Collections.reverseOrder());

        // Compute the prefix sums for efficient sum calculations.
        long[] prefixSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + mutablePrices.get(i);
        }

        int low = 0;
        int high = n;
        // Binary search to find the minimal number of items to remove.
        while (low < high) {
            int mid = (low + high) / 2;
            int remainingItems = n - mid;

            if (remainingItems < k) {
                // If remaining items are less than k, we accept this number of removals.
                high = mid;
            } else {
                // Calculate the sum of the top k prices after removing 'mid' items.
                long sumOfKItems = prefixSums[mid + k] - prefixSums[mid];
                if (sumOfKItems <= threshold) {
                    // Sum is within threshold; try to remove fewer items.
                    high = mid;
                } else {
                    // Sum exceeds threshold; need to remove more items.
                    low = mid + 1;
                }
            }
        }

        return low;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> prices1 = List.of(9, 6, 3, 2, 9, 10, 10, 11); // immutable list
        int k1 = 4;
        int threshold1 = 1;
        System.out.println(reduceGifts(prices1, k1, threshold1)); // Output should be 5

        // Test Case 2
        List<Integer> prices2 = List.of(9, 6, 7, 2, 7, 2); // immutable list
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(prices2, k2, threshold2)); // Output should be 2
    }

    /**
     * Runs sample and additional test cases to verify the correctness of the reduceGifts method.
     */
    private static void testReduceGifts() {
        List<TestCase> testCases = Arrays.asList(
                new TestCase(Arrays.asList(3, 2, 1, 4, 6, 5), 3, 14, 1),
                new TestCase(Arrays.asList(9, 6, 7, 2, 7, 2), 2, 13, 2),
                new TestCase(Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11), 4, 1, 5),
                new TestCase(Arrays.asList(1, 1, 1, 1, 1), 3, 10, 0),
                new TestCase(Arrays.asList(100, 100, 100), 3, 300, 0),
                new TestCase(Arrays.asList(1, 1, 1), 2, 0, 2)
        );

        boolean allPassed = true;
        for (TestCase testCase : testCases) {
            int result = reduceGifts(testCase.prices, testCase.k, testCase.threshold);
            if (result == testCase.expected) {
                System.out.println("Test case passed.");
            } else {
                System.out.println("Test case failed. Expected " + testCase.expected + " but got " + result);
                allPassed = false;
            }
        }

        if (allPassed) {
            System.out.println("All test cases passed.");
        }
    }

    /**
     * Helper class to store test cases.
     */
    private static class TestCase {
        List<Integer> prices;
        int k;
        int threshold;
        int expected;

        TestCase(List<Integer> prices, int k, int threshold, int expected) {
            // Create a mutable list to avoid UnsupportedOperationException during sorting.
            this.prices = new ArrayList<>(prices);
            this.k = k;
            this.threshold = threshold;
            this.expected = expected;
        }
    }
}
