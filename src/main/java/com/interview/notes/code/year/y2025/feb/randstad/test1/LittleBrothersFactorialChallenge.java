package com.interview.notes.code.year.y2025.feb.randstad.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING 100%



## **Little Brother's Factorial Challenge**

Your **10-year-old** brother is a curious boy who has just learned factorials. He has made a **list of factorials** of numbers from **m to n**. Now, your brother wants to challenge you.

You must write a program to find **all the numbers (if any) whose factorials start with an even number**.

Can you write a program to do this?

---

### **Input Format**
- The first line of input contains an **integer m**.
- The second line of input contains an **integer n**.

### **Output Format**
- The output is a **single line of integers**.
- The first integer represents **x**, the number of factorial numbers that start with an even number.
- Followed by **x space-separated** integers representing the **factorials in increasing order**.
- A **space should separate all integers** in the output.

### **Constraints**
`1 ≤ m ≤ 100`
`1 ≤ n ≤ 100`

---

## **Example #1**
### **Input**
```
1
10
```
### **Output**
```
2 3 4 8
```
### **Explanation**
Among the numbers from `1` to `10`, the following numbers start with an even number:
- `2! = 2`
- `3! = 6`
- `4! = 24`
- `8! = 40320`

Each of them starts with an **even number**.

---

## **Example #2**
### **Input**
```
5
7
```
### **Output**
```
0
```
### **Explanation**
No factorials start with an **even number** between **5 and 7**.

---

## **Function Signature**
```java
/*
* Implement method/function with name 'solve' below.
* The function accepts the following as parameters:
* 1. m is of type int.
* 2. n is of type int.
* return List<Integer>.


public static List<Integer> solve(int m, int n) {
    // Write your code here
    return; // return type "List<Integer>".
}
```

        ---

 */
public class LittleBrothersFactorialChallenge {

    public static List<Integer> solve(int m, int n) {
        List<Integer> ans = new ArrayList<>();
        double logFactorial = 0.0;
        for (int i = 1; i <= n; i++) {
            logFactorial += Math.log10(i);
            if (i >= m) {
                double fractional = logFactorial - Math.floor(logFactorial);
                int firstDigit = (int) Math.floor(Math.pow(10, fractional));
                if (firstDigit % 2 == 0) {
                    ans.add(i);
                }
            }
        }
        if (ans.isEmpty()) {
            ans.add(0);
        }
        return ans;
    }

    private static void runTest(int m, int n, List<Integer> expected, String testName) {
        List<Integer> result = solve(m, n);
        if (result.equals(expected)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Example Test 1
        runTest(1, 10, Arrays.asList(2, 3, 4, 8), "Example Test 1");
        // Example Test 2
        runTest(5, 7, List.of(0), "Example Test 2");
        // Edge Case Test
        runTest(1, 1, List.of(0), "Edge Test 1");
        // Large Data Test (m = 1, n = 100)
        List<Integer> largeDataResult = solve(1, 100);
        System.out.println("Large Data Test (m=1, n=100): " + largeDataResult);
    }
}