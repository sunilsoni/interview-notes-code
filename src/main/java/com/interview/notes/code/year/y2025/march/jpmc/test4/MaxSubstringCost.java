package com.interview.notes.code.year.y2025.march.jpmc.test4;

/*
WORKING 100



## **Problem Statement**
Two strings, `s` and `t`, each of length `n`, that contain lowercase English characters are given, along with an integer `K`.

- The cost to change the `i`th character in `s` from `s[i]` to `t[i]` is the absolute difference of their ASCII values:
  \[
  \text{cost} = |s[i] - t[i]|
  \]

- Find the maximum length of a substring of `s` that can be changed to the corresponding substring of `t` with a total cost ≤ `K`.
- If there is no such substring, return `0`.

---

## **Example**
### **Input**
```
s = "adpgki"
t = "cdmxki"
K = 6
```
### **Transformation Process**
1. Change `s[0] = 'a'` → `'c'` with cost `|a - c| = 2`, `K = 6 - 2 = 4`
2. Change `s[2] = 'p'` → `'m'` with cost `|p - m| = 3`, `K = 4 - 3 = 1`
3. The next change `s[4] = 'g'` → `'x'` requires `|g - x| > K`, so we stop.

### **Output**
```
3
```
**Explanation:**
The longest substring that can be modified within cost `K` is `s[0:2]` → `t[0:2]` (length = 3).

---

## **Function Definition**
```java
public static int sameSubstring(String s, String t, int K) {
    // Implement logic
}
```
**Parameters:**
- `s`: String to alter
- `t`: Target string
- `K`: Maximum allowed transformation cost

**Returns:**
- `int`: Maximum length of a substring that can be obtained.

---

## **Constraints**
- \(1 \leq n \leq 2 \times 10^5\)
- \(0 \leq K \leq 10^6\)
- Strings `s` and `t` contain only lowercase English letters.

---

## **Sample Cases**
### **Sample Case 0**
#### **Input**
```
s = "uaccd"
t = "gbbeg"
K = 4
```
#### **Output**
```
3
```
#### **Explanation**
| Index | Current `s[i]` | New `t[i]` | New `s` | Cost | Remaining `K` |
|--------|--------------|------------|---------|------|---------------|
| 1      | 'a' → 'b'  | "ubccd"   | 1    | 3     |
| 2      | 'c' → 'b'  | "ubbcd"   | 1    | 2     |
| 3      | 'c' → 'e'  | "ubbed"   | 2    | 0     |

Substring `s[1,3]` is equal to `t[1,3]`.

---

### **Sample Case 1**
#### **Input**
```
s = "hffk"
t = "larb"
K = 3
```
#### **Output**
```
0
```
#### **Explanation**
- No character can be changed within the budget `K=3`.

---
 */
public class MaxSubstringCost {

    /**
     * Computes the maximum length of a contiguous substring in s which can be transformed into
     * the corresponding substring in t with a total cost no greater than K. The cost to change
     * s[i] to t[i] is defined as the absolute difference between their ASCII values.
     *
     * @param s the source string
     * @param t the target string
     * @param K maximum allowed transformation cost
     * @return maximum length of a substring that can be transformed within cost K
     */
    public static int sameSubstring(String s, String t, int K) {
        int n = s.length();
        int maxLen = 0;
        int curr = 0;  // Tracks the total cost in the current window
        int l = 0;     // Left pointer

        for (int r = 0; r < n; r++) {
            // Add the cost of including s[r] -> t[r] in the window
            curr += Math.abs(s.charAt(r) - t.charAt(r));

            // Shrink the window from the left while total cost exceeds K
            while (curr > K && l <= r) {
                curr -= Math.abs(s.charAt(l) - t.charAt(l));
                l++;
            }

            // Update the maximum valid window size
            maxLen = Math.max(maxLen, r - l + 1);
        }

        return maxLen;
    }

    /**
     * A simple test method that prints whether a test case passes or fails.
     *
     * @param testName a descriptive name for the test case
     * @param s        the source string for the test
     * @param t        the target string for the test
     * @param K        the allowed transformation cost
     * @param expected the expected output
     */
    public static void runTest(String testName, String s, String t, int K, int expected) {
        int result = sameSubstring(s, t, K);
        if (result == expected) {
            System.out.println("Test " + testName + " PASSED");
        } else {
            System.out.println("Test " + testName + " FAILED: Expected " + expected + ", got " + result);
        }
    }

    /**
     * Main method to run sample tests and additional edge cases, including a large input test.
     */
    public static void main(String[] args) {
        // Sample test cases provided in the problem statement
        runTest("Sample Case 0", "uaccd", "gbbeg", 4, 3);
        runTest("Sample Case 1", "hffk", "larb", 3, 0);

        // Provided example in the problem statement
        runTest("Provided Example", "adpgki", "cdmxki", 6, 3);

        // Edge case: When both strings are identical, cost for each position is 0.
        runTest("Edge Case - Identical Strings", "abcdef", "abcdef", 0, 6);

        // Edge case: Extremely large K so that the whole string can be transformed.
        runTest("Edge Case - Large K", "abcdef", "ghijkl", 1000, 6);

        // Single character tests
        runTest("Single Character - Insufficient K", "a", "z", 5, 0);
        runTest("Single Character - Sufficient K", "a", "z", 26, 1);

        // Large Data Test: Create strings of length 200,000.
        // For simplicity, s is composed entirely of 'a' and t entirely of 'z'.
        // The cost per character is |'a' - 'z'| = 25.
        // For K = 25 * 1000, the maximum valid substring length is 1000.
        int n = 200000;
        StringBuilder sBuilder = new StringBuilder(n);
        StringBuilder tBuilder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sBuilder.append('a');
            tBuilder.append('z');
        }
        String sLarge = sBuilder.toString();
        String tLarge = tBuilder.toString();
        int largeK = 25 * 1000;
        runTest("Large Data Test", sLarge, tLarge, largeK, 1000);
    }
}