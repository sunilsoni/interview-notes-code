package com.interview.notes.code.year.y2024.nov24.TikTok.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
FINAL WORKING


### Sample Case 1

**Sample Input for Custom Testing**

```
STDIN                                Function
5                                   → the number of creators n = 5
5                                   → creatorsEngagementPower = [5, 4, 3, 2, 1]
4
3
2
1
3                                   → minCreatorsRequired = 3
20                                  → minTotalEngagementPowerRequired = 20
```

**Sample Output**
```
0
```

**Explanation**
No collaboration is possible in this case because the threshold value is greater than the total sum of the engagement powers of all the creators.

---

### Constraints

- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq creatorsEngagementPower[i] \leq 10^9 \)
- \( 1 \leq minCreatorsRequired \leq n \)
- \( 1 \leq minTotalEngagementPowerRequired \leq 10^{14} \)

---

### Input Format for Custom Testing

1. The first line contains an integer `n`, the size of `creatorsEngagementPower`.
2. Each of the next `n` lines contains an integer, `creatorsEngagementPower[i]`.
3. The next line contains an integer, `minCreatorsRequired`.
4. The next line contains an integer, `minTotalEngagementPowerRequired`.

---

### Sample Case 0

**Sample Input for Custom Testing**

```
STDIN                                Function
7                                   → the number of creators n = 7
4                                   → creatorsEngagementPower = [4, 4, 3, 6, 4, 3, 5]
4
3
6
4
3
5
2                                   → minCreatorsRequired = 2
8                                   → minTotalEngagementPowerRequired = 8
```

**Sample Output**
```
3
```

**Explanation**
One of the optimal ways to form the collaborations is:
1. Use the first and second creators \((4 + 4 = 8)\).
2. Use the third and fourth creators \((3 + 6 = 9)\).
3. Use the fifth, sixth, and seventh creators \((4 + 3 + 5 = 12)\).

---

### Maximize Collaboration

As a data analyst at TikTok, you've been assigned an innovative project to help maximize creator collaborations for peak viral impact. With so many creators competing for attention, each creator has their own engagement power, which measures how likely they are to boost views, likes, and shares.

#### Task
Create collaboration teams where creators join forces to generate ultimate viral content. However, forming these teams follows specific rules:
1. Creators must be **adjacent** based on their posting order. For example, creators `1`, `2`, and `3` can form a team since they post consecutively. However, creators `1`, `3`, and `5` cannot form a team because they are not adjacent.
2. You need at least a **minimum number of creators** (given by `minCreatorsRequired`) to form a valid team.
3. The **total engagement power** (sum of their powers) of the team must meet or exceed a threshold (given by `minTotalEngagementPowerRequired`) to ensure the collaboration generates enough buzz to go viral.

---

#### Example

**Input:**
```
n = 6
creatorsEngagementPower = [4, 6, 8, 11, 9, 12]
minCreatorsRequired = 2
minTotalEngagementPowerRequired = 15
```

**Output:**
```
2
```

**Explanation:**
- The first collaboration includes creators \(1, 2, \text{ and } 3\), with a total engagement power of \(4 + 6 + 8 = 18\), which meets the minimum required threshold.
- The second collaboration includes creators \(4 \text{ and } 5\), with a total engagement power of \(11 + 9 = 20\).

Thus, the maximum number of collaboration groups that can be formed is **2**.

---

### Function Description

Complete the function `createMaximumCollaborations` in the editor.

#### **Function Signature**
```java
public static int createMaximumCollaborations(List<Integer> creatorsEngagementPower, int minCreatorsRequired, long minTotalEngagementPowerRequired);
```

#### **Parameters**
- **`List<Integer> creatorsEngagementPower`**: An array representing the engagement power of each creator.
- **`int minCreatorsRequired`**: The minimum number of creators required in a collaboration.
- **`long minTotalEngagementPowerRequired`**: The minimum total engagement power required for a collaboration.

#### **Returns**
- **`int`**: The maximum number of collaborations that can be formed from the creators.

---

### Constraints

- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq creatorsEngagementPower[i] \leq 10^9 \)
- \( 1 \leq minCreatorsRequired \leq n \)
- \( 1 \leq minTotalEngagementPowerRequired \leq 10^{14} \)

---

### Starter Code
```java
/*
 * Complete the 'createMaximumCollaborations' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts the following parameters:
 * 1. INTEGER_ARRAY creatorsEngagementPower
 * 2. INTEGER minCreatorsRequired
 * 3. LONG_INTEGER minTotalEngagementPowerRequired


        ---

        */
public class TikTokCollaborations {

    public static int createMaximumCollaborations(List<Integer> creatorsEngagementPower, int minCreatorsRequired, long minTotalEngagementPowerRequired) {
        int n = creatorsEngagementPower.size();
        if (n < minCreatorsRequired) {
            return 0;
        }

        // Compute prefix sums
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + creatorsEngagementPower.get(i);
        }

        int count = 0;
        int start = 0;

        while (start + minCreatorsRequired <= n) {
            // Minimum end index for current start
            int low = start + minCreatorsRequired;
            int high = n;
            int validEnd = -1;

            // Binary search to find the minimal end index where the sum >= threshold
            while (low <= high) {
                int mid = low + (high - low) / 2;
                long currentSum = prefixSum[mid] - prefixSum[start];
                if (currentSum >= minTotalEngagementPowerRequired) {
                    validEnd = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (validEnd != -1) {
                count++;
                start = validEnd; // Move past this team
            } else {
                break; // No further valid teams can be formed
            }
        }

        return count;
    }

    // Helper method to generate large test cases
    private static List<Integer> generateLargeTestCase(int n, int maxEngagementPower) {
        Random rand = new Random(42); // Fixed seed for reproducibility
        List<Integer> testCase = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            testCase.add(rand.nextInt(maxEngagementPower) + 1); // 1 to maxEngagementPower
        }
        return testCase;
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 1
        testCases.add(new TestCase(
                Arrays.asList(5, 4, 3, 2, 1),
                3,
                20L,
                0,
                "Sample Case 1"
        ));

        // Sample Case 0
        testCases.add(new TestCase(
                Arrays.asList(4, 4, 3, 6, 4, 3, 5),
                2,
                8L,
                3,
                "Sample Case 0"
        ));

        // Edge Case 1: All creators form one team
        testCases.add(new TestCase(
                Arrays.asList(10, 10, 10, 10),
                4,
                40L,
                1,
                "All Creators Form One Team"
        ));

        // Edge Case 2: No valid team possible
        testCases.add(new TestCase(
                Arrays.asList(1, 1, 1),
                2,
                5L,
                0,
                "No Valid Team Possible"
        ));

        // Edge Case 3: Single creator
        testCases.add(new TestCase(
                List.of(100),
                1,
                50L,
                1,
                "Single Creator"
        ));

        // Edge Case 4: Two conflicting teams
        testCases.add(new TestCase(
                Arrays.asList(5, 5, 5, 5),
                2,
                10L,
                2,
                "Two Conflicting Teams"
        ));

        // Edge Case 5: Minimum creators required equals n
        testCases.add(new TestCase(
                Arrays.asList(5, 10, 15),
                3,
                30L,
                1,
                "Minimum Creators Required Equals n"
        ));

        // Edge Case 6: Large Test Case
        testCases.add(new TestCase(
                generateLargeTestCase(100000, 1000),
                1000,
                500000L,
                -1, // We don't know the expected value, will compute and ensure it runs without errors
                "Large Test Case"
        ));

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = createMaximumCollaborations(tc.creatorsEngagementPower, tc.minCreatorsRequired, tc.minTotalEngagementPowerRequired);
            boolean isPass;
            if (tc.expected == -1L) { // For Large Test Case, just ensure no exception and a valid output
                isPass = result >= 0;
            } else {
                isPass = result == tc.expected;
            }
            if (isPass) {
                System.out.println("Test Case " + (i + 1) + " (" + tc.description + "): PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + " (" + tc.description + "): FAIL");
                System.out.println("   Expected: " + tc.expected + ", Got: " + result);
            }
        }

        System.out.println("\nTotal Passed: " + passed + " out of " + testCases.size());
    }

    // Inner class to represent a test case
    static class TestCase {
        List<Integer> creatorsEngagementPower;
        int minCreatorsRequired;
        long minTotalEngagementPowerRequired;
        long expected;
        String description;

        TestCase(List<Integer> creatorsEngagementPower, int minCreatorsRequired, long minTotalEngagementPowerRequired, long expected, String description) {
            this.creatorsEngagementPower = creatorsEngagementPower;
            this.minCreatorsRequired = minCreatorsRequired;
            this.minTotalEngagementPowerRequired = minTotalEngagementPowerRequired;
            this.expected = expected;
            this.description = description;
        }
    }
}
