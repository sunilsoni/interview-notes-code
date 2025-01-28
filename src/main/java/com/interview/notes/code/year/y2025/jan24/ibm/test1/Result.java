package com.interview.notes.code.year.y2025.jan24.ibm.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
WORKING:


### Problem Statement
Students in a class are asked to stand in ascending order according to their heights for the annual class photograph. Determine the number of students not currently standing in their correct positions.

#### Example:
**Input:** `height = [1, 1, 3, 3, 4, 1]`
**Output:** `3`

Explanation:
The 3 students not standing in the correct positions are at indices [2, 4, 5]. The correct positions are `[1, 1, 1, 3, 3, 4]`.

---

#### Function Description:
Complete the function `countStudents` in the editor below.
The function is expected to return an **integer**.
The function accepts an **integer array** `height` as the parameter.

---

#### Constraints:
- \( 1 \leq n \leq 10^5 \)
- \( 1 \leq \text{height}[i] \leq 10^9 \)

---

### Input Format for Custom Testing:
1. The first line contains an integer \( n \), the size of the array `height`.
2. The next \( n \) lines each contain an element \( \text{height}[i] \), where \( 0 \leq i < n \).

---

### Sample Test Cases:

**Sample Input 0:**
```
5
1
1
3
4
1
```
**Sample Output 0:**
```
3
```
Explanation: The 3 students not in the correct positions are at indices [2, 4, 5].

---

**Sample Input 1:**
```
4
1
2
3
4
```
**Sample Output 1:**
```
0
```
Explanation: All students are in the correct positions.

---

**Sample Input 2:**
```
4
4
3
2
1
```
**Sample Output 2:**
```
4
```
Explanation: None of the students are in the correct positions.

 */
public class Result {
    public static void main(String[] args) {
        // Test cases
        testCountStudents();
    }

    public static int countStudents(List<Integer> height) {
        if (height == null || height.size() <= 1) return 0;

        // Create sorted array to compare with original
        List<Integer> sortedHeight = new ArrayList<>(height);
        Collections.sort(sortedHeight);

        // Count mismatches
        int count = 0;
        for (int i = 0; i < height.size(); i++) {
            if (!height.get(i).equals(sortedHeight.get(i))) {
                count++;
            }
        }
        return count;
    }

    // Test method
    private static void testCountStudents() {
        // Test case 1: Sample case [1,1,3,4,1]
        List<Integer> test1 = Arrays.asList(1, 1, 3, 4, 1);
        int result1 = countStudents(test1);
        System.out.println("Test 1: " + (result1 == 3 ? "PASS" : "FAIL") +
                " (Expected: 3, Got: " + result1 + ")");

        // Test case 2: Already sorted [1,2,3,4]
        List<Integer> test2 = Arrays.asList(1, 2, 3, 4);
        int result2 = countStudents(test2);
        System.out.println("Test 2: " + (result2 == 0 ? "PASS" : "FAIL") +
                " (Expected: 0, Got: " + result2 + ")");

        // Test case 3: Reverse sorted [4,3,2,1]
        List<Integer> test3 = Arrays.asList(4, 3, 2, 1);
        int result3 = countStudents(test3);
        System.out.println("Test 3: " + (result3 == 4 ? "PASS" : "FAIL") +
                " (Expected: 4, Got: " + result3 + ")");

        // Test case 4: Single element
        List<Integer> test4 = Arrays.asList(1);
        int result4 = countStudents(test4);
        System.out.println("Test 4: " + (result4 == 0 ? "PASS" : "FAIL") +
                " (Expected: 0, Got: " + result4 + ")");

        // Test case 5: All same elements
        List<Integer> test5 = Arrays.asList(2, 2, 2, 2);
        int result5 = countStudents(test5);
        System.out.println("Test 5: " + (result5 == 0 ? "PASS" : "FAIL") +
                " (Expected: 0, Got: " + result5 + ")");
    }
}
