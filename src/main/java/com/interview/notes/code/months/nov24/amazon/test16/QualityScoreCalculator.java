package com.interview.notes.code.months.nov24.amazon.test16;

import java.util.*;
/*
FINAL WORKING



### Problem Description

Imagine you're a seller on Amazon, specializing in eco-friendly home products. Each of your items is rated by customers based on its quality and environmental impact.

The overall `qualityScore` of your products is determined by the maximum possible sum of consecutive ratings.

To improve the `qualityScore` of your products and attract more customers, you are given an integer `impactFactor` and two strategies:

1. **Amplify Ratings**: Select a contiguous segment of ratings and amplify them by multiplying each rating in that range by `impactFactor`.
2. **Adjust Ratings**: Select a contiguous segment of ratings and adjust them by dividing each rating in that range by `impactFactor`.

#### Note:

When applying the second strategy (i.e., **Adjust Ratings**):
- For dividing positive ratings, use the **floor** value of the division result.
- For dividing negative ratings, use the **ceiling** value of the division result.

Example:
Given `ratings = [4, -5, 5, -7, 1]` and `impactFactor = 2`,
If we choose to apply the second strategy with the segment [2, 5] (1-based indexing), then the modified ratings are:
\[4, ceil(-5/2), floor(5/2), ceil(-7/2), floor(1/2)\]
Resulting ratings: \[4, -2, 2, -3, 0\].

Given an array of ratings of size `n` and an integer `impactFactor`, determine the maximum possible `qualityScore`, i.e., the maximum possible sum of consecutive ratings by optimally selecting exactly one of the strategies to modify the ratings.

---

### Example
- **Input**:
    ```
    n = 5
    ratings = [5, -3, -3, 2, 4]
    impactFactor = 3
    ```
- **Strategy and Calculation**:
    - Strategy 1 (Amplify) on segment [1, 1]: \[5\*3, -3, -3, 2, 4\] = [15, -3, -3, 2, 4] → qualityScore = 15
    - Strategy 2 (Adjust) on segment [2, 3]: \[5, ceil(-3/3), ceil(-3/3), 2, 4\] = [5, -1, -1, 2, 4] → qualityScore = 9
    - Strategy 1 (Amplify) on segment [4, 5]: \[5, -3, -3, 2\*3, 4\*3\] = [5, -3, -3, 6, 12] → qualityScore = 20
  **Answer**: The maximum `qualityScore` is 20.

---

### Function Signature
```java
public static long calculateMaxQualityScore(int impactFactor, List<Integer> ratings)
```

### Input Format
- `impactFactor`: An integer denoting the value used in the strategies.
- `ratings`: A list of integers representing the ratings.

### Constraints
- \(1 ≤ n ≤ 2 × 10^5\)
- \(1 ≤ impactFactor ≤ 10^4\)
- \(-10^5 ≤ ratings[i] ≤ 10^5\)

---

### Sample Inputs & Outputs

1. **Input**:
    ```
    1
    4
    -2
    -3
    -3
    -1
    ```
   **Output**:
   `3`

   **Explanation**:
   The initial `qualityScore` of the ratings is `3`. Since `impactFactor = 1`, performing any of the strategies will not change the array.

2. **Input**:
    ```
    3
    1
    -4
    ```
   **Output**:
   `-1`

   **Explanation**:
   Using the **Adjust Ratings** strategy on the only element, the modified rating becomes `ceil(-4 / 3) = -1`. Thus, the `qualityScore` is `-1`.

---

Your task is to implement the function to achieve the highest possible `qualityScore` using either of the two strategies effectively.


 */
public class QualityScoreCalculator {

    public static long calculateMaxQualityScore(int impactFactor, List<Integer> ratings) {
        int n = ratings.size();
        int[] originalRatings = new int[n];
        int[] adjustedRatings = new int[n];

        // Copy ratings to an array for faster access
        for (int i = 0; i < n; i++) {
            originalRatings[i] = ratings.get(i);
        }

        // Compute adjusted ratings for the Adjust Ratings strategy
        for (int i = 0; i < n; i++) {
            int rating = originalRatings[i];
            if (rating >= 0) {
                adjustedRatings[i] = (int) Math.floor((double) rating / impactFactor);
            } else {
                adjustedRatings[i] = (int) Math.ceil((double) rating / impactFactor);
            }
        }

        // Compute maximum subarray sum without any modification (no strategy applied)
        long maxNoMod = maxSubarraySum(originalRatings);

        // Compute maximum subarray sum for Amplify Ratings strategy
        long maxAmplify = maxSubarrayWithStrategy(originalRatings, impactFactor, true);

        // Compute maximum subarray sum for Adjust Ratings strategy
        long maxAdjust = maxSubarrayWithStrategy(originalRatings, impactFactor, false);

        // Return the maximum of all three
        return Math.max(maxNoMod, Math.max(maxAmplify, maxAdjust));
    }

    private static long maxSubarraySum(int[] arr) {
        long maxEndingHere = arr[0];
        long maxSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    private static long maxSubarrayWithStrategy(int[] ratings, int impactFactor, boolean isAmplify) {
        int n = ratings.length;
        long maxEndNoMod = ratings[0];
        long maxEndMod;
        if (isAmplify) {
            maxEndMod = ratings[0] * (long) impactFactor;
        } else {
            maxEndMod = adjustedValue(ratings[0], impactFactor);
        }
        long maxTotal = Math.max(maxEndNoMod, maxEndMod);

        for (int i = 1; i < n; i++) {
            long rating = ratings[i];
            long modifiedRating;
            if (isAmplify) {
                modifiedRating = rating * impactFactor;
            } else {
                modifiedRating = adjustedValue(rating, impactFactor);
            }

            // Update maxEndNoMod
            maxEndNoMod = Math.max(rating, maxEndNoMod + rating);

            // Update maxEndMod
            long tempMod = Math.max(modifiedRating,
                    Math.max(maxEndNoMod + modifiedRating, maxEndMod + modifiedRating));

            maxEndMod = tempMod;

            // Update maxTotal
            maxTotal = Math.max(maxTotal, Math.max(maxEndNoMod, maxEndMod));
        }
        return maxTotal;
    }

    private static long adjustedValue(long rating, int impactFactor) {
        if (rating >= 0) {
            return (long) Math.floor((double) rating / impactFactor);
        } else {
            return (long) Math.ceil((double) rating / impactFactor);
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Input 1
        testCases.add(new TestCase(1, Arrays.asList(4, -2, -3, -3, -1), 3L));

        // Sample Input 2
        testCases.add(new TestCase(3, Collections.singletonList(-4), -1L));

        // Custom Test Case
        testCases.add(new TestCase(3, Arrays.asList(5, -3, -3, 2, 4), 20L));

        // Large Input Test Case
        Random random = new Random();
        int largeN = 200000;
        List<Integer> largeRatings = new ArrayList<>(largeN);
        for (int i = 0; i < largeN; i++) {
            largeRatings.add(random.nextInt(200001) - 100000); // Random ratings between -1e5 and 1e5
        }
        testCases.add(new TestCase(10000, largeRatings, null)); // Expected result is not precomputed

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = calculateMaxQualityScore(tc.impactFactor, tc.ratings);
            System.out.println("Test Case " + (i + 1) + ": " + (tc.expectedResult == null ? "Computed result = " + result
                    : (result == tc.expectedResult ? "PASS" : "FAIL (Expected " + tc.expectedResult + ", Got " + result + ")")));
        }
    }

    static class TestCase {
        int impactFactor;
        List<Integer> ratings;
        Long expectedResult;

        TestCase(int impactFactor, List<Integer> ratings, Long expectedResult) {
            this.impactFactor = impactFactor;
            this.ratings = ratings;
            this.expectedResult = expectedResult;
        }
    }
}
