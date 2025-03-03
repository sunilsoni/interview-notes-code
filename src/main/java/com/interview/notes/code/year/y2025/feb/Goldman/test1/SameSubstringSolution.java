package com.interview.notes.code.year.y2025.feb.Goldman.test1;

/*

WORKING 100%

## Same Substring

Two strings, `s` and `t`, each of length `n`, that contain lowercase English characters are given as well as an integer `K`.

- The cost to change the `i`th character in `s` from `s[i]` to `t[i]` is the absolute difference of their ASCII values: `abs(s[i] - t[i])`.
- Find the maximum length of a substring of `s` that can be changed to match `t` with a total cost less than or equal to `K`. If no such substring exists, return `0`.

### Example
#### Input:
```
s = "adpgki"
t = "cdmxki"
K = 6
```
#### Steps:
1. Change `s[0]` from 'a' to 'c' (cost = `abs('a' - 'c') = 2`), resulting in `"cdpgki"` and `K = 6 - 2 = 4`.
2. Change `s[2]` from 'p' to 'm' (cost = `abs('p' - 'm') = 3`), resulting in `"cdmgki"` and `K = 4 - 3 = 1`.
3. The remaining changes would exceed `K`.

#### Output:
```
3
```

## Function Description
Implement the function `sameSubstring`:
```java
public static int sameSubstring(String s, String t, int K) {
    // Implementation
}
```

### Parameters:
- `s`: The string to alter.
- `t`: The string to match.
- `K`: The maximum sum of costs allowed.

### Returns:
- `int`: The maximum length of a substring that can be obtained.

## Constraints:
- `1 ≤ n ≤ 2 × 10^5`
- `0 ≤ K ≤ 10^6`
- Strings `s` and `t` contain only lowercase English letters.

## Sample Cases
### Sample Case 0
#### Input:
```
s = "uaccd"
t = "gbbeg"
K = 4
```
#### Output:
```
3
```
#### Explanation:
| Index in `s` | Current | New  | New `s` | Cost | Remaining `K` |
|-------------|---------|------|---------|------|---------------|
| 1           | a       | b    | ubccd   | 1    | 3             |
| 2           | c       | b    | ubbcd   | 1    | 2             |
| 3           | c       | e    | ubbed   | 2    | 0             |

Substring `s[1, 3]` is now equal to `t[1, 3]`.

---

### Sample Case 1
#### Input:
```
s = "hffk"
t = "larb"
K = 3
```
#### Output:
```
0
```
#### Explanation:
- The cost to change any `s[i]` to `t[i]` is greater than `K = 3`.
- No characters can be changed, and no match is possible.

---

This problem requires efficient handling of substrings and ASCII calculations to determine the longest possible matching substring within the allowed cost.

 */
public class SameSubstringSolution {

    /**
     * Returns the maximum length of a substring of s that can be changed to match t
     * with a total cost less than or equal to K.
     *
     * @param s the original string
     * @param t the target string
     * @param K the maximum allowed total cost
     * @return the length of the longest valid substring; 0 if none exists.
     */
    public static int sameSubstring(String s, String t, int K) {
        int n = s.length();
        int maxLen = 0;
        int left = 0;
        int currentCost = 0;

        // Use a sliding window from left to right.
        for (int right = 0; right < n; right++) {
            // Add cost of converting s[right] to t[right]
            currentCost += Math.abs(s.charAt(right) - t.charAt(right));

            // If the total cost exceeds K, shrink the window from the left.
            while (currentCost > K && left <= right) {
                currentCost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            // Update maximum length found.
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * Main method to run several test cases including edge cases and a large input test.
     */
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 0;

        // Test Case 1: Provided Example
        totalTests++;
        String s1 = "adpgki";
        String t1 = "cdmxki";
        int K1 = 6;
        int expected1 = 3;
        int result1 = sameSubstring(s1, t1, K1);
        if (result1 == expected1) {
            System.out.println("Test 1 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 1 FAIL: expected " + expected1 + " but got " + result1);
        }

        // Test Case 2: Sample Case 0
        totalTests++;
        String s2 = "uaccd";
        String t2 = "gbbeg";
        int K2 = 4;
        int expected2 = 3;
        int result2 = sameSubstring(s2, t2, K2);
        if (result2 == expected2) {
            System.out.println("Test 2 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 2 FAIL: expected " + expected2 + " but got " + result2);
        }

        // Test Case 3: Sample Case 1
        totalTests++;
        String s3 = "hffk";
        String t3 = "larb";
        int K3 = 3;
        int expected3 = 0;
        int result3 = sameSubstring(s3, t3, K3);
        if (result3 == expected3) {
            System.out.println("Test 3 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 3 FAIL: expected " + expected3 + " but got " + result3);
        }

        // Test Case 4: All characters already matching (cost = 0)
        totalTests++;
        String s4 = "aaa";
        String t4 = "aaa";
        int K4 = 0;
        int expected4 = 3;
        int result4 = sameSubstring(s4, t4, K4);
        if (result4 == expected4) {
            System.out.println("Test 4 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 4 FAIL: expected " + expected4 + " but got " + result4);
        }

        // Test Case 5: Single character where cost exceeds K
        totalTests++;
        String s5 = "a";
        String t5 = "z";  // Cost is abs('a' - 'z') = 25
        int K5 = 20;
        int expected5 = 0;
        int result5 = sameSubstring(s5, t5, K5);
        if (result5 == expected5) {
            System.out.println("Test 5 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 5 FAIL: expected " + expected5 + " but got " + result5);
        }

        // Test Case 6: Single character where cost is affordable
        totalTests++;
        String s6 = "a";
        String t6 = "z";  // Cost is 25
        int K6 = 25;
        int expected6 = 1;
        int result6 = sameSubstring(s6, t6, K6);
        if (result6 == expected6) {
            System.out.println("Test 6 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 6 FAIL: expected " + expected6 + " but got " + result6);
        }

        // Test Case 7: Large Data Test
        totalTests++;
        int n = 200000;
        StringBuilder sbS = new StringBuilder();
        StringBuilder sbT = new StringBuilder();
        // Create two large strings where every character is 'a' (cost always 0)
        for (int i = 0; i < n; i++) {
            sbS.append('a');
            sbT.append('a');
        }
        String sLarge = sbS.toString();
        String tLarge = sbT.toString();
        int KLarge = 0;
        int expectedLarge = n;  // Because cost is 0, the whole string is valid
        int resultLarge = sameSubstring(sLarge, tLarge, KLarge);
        if (resultLarge == expectedLarge) {
            System.out.println("Test 7 (Large data) PASS");
            testsPassed++;
        } else {
            System.out.println("Test 7 (Large data) FAIL: expected " + expectedLarge + " but got " + resultLarge);
        }

        System.out.println("Total tests passed: " + testsPassed + "/" + totalTests);
    }
}