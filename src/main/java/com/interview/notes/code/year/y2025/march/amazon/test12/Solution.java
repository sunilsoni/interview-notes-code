package com.interview.notes.code.year.y2025.march.amazon.test12;

import java.util.*;

/*

### ✅ Problem: Get Minimum Lexicographical String of Box IDs

The team at an Amazon warehouse is optimizing the packing of a set of boxes labeled with IDs (digits from 0–9). These boxes are currently in a single row, represented by a string `s_id`.

To make packing more efficient, the team can perform the following operation **any number of times** (including zero):

- **Choose an index `i`**, remove the digit `s_id[i]`, compute `min(s_id[i] + 1, 9)` (i.e., increase by 1 but max 9), and **insert this new digit** anywhere in the string (beginning, middle, or end).

The goal is to **return the lexicographically smallest string possible** after applying the above operation as many times as needed.

---

### 🧪 Example

For `s_id = "26547"`:

Steps:
1. Delete '5' → insert '6' at position 4 → "26467"
2. Delete '6' → insert '7' at position 4 → "24677"

**Result**: `"24677"` is the smallest possible string.

---

### 📥 Input Format

- A single string `s_id` (contains digits 0–9).

---

### 📤 Output Format

- Return a **string** which is the **lexicographically minimal** version of `s_id`.

---

### 🔧 Function Signature

```java
public static String getMinimumString(String s_id)
```

---

### 📌 Constraints

- \(1 \leq |s\_id| \leq 2 \times 10^5\)
- `s_id` contains digits only: `'0'` to `'9'`

---

### 🧪 Sample Test Cases

#### Sample Case 0

**Input:**
```
04829
```

**Output:**
```
02599
```

**Explanation:**
- Move '8' to end as '9' → "04299"
- Move '4' to index 3 as '5' → "02599"

---

#### Sample Case 1

**Input:**
```
34892
```

**Output:**
```
24599
```

**Steps:**
- '3' → remove, insert '4' at end → "48924"
- '4' → '5' at end → "89245"
- '8' → '9' at end → "92459"
- '9' → '9' at end → "24599"

---

 */
public class Solution {

    // Function to get the lexicographically smallest string after operations
    public static String getMinimumString(String s_id) {
        int n = s_id.length();
        // List to collect final digits (each digit as integer)
        ArrayList<Integer> finalDigits = new ArrayList<>();
        // Initialize minSoFar with a character greater than any digit ('9' < ':' in ASCII)
        char minSoFar = ':';
        // Process the string from rightmost digit to leftmost
        for (int i = n - 1; i >= 0; i--) {
            char curr = s_id.charAt(i);
            // If current digit is greater than the minimum seen so far, perform the move operation:
            // Remove it, increase by one (capped at 9), and add to the final list.
            if (curr > minSoFar) {
                int incremented = (curr - '0') + 1;
                if (incremented > 9) {
                    incremented = 9;
                }
                finalDigits.add(incremented);
            } else {
                // Otherwise, keep the digit unchanged and update minSoFar.
                finalDigits.add(curr - '0');
                minSoFar = curr;
            }
        }
        // Since we can insert moved digits anywhere, sort the final digits to get the smallest lexicographical order.
        Collections.sort(finalDigits);
        StringBuilder result = new StringBuilder();
        for (int digit : finalDigits) {
            result.append(digit);
        }
        return result.toString();
    }

    // Main method with test cases (no JUnit, using a simple main method)
    public static void main(String[] args) {
        // Array of test cases: {input, expectedOutput}
        String[][] testCases = {
            {"04829", "02599"},
            {"34892", "24599"},
            {"26547", "24677"},
            {"10", "02"},
            {"321", "134"},
            {"999", "999"},
            {"000", "000"}
        };

        int passCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i][0];
            String expected = testCases[i][1];
            String output = getMinimumString(input);
            if (output.equals(expected)) {
                System.out.println("Test case " + i + " PASSED");
                passCount++;
            } else {
                System.out.println("Test case " + i + " FAILED. Input: " + input + 
                                   " Expected: " + expected + " Got: " + output);
            }
        }
        
        // Testing with large data input (up to 200,000 digits)
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        int len = 200000;
        for (int i = 0; i < len; i++) {
            sb.append(rand.nextInt(10));
        }
        String largeInput = sb.toString();
        long startTime = System.currentTimeMillis();
        String largeOutput = getMinimumString(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large test case processed in " + (endTime - startTime) + " ms.");

        // Summary of test results
        System.out.println("Passed " + passCount + " out of " + testCases.length + " test cases.");
    }
}
