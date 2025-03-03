package com.interview.notes.code.year.y2025.feb.randstad.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*

WORKING 100%

## **Grand Number Game**

In a thrilling game scenario, you are presented with an **array containing 2N positive integers**. Alongside this, you are equipped with **N operations** to strategically manipulate these numbers.

- In each operation, you **choose any two positive integers** from the array.
- The algorithm **calculates the Greatest Common Divisor (GCD)** of the selected numbers, multiplying it by the **corresponding operation number**.
- The resulting value contributes to your **total score**.
- The ultimate objective is to **optimize your actions** and achieve the **highest possible total score**.

Can you devise a **winning strategy** and uncover the **maximum total score**?

---

### **Input Format**
1. The first line of input contains an **integer N**, representing the **number of operations**.
2. The second line of input contains **2N space-separated integers**, representing the array.

### **Output Format**
- Print the **maximum total score**.

### **Constraints**
`1 ≤ N ≤ 10`
`1 ≤ arr[i] ≤ 10^9`

---

## **Example #1**
### **Input**
```
2
3 4 9 5
```
### **Output**
```
7
```
### **Explanation**
The **maximum score** is calculated as:
**1 * gcd(4,5) + 2 * gcd(3,9) = 7**.

---

## **Example #2**
### **Input**
```
3
1 2 3 4 5 6
```
### **Output**
```
14
```
### **Explanation**
The **maximum score** is calculated as:
**(1 * gcd(1,5) = 1) + (2 * gcd(2,4) = 4) + (3 * gcd(3,6) = 9) = 14**.

---

 */
public class GrandNumberGame {

    public static int solve(List<Integer> arr) {
        int n = arr.size(); // Total number of elements = 2 * N
        int totalStates = 1 << n;
        int[] dp = new int[totalStates];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = arr.get(i);
        }

        for (int mask = 0; mask < totalStates; mask++) {
            if (dp[mask] == -1) continue;
            int count = Integer.bitCount(mask);
            int op = count / 2 + 1; // Operation number starts from 1
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) continue;
                for (int j = i + 1; j < n; j++) {
                    if ((mask & (1 << j)) != 0) continue;
                    int newMask = mask | (1 << i) | (1 << j);
                    int score = dp[mask] + op * gcd(nums[i], nums[j]);
                    dp[newMask] = Math.max(dp[newMask], score);
                }
            }
        }
        return dp[totalStates - 1];
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private static void runTest(List<Integer> arr, int expected, String testName) {
        int result = solve(arr);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Example Test 1
        runTest(Arrays.asList(3, 4, 9, 5), 7, "Example Test 1");
        // Example Test 2
        runTest(Arrays.asList(1, 2, 3, 4, 5, 6), 14, "Example Test 2");
        // Edge Test 1: Minimum case with N=1
        runTest(Arrays.asList(10, 15), 5, "Edge Test 1");
        // Edge Test 2: All elements equal; maximum score is 6 (1*2 + 2*2 = 6)
        runTest(Arrays.asList(2, 2, 2, 2), 6, "Edge Test 2");

        // Large Data Test: Random test with 20 elements (N=10)
        Random rand = new Random();
        List<Integer> largeData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            largeData.add(rand.nextInt(1_000_000_000) + 1);
        }
        System.out.println("Large Data Test (random 20 elements): " + solve(largeData));
    }
}