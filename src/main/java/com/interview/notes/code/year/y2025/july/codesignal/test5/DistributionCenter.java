package com.interview.notes.code.year.y2025.july.codesignal.test5;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
WORKING SOLUTION

Here is the complete and combined version of the **second coding question**:

---

### 📦 Problem Statement

You're managing a network of distribution centers that handle package deliveries. Each center has a different capacity for processing packages before needing a reset.

You are given:

* An array of integers `centerCapacities`, where `centerCapacities[i]` (between 1 and 5) represents the **maximum number of packages** that the i-th distribution center can process before requiring maintenance.

* An array of strings `dailyLog`, where each entry is either:

  * `"PACKAGE"` – a new package arrives
  * `"CLOSURE x"` – the x-th distribution center temporarily **closes for renovations**

📦 Packages are sent to centers in **sequential order** (from 0 to end, then wrap back to 0), skipping centers that are **closed** or have reached their capacity.

🔄 After each full rotation through all centers (back to 0), all non-closed centers are **restored to full capacity**.

If **multiple centers** processed the same maximum number of packages, return the **highest index** among them.

---

### 🧪 Input/Output

* ⏱ **Time Limit**: 3 seconds (Java)
* 🧠 **Memory Limit**: 1 GB

#### 🔡 Input

```java
int[] centerCapacities
```

* Array of integers (1 ≤ length ≤ 100, 1 ≤ value ≤ 5) — capacities of centers.

```java
String[] dailyLog
```

* Array of operations to process (1 ≤ length ≤ 1000) — either "PACKAGE" or "CLOSURE x"

#### 🔢 Output

```java
int
```

* Return the index of the center that processed the **most packages**. If tied, return the **highest index**.

---

### 📌 Example

**Input:**

```java
centerCapacities = [1, 2, 1, 2, 1];
dailyLog = [
  "PACKAGE",
  "PACKAGE",
  "CLOSURE 2",
  "PACKAGE",
  "CLOSURE 3",
  "PACKAGE",
  "PACKAGE"
];
```

**Output:**

```
1
```

---

### 📝 Explanation:

Here's how packages are assigned:

1. `"PACKAGE"` → center 0 → count: `[1, 0, 0, 0, 0]`
2. `"PACKAGE"` → center 1 → count: `[1, 1, 0, 0, 0]`
3. `"CLOSURE 2"` → close center 2
4. `"PACKAGE"` → center 1 again (still has capacity) → `[1, 2, 0, 0, 0]`
5. `"CLOSURE 3"` → close center 3
6. `"PACKAGE"` → center 4 (centers 2 and 3 closed) → `[1, 2, 0, 0, 1]`
7. Rotation → restore all non-closed centers
8. `"PACKAGE"` → center 0 → `[2, 2, 0, 0, 1]`

So final counts = `[2, 2, 0, 0, 1]`
**Max = 2**, indexes = 0 and 1 → **Return 1** (higher index)

---

### 🧠 Notes:

* Closed centers are skipped completely
* Centers reset only after one complete cycle
* Always return the **highest index** in case of tie

---

Would you like the Java implementation next?


 */
public class DistributionCenter {

    public static int solution(int[] centerCapacities, String[] dailyLog) {
        int n = centerCapacities.length;
        int[] remainingCap = Arrays.copyOf(centerCapacities, n);
        int[] processed = new int[n];
        boolean[] closed = new boolean[n];

        int cur = 0;
        for (String log : dailyLog) {
            if (log.startsWith("CLOSURE")) {
                int closeIdx = Integer.parseInt(log.split(" ")[1]);
                closed[closeIdx] = true;
                remainingCap[closeIdx] = 0;
                // If current center got closed, move cur to next open+not full center
                if (cur == closeIdx) {
                    cur = (cur + 1) % n;
                    int looped = 0;
                    while ((closed[cur] || remainingCap[cur] == 0) && looped < n) {
                        cur = (cur + 1) % n;
                        looped++;
                    }
                }
            } else if ("PACKAGE".equals(log)) {
                // Find next available center with capacity and not closed
                int looped = 0;
                while ((closed[cur] || remainingCap[cur] == 0) && looped < n) {
                    cur = (cur + 1) % n;
                    looped++;
                }
                // Assign package to current center
                processed[cur]++;
                remainingCap[cur]--;
                // If current center is now empty, move cur to next available for next round
                if (remainingCap[cur] == 0) {
                    int next = (cur + 1) % n;
                    int nextLoop = 0;
                    while ((closed[next] || remainingCap[next] == 0) && nextLoop < n) {
                        next = (next + 1) % n;
                        nextLoop++;
                    }
                    cur = next;
                }
                // Check if need reset (all open centers at 0)
                boolean needReset = true;
                for (int i = 0; i < n; i++) {
                    if (!closed[i] && remainingCap[i] > 0) {
                        needReset = false;
                        break;
                    }
                }
                if (needReset) {
                    for (int i = 0; i < n; i++) {
                        if (!closed[i]) remainingCap[i] = centerCapacities[i];
                    }
                    // After reset, move cur to next available open center
                    int resetLoop = 0;
                    while ((closed[cur] || remainingCap[cur] == 0) && resetLoop < n) {
                        cur = (cur + 1) % n;
                        resetLoop++;
                    }
                }
            }
        }
        int maxP = IntStream.of(processed).max().getAsInt();
        int ans = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (processed[i] == maxP) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    // Main method for test cases (prints PASS/FAIL)
    public static void main(String[] args) {
        // Example test case (from problem)
        int[] c1 = {1, 2, 1, 2, 1};
        String[] log1 = {"PACKAGE", "PACKAGE", "CLOSURE 2", "PACKAGE", "CLOSURE 3", "PACKAGE", "PACKAGE"};
        int expected1 = 1;
        runTest(1, c1, log1, expected1);

        // Test 2: Only 1 center
        int[] c2 = {3};
        String[] log2 = {"PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"};
        int expected2 = 0;
        runTest(2, c2, log2, expected2);

        // Test 3: All centers closed except one
        int[] c3 = {2, 3, 2};
        String[] log3 = {"CLOSURE 0", "CLOSURE 2", "PACKAGE", "PACKAGE", "PACKAGE"};
        int expected3 = 1;
        runTest(3, c3, log3, expected3);

        // Test 4: Large data input (all equal)
        int n = 100;
        int[] c4 = new int[n];
        Arrays.fill(c4, 5);
        String[] log4 = new String[1000];
        Arrays.fill(log4, "PACKAGE");
        int expected4 = n - 1;
        runTest(4, c4, log4, expected4);

        // Test 5: Edge reset
        int[] c5 = {2, 1};
        String[] log5 = {"PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"};
        int expected5 = 0; // [2,2]
        runTest(5, c5, log5, expected5);

        // Test 6: Closure before package
        int[] c6 = {1, 1, 1};
        String[] log6 = {"CLOSURE 2", "PACKAGE", "PACKAGE"};
        int expected6 = 1;
        runTest(6, c6, log6, expected6);

        // *** Test 7: Your failing test case ***
        int[] c7 = {2, 3};
        String[] log7 = {"PACKAGE", "PACKAGE"};
        int expected7 = 0; // both to center 0
        runTest(7, c7, log7, expected7);

        // *** Test 8: Your other failing test case (was test 4 in screenshots) ***
        int[] c8 = {1, 1, 2};
        String[] log8 = {"PACKAGE", "PACKAGE", "PACKAGE", "CLOSURE 2", "PACKAGE"};
        int expected8 = 0;
        runTest(8, c8, log8, expected8);

        // Add any new/edge cases below as needed!
    }

    private static void runTest(int testNo, int[] centerCap, String[] log, int expected) {
        int actual = solution(centerCap, log);
        if (actual == expected)
            System.out.println("Test " + testNo + ": PASS");
        else
            System.out.println("Test " + testNo + ": FAIL (Expected: " + expected + ", Got: " + actual + ")");
    }
}
