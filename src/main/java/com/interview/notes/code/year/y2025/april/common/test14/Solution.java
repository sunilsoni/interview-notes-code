package com.interview.notes.code.year.y2025.april.common.test14;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* WORKING
Here is the combined and properly formatted question with the relevant code stub:

---

### **Problem Statement**

You are given a string `S` consisting of `N` characters.

The string is a **valid phone number** if it consists of **three segments of digits** (each three digits long) **separated by single dashes**.

#### **Write a function:**

```java
class Solution {
    public boolean solution(String S);
}
```

that, **given a string `S` of length `N`**, returns `true` if the string represents a valid phone number and `false` if it doesn't.

---

### **Examples**

1. Input: `S = "123-123-123"` → Output: `true`
2. Input: `S = "123 123 123"` → Output: `false` (segments are separated by spaces)
3. Input: `S = "123-123-1234"` → Output: `false` (too many digits in last segment)
4. Input: `S = "001-501-511"` → Output: `true`
5. Input: `S = "123-abc-123"` → Output: `false` (contains letters)

---

### **Constraints**

- The length of string `S` is within the range `[0..30]`
- String `S` consists only of:
  - lowercase letters (`a–z`)
  - digits (`0–9`)
  - and characters `"-"`, `" "`, `"#"`, `"_"`

---

### **Note**
Focus on **correctness**, not performance.

---

 */
public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        Map<String, Boolean> tests = new LinkedHashMap<>();
        // provided examples
        tests.put("123-123-123", true);
        tests.put("123 123 123", false);
        tests.put("123-123-1234", false);
        tests.put("001-501-511", true);
        tests.put("123-abc-123", false);
        // some extra edge cases
        tests.put("", false);
        tests.put("12-345-678", false);
        tests.put("123-45-678", false);
        tests.put("123-4567-89", false);
        tests.put("123--123-123", false);
        // large input case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) sb.append('1');
        tests.put(sb.toString(), false);

        int n = 1;
        for (Map.Entry<String, Boolean> e : tests.entrySet()) {
            boolean out = sol.solution(e.getKey());
            String res = (out == e.getValue()) ? "PASS" : "FAIL";
            System.out.println("Test " + n++ + ": " + res);
        }
    }

    public boolean solution(String S) {
        if (S == null) return false;
        // split with -1 to catch trailing empty segments
        String[] parts = S.split("-", -1);
        if (parts.length != 3) return false;
        // each part must be exactly 3 digits
        return Arrays.stream(parts)
                .allMatch(p -> p.length() == 3
                        && p.chars().allMatch(Character::isDigit));
    }
}