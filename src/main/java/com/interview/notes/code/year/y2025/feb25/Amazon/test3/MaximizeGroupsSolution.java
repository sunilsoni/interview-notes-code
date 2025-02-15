package com.interview.notes.code.year.y2025.feb25.Amazon.test3;

import java.util.Arrays;
import java.util.List;

/*
12/15 Pass


### **Code Question 2**
In Amazon’s massive warehouse inventory, there are \( n \) different types of products.
You are given an array **products** of size \( n \), where `products[i]` represents the number of items of product type \( i \) (where \( i \) ranges from \( 0 \) to \( n-1 \)).
These products need to be packed into batches for shipping.

The batch packing must adhere to the following conditions:
1. No two items in the same batch can be of the same product type, meaning all items in a batch must be of distinct types.
2. The number of items packed in the current batch must be strictly greater than the number packed in the previous batch.

Additionally, note that:
- Each item can be packed only once.
- It is not required to pack every item.

#### **Determine the maximum number of batches that can be prepared for shipment.**

**Note:**
The product types are numbered from \( 0 \) to \( n-1 \).
Thus, the number of items of product type \( i \) \((0 \leq i \leq n-1)\) is given by `products[i]`.

---

### **Example**
#### **Input**
```
n = 5
products = [2, 3, 1, 4, 2]
```

#### **Optimal way to prepare the batches:**
1. The first batch contains **1** item of product type **4**. Remaining items: `[2, 3, 1, 4, 1]`
2. The second batch contains **2** items of product types: **0** and **1**. Remaining items: `[1, 2, 1, 4, 1]`
3. The third batch contains **3** items of product types: **0, 1, and 3**. Remaining items: `[0, 1, 1, 3, 1]`
4. The fourth batch contains **4** items of product types: **1, 2, 3, and 4**. Remaining items: `[0, 0, 0, 2, 0]`

Even though 2 items remain, a batch requires 5 items of different product types.
Therefore, **only 4 batches** can be prepared, which is the maximum possible.

#### **Output**
```
4
```

---

### **Function Description**
Complete the function **maximizeGroups** in the editor below.

#### **Function Signature**
```java
public static int maximizeGroups(List<Integer> products)
```

#### **Parameters**
- `List<Integer> products`: The number of items of each product type.

#### **Returns**
- `int`: The maximum number of batches that can be prepared for shipment.

---

### **Constraints**
- \( 1 \leq n \leq 10^5 \)
- \( 0 \leq products[i] \leq 10^9 \)

---

### **Input Format For Custom Testing**
- The first line contains an integer, \( n \), denoting the size of the array **products**.
- Each of the next \( n \) lines contains an integer describing **products[i]**.

---

### **Sample Cases**

#### **Sample Case 0**
##### **Input**
```
3
1
2
7
```
##### **Output**
```
3
```
##### **Explanation**
The optimal way to prepare the batches is:
1. First batch: **1** item of product type **2**, remaining: `[1, 2, 6]`
2. Second batch: **2** items of product types **1 and 2**, remaining: `[1, 1, 5]`
3. Third batch: **3** items of product types **0, 1, and 2**, remaining: `[0, 0, 4]`

Four items are required for the next batch, but only three remain, all of the same type.
Thus, the answer is **3**.

---

#### **Sample Case 1**
##### **Input**
```
4
1
2
8
9
```
##### **Output**
```
4
```
##### **Explanation**
The optimal way to prepare the batches is:
1. First batch: **1** item of product type **3**, remaining: `[1, 2, 8]`
2. Second batch: **2** items of product types **2 and 3**, remaining: `[1, 2, 7]`
3. Third batch: **3** items of product types **1, 2, and 3**, remaining: `[1, 1, 6]`
4. Fourth batch: **4** items of product types **0, 1, 2, and 3**, remaining: `[0, 0, 5]`

The next batch requires 5 distinct types, but only 2 distinct types are left.
Thus, the answer is **4**.

---

### **Function Implementation Template**
```java
/*
 * Complete the 'maximizeGroups' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY products as parameter.


public static int maximizeGroups(List<Integer> products) {
    // Write your code here
}
```
 */
public class MaximizeGroupsSolution {

    /*
     * The function to return the maximum number of batches
     * subject to:
     *   - The i-th batch requires i distinct product types (each 1 item).
     *   - We have 'n' product types in 'products', each with some count.
     */
    public static int maximizeGroups(List<Integer> products) {
        long sum = 0;
        int n = products.size();
        for (int p : products) {
            sum += p;
        }

        // 1) Based on total items, x(x+1)/2 <= sum => x up to about sqrt(2*sum).
        // We'll add 2 for a small buffer
        long maxFromSum = (long) Math.floor(Math.sqrt(2.0 * sum)) + 2;

        // 2) But x cannot exceed the distinct product count 'n'
        long upperBound = Math.min(n, maxFromSum);

        // Convert to long[] for convenience
        long[] counts = new long[n];
        for (int i = 0; i < n; i++) {
            counts[i] = products.get(i);
        }

        // 3) Binary search in [0 .. upperBound]
        long left = 0, right = upperBound, answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (canFormXbatches(counts, mid)) {
                answer = mid;    // mid is feasible
                left = mid + 1;  // try for a bigger value
            } else {
                right = mid - 1; // go smaller
            }
        }

        return (int) answer;  // cast down to int is safe here
    }

    /*
     * Checks if we can form 'x' batches.
     * Condition: sum( min(counts[i], x) ) >= x*(x+1)/2
     */
    private static boolean canFormXbatches(long[] counts, long x) {
        if (x == 0) return true; // 0 batches is trivially possible
        long needed = x * (x + 1) / 2;
        long sumMins = 0;
        for (long c : counts) {
            // each type can be used up to 'x' times across x batches (once per batch)
            sumMins += Math.min(c, x);
            if (sumMins >= needed) {
                return true;
            }
        }
        return (sumMins >= needed);
    }

    /*
     * Simple test in main() WITHOUT JUnit.
     * This method runs maximizeGroups on each input and prints PASS/FAIL.
     */
    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 7), 3, "Sample Case 0");   // Expected=3
        test(Arrays.asList(1, 2, 8, 9), 4, "Sample Case 1"); // Expected=4

        // Additional checks

        // 1) All zero => no batches
        test(Arrays.asList(0, 0, 0), 0, "All zero products");

        // 2) Single large product => can only form 1 batch
        //   (batch 2 requires 2 distinct types, but we only have 1 type)
        test(Arrays.asList(10_000_000), 1, "One large type");

        // 3) Mixed small
        //   [0,1,1,1,1] => at most 2 batches
        //    - 1st batch needs 1 distinct => possible
        //    - 2nd batch needs 2 distinct => possible
        //    - 3rd batch needs 3 distinct => not enough items left distinct
        test(Arrays.asList(0, 1, 1, 1, 1), 2, "One zero, four ones => 2 batches");

        // 4) Same counts => e.g. [5,5,5] => we have 3 types
        //    The 3rd batch needs 3 distinct => possible
        //    The 4th batch needs 4 distinct => not possible since only 3 types
        //    so we expect 3
        test(Arrays.asList(5, 5, 5), 3, "Three product types each count=5");
    }

    // Helper for easy pass/fail testing
    private static void test(List<Integer> input, int expected, String testName) {
        int result = maximizeGroups(input);
        System.out.println("Test: " + testName);
        System.out.println("Products: " + input);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println("-----------------------------------");
    }
}
