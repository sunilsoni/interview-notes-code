package com.interview.notes.code.months.dec24.amazon.test11;

import java.util.*;

/*
WORKING


### Function Description
Complete the function **`minDaysToDeliverParcels`** in the editor below.

### Function Signature
```java
public static int minDaysToDeliverParcels(List<Integer> parcels);
```

### Parameters
- **`List<Integer> parcels`**: A list of integers where `parcels[i]` represents the number of parcels to be delivered from the **i-th delivery center**.

### Returns
- **int**: The minimum number of days required to deliver all the parcels.

---

### Constraints
- \( 1 \leq n \leq 10^6 \) (where \( n \) is the size of the list `parcels`)
- \( 0 \leq parcels[i] \leq 10^9 \)

---

### Input Format for Custom Testing
1. The first line contains an integer \( n \), the number of delivery centers.
2. The next \( n \) lines each contain an integer \( parcels[i] \), representing the number of parcels at the **i-th delivery center**.

---

### Example Input and Output

#### **Sample Case 0**

**Input**
```
4
4
2
3
4
```

**Output**
```
3
```

**Explanation**
- **Day 1**: Deliver 2 parcels from each center. Remaining parcels: [2, 0, 1, 2].
- **Day 2**: Deliver 1 parcel from each remaining center. Remaining parcels: [1, 0, 0, 1].
- **Day 3**: Deliver 1 parcel from the remaining centers. Remaining parcels: [0, 0, 0, 0].

A total of **3 days** is required to deliver all parcels.

---

#### **Sample Case 1**

**Input**
```
6
3
3
3
3
3
3
```

**Output**
```
1
```

**Explanation**
- Each delivery center can dispatch all 3 parcels on the first day.

---

### Visual Example

Given `parcels = [2, 3, 4, 3, 3]` (5 delivery centers):
1. **Day 1**: Deliver 2 parcels from each center.
2. **Day 2**: Deliver 1 parcel from remaining centers.
3. **Day 3**: Deliver the last parcel.

The minimum days required = **3**.

---

### Code Structure
You need to implement the function **`minDaysToDeliverParcels`** to determine the minimum number of days needed to deliver all parcels, ensuring an equal number of parcels are delivered from each center with remaining parcels on each day.

 */
public class MinDaysToDeliverParcels {

    // Function to find minimum days
    public static int minDaysToDeliverParcels(List<Integer> parcels) {
        Set<Integer> uniquePositive = new HashSet<>();
        for (int p : parcels) {
            if (p > 0) uniquePositive.add(p);
        }
        return uniquePositive.size();
    }

    // Simple main method for testing
    public static void main(String[] args) {
        // Test cases
        test(new int[]{3, 3, 3, 3, 3, 3}, 1);
        test(new int[]{4, 2, 3, 4}, 3);
        test(new int[]{0, 0, 0}, 0);
        test(new int[]{2, 3, 4, 3, 3}, 3);
        test(new int[]{10}, 1);
        test(new int[]{1, 2, 3, 4, 5}, 5);
    }

    private static void test(int[] input, int expected) {
        List<Integer> parcels = new ArrayList<>();
        for (int i : input) parcels.add(i);
        int result = minDaysToDeliverParcels(parcels);
        System.out.println("Input: " + Arrays.toString(input) +
                " | Expected: " + expected +
                " | Got: " + result +
                " | " + (result == expected ? "PASS" : "FAIL"));
    }
}
