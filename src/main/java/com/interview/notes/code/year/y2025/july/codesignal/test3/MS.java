package com.interview.notes.code.year.y2025.july.codesignal.test3;

/*
Here’s the **full combined version** of your problem statement, as seen in your screenshots:

---

### 🏔️ Most Similar Mountain Peaks

A mountaineer is studying a mountain range consisting of peaks of varying heights arranged in a line from index `0` to `n-1`.

Due to an optical illusion, the mountaineer can **only compare peaks that are at least a certain number of positions apart**, called the `viewingGap`.

Your task is to determine the **minimum height difference** between any two peaks that are at least `viewingGap` positions apart.

---

### 🧾 Input

* `int[] heights`
  An array of positive integers, representing the height (in meters) of each mountain peak.

  * `2 ≤ heights.length ≤ 10⁵`
  * `1 ≤ heights[i] ≤ 10⁹`

* `int viewingGap`
  The minimum number of positions apart two peaks must be to compare.

  * `1 ≤ viewingGap ≤ heights.length`

---

### ✅ Function Signature

```java
int solution(int[] heights, int viewingGap)
```

---

### 🎯 Output

* Return an integer: the **smallest height difference** between any two peaks that are at least `viewingGap` indices apart.

---

### 🧪 Examples

#### Example 1:

```java
heights = [1, 5, 4, 10, 9]
viewingGap = 3
```

Only the following pairs are valid:

* (0, 3): |1 - 10| = 9
* (0, 4): |1 - 9| = 8
* (1, 4): |5 - 9| = 4 ✅ → minimum

**Output:** `4`

---

#### Example 2:

```java
heights = [3, 10, 5, 8]
viewingGap = 1
```

Valid pairs include:

* (0, 2): |3 - 5| = 2
* (1, 3): |10 - 8| = 2 ✅ → both same, so min = 2

**Output:** `2`

---

### 💡 Constraints Summary

* Only pairs `(i, j)` where `|i - j| ≥ viewingGap` are allowed.
* The answer is `min(abs(heights[i] - heights[j]))` for all such valid pairs.

---


 */
class MS {
    public static void main(String[] a) {
        int[][] H = {{1, 5, 4, 10, 9}, {3, 10, 5, 8}, {7, 7}, {2, 100, 3, 101}};
        int[] G = {3, 1, 1, 2}, E = {4, 2, 0, 1};
        for (int i = 0; i < H.length; i++) {
            int r = solution(H[i], G[i]);
            System.out.printf("T%d:%s%n", i + 1, r == E[i] ? "PASS" : "FAIL");
        }
        int N = 200_000, g = 5_000;
        int[] L = new int[N];
        java.util.Random R = new java.util.Random(123);
        for (int i = 0; i < N; i++) L[i] = R.nextInt(1_000_000_000);
        long t = System.nanoTime();
        solution(L, g);
        System.out.printf("Large:%.3fms%n", (System.nanoTime() - t) / 1e6);
    }

    public static int solution(int[] heights, int viewingGap) {
        java.util.TreeSet<Integer> s = new java.util.TreeSet<>();
        int m = Integer.MAX_VALUE;
        for (int i = viewingGap; i < heights.length; i++) {
            s.add(heights[i - viewingGap]);
            Integer lo = s.floor(heights[i]), hi = s.ceiling(heights[i]);
            if (lo != null) m = Math.min(m, heights[i] - lo);
            if (hi != null) m = Math.min(m, hi - heights[i]);
        }
        return m;
    }
}