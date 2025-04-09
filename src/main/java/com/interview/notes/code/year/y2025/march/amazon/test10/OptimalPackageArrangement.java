package com.interview.notes.code.year.y2025.march.amazon.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Here is the combined and fully structured version of the problem statement and function signature from your screenshots:

---

### ✅ Problem: Optimize Package Arrangement for Amazon Fulfillment Center

Amazon Fulfillment Center needs to optimize the arrangement of its packages. Each package has an **importance** level, represented by an array of integers called `importance`. The packages can be arranged in **any order**.

The goal is to **arrange** the packages so that the **prefix sum** of importance **never becomes non-positive** (i.e., it never becomes zero or negative).

If it is **impossible** to arrange the packages so that the prefix sum never becomes non-positive, then the objective is to **maximize the index** of the **first non-positive prefix sum**.

If **no way** exists to achieve a non-positive prefix sum, return **-1**.

---

### 🔍 Function Signature

```java
public static int findOptimalPackageArrangement(List<Integer> importance)
```

---

### 📥 Input Format

- The first line contains an integer `n`, the number of elements in `importance`.
- Each of the next `n` lines contains an integer `importance[i]`.

---

### 📤 Output Format

- Return the maximum index of the first non-positive prefix sum after arranging the array.
- If all prefix sums are always positive for any arrangement, return `-1`.

---

### 📌 Constraints

- \(1 \leq n \leq 10^5\)
- \(-10^9 \leq importance[i] \leq 10^9\)

---

### 🧪 Sample Cases

#### Sample Case 0

**Input:**
```
2
1
2
```

**Output:**
```
-1
```

**Explanation:**

Permutations of [1, 2] = [1, 2], [2, 1]
All have positive prefix sums at every index, so return -1.

---

#### Sample Case 1

**Input:**
```
4
1
2
-6
3
```

**Output:**
```
3
```

**Explanation:**

Optimal order: [1, 2, 3, -6] → prefix sums: [1, 3, 6, 0]
First non-positive prefix sum at index 3.

---

### 💡 Example

For `importance = [2, 1, -4]`, the best arrangement is `[2, 1, -4]`:

| Prefix Sum Order     | Prefix Sums    | First Non-Positive Index |
|----------------------|----------------|---------------------------|
| [2, 1, -4]           | [2, 3, -1]     | 2                         |
| [2, -4, 1]           | [2, -2, -1]    | 1                         |
| [-4, 1, 2]           | [-4, -3, -1]   | 0                         |

Max index is **2**.

---

Would you like a sample solution or implementation next?
 */
public class OptimalPackageArrangement {

    public static int findOptimalPackageArrangement(List<Integer> importance) {
        importance.sort(Comparator.reverseOrder());

        long prefixSum = 0;
        for (int i = 0; i < importance.size(); i++) {
            prefixSum += importance.get(i);
            if (prefixSum <= 0) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        test(Arrays.asList(1, 2), -1);
        test(Arrays.asList(1, 2, -6, 3), 3);
        test(Arrays.asList(2, 1, -4), 2);

        // Edge case: large positive input
        test(IntStream.generate(() -> 1).limit(100000).boxed().collect(Collectors.toList()), -1);

        // Edge case: large input leading to negative
        List<Integer> largeNegative = IntStream.generate(() -> -1).limit(100000).boxed().collect(Collectors.toList());
        test(largeNegative, 0);

        // Mixed large input
        List<Integer> mixedLargeInput = IntStream.concat(
                        IntStream.generate(() -> 1000000).limit(50000),
                        IntStream.generate(() -> -1000001).limit(50000))
                .boxed().collect(Collectors.toList());
        test(mixedLargeInput, 99999);
    }

    private static void test(List<Integer> input, int expected) {
        int result = findOptimalPackageArrangement(new ArrayList<>(input));
        System.out.println("Test " + (expected == result ? "PASSED" : "FAILED") + ": Expected = " + expected + ", Got = " + result);
    }
}
