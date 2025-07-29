package com.interview.notes.code.year.y2025.july.codesignal.test3;

import java.util.*;
import java.util.stream.*;
/*
Here is the complete and properly formatted version of the problem statement based on the images you provided:

---

### 📘 Problem Statement

You are developing a feature for a linguistics research tool aimed at analyzing vowel patterns within words. Linguists are particularly interested in sequences of vowels ("a", "e", "i", "o", or "u") within the text.

Given a string `text` consisting of lowercase English letters, **count the number of substrings of length 3** that contain **exactly two vowels** (not necessarily different ones).

> ⏱ **Note**: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than **O(text.length)** will fit within the execution time limit.

---

### 🧪 Input/Output

* **\[execution time limit]** 3 seconds (Java)
* **\[memory limit]** 1 GB

#### 🔡 Input

* `String text`: A string consisting of lowercase English letters.
  **Guaranteed constraints:**
  `0 ≤ text.length ≤ 1000`

#### 🔢 Output

* `int`: The number of substrings of length 3 in the given string that contain **exactly two vowels**.

---

### 📌 Examples

#### Example 1:

**Input:** `"aeiobe"`
**Output:** `2`
**Explanation:**

* `"iob"` → vowels: `i`, `o` ✅
* `"obe"` → vowels: `o`, `e` ✅

#### Example 2:

**Input:** `""`
**Output:** `0`
**Explanation:** Empty string has no substrings of length 3.

#### Example 3:

**Input:** `"banana"`
**Output:** `2`
**Explanation:**

* `"ana"` → vowels: `a`, `a` ✅
* `"ana"` → vowels: `a`, `a` ✅

---

### 🧠 Method Signature

```java
int solution(String text)
```

Would you like the full Java 8 implementation with a `main` method for testing?

 */
public class VowelSubstringCounter {

    // solution method: counts substrings of length 3 with exactly 2 vowels
    public static int solution(String text) {
        if (text == null || text.length() < 3) {
            return 0;
        }
        return (int) IntStream
            .range(0, text.length() - 2)
            .mapToObj(i -> text.substring(i, i + 3))
            .filter(s -> countVowels(s) == 2)
            .count();
    }

    // helper: count vowels in a 3-char string
    private static long countVowels(String s) {
        return s.chars()
                .filter(c -> "aeiou".indexOf(c) >= 0)
                .count();
    }

    // simple Test holder
    private static class Test {
        String input;
        int expected;
        Test(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }

    // main method: runs tests and reports PASS/FAIL
    public static void main(String[] args) {
        List<Test> tests = Arrays.asList(
            new Test("aeiobe", 2),    // "iob", "obe"
            new Test("", 0),          // too short
            new Test("banana", 2),    // two "ana"s
            new Test("abecidofu", 4)  // "abe","bec","eci","ido" etc.
        );

        for (Test t : tests) {
            int result = solution(t.input);
            if (result == t.expected) {
                System.out.println("PASS: \"" + t.input + "\" -> " + result);
            } else {
                System.out.println("FAIL: \"" + t.input + "\" -> expected "
                                   + t.expected + ", got " + result);
            }
        }

        // large-data test: 100,000 consonants → expect 0
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            sb.append('b');
        }
        String large = sb.toString();
        long start = System.currentTimeMillis();
        int largeResult = solution(large);
        long duration = System.currentTimeMillis() - start;
        System.out.println("Large test (100k): result=" + largeResult
                           + " time=" + duration + "ms");
    }
}