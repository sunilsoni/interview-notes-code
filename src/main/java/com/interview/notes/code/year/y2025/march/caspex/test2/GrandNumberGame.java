package com.interview.notes.code.year.y2025.march.caspex.test2;

import java.util.*;
import java.util.stream.*;
/*
WORKING 100%


### **Grand Number Game**

In a thrilling game scenario, you are presented with an array containing **2N** positive integers. Alongside this, you are equipped with **N** operations to strategically manipulate these numbers.

In each operation, you have the power to choose any two positive integers from the array. To determine your score in each round, the algorithm calculates the **greatest common divisor (GCD)** of the selected numbers, multiplying it by the corresponding operation number. The resulting value contributes to your total score.

The ultimate objective is to optimize your actions and achieve the highest possible total score. **Can you devise a winning strategy and uncover the maximum total score?**

---

### **Input**
- The first line of input contains an integer **N**, representing the number of operations.
- The second line of input contains **2N** space-separated integers, representing the array.

---

### **Output**
Print the **maximum total score**.

---

### **Constraints**
- \( 1 \leq N \leq 10 \)
- \( 1 \leq \text{arr}_i \leq 10^9 \)

---

### **Example #1**
**Input**
```
2
3 4 9 5
```
**Output**
```
7
```
**Explanation:**
Max score is **1** × **gcd(4,5)** + **2** × **gcd(3,9)** = **7**.

---

### **Example #2**
**Input**
```
3
1 2 3 4 5 6
```
**Output**
```
14
```
**Explanation:**
Max score is **(1 × gcd(1,5) = 1) + (2 × gcd(2,4) = 4) + (3 × gcd(3,6) = 9) = 14**.

---

 */
public class GrandNumberGame {

    // Function to compute the greatest common divisor (gcd) of two numbers.
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    
    // DP memoization map: key is the bitmask representing used numbers.
    private static Map<Integer, Integer> memo;
    
    // Main solver method
    public static int solve(List<Integer> arr) {
        int n = arr.size();
        memo = new HashMap<>();
        // start recursion with mask 0 (none used) and operation number 1.
        return dfs(arr, 0, 1);
    }
    
    // DFS function with bitmask to track used indices and op represents the current operation number.
    private static int dfs(List<Integer> arr, int mask, int op) {
        int n = arr.size();
        // when mask covers all numbers, return 0 (base case)
        if (mask == (1 << n) - 1) {
            return 0;
        }
        if(memo.containsKey(mask)){
            return memo.get(mask);
        }
        int maxScore = 0;
        // try every pair of indices that are not used yet
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) continue; // if index i is already used, skip
            for (int j = i + 1; j < n; j++) {
                if ((mask & (1 << j)) != 0) continue; // if index j is already used, skip
                // Calculate current operation score using gcd * op number.
                int currentScore = op * gcd(arr.get(i), arr.get(j));
                // mark indices i and j as used
                int newMask = mask | (1 << i) | (1 << j);
                // recursively calculate the score for the remaining numbers
                int totalScore = currentScore + dfs(arr, newMask, op + 1);
                maxScore = Math.max(maxScore, totalScore);
            }
        }
        memo.put(mask, maxScore);
        return maxScore;
    }
    
    // Testing method: it runs test cases and prints PASS/FAIL for each.
    public static void main(String[] args) {
        // List of test cases: each test case contains an input array and expected output.
        List<TestCase> testCases = new ArrayList<>();
        
        // Provided Test Case #1
        testCases.add(new TestCase(2, Arrays.asList(3, 4, 9, 5), 7));
        // Provided Test Case #2
        testCases.add(new TestCase(3, Arrays.asList(1, 2, 3, 4, 5, 6), 14));
        
        // Additional test cases:
        // 1. Minimal input
        testCases.add(new TestCase(1, Arrays.asList(10, 20), 1 * gcd(10, 20)));
        // 2. Larger input but within constraints (N=4, 8 numbers)
        // For this test, we are printing the computed result for further inspection.
        testCases.add(new TestCase(4, Arrays.asList(12, 15, 18, 21, 24, 30, 35, 40), 0));
        
        int passed = 0;
        for (TestCase tc : testCases) {
            int result = solve(tc.arr);
            // If expected is given as 0, print the computed result (dynamic case)
            if (tc.expected == 0) {
                System.out.println("Test case with N = " + tc.n + " and input " + tc.arr 
                                   + " computed result: " + result);
            } else if (result == tc.expected) {
                System.out.println("Test case PASSED for input " + tc.arr 
                                   + ". Expected: " + tc.expected + ", Got: " + result);
                passed++;
            } else {
                System.out.println("Test case FAILED for input " + tc.arr 
                                   + ". Expected: " + tc.expected + ", Got: " + result);
            }
        }
        
        // Additional large test: to handle maximum constraint scenario, N=10 (20 numbers)
        Random rand = new Random();
        int N = 10;
        List<Integer> largeInput = IntStream.range(0, 2 * N)
                                    .mapToObj(i -> rand.nextInt(1_000_000_000) + 1)
                                    .collect(Collectors.toList());
        // Time measurement for large test case.
        long startTime = System.currentTimeMillis();
        int largeResult = solve(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large test case (N=10) executed in " + (endTime - startTime) 
                           + " ms, result: " + largeResult);
        
        System.out.println("Total passed test cases: " + passed + "/" + (testCases.size()-1)); // excluding dynamic test case
    }
    
    // Helper TestCase class to hold test case data.
    static class TestCase {
        int n;
        List<Integer> arr;
        int expected;
        public TestCase(int n, List<Integer> arr, int expected) {
            this.n = n;
            this.arr = arr;
            this.expected = expected;
        }
    }
}
