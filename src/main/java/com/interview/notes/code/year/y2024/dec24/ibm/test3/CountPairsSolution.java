package com.interview.notes.code.year.y2024.dec24.ibm.test3;

import java.util.*;

/*
WORKING


### **Screenshot 1**: Sample Case 2

#### **Sample Input 2**
```
6       -> projectCosts[] size n = 6
2       -> projectCosts = [2, 4, 6, 8, 10, 12]
4
6
8
10
12
2       -> target = 2
```

#### **Sample Output 2**
```
5
```

#### **Explanation 2**
Count the number of pairs in `projectCosts` whose difference is `target = 2`. The following five pairs meet the criterion: `(2, 4), (4, 6), (6, 8), (8, 10), and (10, 12)`.

---

### **Screenshot 2**: Sample Case 1

#### **Sample Input 1**
```
10      -> projectCosts[] size n = 10
363374326 -> projectCosts = [363374326, 364147530, 61825163, 107306571, 128124602, 139946991, 428047635, 491595254, 879792181, 106926279]
364147530
61825163
107306571
128124602
139946991
428047635
491595254
879792181
106926279
1       -> target = 1
```

#### **Sample Output 1**
```
0
```

#### **Explanation 1**
Count the number of pairs in `projectCosts` whose difference is `target = 1`. Because no such pair of integers exists, return `0`.

---

### **Screenshot 3**: Sample Case 0

#### **Sample Input 0**
```
5       -> projectCosts[] size n = 5
1       -> projectCosts = [1, 5, 3, 4, 2]
5
3
4
2
2       -> target = 2
```

#### **Sample Output 0**
```
3
```

#### **Explanation 0**
Count the number of pairs in `projectCosts` whose difference is `target = 2`. The following three pairs meet the criterion: `(1, 3), (5, 3), and (4, 2)`.

---

### **Screenshot 4**: Question Description

#### **Problem Statement**
A number of bids are received for a project. Determine the number of distinct pairs of project costs where their absolute difference is some target value. Two pairs are distinct if they differ in at least one value.

#### **Example**
```
n = 3
projectCosts = [1, 3, 5]
target = 2
```
Output: `2`

**Function Description**
Complete the function `countPairs` in the editor below.

**Parameters:**
- `int projectCosts[n]`: array of integers
- `int target`: the target difference

**Return:**
- `int`: the number of distinct pairs in `projectCosts` with an absolute difference of `target`.

**Constraints:**
- `5 ≤ n ≤ 10^5`
- `0 < projectCosts[i] ≤ 2 x 10^9`
- Each `projectCosts[i]` is distinct.
- `1 ≤ target ≤ 10^9`

**Input Format for Custom Testing**
1. First line: integer `n`, the size of the array `projectCosts`.
2. Next `n` lines: elements of `projectCosts`.
3. Final line: integer `target`, the target difference.

---

### **Screenshot 5**: Function Template

#### **Code Template**
```java
/*
 * Complete the 'countPairs' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY projectCosts
 *  2. INTEGER target
 */
public class CountPairsSolution {

    /*
     * Complete the 'countPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY projectCosts
     *  2. INTEGER target
     */
    public static int countPairs(List<Integer> projectCosts, int target) {
        if (projectCosts == null || projectCosts.size() < 2) {
            return 0;
        }

        Set<Integer> costSet = new HashSet<>(projectCosts);
        int count = 0;

        for (Integer cost : projectCosts) {
            if (costSet.contains(cost + target)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase(
                Arrays.asList(1, 5, 3, 4, 2),
                2,
                3
        ));

        // Sample Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(363374326, 364147530, 61825163, 107306571, 128124602,
                        139946991, 428047635, 491595254, 879792181, 106926279),
                1,
                0
        ));

        // Sample Test Case 2
        testCases.add(new TestCase(
                Arrays.asList(2, 4, 6, 8, 10, 12),
                2,
                5
        ));

        // Additional Test Case 3: No pairs
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                10,
                0
        ));

        // Additional Test Case 4: All possible pairs
        testCases.add(new TestCase(
                Arrays.asList(1, 3, 5, 7, 9),
                2,
                4
        ));

        // Additional Test Case 5: Large Input
        List<Integer> largeInput = new ArrayList<>();
        int largeN = 100000;
        for (int i = 1; i <= largeN; i++) {
            largeInput.add(i);
        }
        testCases.add(new TestCase(
                largeInput,
                1,
                largeN - 1
        ));

        // Additional Test Case 6: Target equals zero (edge case, although target >=1 as per constraints)
        // Since target >=1, this case should return 0
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                0,
                0
        ));

        // Additional Test Case 7: Minimum input size
        testCases.add(new TestCase(
                Arrays.asList(10, 20, 30, 40, 50),
                10,
                4
        ));

        // Additional Test Case 8: Maximum target difference
        testCases.add(new TestCase(
                Arrays.asList(1, 1000000000, 500000000, 1000000001, 999999999),
                1000000000,
                2
        ));

        // Execute all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = countPairs(tc.projectCosts, tc.target);
            if (result == tc.expected) {
                System.out.println("Test Case " + i + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + i + ": FAIL (Expected " + tc.expected + ", Got " + result + ")");
            }
        }

        System.out.println("\nTotal Passed: " + passed + " out of " + testCases.size());
    }

    // Helper class to represent a test case
    static class TestCase {
        List<Integer> projectCosts;
        int target;
        int expected;

        TestCase(List<Integer> projectCosts, int target, int expected) {
            this.projectCosts = projectCosts;
            this.target = target;
            this.expected = expected;
        }
    }
}
