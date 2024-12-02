package com.interview.notes.code.year.y2024.nov24.test18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
WORKING:



### Question: Anagram Difference

An **anagram** is a word whose characters can be rearranged to create another word. Given two strings, determine the **minimum number of characters** in either string that must be modified to make the two strings anagrams. If it is not possible to make the two strings anagrams, return **-1**.

---

#### **Function Description**

You need to implement the function `getMinimumDifference` with the following parameters:

**Parameters:**
- `string a[n]`: an array of strings.
- `string b[n]`: an array of strings.

**Returns:**
- `int[n]`: an array where each element is the minimum number of modifications required to make `a[i]` and `b[i]` anagrams, or `-1` if it's not possible.

---

### **Explanation**

Perform the following calculations for the provided input arrays `a` and `b`:

1. **Input Constraints:**
   - `1 ≤ n ≤ 100`
   - `0 ≤ |a[i]|, |b[i]| ≤ 10⁴`
   - Strings consist of lowercase English characters `[a-z]`.

2. **Steps to Determine the Result:**
   - If the lengths of `a[i]` and `b[i]` are not equal, return `-1` because they cannot be anagrams.
   - If lengths are equal:
     - Count the frequency of each character in both strings.
     - Compute the difference in character frequencies to find the number of modifications required to make the two strings anagrams.

---

#### **Examples**

**Example 1:**

Input:
```
a = ['tea', 'tea', 'act']
b = ['ate', 'toe', 'acts']
```

Output:
```
[0, 1, -1]
```

**Explanation:**
- For `a[0] = tea` and `b[0] = ate`: They are already anagrams, so **0 modifications** are needed.
- For `a[1] = tea` and `b[1] = toe`: Modify **1 character** in either string to make them anagrams.
- For `a[2] = act` and `b[2] = acts`: They have different lengths, so return **-1**.

---

**Example 2 (Custom):**

Input:
```
n = 5
a = ['a', 'jk', 'abb', 'mn', 'abc']
b = ['bb', 'kj', 'bbc', 'op', 'def']
```

Output:
```
[-1, 0, 1, 2, 3]
```

**Explanation:**
1. **Index 0:** `a = 'a'`, `b = 'bb'`: Different lengths → return `-1`.
2. **Index 1:** `a = 'jk'`, `b = 'kj'`: Already anagrams → return `0`.
3. **Index 2:** `a = 'abb'`, `b = 'bbc'`: Modify **1 character** → return `1`.
4. **Index 3:** `a = 'mn'`, `b = 'op'`: Modify **2 characters** → return `2`.
5. **Index 4:** `a = 'abc'`, `b = 'def'`: Modify **3 characters** → return `3`.

---

### **Input Format for Custom Testing**

- The first line contains an integer, `n`, the number of strings in the array `a`.
- The next `n` lines each contain a string describing `a[i]`.
- The next line contains an integer, `n`, the number of strings in the array `b`.
- The next `n` lines each contain a string describing `b[i]`.

---

**Sample Input for Custom Testing:**
```
5
a
jk
abb
mn
abc
5
bb
kj
bbc
op
def
```

**Sample Output:**
```
[-1, 0, 1, 2, 3]
```

 */
public class AnagramDifferenceTester {

    /*
     * Complete the 'getMinimumDifference' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY a
     *  2. STRING_ARRAY b
     */

    public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {
        List<Integer> result = new ArrayList<>();
        int n = a.size();
        for (int i = 0; i < n; i++) {
            String strA = a.get(i);
            String strB = b.get(i);
            if (strA.length() != strB.length()) {
                result.add(-1);
                continue;
            }

            int[] freq = new int[26];

            for (char c : strA.toCharArray()) {
                freq[c - 'a']++;
            }

            for (char c : strB.toCharArray()) {
                freq[c - 'a']--;
            }

            int modifications = 0;
            for (int count : freq) {
                modifications += Math.abs(count);
            }

            // Each modification can fix one excess character in either string
            // Therefore, total modifications required is half the total difference
            modifications /= 2;
            result.add(modifications);
        }
        return result;
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                Arrays.asList("tea", "tea", "act"),
                Arrays.asList("ate", "toe", "acts"),
                Arrays.asList(0, 1, -1)
        ));

        // Example 2 (Custom)
        testCases.add(new TestCase(
                Arrays.asList("a", "jk", "abb", "mn", "abc"),
                Arrays.asList("bb", "kj", "bbc", "op", "def"),
                Arrays.asList(-1, 0, 1, 2, 3)
        ));

        // Edge Case 1: Both strings empty
        testCases.add(new TestCase(
                Arrays.asList(""),
                Arrays.asList(""),
                Arrays.asList(0)
        ));

        // Edge Case 2: One string empty, one non-empty
        testCases.add(new TestCase(
                Arrays.asList(""),
                Arrays.asList("a"),
                Arrays.asList(-1)
        ));

        // Edge Case 3: Strings with all identical characters
        testCases.add(new TestCase(
                Arrays.asList("aaaa", "aaaa"),
                Arrays.asList("aaaa", "aaaa"),
                Arrays.asList(0, 0)
        ));

        // Edge Case 4: Strings with no overlapping characters
        testCases.add(new TestCase(
                Arrays.asList("abc", "def"),
                Arrays.asList("xyz", "uvw"),
                Arrays.asList(3, 3)
        ));

        // Large Input Case: Identical large strings
        String largeA = generateString('a', 10000);
        String largeB = generateString('a', 10000);
        testCases.add(new TestCase(
                Arrays.asList(largeA),
                Arrays.asList(largeB),
                Arrays.asList(0)
        ));

        // Large Input Case with modifications
        String largeC = generateString('a', 9999) + "b";
        String largeD = generateString('a', 10000);
        testCases.add(new TestCase(
                Arrays.asList(largeC),
                Arrays.asList(largeD),
                Arrays.asList(1)
        ));

        // Additional Test Cases

        // Test Case 7: Completely different strings with maximum modifications
        testCases.add(new TestCase(
                Arrays.asList("abcd", "wxyz"),
                Arrays.asList("wxyz", "abcd"),
                Arrays.asList(4, 4)
        ));

        // Test Case 8: Single character strings
        testCases.add(new TestCase(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("a", "a", "c"),
                Arrays.asList(0, 1, 0)
        ));

        // Test Case 9: Mixed case where some strings can and cannot be anagrams
        testCases.add(new TestCase(
                Arrays.asList("listen", "silent", "triangle", "integral", "apple"),
                Arrays.asList("silent", "listen", "integral", "triangle", "papel"),
                Arrays.asList(0, 0, 0, 0, 0)
        ));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> output = getMinimumDifference(tc.a, tc.b);
            if (output.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got     : " + output);
            }
        }
        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    // Helper method to generate a string with a specific character repeated 'count' times
    private static String generateString(char c, int count) {
        char[] chars = new char[count];
        Arrays.fill(chars, c);
        return new String(chars);
    }

    // Inner class to represent a test case
    static class TestCase {
        List<String> a;
        List<String> b;
        List<Integer> expected;

        TestCase(List<String> a, List<String> b, List<Integer> expected) {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }
    }
}
