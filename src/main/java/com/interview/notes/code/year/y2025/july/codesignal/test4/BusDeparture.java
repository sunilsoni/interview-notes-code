package com.interview.notes.code.year.y2025.july.codesignal.test4;

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
public class BusDeparture {

    /** 
     * Returns minutes since the last bus departed before current_time,
     * or –1 if no bus has left yet.
     */
    public static int solution(String[] departure_times, String current_time) {
        int now = toMinutes(current_time);

        // Stream through departures, convert to minutes, keep only those < now,
        // then take the max (most recent). If none, return -1.
        OptionalInt last = Arrays.stream(departure_times)
            .mapToInt(BusDeparture::toMinutes)
            .filter(min -> min < now)
            .max();

        return last.isPresent()
            ? now - last.getAsInt()
            : -1;
    }

    /** Helper to convert "HH:MM" → total minutes since midnight. */
    private static int toMinutes(String hhmm) {
        String[] parts = hhmm.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    /** Simple test holder. */
    private static class Test {
        String[] departures;
        String current;
        int expected;
        Test(String[] d, String c, int e) {
            departures = d;
            current = c;
            expected = e;
        }
    }

    public static void main(String[] args) {
        List<Test> tests = Arrays.asList(
            new Test(new String[]{"12:30","14:00","19:55"}, "14:30", 30),
            new Test(new String[]{"00:00","14:00","19:55"}, "00:00", -1),
            new Test(new String[]{"12:30","14:00","19:55"}, "14:00", 90),
            new Test(new String[]{"08:15","08:45","09:00"}, "08:30", 15),
            new Test(new String[]{"23:00","23:30","23:59"}, "00:10", -1)
        );

        for (Test t : tests) {
            int result = solution(t.departures, t.current);
            if (result == t.expected) {
                System.out.println("PASS: current=" + t.current +
                                   ", got " + result);
            } else {
                System.out.println("FAIL: current=" + t.current +
                                   ", expected " + t.expected +
                                   ", got " + result);
            }
        }
    }
}