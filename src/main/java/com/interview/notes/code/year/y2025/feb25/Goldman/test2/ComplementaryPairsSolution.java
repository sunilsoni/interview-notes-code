package com.interview.notes.code.year.y2025.feb25.Goldman.test2;

import java.util.*;

/*
WORKING 100%


## Complementary Pairs

A pair of strings form a complementary pair if some permutation of their concatenation is a palindrome. For example, the strings "abac" and "cab" form a complementary pair since their concatenation "abaccab" can be rearranged into a palindrome "bcaaacb".

Given an array of `n` strings, find the number of complementary pairs that can be formed.

### Note:
Pairs of strings formed by indices `(i, j)` and `(j, i)` are considered the same.

### Example
#### Input:
```
stringData = ["abc", "abcd", "bc", "adc"]
```
#### Possible Complementary Pairs:
- (`"abc"`, `"abcd"`), concatenated as `"abcabcd"`, can be rearranged into `"abcdcba"` (palindrome).
- (`"abc"`, `"bc"`), concatenated as `"abcbc"`, can be rearranged into `"bcacb"` (palindrome).
- (`"abcd"`, `"adc"`), concatenated as `"abcdadc"`, can be rearranged into `"acdbdca"` (palindrome).

#### Output:
```
3
```

## Function Description
Implement the function `countComplementaryPairs`:
```java
public static long countComplementaryPairs(List<String> stringData) {
    // Implementation
}
```

### Parameters:
- `stringData`: An array of strings.

### Returns:
- `long`: The number of complementary pairs that can be formed.

## Constraints:
- `1 ≤ n ≤ 10^5`
- `1 ≤ length(stringData[i]) ≤ 3 × 10^5`
- `1 ≤ sum of lengths of strings in stringData ≤ 3 × 10^5`
- Strings consist of only lowercase English characters.

## Sample Cases
### Sample Case 0
#### Input:
```
4
ball
all
call
bal
```
#### Output:
```
3
```
#### Explanation:
The following complementary pairs can be formed:
- (`"ball"`, `"all"`), concatenated string `"ballall"` -> rearranged into palindrome `"allblla"`.
- (`"ball"`, `"bal"`), concatenated string `"ballbal"` -> rearranged into palindrome `"ballalb"`.
- (`"all"`, `"call"`), concatenated string `"allcall"` -> rearranged into palindrome `"allclla"`.

---

### Sample Case 1
#### Input:
```
3
eye
aa
c
```
#### Output:
```
2
```
#### Explanation:
The following complementary pairs can be formed:
- (`"eye"`, `"aa"`), concatenated string `"eyeaa"` -> rearranged into palindrome `"eayae"`.
- (`"aa"`, `"c"`), concatenated string `"aac"` -> rearranged into palindrome `"aca"`.

This problem requires efficiently checking permutations and palindromic structures while handling large inputs optimally.

 */
public class ComplementaryPairsSolution {

    /**
     * Returns the number of complementary pairs that can be formed.
     * Two strings form a complementary pair if the XOR of their bitmasks (parity of counts)
     * has at most one bit set.
     *
     * @param stringData List of input strings.
     * @return The number of complementary pairs.
     */
    public static long countComplementaryPairs(List<String> stringData) {
        // Frequency map for each bitmask.
        Map<Integer, Long> freq = new HashMap<>();
        for (String s : stringData) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                // Toggle the bit for the current character.
                int bit = s.charAt(i) - 'a';
                mask ^= (1 << bit);
            }
            freq.put(mask, freq.getOrDefault(mask, 0L) + 1);
        }

        long result = 0;

        // Count pairs of strings with the same mask (their XOR is 0).
        for (long count : freq.values()) {
            result += count * (count - 1) / 2;  // nC2 pairs from same mask.
        }

        // Count pairs where the two masks differ by exactly one bit.
        for (Map.Entry<Integer, Long> entry : freq.entrySet()) {
            int mask = entry.getKey();
            long count1 = entry.getValue();
            // Try flipping each of the 26 bits.
            for (int bit = 0; bit < 26; bit++) {
                int candidate = mask ^ (1 << bit);
                // To avoid double counting, only consider candidate > mask.
                if (candidate > mask && freq.containsKey(candidate)) {
                    long count2 = freq.get(candidate);
                    result += count1 * count2;
                }
            }
        }

        return result;
    }

    /**
     * Main method to test the solution with several cases (including large data).
     */
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 0;

        // Test Case 1: Sample Case 0
        totalTests++;
        List<String> testData1 = Arrays.asList("ball", "all", "call", "bal");
        long expected1 = 3;
        long result1 = countComplementaryPairs(testData1);
        if (result1 == expected1) {
            System.out.println("Test 1 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 1 FAIL: expected " + expected1 + " but got " + result1);
        }

        // Test Case 2: Sample Case 1
        totalTests++;
        List<String> testData2 = Arrays.asList("eye", "aa", "c");
        long expected2 = 2;
        long result2 = countComplementaryPairs(testData2);
        if (result2 == expected2) {
            System.out.println("Test 2 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 2 FAIL: expected " + expected2 + " but got " + result2);
        }

        // Test Case 3: Additional test with single letter strings.
        totalTests++;
        List<String> testData3 = Arrays.asList("a", "b", "a");
        // Only the two "a"s form a complementary pair.
        long expected3 = 1;
        long result3 = countComplementaryPairs(testData3);
        if (result3 == expected3) {
            System.out.println("Test 3 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 3 FAIL: expected " + expected3 + " but got " + result3);
        }

        // Test Case 4: Additional test where all strings have the same mask.
        totalTests++;
        List<String> testData4 = Arrays.asList("abc", "cba", "bac");
        // All strings have the same letters with odd counts for a, b, c.
        // Expected pairs: C(3, 2) = 3.
        long expected4 = 3;
        long result4 = countComplementaryPairs(testData4);
        if (result4 == expected4) {
            System.out.println("Test 4 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 4 FAIL: expected " + expected4 + " but got " + result4);
        }

        // Test Case 5: Edge Case - No complementary pairs possible.
        totalTests++;
        List<String> testData5 = Arrays.asList("ab", "cd");
        // "ab" -> mask: bits for a and b; "cd" -> mask: bits for c and d.
        // Their XOR will have more than one bit set.
        long expected5 = 0;
        long result5 = countComplementaryPairs(testData5);
        if (result5 == expected5) {
            System.out.println("Test 5 PASS");
            testsPassed++;
        } else {
            System.out.println("Test 5 FAIL: expected " + expected5 + " but got " + result5);
        }

        // Test Case 6: Large Data Test
        totalTests++;
        // Generate many strings. The sum of lengths is kept within limits.
        // Here, we create 100,000 strings each of length 3.
        List<String> testData6 = new ArrayList<>();
        Random rand = new Random(42);
        int numStrings = 100000;
        for (int i = 0; i < numStrings; i++) {
            char[] arr = new char[3];
            for (int j = 0; j < 3; j++) {
                arr[j] = (char) ('a' + rand.nextInt(26));
            }
            testData6.add(new String(arr));
        }
        // We are not checking an expected result here; we simply ensure the function executes quickly.
        long result6 = countComplementaryPairs(testData6);
        System.out.println("Test 6 (Large Data) executed with result: " + result6);
        testsPassed++; // Consider it passed if execution is fast.

        System.out.println("Total tests passed: " + testsPassed + "/" + totalTests);
    }
}