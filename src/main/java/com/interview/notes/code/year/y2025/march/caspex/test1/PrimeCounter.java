package com.interview.notes.code.year.y2025.march.caspex.test1;

/*
WORKING 100%

### **Prime numbers less than N**

You are given a non-negative integer **N**. Your task is to write a program that can print the **number of prime numbers less than N**.

#### **Input**
The input contains an integer **N**, representing the non-negative integer.

#### **Output**
Print the number of prime numbers less than **N**.

#### **Constraints**
\( 0 \leq N \leq 5 \times 10^6 \)

---

### **Example #1**
**Input**
```
10
```
**Output**
```
4
```
**Explanation:** There are **4** prime numbers less than **10**, they are **2, 3, 5, 7**.

---

### **Example #2**
**Input**
```
0
```
**Output**
```
0
```
**Explanation:** There are no prime numbers less than **0**.

---

 */
public class PrimeCounter {

    /**
     * Counts the number of prime numbers less than N
     *
     * @param N The upper bound (exclusive)
     * @return The count of prime numbers less than N
     */
    public static int solve(int N) {
        // Handle edge cases
        if (N <= 2) {
            return 0; // No primes less than 2
        }

        // Use Sieve of Eratosthenes to find all primes less than N
        boolean[] isComposite = new boolean[N];
        // Initialize: false means potentially prime

        // Start from the first prime number, 2
        for (int i = 2; i * i < N; i++) {
            if (!isComposite[i]) {
                // Mark all multiples of i as composite
                for (int j = i * i; j < N; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        // Count the prime numbers
        int count = 0;
        for (int i = 2; i < N; i++) {
            if (!isComposite[i]) {
                count++;
            }
        }

        return count;
    }

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        // Test cases
        testCase(10, 4);
        testCase(0, 0);
        testCase(2, 0);
        testCase(3, 1);
        testCase(20, 8);
        testCase(100, 25);

        // Test large input
        long startTime = System.currentTimeMillis();
        int result = solve(5000000);
        long endTime = System.currentTimeMillis();
        System.out.println("Large test case (N=5,000,000): " + result +
                " (Time: " + (endTime - startTime) + "ms)");

        // The correct answer for N=5,000,000 is 348,513
        testCase(5000000, 348513);
    }

    /**
     * Helper method to test and verify a test case
     */
    private static void testCase(int input, int expected) {
        int result = solve(input);
        boolean passed = result == expected;

        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("-------------------");
    }
}