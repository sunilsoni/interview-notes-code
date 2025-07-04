package com.interview.notes.code.year.y2025.June.amazon.test14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*

---

### **Amazon Rewards Tournament Problem**

Amazon Shopping is running a reward collection event for its customers. There are `n` customers and the `iᵗʰ` customer has collected `initialRewards[i]` points so far.

A final tournament is to take place where:

* The **winner** will be awarded **n** points.
* The **runner-up** will get **n - 1** points.
* The **third place** will get **n - 2** points.
* ...
* The **last place** will get **1** point.

Each customer can only receive one of these tournament rankings.

---

### **Goal:**

Given an integer array `initialRewards` of length `n`, representing the reward points of the customers before the tournament:

Return the number of customers such that **if that customer wins the final tournament**, they will end up with **the highest total points**.

> **Note:** Total points = `initialRewards[i] + n` (if the i-th customer wins).

---

### **Function Signature**

```java
public static int countPossibleWinners(List<Integer> initialRewards)
```

---

### **Input**

* `n`: The number of customers.
* `initialRewards[i]`: The initial points of the `iᵗʰ` customer.

---

### **Constraints**

* `1 ≤ n ≤ 10⁵`
* `1 ≤ initialRewards[i] ≤ 10⁵`

---

### **Output**

* Returns the number of customers who can have the highest total points **if they win**.

---

### **Examples**

#### **Sample Input 0**

```
3
8
10
9
```

#### **Sample Output 0**

```
2
```

#### **Explanation:**

* If 2nd customer wins: `10 + 3 = 13` → max
* If 3rd customer wins: `9 + 3 = 12` → second highest
* 1st: `8 + 3 = 11` → less
  So only 2nd and 3rd can win and become highest → answer: **2**

---

#### **Sample Input 1**

```
4
5
7
9
11
```

#### **Sample Output 1**

```
1
```

#### **Explanation:**

* Only 4th customer can win and become highest.
* `11 + 4 = 15`
  Others:
* 3rd: `9 + 4 = 13`
* 2nd: `7 + 4 = 11`
* 1st: `5 + 4 = 9`
  → Only 4th is highest → answer: **1**

---


 */
public class PossibleWinners {

    public static int countPossibleWinners(List<Integer> initialRewards) {
        int n = initialRewards.size();
        // highest initial reward
        int max = initialRewards.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
        // threshold = max-1, since runner-up can get at most (n–1) extra points
        int threshold = max - 1;
        // anyone with reward >= threshold can tie or beat the best competitor
        return (int) initialRewards.stream()
                .filter(r -> r >= threshold)
                .count();
    }

    public static void main(String[] args) {
        // fixed sample tests
        Map<List<Integer>, Integer> samples = new LinkedHashMap<>();
        samples.put(Arrays.asList(8, 10, 9), 2);
        samples.put(Arrays.asList(5, 7, 9, 11), 1);
        samples.put(Arrays.asList(1, 3, 4), 2);

        System.out.println("=== Sample Tests ===");
        int passed = 0, total = 0;
        for (var entry : samples.entrySet()) {
            total++;
            int got = countPossibleWinners(entry.getKey());
            int want = entry.getValue();
            boolean ok = got == want;
            if (ok) passed++;
            System.out.printf("Test %d: got=%d want=%d → %s%n",
                    total, got, want, ok ? "PASS" : "FAIL");
        }
        System.out.printf("Sample results: %d/%d passed%n%n", passed, total);

        // large random test
        System.out.println("=== Large Random Test ===");
        Random rnd = new Random(123);
        int N = 1_000_000;
        List<Integer> big = IntStream.range(0, N)
                .map(i -> rnd.nextInt(100_000) + 1)
                .boxed()
                .collect(Collectors.toList());
        long start = System.nanoTime();
        int result = countPossibleWinners(big);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("N=%d → result=%d  (took %d ms)%n", N, result, ms);
    }
}