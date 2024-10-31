package com.interview.notes.code.months.oct24.amazon.test16;

/*
WORKING

**Problem Description:**

Developers at Amazon are working on a text generation utility for one of their new products. The utility generates only special strings. A string is special if there are no matching adjacent characters.

Given a string `s` of length `n`, generate a special string of length `n` that is lexicographically greater than `s`. If multiple such special strings are possible, return the lexicographically smallest string among them.

### Important Notes:
1. **Special String**: A string is special if there are no two adjacent characters that are the same.
2. **Lexicographical Order**: A string `a` is lexicographically smaller than a string `b` if one of the following holds:
   - `a` is a prefix of `b`, but `a` is not equal to `b`. For example, `"abc"` is smaller than `"abcd"`.
   - In the first position where `a` and `b` differ, the character in `a` comes before the character in `b` in the alphabet. For example, `"abc"` is smaller than `"abd"` because `c` comes before `d`.

**Constraints:**
- \( 1 \leq |s| \leq 10^6 \)
- The string `s` consists of lowercase English letters only.

**Function Signature:**

```java
public static String getSpecialString(String s) {
  // Write your code here
}
```

### Additional Considerations:
- If the character is `'z'`, it is the last character in the alphabet and cannot be increased further. The string should not wrap around to `'a'` after `'z'`.
- The output string must not have any adjacent characters that are the same.

### Sample Case 0:
**Input:**
```
abccde
```
**Output:**
```
abcdab
```
**Explanation:**
Some of the special strings that are lexicographically greater than `s` are `"abcdde"`, `"abcdab"`, `"abcdbc"`.

---

**Sample Case 1:**

**Input:**
```
zzab
```

**Output:**
```
-1
```

**Explanation:**
There is no special string of length 4 that is lexicographically greater than `s`.

 */
public class SpecialStringGeneratorWorking {
    public static String getSpecialString(String s) {
        int n = s.length();
        char[] output = s.toCharArray();

        // Compute specialUpTo: the largest index where the prefix up to that index is special
        int specialUpTo = 0;
        for (int i = 1; i < n; i++) {
            if (output[i] != output[i - 1] && specialUpTo == i - 1) {
                specialUpTo = i;
            }
        }

        // Iterate from the end to find the position to modify
        for (int i = n - 1; i >= 0; i--) {
            // Check if the prefix up to i-1 is special
            if (i == 0 || specialUpTo >= i - 1) {
                char prevChar = (i > 0) ? output[i - 1] : '\0';
                // Try to find the next valid character for position i
                for (char c = (char) (output[i] + 1); c <= 'z'; c++) {
                    if (c != prevChar) {
                        output[i] = c;
                        // Set the rest of the string to the smallest possible characters
                        for (int j = i + 1; j < n; j++) {
                            char nextChar = 'a';
                            if (j > 0 && nextChar == output[j - 1]) {
                                nextChar = 'b';
                            }
                            output[j] = nextChar;
                        }
                        return new String(output);
                    }
                }
            }
        }

        // If no valid string is found, return -1
        return "-1";
    }

    // For testing purposes
    public static void main(String[] args) {
        System.out.println(getSpecialString("abccde")); // Expected: abcdab
        System.out.println(getSpecialString("zzab"));   // Expected: -1
        System.out.println(getSpecialString("aab"));    // Expected: aba
        System.out.println(getSpecialString("aba"));    // Expected: abc
        System.out.println(getSpecialString("aazz"));   // Expected: abab
        System.out.println(getSpecialString("a"));      // Expected: b
        System.out.println(getSpecialString("z"));      // Expected: -1
    }
}
