package com.interview.notes.code.year.y2025.jan24.amazon.test7;

import java.util.*;
/*
WORKING 13/15


### Problem: Optimizing the Queue Algorithm

The developers at Amazon SQS are working on optimizing the message queue algorithm. There are **n** events to be sent through a queue, and the size of the \( i^{th} \) event payload is denoted by **payload[i]**.

The queue performs more efficiently if a subset of the events can be selected and rearranged into a new array called **optimizedPayload**, which satisfies the following conditions, regardless of the original order of the elements:

1. The first part of **optimizedPayload** forms an increasing sequence:
   \[
   optimizedPayload[1] < optimizedPayload[2] < \dots < optimizedPayload[i]
   \]
   (Increasing order from the start to \( i \)).

2. The second part of **optimizedPayload** forms a decreasing sequence:
   \[
   optimizedPayload[i+1] > optimizedPayload[i+2] > \dots > optimizedPayload[j]
   \]
   (Decreasing order from \( i+1 \) to \( j \)).

3. The third part of **optimizedPayload** forms an increasing sequence:
   \[
   optimizedPayload[j+1] < optimizedPayload[j+2] < \dots < optimizedPayload[n]
   \]
   (Increasing order from \( j+1 \) to the end).

The order of elements in **optimizedPayload** can be rearranged to meet these conditions, meaning the original order of the **payload** array does not matter when forming the subset.

---

### Task

Determine the maximum number of events that can be selected and rearranged to form **optimizedPayload** that satisfies the increasing-decreasing-increasing configuration.

Given **n** events and an array **payload**, find the maximum number of events that can be selected to form the **optimizedPayload** array that meets these conditions.

---

### Function Description

Complete the function **`getMaximumEvents`** in the editor below.

#### Parameters:
- **`payload`**: A list of integers representing the sizes of the event payloads.

#### Returns:
- An integer: The maximum number of events that can be selected for the queue.

---

### Constraints
- \( 2 \leq n \leq 2 \cdot 10^5 \)
- \( 1 \leq payload[i] \leq 10^9 \)
- The array **payload** contains at least **2 distinct elements**.

---

### Input Format
1. The first line contains an integer **n**, the number of events.
2. Each of the next **n** lines contains an integer, the size of \( payload[i] \).

---

### Sample Cases

#### Sample Case 1

**Input**
```
2
1
100
```

**Output**
```
2
```

**Explanation:**
Consider the subset **optimizedPayload = [100, 1]**. This satisfies the conditions as follows:
1. Increasing part (1 to \( i \)): \([100]\), with \( i = 1 \).
2. Decreasing part (\( i \) to \( j \)): \([1]\), with \( j = 2 \).
3. Increasing part (\( j \) to end): \([1]\).

All conditions are met, and the maximum number of events selected is 2. Hence, the answer is **2**.

---

#### Sample Case 0

**Input**
```
7
5
5
2
1
3
4
5
```

**Output**
```
6
```

**Explanation:**
Consider the subset **optimizedPayload = [1, 3, 5, 4, 2, 5]**. This satisfies the conditions as follows:
1. Increasing part (1 to \( i \)): \([1, 3, 5]\), with \( i = 3 \).
2. Decreasing part (\( i \) to \( j \)): \([5, 4, 2]\), with \( j = 5 \).
3. Increasing part (\( j \) to end): \([2, 5]\).

All conditions are met, and the maximum number of events selected is 6. Hence, the answer is **6**.

---

### Example

**Input**
```
9
1 3 5 4 2 6 8 7 9
```

**Output**
```
9
```

**Explanation:**
Consider the subset **optimizedPayload = [1, 3, 5, 4, 2, 6, 7, 8, 9]**. This satisfies the conditions:
1. Increasing part: \([1, 3, 5]\).
2. Decreasing part: \([5, 4, 2]\).
3. Increasing part: \([2, 6, 7, 8, 9]\).

The maximum number of events selected is **9**.

---

### Function Template

```java
public static int getMaximumEvents(List<Integer> payload) {
    // Write your code here
}
```

 */
public class OptimizedQueueSolutionFixed {
    
    public static int getMaximumEvents(List<Integer> payload) {
        // 1) Count frequency of each distinct value
        Map<Integer, Integer> freq = new HashMap<>();
        for (int val : payload) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }
        
        // 2) Sort the distinct values
        List<Integer> distinct = new ArrayList<>(freq.keySet());
        Collections.sort(distinct);
        
        // 3) Build three ascending lists (tentative):
        //    inc1, decAsc, inc2
        List<Integer> inc1 = new ArrayList<>();
        List<Integer> decAsc = new ArrayList<>();
        List<Integer> inc2 = new ArrayList<>();
        
        for (int x : distinct) {
            int count = freq.get(x);
            // inc1: use up to 1 copy if available
            if (count > 0) {
                inc1.add(x);
                count--;
            }
            // decAsc: use up to 1 copy if still available
            if (count > 0) {
                decAsc.add(x);
                count--;
            }
            // inc2: use up to 1 copy if still available
            if (count > 0) {
                inc2.add(x);
                count--;
            }
        }
        
        // 4) Remove repeated boundary values in a loop:
        //    (A) inc1.last vs. decAsc.last (which becomes the first element of the reversed middle)
        while (!inc1.isEmpty() && !decAsc.isEmpty() &&
               inc1.get(inc1.size() - 1).equals(decAsc.get(decAsc.size() - 1))) {
            decAsc.remove(decAsc.size() - 1);
        }
        
        //    (B) decAsc.first vs. inc2.first (which becomes the last element of the reversed middle)
        while (!decAsc.isEmpty() && !inc2.isEmpty() &&
               decAsc.get(0).equals(inc2.get(0))) {
            inc2.remove(0);
        }
        
        // 5) Reverse the middle to get a strictly-decreasing sub-array in final arrangement
        Collections.reverse(decAsc);
        
        // 6) The total size is how many events we can use
        return inc1.size() + decAsc.size() + inc2.size();
    }
    
    // ---------------------------------------------------
    // Simple main() to demonstrate usage & run a few tests
    // ---------------------------------------------------
    public static void main(String[] args) {
        boolean allPassed = true;
        
        // A helper for quick pass/fail
        allPassed &= testPayload(
            "[2,1,3,3,1,2,1,2,3]",
            Arrays.asList(2,1,3,3,1,2,1,2,3),
            7
        );
        
        // Previous official small samples
        allPassed &= testPayload(
            "[1,100]",
            Arrays.asList(1, 100),
            2
        );
        
        allPassed &= testPayload(
            "[5,5,2,1,3,4,5]",
            Arrays.asList(5,5,2,1,3,4,5),
            6
        );
        
        allPassed &= testPayload(
            "[1,3,5,4,2,6,8,7,9]",
            Arrays.asList(1,3,5,4,2,6,8,7,9),
            9
        );
        
        // The bigger test that was failing
        // (No exact known "expected" given in your message, but let's see if it runs cleanly)
        List<Integer> big = Arrays.asList(
            1074, 1025, 1044, 1071, 1080, 1046, 1028, 1096, 1001, 1074,
            1024, 1081, 1083, 1016, 1055, 1031, 1001, 1027, 1036, 1056,
            1038, 1017, 1010, 1078, 1005, 1039, 1067, 1067, 1015, 1039,
            1062, 1092, 1048, 1090, 1009, 1054, 1067, 1030, 1079, 1056,
            1017, 1033, 1027, 1075, 1054, 1020, 1079, 1021, 1044, 1010,
            1066, 1066, 1073, 1090, 1003, 1034, 1033, 1064, 1079, 1020,
            1094, 1000, 1051, 1024, 1030, 1001, 1052, 1095, 1021, 1088,
            1098, 1006, 1065, 1031, 1001, 1067, 1032, 1074, 1091, 1083,
            1009, 1093, 1027, 1053, 1011, 1008, 1079, 1042, 1020, 1050,
            1091, 1019, 1096, 1006, 1024, 1066, 1016, 1037, 1099
        );
        int bigResult = getMaximumEvents(big);
        System.out.println("Big array test => got " + bigResult + " (no fixed expected given)");
        
        System.out.println("--------------------------------");
        System.out.println(allPassed ? "ALL SMALL TESTS PASSED" : "SOME TEST(S) FAILED");
    }
    
    // Quick test harness
    private static boolean testPayload(String name, List<Integer> input, int expected) {
        int got = getMaximumEvents(input);
        boolean pass = (got == expected);
        System.out.println(name + " => expected: " + expected + ", got: " + got 
                           + " => " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
}
