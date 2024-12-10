package com.interview.notes.code.months.dec24.ibm.test1;

/*
WORKING:



**Problem Description:**
A binary string `S` (containing only `0`s and `1`s) encodes an encryption key represented by the count of valid substrings in `S`.

### **Definition of a Valid Substring**
A substring is valid if it meets the following conditions:
1. **No two adjacent characters are the same.**
2. **Its length is between `minLength` and `maxLength` (inclusive).**

### **Task**
Write a function `countValidSubstrings` that takes the following inputs:
- `String S`: The binary string.
- `int minLength`: The minimum length of a valid substring.
- `int maxLength`: The maximum length of a valid substring.

The function should return a `long` integer representing the number of valid substrings in `S`.

---

### **Constraints**
- \( 1 \leq |S| \leq 200,000 \) (length of `S`).
- \( 1 \leq \text{minLength} \leq \text{maxLength} \leq |S| \).

---

### **Input Format**
1. The first line contains the binary string `S`.
2. The second line contains the integer `minLength`.
3. The third line contains the integer `maxLength`.

---

### **Output Format**
Return the count of valid substrings as a single integer.

---

### **Examples**

#### **Sample Input 1**
```
110
1
3
```

#### **Sample Output 1**
```
4
```

**Explanation:**
The substrings and their validity are:
| Substring | Length | Is Valid? |
|-----------|--------|-----------|
| `"1"`     | 1      | Yes       |
| `"1"`     | 1      | Yes       |
| `"0"`     | 1      | Yes       |
| `"11"`    | 2      | No        |
| `"10"`    | 2      | Yes       |
| `"110"`   | 3      | No        |

Total valid substrings = 4.

---

#### **Sample Input 2**
```
1010
2
2
```

#### **Sample Output 2**
```
3
```

**Explanation:**
The substrings and their validity are:
| Substring | Length | Is Valid? |
|-----------|--------|-----------|
| `"1"`     | 1      | No (Length < minLength) |
| `"0"`     | 1      | No (Length < minLength) |
| `"1"`     | 1      | No (Length < minLength) |
| `"0"`     | 1      | No (Length < minLength) |
| `"10"`    | 2      | Yes                     |
| `"01"`    | 2      | Yes                     |
| `"10"`    | 2      | Yes                     |
| `"101"`   | 3      | No (Length > maxLength) |
| `"010"`   | 3      | No (Length > maxLength) |
| `"1010"`  | 4      | No (Length > maxLength) |

Total valid substrings = 3.

 */
public class CountValidSubstrings {
    // Method to count valid substrings
    public static long countValidSubstrings(String S, int minLength, int maxLength) {
        long totalValid = 0;
        int n = S.length();

        // Identify maximal alternating segments
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || S.charAt(i) == S.charAt(i - 1)) {
                // Segment [start, i-1] is alternating
                int length = i - start;
                // Count valid substrings in this segment
                // sum over d in [minLength, maxLength] of (length - d + 1) if length >= d
                if (length >= minLength) {
                    // If length >= minLength, calculate sum directly
                    int low = minLength;
                    int high = Math.min(maxLength, length);
                    // Number of valid substrings = sum_{d=low to high} (length - d + 1)
                    // = sum_{d=low to high} length + 1 - d
                    // = (high - low + 1)*(length + 1) - (low+high)*((high - low + 1)/2)
                    // But easier is to just loop since maxLength-minLength might be small relative to length.
                    // However, minLength and maxLength could be large, but still O(1) in complexity relative to n.
                    // We'll do a simple arithmetic series calculation:

                    int countOfD = high - low + 1;
                    // sum of (length + 1 - d) for d=low to high
                    // = countOfD*(length + 1) - (low+high)*countOfD/2
                    long sum = countOfD * (long) (length + 1) - (long) (low + high) * countOfD / 2;
                    totalValid += sum;
                }
                start = i; // start new segment
            }
        }

        return totalValid;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Basic Tests (Provided Examples):
        // Example 1:
        String S1 = "110";
        int minL1 = 1;
        int maxL1 = 3;
        // From the provided example, result should be 4
        long result1 = countValidSubstrings(S1, minL1, maxL1);
        System.out.println("Test Case 1: " + (result1 == 4 ? "PASS" : "FAIL") + " (Expected 4, Got " + result1 + ")");

        // Example 2:
        String S2 = "1010";
        int minL2 = 2;
        int maxL2 = 2;
        // Sample given: valid substrings of length 2 are "10"(pos0), "01"(pos1), "10"(pos2) total=3
        long result2 = countValidSubstrings(S2, minL2, maxL2);
        System.out.println("Test Case 2: " + (result2 == 3 ? "PASS" : "FAIL") + " (Expected 3, Got " + result2 + ")");

        // Another example from the description:
        // S="1011", minLength=2, maxLength=3, expected=3 (from the explanation)
        String S3 = "1011";
        int minL3 = 2;
        int maxL3 = 3;
        long result3 = countValidSubstrings(S3, minL3, maxL3);
        System.out.println("Test Case 3: " + (result3 == 3 ? "PASS" : "FAIL") + " (Expected 3, Got " + result3 + ")");

        // Edge Case: Single character S="0", min=1, max=1
        // Valid substrings: just "0" (length=1, alternating trivially)
        String S4 = "0";
        int minL4 = 1;
        int maxL4 = 1;
        long result4 = countValidSubstrings(S4, minL4, maxL4);
        System.out.println("Test Case 4: " + (result4 == 1 ? "PASS" : "FAIL") + " (Expected 1, Got " + result4 + ")");

        // Large Input Test:
        // Construct a large alternating string and test performance
        StringBuilder sb = new StringBuilder();
        int largeSize = 200000; // max constraint
        for (int i = 0; i < largeSize; i++) {
            sb.append(i % 2 == 0 ? '0' : '1');
        }
        String S5 = sb.toString();
        int minL5 = 1;
        int maxL5 = 1000; // arbitrary
        // Just run to check performance and not crash, no exact expected value easily computed here
        long result5 = countValidSubstrings(S5, minL5, maxL5);
        System.out.println("Test Case 5: Large Input test completed. Result=" + result5);

        // Additional Edge: All same character string
        String S6 = "00000";
        int minL6 = 1;
        int maxL6 = 3;
        // No two adjacent different, so no alternating substring longer than length=1
        // Valid substrings are just single chars if minL=1 allowed.
        // Here min=1,max=3, single chars '0' are valid since length=1 and no adjacent violation inside single char.
        // For string of length 5 all same: alternating segments are basically each character?
        // Actually, a single char is an alternating substring of length 1. So we have 5 such substrings of length 1.
        // Length 2 or more is not valid due to repetition.
        long result6 = countValidSubstrings(S6, minL6, maxL6);
        System.out.println("Test Case 6: " + (result6 == 5 ? "PASS" : "FAIL") + " (Expected 5, Got " + result6 + ")");
    }
}
