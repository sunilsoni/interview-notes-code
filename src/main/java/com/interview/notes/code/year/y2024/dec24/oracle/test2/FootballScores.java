package com.interview.notes.code.year.y2024.dec24.oracle.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*


Implement a Map that remembers a time when you set a value.

Implement a Map that remembers a time when you set a value. put (String key, String value, Long timestamp)
get (String key, Long timestamp)
put("a", "apple", 2)
get("a", 0) →> null get ("a", 3) -> "apple"

if not avaiable then find nearest one object and return






Here is the combined extracted text from the uploaded images:

---

**Complete the 'counts' function below.**

- The function is expected to return an INTEGER_ARRAY.
- The function accepts the following parameters:
  1. INTEGER_ARRAY `teamA`
  2. INTEGER_ARRAY `teamB`

```java
public static List<Integer> counts(List<Integer> teamA, List<Integer> teamB) {
    // Write your code here
}
```

---

**Sample Output 0**

**Explanation 0:**

Given values are `n = 4`, `teamA = [1, 4, 2, 4]`, `m = 2`, and `teamB = [3, 5]`.

1. For `teamB[0] = 3`, we have 2 elements in `teamA` (`teamA[0] = 1` and `teamA[2] = 2`) that are ≤ `teamB[0]`.
2. For `teamB[1] = 5`, we have 4 elements in `teamA` (`teamA[0] = 1`, `teamA[1] = 4`, `teamA[2] = 2`, and `teamA[3] = 4`) that are ≤ `teamB[1]`.

Thus, the function returns the array `[2, 4]` as the answer.

---

**Constraints:**

- \( 2 \leq n, m \leq 10^5 \)
- \( 1 \leq \text{teamA}[j] \leq 10^9 \), where \( 0 \leq j < n \)
- \( 1 \leq \text{teamB}[i] \leq 10^9 \), where \( 0 \leq i < m \)

---

**Input Format For Custom Testing:**

Input from stdin will be processed as follows and passed to the function.

1. The first line contains an integer `n`, the number of elements in `teamA`.
2. The next `n` lines each contain an integer describing `teamA[j]` where \( 0 \leq j < n \).
3. The next line contains an integer `m`, the number of elements in `teamB`.
4. The next `m` lines each contain an integer describing `teamB[i]` where \( 0 \leq i < m \).

---

**Sample Case 0:**

**Sample Input 0:**
```plaintext
4
1
4
2
4
2
3
5
```

**Function Arguments:**
- teamA = [1, 4, 2, 4]
- teamB = [3, 5]

**Output:**
```plaintext
2
4
```

---

**Football Scores - Description**

The number of goals achieved by two football teams in matches in a league is given in the form of two lists. For each match of `teamB`, compute the total number of matches of `teamA` where `teamA` has scored less than or equal to the number of goals scored by `teamB` in that match.

---

**Example:**

Input:
- teamA = [1, 2, 3]
- teamB = [2, 4]

Explanation:
- Team A has played three matches and scored `teamA = [1, 2, 3]` goals in each match respectively.
- Team B has played two matches and scored `teamB = [2, 4]` goals in each match respectively.

For `teamB[0] = 2`, `teamA` has 2 matches with scores `1` and `2` that satisfy the condition.
For `teamB[1] = 4`, `teamA` has 3 matches with scores `1`, `2`, and `3` that satisfy the condition.

**Output:**
```plaintext
2
3
```
 */
public class FootballScores {

    // Main solution method
    public static List<Integer> counts(List<Integer> teamA, List<Integer> teamB) {
        // Sort teamA to optimize counting process
        Collections.sort(teamA);
        List<Integer> result = new ArrayList<>();

        // For each score in teamB, count valid scores in teamA
        for (int score : teamB) {
            result.add(countLessOrEqual(teamA, score));
        }

        return result;
    }

    // Helper method to count numbers less than or equal to target
    private static int countLessOrEqual(List<Integer> sortedList, int target) {
        int left = 0;
        int right = sortedList.size() - 1;

        // Binary search to find rightmost position
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedList.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // Test method
    public static void main(String[] args) {
        // Test case 1: Sample case
        runTest(
                Arrays.asList(1, 4, 2, 4),
                Arrays.asList(3, 5),
                Arrays.asList(2, 4),
                "Sample Test Case"
        );

        // Test case 2: Edge case - Empty teams
        runTest(
                new ArrayList<>(),
                Arrays.asList(1),
                Arrays.asList(0),
                "Empty Team A Test"
        );

        // Test case 3: Large numbers
        runTest(
                Arrays.asList(1000000000, 999999999),
                Arrays.asList(1000000000),
                Arrays.asList(2),
                "Large Numbers Test"
        );

        // Test case 4: Equal numbers
        runTest(
                Arrays.asList(2, 2, 2, 2),
                Arrays.asList(2),
                Arrays.asList(4),
                "Equal Numbers Test"
        );

        // Test case 5: Large dataset
        List<Integer> largeTeamA = new ArrayList<>();
        List<Integer> largeTeamB = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeTeamA.add(i);
            if (i < 1000) largeTeamB.add(i * 100);
        }
        runTest(
                largeTeamA,
                largeTeamB,
                null, // Expected result calculated during runtime
                "Large Dataset Test"
        );
    }

    // Helper method to run tests
    private static void runTest(List<Integer> teamA, List<Integer> teamB,
                                List<Integer> expected, String testName) {
        System.out.println("\nRunning " + testName + "...");

        long startTime = System.currentTimeMillis();
        List<Integer> result = counts(teamA, teamB);
        long endTime = System.currentTimeMillis();

        if (expected != null) {
            boolean passed = result.equals(expected);
            System.out.println("Status: " + (passed ? "PASSED" : "FAILED"));
            if (!passed) {
                System.out.println("Expected: " + expected);
                System.out.println("Got: " + result);
            }
        } else {
            System.out.println("Result size: " + result.size());
        }

        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
