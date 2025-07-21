package com.interview.notes.code.year.y2025.july.common.test5;

import java.util.Arrays;
import java.util.List;

/*
Great! Based on all your screenshots and the partial function stub provided, here's the **complete problem description with Java function signature** and example inputs/outputs bundled together properly:

---

## 🌀 Monsoon Umbrellas

### 📝 Problem Description

Umbrellas are available in different sizes that can each shelter a certain number of people. Given:

* an integer `requirement` representing the exact number of people needing shelter, and
* a list of umbrella sizes (`sizes[]`) representing how many people each umbrella can shelter,

you need to determine the **minimum number of umbrellas** required to shelter **exactly** that many people.
If it's **impossible** to achieve the exact requirement, return `-1`.

        ### 📤 Return

* An `int`: the **minimum number of umbrellas** needed to cover **exactly** `requirement` people, or
* `-1` if it's **not possible**.

        ---

        ### ✅ Constraints

* `1 ≤ requirement ≤ 1000`
        * `1 ≤ sizes.length ≤ 1000`
        * `1 ≤ sizes[i] ≤ 1000`

        ---

        ### 📘 Examples

#### Example 1:

        **Input:**

        ```
        requirement = 5
        sizes = [3, 5]
        ```

        **Output:** `1`
        **Explanation:** One umbrella of size 5 covers exactly 5 people.

---

        #### Example 2:

        **Input:**

        ```
        requirement = 8
        sizes = [3, 5]
        ```

        **Output:** `2`
        **Explanation:** 3 + 5 = 8 → Use 1 umbrella of each size.

        ---

        #### Example 3:

        **Input:**

        ```
        requirement = 7
        sizes = [3, 5]
        ```

        **Output:** `-1`
        **Explanation:** No combination of sizes adds up to exactly 7.

        ---

        #### Sample Case:

        **Input:**

        ```
        requirement = 4
        sizes = [2, 4]
        ```

        **Output:** `1`
        **Explanation:** Either use 1 umbrella of size 4, or 2 of size 2.


 */
public class MonsoonUmbrellas {

    public static int getUmbrellas(int requirement, List<Integer> sizes) {
        // Create array to store minimum umbrellas needed for each requirement value
        int[] dp = new int[requirement + 1];

        // Initialize with a value larger than maximum possible umbrellas needed
        Arrays.fill(dp, requirement + 1);

        // Base case: 0 people need 0 umbrellas
        dp[0] = 0;

        // For each possible requirement from 1 to target
        for (int i = 1; i <= requirement; i++) {
            // Try each umbrella size
            for (int size : sizes) {
                // If current umbrella size can be used (doesn't exceed current requirement)
                if (size <= i) {
                    // Update minimum umbrellas needed if using current size gives better result
                    dp[i] = Math.min(dp[i], dp[i - size] + 1);
                }
            }
        }

        // Return -1 if no solution found, otherwise return minimum umbrellas needed
        return dp[requirement] > requirement ? -1 : dp[requirement];
    }

    public static void main(String[] args) {
        // Test cases
        runTest(5, Arrays.asList(3, 5), 1, "Test Case 1");
        runTest(8, Arrays.asList(3, 5), 2, "Test Case 2");
        runTest(7, Arrays.asList(3, 5), -1, "Test Case 3");
        runTest(4, Arrays.asList(2, 4), 1, "Test Case 4");

        // Edge cases
        runTest(1000, Arrays.asList(2, 5, 10), 100, "Large Input Test");
        runTest(1, Arrays.asList(2), -1, "Small Impossible Test");
        runTest(10, Arrays.asList(1), 10, "Single Size Test");
    }

    private static void runTest(int requirement, List<Integer> sizes,
                                int expectedOutput, String testName) {
        int result = getUmbrellas(requirement, sizes);
        System.out.println(testName + ": " +
                (result == expectedOutput ? "PASS" : "FAIL") +
                " (Expected: " + expectedOutput + ", Got: " + result + ")");
    }
}
