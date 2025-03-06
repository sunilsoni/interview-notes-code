package com.interview.notes.code.year.y2025.march.Glider.test1;

import java.util.*;
import java.util.stream.*;
/*
WORKING 100

### **Grand Number Game**

In a thrilling game scenario, you are presented with an array containing **2N** positive integers. Alongside this, you are equipped with **N** operations to strategically manipulate these numbers. In each operation, you have the power to choose any two positive integers from the array.

To determine your score in each round, the algorithm calculates the **greatest common divisor (GCD)** of the selected numbers, multiplying it by the corresponding operation number. The resulting value contributes to your total score.

The ultimate objective is to optimize your actions and achieve the highest possible total score. **Can you devise a winning strategy and uncover the maximum total score?**

---

### **Input**
1. The first line of input contains an integer **N**, representing the number of operations.
2. The second line of input contains **2N** space-separated integers, representing the array.

---

### **Output**
Print the maximum total score.

---

### **Constraints**
- \(1 \leq N \leq 10\)
- \(1 \leq arr_i \leq 10^9\)

---

### **Example 1**
#### **Input**
```
2
3 4 9 5
```
#### **Output**
```
7
```
#### **Explanation**
Max score is:
- \(1 \times \gcd(4,5) + 2 \times \gcd(3,9) = 7\).

---

### **Example 2**
#### **Input**
```
3
1 2 3 4 5 6
```
#### **Output**
```
14
```
#### **Explanation**
Max score is:
- \(1 \times \gcd(1,5) = 1\)
- \(2 \times \gcd(2,4) = 4\)
- \(3 \times \gcd(3,6) = 9\)

Total score = **14**.

 */
public class GrandNumberGame {

    public static int solve(List<Integer> arr) {
        int n = arr.size() / 2;
        Map<String, Integer> memo = new HashMap<>();
        return backtrack(arr, 1, memo);
    }

    private static int backtrack(List<Integer> nums, int op, Map<String, Integer> memo) {
        if (nums.isEmpty()) return 0;
        String key = nums.stream().sorted().map(String::valueOf).collect(Collectors.joining(","));
        if (memo.containsKey(key)) return memo.get(key);

        int max = 0;
        int size = nums.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                List<Integer> next = new ArrayList<>(nums);
                int a = next.remove(j);
                int b = next.remove(i);
                int score = op * gcd(a, b);
                max = Math.max(max, score + backtrack(next, op + 1, memo));
            }
        }

        memo.put(key, max);
        return max;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        test(Arrays.asList(3, 4, 9, 5), 7); // Example 1
        test(Arrays.asList(1, 2, 3, 4, 5, 6), 14); // Example 2

        // Additional large test
        test(IntStream.range(1, 21).boxed().collect(Collectors.toList()), -1); // Larger input

        // Edge Case
        test(Arrays.asList(1, 1), 1);
        test(Arrays.asList(1, 1000000000), 1);
    }

    private static void test(List<Integer> input, int expected) {
        long startTime = System.currentTimeMillis();
        int result = solve(input);
        long endTime = System.currentTimeMillis();

        String status = (expected == -1 || result == expected) ? "PASS" : "FAIL";
        System.out.println("Input: " + input);
        System.out.println("Expected: " + (expected == -1 ? "(Large case, manual verify)" : expected));
        System.out.println("Output: " + result);
        System.out.println("Status: " + status);
        System.out.println("Execution Time: " + (endTime - startTime) + "ms");
        System.out.println("---------------------------------------");
    }
}