package com.interview.notes.code.year.y2025.jan25.ibm.test4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
PARTIALL 5 failing


#### Consecutive Numbers Game

You are given an array `a` of `n` numbers, and you are playing a number game as follows:

1. In the first iteration, take number pairs - `a[i]` and `a[i+1]`, starting from `i=0`. If the product is divisible by 3, replace `a[i]` with the product; otherwise, keep the element unchanged.
2. Do the same for all number pairs in their order of appearance in the array.
3. Since the last number has no pair, leave it as is.
4. Repeat the game for multiple iterations until the first `n-1` elements in the array are multiples of 3.

Your task is to find the number of rounds before the game ends.

---

### Input:
- The first line corresponds to `n`, the number of elements in the list.
- The next line contains `n` integers, representing the elements of the array.

---

### Output:
The output consists of a single integer corresponding to the number of rounds before the game ends.

---

### Examples:

#### Example #1:

**Input:**
```
5
34 56 20 90 100
```

**Output:**
```
3
```

**Explanation:**
- **Round 1:** {34 56 1800 9000 100}
- **Round 2:** {34 100800 16200000 90000000 100}
- **Round 3:** {3427200 100800 16200000 90000000 100}

The first 3 numbers are multiples of 3. Hence, the number of rounds is **3**.

---

#### Example #2:

**Input:**
```
4
1 333 222 22
```

**Output:**
```
1
```

**Explanation:**
- **Round 1:** {333 333 222 22}

The first 3 numbers are multiples of 3. Hence, the number of rounds is **1**.

---

### Implementation:

**Skeleton Code:**

```java
class Outcome {

    /*
     * Implement method/function with name 'solve' below.
     * The function accepts the following as parameters:
     * 1. arr is of type List<Integer>
     * Return type: int


public static int solve(List<Integer> arr) {
    // Write your code here
    return 0; // return type "int".
}
}
        ```

 */
public class ConsecutiveNumbersGame {

    public static int solve(List<Integer> arr) {
        if (arr == null || arr.size() <= 1) {
            return 0;
        }

        // Convert to BigInteger to avoid overflow
        List<BigInteger> bigArr = new ArrayList<>();
        for (int x : arr) {
            bigArr.add(BigInteger.valueOf(x));
        }

        int rounds = 0;
        while (!allButLastAreMultiplesOf3(bigArr)) {
            boolean changed = false;
            List<BigInteger> newArr = new ArrayList<>(bigArr);
            for (int i = 0; i < bigArr.size() - 1; i++) {
                // Check divisibility by 3 without computing huge product unnecessarily
                if (bigArr.get(i).mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)
                        || bigArr.get(i + 1).mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) {
                    BigInteger product = bigArr.get(i).multiply(bigArr.get(i + 1));
                    newArr.set(i, product);
                    changed = true;
                }
            }
            // If no changes in this round, no further progress is possible
            if (!changed) {
                break;
            }
            bigArr = newArr;
            rounds++;
        }
        return rounds;
    }

    private static boolean allButLastAreMultiplesOf3(List<BigInteger> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (!arr.get(i).mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Example #1
        List<Integer> t1 = List.of(34, 56, 20, 90, 100);
        int r1 = solve(new ArrayList<>(t1));
        System.out.println("Test #1: " + (r1 == 3 ? "PASS" : "FAIL")
                + " (Expected 3, Got " + r1 + ")");

        // Example #2
        List<Integer> t2 = List.of(1, 333, 222, 22);
        int r2 = solve(new ArrayList<>(t2));
        System.out.println("Test #2: " + (r2 == 1 ? "PASS" : "FAIL")
                + " (Expected 1, Got " + r2 + ")");

        // Single element
        List<Integer> t3 = List.of(7);
        int r3 = solve(new ArrayList<>(t3));
        System.out.println("Test #3: " + (r3 == 0 ? "PASS" : "FAIL")
                + " (Expected 0, Got " + r3 + ")");

        // Two-element array that might never converge
        List<Integer> t4 = List.of(2, 2);
        int r4 = solve(new ArrayList<>(t4));
        System.out.println("Test #4 Rounds: " + r4);

        // Large random test
        List<Integer> t5 = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            t5.add(rand.nextInt(500) + 1);
        }
        int r5 = solve(new ArrayList<>(t5));
        System.out.println("Large Test Rounds: " + r5);
    }
}
