package com.interview.notes.code.year.y2025.july.codesignal.test4;

import java.util.*;

/*
Here is the complete and properly formatted version of the **third coding question**:

---

### 🧭 Problem Statement

A traveler visited a series of **unique landmarks** on a journey. Unfortunately, their travel journal was damaged, and they can no longer remember the exact order of their visits. However, they have a collection of **photos**, each showing **exactly two landmarks** that were visited **consecutively** (either one could have been visited first).

You're given a list of such photos represented as pairs of landmark IDs in `travelPhotos`.

👉 Your task is to reconstruct the **complete journey** (in either forward or reverse order).

* Each landmark was visited **exactly once**
* For every consecutive pair of landmarks in the journey, there **exists a photo** showing that connection

---

### 📘 Example

```java
travelPhotos = [
  [3, 5],
  [1, 4],
  [2, 4],
  [1, 5]
]
```

**Output:**

```java
[3, 5, 1, 4, 2]
```

**Explanation:**

* Pairs show the following direct visits:
  3—5, 1—4, 2—4, 1—5
* By chaining: 3 → 5 → 1 → 4 → 2
* The reverse `[2, 4, 1, 5, 3]` is also valid.

---

### 🧪 Input/Output

* ⏱ **Execution time limit**: 3 seconds (Java)
* 💾 **Memory limit**: 1 GB

#### 🔡 Input

```java
int[][] travelPhotos
```

* Each sub-array of size 2 represents a photo of two **consecutive landmarks**
* All IDs are positive and **unique overall**

**Constraints:**

* `1 ≤ travelPhotos.length ≤ 5 * 10^4`
* `travelPhotos[i].length == 2`
* `1 ≤ travelPhotos[i][j] ≤ 10^6`

#### 🔢 Output

```java
int[]
```

* Return the reconstructed journey as an array of landmark IDs

---

### ✅ Notes

* Both `[3, 5, 1, 4, 2]` and `[2, 4, 1, 5, 3]` are correct
* You may start from either endpoint
* Think of it like building a **path in a graph** where:

  * Each edge is bidirectional
  * Each node has exactly 1 or 2 neighbors
  * Exactly two nodes have only one neighbor (start/end)

---

Would you like the Java solution with test cases in `main()` next?

 */
public class TravelJourney {

    public static int[] solution(int[][] travelPhotos) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // Build graph
        Arrays.stream(travelPhotos).forEach(pair -> {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        });

        // Find start (landmark with only 1 neighbor)
        int start = graph.entrySet().stream()
                .filter(e -> e.getValue().size() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);

        int n = graph.size();
        int[] journey = new int[n];
        journey[0] = start;

        int prev = -1;
        for (int i = 1; i < n; i++) {
            List<Integer> neighbors = graph.get(journey[i - 1]);
            journey[i] = neighbors.get(0) == prev ? neighbors.get(1) : neighbors.get(0);
            prev = journey[i - 1];
        }

        return journey;
    }

    public static void main(String[] args) {
        // Tests
        int[][][] tests = {
                {{3, 5}, {1, 4}, {2, 4}, {1, 5}},
                {{1, 2}, {2, 3}, {3, 4}},
                {{10, 20}},
                {{100, 200}, {200, 300}, {300, 400}, {400, 500}, {500, 600}},
        };

        int[][] expected = {
                {3, 5, 1, 4, 2},
                {1, 2, 3, 4},
                {10, 20},
                {100, 200, 300, 400, 500, 600}
        };

        for (int i = 0; i < tests.length; i++) {
            int[] res = solution(tests[i]);
            boolean pass = Arrays.equals(res, expected[i]) || isReverse(res, expected[i]);
            System.out.println("Test " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println(" Expected: " + Arrays.toString(expected[i]));
                System.out.println(" Got     : " + Arrays.toString(res));
            }
        }

        // Large test
        int size = 100_000;
        int[][] large = new int[size - 1][2];
        for (int i = 1; i < size; i++) {
            large[i - 1] = new int[]{i, i + 1};
        }

        long startTime = System.currentTimeMillis();
        int[] largeRes = solution(large);
        long endTime = System.currentTimeMillis();

        boolean largePass = largeRes.length == size && largeRes[0] == 1 && largeRes[size - 1] == size;
        System.out.println("Large test: " + (largePass ? "PASS" : "FAIL") + " (Time: " + (endTime - startTime) + " ms)");
    }

    private static boolean isReverse(int[] a, int[] b) {
        if (a.length != b.length) return false;
        int n = a.length;
        for (int i = 0; i < n; i++) if (a[i] != b[n - 1 - i]) return false;
        return true;
    }
}
