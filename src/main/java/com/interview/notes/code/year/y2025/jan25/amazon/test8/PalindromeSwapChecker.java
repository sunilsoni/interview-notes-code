package com.interview.notes.code.year.y2025.jan25.amazon.test8;

/*
WORKING 100%

Problem: Minimum Palindrome Swaps

You are given a binary string, **s**, consisting of characters `'0'` and `'1'`. Transform this string into a palindrome by performing some operations. In one operation, you can swap any two characters, \(s[i]\) and \(s[j]\). Determine the **minimum number of swaps** required to make the string a palindrome. If it is **impossible**, return `-1`.

---

### Function Description

Complete the function **`minPalindromeSwaps`** in the editor below.

#### Parameters:
- **`s`**: A binary string of length \( |s| \).

#### Returns:
- An integer: The minimum number of swaps required to make the string a palindrome, or `-1` if it is not possible.

---

### Constraints
- \( 1 \leq |s| \leq 2 \cdot 10^5 \)
- \( s[i] \in \{'0', '1'\} \)

---

### Input Format

The first and only line contains the string \( s \).

---

### Sample Cases

#### Sample Case 1

**Input**
```
1110
```

**Output**
```
-1
```

**Explanation**:
There is no way to make \( s \) a palindrome.

---

#### Sample Case 0

**Input**
```
101000
```

**Output**
```
1
```

**Explanation**:
- Swap characters at indices (3, 6): \( 101000 \to 100001 \).

The string is now a palindrome.

---

#### Example Walkthrough

Let \( s = "0100101" \). The following shows the minimum number of steps required. It uses 1-based indexing:
1. Swap characters with indices \( (4, 5) \): \( 0100101 \to 0101001 \).
2. Swap characters with indices \( (1, 2) \): \( 0101001 \to 1001001 \).

The binary string is now a palindrome: \( 1001001 \).

The answer is **2 swaps**.

---

### Function Template

```java
public static int minPalindromeSwaps(String s) {
    // Write your code here
}
```
 */
public class PalindromeSwapChecker {

    /**
     * Returns the minimum number of swaps required to make s a palindrome,
     * or -1 if impossible.
     */
    public static int minPalindromeSwaps(String s) {
        int n = s.length();
        int zeroCount = 0;

        // Count the zeros
        for (char c : s.toCharArray()) {
            if (c == '0') zeroCount++;
        }

        int oneCount = n - zeroCount;

        // 1) Check feasibility
        // Even length => both counts must be even
        // Odd length => exactly one of them is odd
        if (n % 2 == 0) {
            // n even: zeroCount and oneCount must both be even
            if ((zeroCount % 2 != 0) || (oneCount % 2 != 0)) {
                return -1;
            }
        } else {
            // n odd: only one of them is odd
            if ((zeroCount % 2 + oneCount % 2) != 1) {
                return -1;
            }
        }

        // 2) Count mismatched pairs
        int mismatch = 0;
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                mismatch++;
            }
        }

        // 3) Compute result
        return (mismatch + 1) / 2;  // integer division with ceiling for odd mismatch
    }

    /**
     * Main method for testing (no JUnit).
     * We run several test scenarios, print PASS/FAIL, and show the output.
     */
    public static void main(String[] args) {

        // Helper for test results
        String[][] tests = {
                // { inputString, expectedResult }
                {"1110", "-1"},  // from sample => impossible
                {"101000", "1"},  // from sample => 1 swap
                {"0100101", "2"},  // example => 2 swaps
                {"0", "0"},  // single char => already palindrome
                {"00", "0"},  // even length, all same => palindrome
                {"01", "-1"},  // 2 chars, 1 zero/1 one => cannot both be even => -1
                {"001", "1"},  // can fix in 1 swap (middle helps)
                {"100", "1"},  // also fix in 1 swap
                {"0101", "1"},  // mismatch=2 => (2+1)/2=1
        };

        for (String[] test : tests) {
            String input = test[0];
            String expected = test[1];

            int result = minPalindromeSwaps(input);
            String outcome = (String.valueOf(result).equals(expected)) ? "PASS" : "FAIL";

            System.out.printf(
                    "Input: %s | Expected: %s | Got: %d => %s%n",
                    input, expected, result, outcome
            );
        }

        // Additional Large Test (just to confirm performance, not a true correctness check)
        // Construct a random large string of length ~200,000 with roughly half zeros, half ones
        // Then just run the method to ensure it completes quickly.
        // (We won't check correctness exhaustively here, but we demonstrate it doesn't timeout.)
        int largeSize = 200_000;
        StringBuilder sb = new StringBuilder(largeSize);
        for (int i = 0; i < largeSize; i++) {
            // Alternate '0' and '1' or random them
            sb.append((i % 2 == 0) ? '0' : '1');
        }
        long startTime = System.nanoTime();
        int largeResult = minPalindromeSwaps(sb.toString());
        long endTime = System.nanoTime();
        double elapsedMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("\nLarge test (n=200000) completed in " + elapsedMs + " ms. "
                + "Result was: " + largeResult);
    }
}
