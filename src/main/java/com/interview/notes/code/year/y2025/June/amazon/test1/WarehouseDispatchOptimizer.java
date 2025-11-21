package com.interview.notes.code.year.y2025.June.amazon.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
## **Code Question: Warehouse Dispatch Optimization**

Amazon operates numerous warehouses, each holding a certain amount of inventory (`inventory[i]` units of a product). You and your co-worker are tasked with dispatching items from these warehouses to fulfill customer orders, using the following rules:

### **Dispatch Process:**

1. When dispatching from warehouse `i`, **you** reduce the inventory by `dispatch1` units.
2. After your dispatch, **your co-worker** reduces the inventory by `dispatch2` units.
3. This process repeats for the warehouse until the inventory reaches **zero or becomes negative**.
4. You **earn 1 credit** if the warehouse is emptied **on your turn**.

Your **co-worker** can **skip their turn** up to `skips` times during the process. The challenge is to decide the optimal turns to skip such that **you** maximize the number of **credits** (i.e., the number of warehouses emptied on your turn).

---

## **Function Signature**

```java
public static int getMaximumCredits(List<Integer> inventory, int dispatch1, int dispatch2, int skips)
```

### **Parameters**

* `inventory`: List of integers, inventory at each warehouse
* `dispatch1`: Integer, your dispatch units per turn
* `dispatch2`: Integer, your co-worker's dispatch units per turn
* `skips`: Integer, maximum number of turns your co-worker can skip

### **Return**

* `int`: The **maximum number of credits** both you and your co-worker can collectively earn (i.e., number of warehouses emptied **on your turn**)

---

## **Constraints**

* $1 \leq n \leq 10^5$
* $1 \leq \text{inventory}[i], \text{dispatch1}, \text{dispatch2}, \text{skips} \leq 10^9$

---

## **Example 1**

### **Input**

```plaintext
inventory = [10, 6, 12, 8, 15, 1]
dispatch1 = 2
dispatch2 = 3
skips = 3
```

### **Output**

```plaintext
5
```

### **Explanation**

Optimally skip turns to maximize the warehouses you can empty:

* Skip at warehouse 1 and 4
* Resulting in 5 warehouses emptied on your turn

---

## **Example 2**

### **Input**

```plaintext
inventory = [3, 7, 17, 21, 12, 19]
dispatch1 = 4
dispatch2 = 3
skips = 2
```

### **Output**

```plaintext
4
```

---

## **Example 3**

### **Input**

```plaintext
inventory = [1, 101]
dispatch1 = 1
dispatch2 = 100
skips = 2
```

### **Output**

```plaintext
1
```

---

 ====



Here is the **combined and structured version** of the second coding question based on the screenshots:

---


 */

/**
 * Warehouse Dispatch Optimization
 * <p>
 * You and a co-worker take turns dispatching items from warehouses.
 * You remove 'dispatch1' units, then co-worker removes 'dispatch2'.
 * Co-worker can skip up to 'skips' turns (shared budget across all warehouses).
 * You earn a credit when you empty a warehouse on your turn.
 * <p>
 * This implementation computes the minimum skips needed per warehouse
 * to ensure you end on your turn, then uses a greedy strategy to
 * maximize credits within the skip budget.
 */
public class WarehouseDispatchOptimizer {

    /**
     * Compute maximum credits (warehouses you empty on your turn).
     *
     * @param inventory List of inventory counts per warehouse
     * @param dispatch1 Units you dispatch each turn
     * @param dispatch2 Units co-worker dispatches each turn
     * @param skips     Maximum co-worker skips available
     * @return Maximum credits
     */
    public static int getMaximumCredits(List<Integer> inventory, int dispatch1, int dispatch2, int skips) {
        long a = dispatch1;
        long b = dispatch2;
        long cycle = a + b;

        // Calculate skips needed for each warehouse
        int n = inventory.size();
        int[] needed = new int[n];
        for (int i = 0; i < n; i++) {
            long v = inventory.get(i);
            long r = v % cycle;
            if (r == 0) {
                r = cycle;
            }
            // Minimum extra your-turns beyond the first: ceil(r/a) - 1
            int k = (int) ((r + a - 1) / a) - 1;
            needed[i] = Math.max(k, 0);
        }

        // Greedy: sort by needed skips ascending
        Arrays.sort(needed);
        int credits = 0;
        int remainingSkips = skips;
        for (int k : needed) {
            if (k <= remainingSkips) {
                credits++;
                remainingSkips -= k;
            } else {
                break;
            }
        }
        return credits;
    }

    /**
     * Simple main method to test provided and edge cases.
     * PASS/FAIL for each case, plus a large-data performance test.
     */
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                new TestCase(Arrays.asList(10, 6, 12, 8, 15, 1), 2, 3, 3, 5),
                new TestCase(Arrays.asList(3, 7, 17, 21, 12, 19), 4, 3, 2, 4),
                new TestCase(Arrays.asList(1, 101), 1, 100, 2, 1),
                // Edge cases
                new TestCase(List.of(5), 10, 5, 0, 1),    // you empty immediately
                new TestCase(List.of(6), 4, 4, 1, 1),    // skip to get credit
                new TestCase(Arrays.asList(9, 9, 9), 3, 6, 3, 3), // all need exactly 2 skips each; only one credit
                // Large-data test: 100k warehouses, small values
                new TestCase(IntStream.range(0, 100_000)
                        .map(i -> 1)
                        .boxed().collect(Collectors.toList()), 2, 2, 0, 100_000)
        );

        int idx = 1;
        for (TestCase tc : tests) {
            int result = getMaximumCredits(tc.inventory, tc.dispatch1, tc.dispatch2, tc.skips);
            boolean pass = (tc.expected < 0 || result == tc.expected);
            String label = (tc.expected >= 0) ? (pass ? "PASS" : "FAIL")
                    : "RESULT";
            System.out.printf("Test %d: %s (expected=%d, actual=%d)%n",
                    idx++, label, tc.expected, result);
        }

        // Clarifying questions / suggestions
        System.out.println("\nClarifications Needed:");
        System.out.println("- Is the skip budget shared across all warehouses or reset per warehouse?");
        System.out.println("- Can the co-worker choose to skip non-consecutive turns? " +
                "(Our solution assumes any distribution of skips.)");
        System.out.println("\nImprovement Ideas:");
        System.out.println("- If 'skips' is very large compared to n, we can shortcut and return n immediately.");
        System.out.println("- For extremely large inputs, consider counting sort on skip needs if range is small.");
    }

    /**
     * Helper class for testing
     */
    private static class TestCase {
        List<Integer> inventory;
        int dispatch1, dispatch2, skips, expected;

        TestCase(List<Integer> inv, int d1, int d2, int s, int exp) {
            this.inventory = inv;
            this.dispatch1 = d1;
            this.dispatch2 = d2;
            this.skips = s;
            this.expected = exp;
        }
    }
}
