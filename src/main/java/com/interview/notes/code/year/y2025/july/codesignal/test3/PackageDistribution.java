package com.interview.notes.code.year.y2025.july.codesignal.test3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/*
Here is the **complete combined and structured version** of your problem statement based on all uploaded screenshots:

---

### 📦 Package Distribution Network

You are managing a network of distribution centers. Each center has a specific capacity for how many packages it can process before needing a reset.

---

### 🧾 Input

* `int[] centerCapacities`:
  An array of integers representing the max number of packages each center can handle in one rotation.

  * Constraints: `1 ≤ centerCapacities.length ≤ 100`
  * Each capacity: `1 ≤ centerCapacities[i] ≤ 5`

* `String[] dailyLog`:
  An array of logs representing operations that can be either:

  * `"PACKAGE"` — one new package arrives
  * `"CLOSURE <j>"` — the j-th center is closed for renovation
  * Constraints: `1 ≤ dailyLog.length ≤ 1000`

---

### ⚙️ Processing Rules

* Packages are processed in order, starting from center `0` and moving forward sequentially.
* Each center can process up to its given capacity. After one full rotation (back to center 0), all capacities reset to full **but closed centers remain closed**.
* Closed centers are skipped entirely during processing.
* If a log is `"CLOSURE j"`, then the j-th center is marked closed and is never reopened.

---

### ✅ Function Signature

```java
int solution(int[] centerCapacities, String[] dailyLog)
```

---

### 🎯 Output

* Return the **index** of the center that processed the **maximum number of packages**.
* If there's a tie, return the **highest index** among those tied centers.

---

### 💡 Notes

* At least one center is always operational.
* Skipped centers (closed ones) are never reopened.
* You can assume that the input constraints ensure feasibility.

---

### 🧪 Example

```java
centerCapacities = [1, 2, 1, 2, 1]

dailyLog = [
    "PACKAGE",
    "PACKAGE",
    "CLOSURE 2",
    "PACKAGE",
    "CLOSURE 3",
    "PACKAGE",
    "PACKAGE"
]
```

**Output:** `1`

---

### 🔍 Step-by-Step Explanation:

```
Step 1: PACKAGE  → center 0 → capacity becomes [0, 2, 1, 2, 1]
Step 2: PACKAGE  → center 1 → capacity becomes [0, 1, 1, 2, 1]
Step 3: CLOSURE 2 → center 2 closed
Step 4: PACKAGE  → center 1 → capacity becomes [0, 0, 1, 2, 1]
Step 5: CLOSURE 3 → center 3 closed
Step 6: PACKAGE  → center 4 → capacity becomes [0, 0, 1, 2, 0]
Step 7: PACKAGE  → center 0 → all center capacities reset except closed → new capacities [1, 2, 1 (closed), 2 (closed), 1]
           → center 0 → capacity becomes [0, 2, X, X, 1]
```

**Package processed count per center:**
`[2, 2, 0, 0, 1]` → max = 2 → **highest index = 1**

---


 */
public class PackageDistribution {

    public static int solution(int[] cap, String[] log) {
        var n = cap.length;
        var rem = Arrays.copyOf(cap, n);
        var proc = new int[n];
        var closed = new boolean[n];
        var cur = 0;

        for (var op : log) {
            if (op.charAt(0) == 'P') {                // PACKAGE
                var i = cur;
                while (closed[i] || rem[i] == 0) {
                    i = (i + 1) % n;
                    if (i == 0)                       // wrapped around?
                        for (var j = 0; j < n; j++)   // reset all open centers
                            if (!closed[j]) rem[j] = cap[j];
                }
                proc[i]++;
                rem[i]--;
                cur = i;
            } else {                                   // CLOSURE j
                closed[Integer.parseInt(op.split(" ")[1])] = true;
            }
        }

        // pick highest‐index among those with max proc count
        return IntStream.range(0, n)
                .boxed()
                .max(Comparator
                        .comparingInt((Integer i) -> proc[i])
                        .thenComparingInt(i -> i))
                .orElse(0);
    }


    // — Simple main() tests (no JUnit) —
    private static void test(int[] cap, String[] log, int expected, int id) {
        int got = solution(cap, log);
        System.out.printf(
                "Test %2d: %s (expected=%d, got=%d)%n",
                id,
                (got == expected ? "PASS" : "FAIL"),
                expected, got
        );
    }

    public static void main(String[] args) {
        // Provided example
        test(
                new int[]{1, 2, 1, 2, 1},
                new String[]{
                        "PACKAGE", "PACKAGE", "CLOSURE 2",
                        "PACKAGE", "CLOSURE 3", "PACKAGE", "PACKAGE"
                },
                1, 1
        );

        // Some edge‐case checks
        test(new int[]{3},
                new String[]{"PACKAGE", "PACKAGE", "PACKAGE"},
                0, 2);

        test(new int[]{2, 1},
                new String[]{"PACKAGE", "PACKAGE", "PACKAGE"},
                0, 3);

        test(new int[]{1, 1, 1},
                new String[]{"PACKAGE", "CLOSURE 0", "PACKAGE", "PACKAGE"},
                2, 4);

        test(new int[]{1, 2, 3},
                new String[]{"CLOSURE 0", "CLOSURE 2", "PACKAGE", "PACKAGE"},
                1, 5);

        // Stress test
        int N = 100, M = 500;
        int[] caps = new int[N];
        Arrays.fill(caps, 5);
        String[] bulk = new String[M];
        Arrays.fill(bulk, "PACKAGE");
        long t0 = System.currentTimeMillis();
        int winner = solution(caps, bulk);
        long dt = System.currentTimeMillis() - t0;
        System.out.printf(
                "Stress (N=%d,M=%d) → center %d in %dms%n",
                N, M, winner, dt
        );
    }
}