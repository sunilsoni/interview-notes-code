package com.interview.notes.code.year.y2025.july.codility.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*

### 🧩 Problem Statement: Maximum Sum of Two Non-Attacking Rooks

You are given a matrix `A` representing a chessboard with **N rows** and **M columns**.
Each square of the chessboard contains an **integer** representing a **points-based score**.

You have to place **two rooks** on the chessboard in such a way that:

* They **do not attack each other** (i.e., they must be placed in **different rows** and **different columns**).
* The **sum of points** on the two chosen squares is **maximized**.

> 🛡️ Rooks in chess can attack each other if they are in the **same row** or the **same column**.

---

### ✅ Examples

#### Example 1:

**Matrix A with 2 rows and 2 columns**

```
1 4
2 3
```

* Place rooks at `A[0][1] = 4` and `A[1][0] = 2`
* Max sum = 4 + 2 = **6** ✅

---

#### Example 2:

**Matrix A with 3 rows and 3 columns**

```
15 1 5
16 3 8
2 6 4
```

* Choose `A[0][0] = 15` and `A[1][2] = 8`
* Max sum = 15 + 8 = **23**

---

#### Example 3:

**Matrix A with 3 rows and 2 columns**

```
12 12
12 0
0 7
```

* Best options: `A[0][0] + A[1][1] = 12 + 12 = 24` ✅

---

#### Example 4:

**Matrix A with 2 rows and 3 columns**

```
1 2 14
8 3 15
```

* Choose `A[0][2] = 14` and `A[1][0] = 8`
* Max sum = 14 + 8 = **22**

---

### 📌 Constraints:

* `2 <= N, M <= 600`
* `0 <= A[i][j] <= 1,000,000,000`

---

### 🧠 Goal:

Write an **efficient** function to compute the **maximum sum** of any two **non-attacking rook placements**.

```java
class Solution {
    public int solution(int[][] A) {
        // Implement your solution here
    }
}
```

 */
public class Solution {
    // Simple main() to run provided tests and a large random test
    public static void main(String[] args) {
        Solution sol = new Solution();

        // --- Provided examples ---
        List<int[][]> tests = Arrays.asList(
                new int[][]{{1, 4}, {2, 3}},                 // 2×2 → 6
                new int[][]{{15, 1, 5}, {16, 3, 8}, {2, 6, 4}},    // 3×3 → 23
                new int[][]{{12, 12}, {12, 12}, {0, 7}},     // 3×2 → 24
                new int[][]{{1, 2, 14}, {8, 3, 15}}              // 2×3 → 22
        );
        List<Integer> expected = Arrays.asList(6, 23, 24, 22);

        IntStream.range(0, tests.size()).forEach(i -> {
            int res = sol.solution(tests.get(i));
            System.out.printf("Test #%d: %s (expected=%d, got=%d)%n",
                    i + 1,
                    res == expected.get(i) ? "PASS" : "FAIL",
                    expected.get(i), res);
        });

        // --- Large test: 600×600, only two high cells at (0,0)=100 and (1,1)=200 ---
        int N = 600, M = 600;
        int[][] large = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(large[i], 0);
        large[0][0] = 100;
        large[1][1] = 200;
        int want = 300;
        int got = sol.solution(large);
        System.out.printf("Large test: %s (expected=%d, got=%d)%n",
                got == want ? "PASS" : "FAIL", want, got);
    }

    // Returns the max sum of two non-attacking rooks
    public int solution(int[][] A) {
        int N = A.length, M = A[0].length;
        // For each row i, find top two values (v1,v2) and their columns (c1,c2)
        long[] v1 = new long[N], v2 = new long[N];
        int[] c1 = new int[N], c2 = new int[N];

        for (int i = 0; i < N; i++) {
            long max1 = -1, max2 = -1;
            int col1 = -1, col2 = -1;
            for (int j = 0; j < M; j++) {
                long val = A[i][j];
                if (val > max1) {
                    // bump down previous max1→max2
                    max2 = max1;
                    col2 = col1;
                    max1 = val;
                    col1 = j;
                } else if (val > max2) {
                    max2 = val;
                    col2 = j;
                }
            }
            v1[i] = max1;
            v2[i] = max2;
            c1[i] = col1;
            c2[i] = col2;
        }

        long best = 0;
        // Try every pair of rows (i,k)
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                long sum;
                if (c1[i] != c1[k]) {
                    // their best columns differ → take both best
                    sum = v1[i] + v1[k];
                } else {
                    // conflict in column → use second-best in one row
                    sum = Math.max(v1[i] + v2[k], v2[i] + v1[k]);
                }
                if (sum > best) best = sum;
            }
        }
        return (int) best;  // fits in 32-bit since max A[i][j]≤10^9
    }
}