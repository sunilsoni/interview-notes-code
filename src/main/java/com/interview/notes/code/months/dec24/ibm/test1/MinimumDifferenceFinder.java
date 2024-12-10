package com.interview.notes.code.months.dec24.ibm.test1;

import java.util.*;

/*
WORKING



**Problem Description:**
Given a set of distinct measurements taken at different times, find the minimum possible absolute difference between any two measurements. Print all pairs of measurements that have this minimum difference in ascending order, with the elements of each pair ordered such that \( a < b \). Each pair should be displayed on a new line, separated by a single space.

---

### **Task**
Write a function `minimumDifference` that:
- Finds the minimum absolute difference between any two elements in the array.
- Prints all pairs of elements with this minimum difference, ordered as described above.

---

### **Input Format**
1. The first line contains an integer \( n \): the size of the array `measurements`.
2. The next \( n \) lines each contain an integer \( \text{measurements}[i] \): the values of the measurements.

---

### **Output Format**
Print the distinct pairs of measurements that have the minimum absolute difference, ordered as follows:
- Pairs should be printed in ascending order by the first element of the pair.
- If two pairs have the same first element, order by the second element.
- Each pair should be printed on a new line, separated by a single space.

---

### **Constraints**
- \( 2 \leq n \leq 200,000 \)
- \( -10^9 \leq \text{measurements}[i] \leq 10^9 \)

---

### **Examples**

#### **Sample Input 0**
```
5
6
5
4
3
7
```

#### **Sample Output 0**
```
3 4
4 5
5 6
6 7
```

**Explanation:**
The minimum absolute difference between any two elements is 1. The pairs with this difference are:
- (3, 4)
- (4, 5)
- (5, 6)
- (6, 7)

All pairs are printed in ascending order.

---

#### **Sample Input 1**
```
4
1
3
5
10
```

#### **Sample Output 1**
```
1 3
3 5
```

**Explanation:**
The minimum absolute difference between any two elements is 2. The pairs with this difference are:
- (1, 3)
- (3, 5)

Both pairs are printed in ascending order.

---
 */
public class MinimumDifferenceFinder {
    public static void minimumDifference(List<Integer> measurements) {
        // Convert to array for easier sorting
        int n = measurements.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = measurements.get(i);
        }

        // Sort the array
        Arrays.sort(arr);

        // First pass: find minimum difference
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        // Second pass: print all pairs with minDiff
        for (int i = 0; i < n - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff == minDiff) {
                System.out.println(arr[i] + " " + arr[i + 1]);
            }
        }
    }

    // Simple main method for testing (no JUnit)
    public static void main(String[] args) {
        // Test Case 0 (from sample):
        // Input: [6, 5, 4, 3, 7]
        // Sorted: [3,4,5,6,7]
        // Minimum diff = 1
        // Pairs: (3,4), (4,5), (5,6), (6,7)
        List<Integer> test1 = Arrays.asList(6, 5, 4, 3, 7);
        System.out.println("Test Case 0 Output:");
        minimumDifference(test1);
        // Expected:
        // 3 4
        // 4 5
        // 5 6
        // 6 7

        System.out.println();

        // Test Case 1 (from sample):
        // Input: [1, 3, 5, 10]
        // Sorted: [1,3,5,10]
        // Differences: (3-1)=2, (5-3)=2, (10-5)=5
        // minDiff=2, Pairs: (1,3) and (3,5)
        List<Integer> test2 = Arrays.asList(1, 3, 5, 10);
        System.out.println("Test Case 1 Output:");
        minimumDifference(test2);
        // Expected:
        // 1 3
        // 3 5

        System.out.println();

        // Additional Edge Case:
        // Input: [2,4]
        // Only one pair, difference=2
        // Output: "2 4"
        List<Integer> test3 = Arrays.asList(2, 4);
        System.out.println("Edge Case Output:");
        minimumDifference(test3);
        // Expected:
        // 2 4

        System.out.println();

        // Large Input Test (not printing results here, just ensure performance):
        // We'll generate a large list of distinct integers.
        int largeN = 200000;
        List<Integer> largeInput = new ArrayList<>(largeN);
        Random rand = new Random(0);
        for (int i = 0; i < largeN; i++) {
            largeInput.add(i); // distinct and sorted by construction, but we can shuffle
        }
        Collections.shuffle(largeInput, rand);
        System.out.println("Large Input Test (Performance check):");
        // Running the method to ensure it handles large data efficiently
        minimumDifference(largeInput);
        // This should run efficiently without errors.
    }
}
