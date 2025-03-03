package com.interview.notes.code.year.y2025.feb.randstad.test1;

/*
WORKING 100%

Here is the properly formatted and structured text:

---

## **Prime Numbers Less Than N**

You are given a **non-negative** integer **N**. Your task is to write a program that prints the **number of prime numbers less than N**.

---

### **Input Format**
- The input contains an **integer N**, representing the non-negative integer.

### **Output Format**
- Print the **number of prime numbers** less than **N**.

### **Constraints**
`0 ≤ N ≤ 5 * 10^6`

---

## **Example #1**
### **Input**
```
10
```
### **Output**
```
4
```
### **Explanation**
There are **4 prime numbers** less than `10`, which are **2, 3, 5, and 7**.

---

## **Example #2**
### **Input**
```
0
```
### **Output**
```
0
```
### **Explanation**
There are **no prime numbers** less than `0`.

---

## **Function Signature**
```java
/*
* Implement method/function with name 'solve' below.
* The function accepts the following as parameters:
* 1. n is of type int.
* return int.


public static int solve(int n) {
    // Write your code here
    return; // return type "int".
}
```


  */
public class PrimeCounter {
    // Main method to test the solution
    public static void main(String[] args) {
        // Test cases
        testPrimeCounter();
    }

    // Solution method
    public static int solve(int n) {
        if (n <= 2) return 0;

        // Create boolean array for Sieve of Eratosthenes
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        // Implement Sieve of Eratosthenes
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Count prime numbers
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }

        return count;
    }

    // Test method
    private static void testPrimeCounter() {
        // Test case 1: Example from problem
        test(10, 4, "Basic test case");

        // Test case 2: Edge case - 0
        test(0, 0, "Zero input");

        // Test case 3: Edge case - 1
        test(1, 0, "One input");

        // Test case 4: Edge case - 2
        test(2, 0, "Two input");

        // Test case 5: Small number
        test(5, 2, "Small number");

        // Test case 6: Larger number
        test(20, 8, "Larger number");

        // Test case 7: Edge case - Maximum constraint
        test(5000000, 348513, "Large input test");
    }

    // Helper method to run tests
    private static void test(int input, int expected, String testName) {
        int result = solve(input);
        System.out.printf("Test %s: ", testName);
        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.printf("FAIL (Expected: %d, Got: %d)%n", expected, result);
        }
    }
}
