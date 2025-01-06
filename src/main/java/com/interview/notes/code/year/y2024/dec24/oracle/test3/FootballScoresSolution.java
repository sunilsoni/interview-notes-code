package com.interview.notes.code.year.y2024.dec24.oracle.test3;

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
public class FootballScoresSolution {

    /**
     * Returns a list where each element is the count of elements in teamA
     * that are <= the corresponding element in teamB.
     */
    public static List<Integer> counts(List<Integer> teamA, List<Integer> teamB) {
        // Sort teamA for binary search
        Collections.sort(teamA);

        List<Integer> result = new ArrayList<>(teamB.size());

        for (int b : teamB) {
            // Find the number of elements in teamA <= b
            int count = countLessOrEqual(teamA, b);
            result.add(count);
        }

        return result;
    }

    /**
     * Uses binary search to find how many elements in sortedList are <= value.
     */
    private static int countLessOrEqual(List<Integer> sortedList, int value) {
        int low = 0;
        int high = sortedList.size() - 1;
        int position = -1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (sortedList.get(mid) <= value) {
                position = mid; // this element is <= value
                low = mid + 1;  // try to find a bigger index still <= value
            } else {
                high = mid - 1;
            }
        }

        // If position = -1, no elements are <= value
        // Otherwise, position gives the last index with element <= value
        // Count = position + 1 (since indices start at 0)
        return position == -1 ? 0 : (position + 1);
    }

    public static void main(String[] args) {
        // Testing approach:
        // 1. Run given sample test
        test(new int[]{1, 4, 2, 4}, new int[]{3, 5}, new int[]{2, 4}, "Sample Test");

        // 2. Edge case: all teamA less than teamB
        test(new int[]{1, 1, 1}, new int[]{5, 10}, new int[]{3, 3}, "All A <= B");

        // 3. Edge case: all teamA greater than teamB
        test(new int[]{10, 11}, new int[]{1, 2}, new int[]{0, 0}, "All A > B");

        // 4. Edge case: duplicates in A
        test(new int[]{2, 2, 2, 2}, new int[]{2, 3}, new int[]{4, 4}, "Duplicates in A");

        // 5. teamB smaller than smallest element in teamA
        test(new int[]{5, 6, 7}, new int[]{1, 2}, new int[]{0, 0}, "All B < A");

        // 6. Random test
        test(new int[]{1, 2, 3}, new int[]{2, 4}, new int[]{2, 3}, "Random Test");

        // Optionally test large data:
        // (This is a simple example; in practice, you could generate arrays of size 100000 and run.)
        // For demonstration, we won't generate huge arrays here, but this step can be performed offline.
    }

    /**
     * A simple test method (not using JUnit).
     * Compares the actual result from counts() with the expected result array.
     * Prints "PASS" if matches, otherwise "FAIL".
     */
    private static void test(int[] teamA, int[] teamB, int[] expected, String testName) {
        List<Integer> aList = new ArrayList<>(teamA.length);
        for (int x : teamA) aList.add(x);

        List<Integer> bList = new ArrayList<>(teamB.length);
        for (int x : teamB) bList.add(x);

        List<Integer> actual = counts(aList, bList);

        boolean pass = true;
        if (actual.size() != expected.length) {
            pass = false;
        } else {
            for (int i = 0; i < expected.length; i++) {
                if (!actual.get(i).equals(expected[i])) {
                    pass = false;
                    break;
                }
            }
        }

        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Actual:   " + actual.toString());
        }
    }
}
