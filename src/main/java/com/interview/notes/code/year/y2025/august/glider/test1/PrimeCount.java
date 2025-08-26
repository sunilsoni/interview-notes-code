package com.interview.notes.code.year.y2025.august.glider.test1;


import java.util.*;
import java.util.stream.IntStream;
/*
Here’s the properly combined final question from the screenshots and discussion:

---

### Final Question

**Prime numbers less than N**

You are given a non-negative integer **N**. Your task is to write a program that can print the **number of prime numbers less than N**.

#### Input

* The input contains an integer `N`, representing the non-negative integer.

#### Output

* Print the number of prime numbers less than `N`.

#### Constraints

* `0 <= N <= 5 * 10^6`

#### Example #1

Input

```
10
```

Output

```
4
```

Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

#### Example #2

Input

```
0
```

Output

```
0
```

Explanation: There are no prime numbers less than 0.

---

#### Function to Implement



 */
public class PrimeCount {

    public static int solve(int n) {
        if (n <= 2) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return (int) IntStream.range(2, n).filter(i -> isPrime[i]).count();
    }

    public static void main(String[] args) {
        int[][] tests = {
                {10, 4},
                {0, 0},
                {1, 0},
                {2, 0},
                {3, 1},
                {20, 8},
                {100, 25},
                {1000, 168}
        };

        for (int[] test : tests) {
            int input = test[0];
            int expected = test[1];
            int output = solve(input);
            System.out.println("Input: " + input + " | Expected: " + expected + " | Got: " + output +
                    " | Result: " + (output == expected ? "PASS" : "FAIL"));
        }

        int largeInput = 5000000;
        long start = System.currentTimeMillis();
        int result = solve(largeInput);
        long end = System.currentTimeMillis();
        System.out.println("Large Input Test N=" + largeInput + " | Result: " + result + " | Time(ms): " + (end - start));
    }
}