package com.interview.notes.code.year.y2025.march.caspex.test13;

import java.util.*;

/*
WORKING 100%

### **The Only Way is Up**

A sequence, `ar[]`, is called a *rising sequence* if for each `i > 0`,
`ar[i] > ar[i-1]`

Given a sequence and a positive integer **B**, you need to convert the sequence into a rising sequence by adding an integer **B** to one or all of its elements, any number of times.
Your task is to find out the total number of times you need to add **B** to make this a rising sequence.

---

### **Input**
- The first line of input contains the integer **B**.
- The second line of input contains an integer **N**, representing the size of the array `ar[]`.
- The third line of input contains **N** space-separated integers representing the elements of the array.

---

### **Output**
Print the total number of times you add **B** to the elements to make `ar[]` a rising sequence.

---

### **Constraints**
- 2 ≤ N ≤ 2000
- 1 ≤ B ≤ 10⁶
- 1 ≤ arᵢ ≤ 10⁶

---

### **Time Complexity**
Your solution needs to be optimal because a brute force method of O(N²) may not complete the running of the large test cases.

---

### **Function Signature**
```java
public static int solve(int B, List<Integer> ar);
```

---

### **Example #1**
**Input:**
```
2
4
1 3 3 2
```

**Output:**
```
3
```

---

### **Example #2**
**Input:**
```
1
2
1 1
```

**Output:**
```
1
```

---
 */
public class RisingSequenceSolver {

    // Function to solve the problem
    public static int solve(int B, List<Integer> ar) {
        int count = 0;
        // Start with the first element as the previous value
        int prev = ar.get(0);
        for (int i = 1; i < ar.size(); i++) {
            int curr = ar.get(i);
            // If current element is not greater than previous, add B until it is
            if (curr <= prev) {
                // Compute minimum increments needed so that curr + k*B > prev
                int diff = prev + 1 - curr;
                int k = (diff + B - 1) / B; // Ceiling division
                count += k;
                curr += k * B;
            }
            // Update prev to the new (or unchanged) current value
            prev = curr;
        }
        return count;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // List of test cases: each test case is a map with keys "B", "ar", and "expected"
        List<Map<String, Object>> tests = new ArrayList<>();

        // Example Test Case #1
        tests.add(new HashMap<String, Object>() {{
            put("B", 2);
            put("ar", Arrays.asList(1, 3, 3, 2));
            put("expected", 3);
        }});

        // Example Test Case #2
        tests.add(new HashMap<String, Object>() {{
            put("B", 1);
            put("ar", Arrays.asList(1, 1));
            put("expected", 1);
        }});

        // Additional test: already rising sequence
        tests.add(new HashMap<String, Object>() {{
            put("B", 5);
            put("ar", Arrays.asList(2, 7, 12, 20));
            put("expected", 0);
        }});

        // Edge case: single element (already rising)
        tests.add(new HashMap<String, Object>() {{
            put("B", 10);
            put("ar", List.of(100));
            put("expected", 0);
        }});

        // Large data test: Increasing but with duplicates that require adjustment
        int n = 2000;
        int B = 3;
        List<Integer> largeList = new ArrayList<>();
        // Create a list with many duplicates
        for (int i = 0; i < n; i++) {
            largeList.add(1000);
        }
        // Expected: For first element no change, then for each subsequent element,
        // we need to add enough B's to be greater than previous element.
        // The difference between consecutive numbers must be at least 1.
        // For each duplicate starting from index 1, the needed increment is 1,
        // so required k = (1 + 3 - 1) / 3 = 1 each time.
        int expectedLarge = n - 1;
        tests.add(new HashMap<String, Object>() {{
            put("B", B);
            put("ar", largeList);
            put("expected", expectedLarge);
        }});

        // Run tests
        boolean allPassed = true;
        int testNum = 1;
        for (Map<String, Object> test : tests) {
            int testB = (int) test.get("B");
            @SuppressWarnings("unchecked")
            List<Integer> testAr = (List<Integer>) test.get("ar");
            int expected = (int) test.get("expected");
            int result = solve(testB, testAr);
            boolean passed = (result == expected);
            System.out.println("Test Case " + testNum + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Expected: " + expected + ", Got: " + result);
                allPassed = false;
            }
            testNum++;
        }
        System.out.println("Overall: " + (allPassed ? "ALL TESTS PASSED" : "SOME TESTS FAILED"));
    }
}
