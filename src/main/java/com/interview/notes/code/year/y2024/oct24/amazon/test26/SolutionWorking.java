package com.interview.notes.code.year.y2024.oct24.amazon.test26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

FINAL WORKING

### Problem Description

Amazon Books is a retail store that sells the newly launched novel "The Story of Amazon". The novel is divided into `n` volumes numbered from 1 to `n` and unfortunately, all the volumes are currently out of stock.

The Amazon team announced that starting today, they will bring exactly one volume of "The Story of Amazon" in stock for each of the next `n` days. On the `n`th day, all volumes will be in stock. Being an impatient bookworm, each day you will purchase the maximum number of volumes you can such that:

1. You have not purchased the volume before.
2. You already own all prequels (prior volumes).

**Note:** For the `i`th volume of the novel, all the volumes `j` such that `j < i` are its prequels.

Determine the volumes you would purchase each day. You should return an array of `n` arrays where the `i`th array contains:
- The volume numbers sorted in increasing order if you purchased some volumes on the `i`th day.
- The single element `-1` if you did not purchase any book.

---

### Example

Given `volumes = [2, 1, 4, 3]`

Initially, all volumes are out of stock:

1. **On the first day**, Volume No. 2 becomes available. Since you do not own its prequel (Volume 1) yet, you will not purchase it. The answer for the first day is `[-1]`.
2. **On the second day**, Volume No. 1 becomes available. Now, you can purchase both volumes 1 and 2 together. The answer for the second day is `[1, 2]`.
3. **On the third day**, Volume No. 4 becomes available. Since you do not have one of its prequels (Volume 3) yet, you will not buy Volume 4. The answer for the third day is `[-1]`.
4. **On the fourth day**, Volume No. 3 becomes available. Now, you can purchase both volumes 3 and 4. The answer for the fourth day is `[3, 4]`.

The final answer is `[-1], [1, 2], [-1], [3, 4]`.

---

### Constraints

- `1 ≤ n ≤ 10^5`
- `1 ≤ volumes[i] ≤ n`
- Elements in `volumes` are distinct.

---

### Input Format for Custom Testing

The first line contains an integer, `n`, the number of volumes.
Each line `i` of the `n` subsequent lines (where `0 ≤ i < n`) contains an integer, `volumes[i]`.

---

### Sample Cases

#### Sample Case 0

**Input:**

```
5
1
4
3
2
5
```

**Output:**

```
1
-1
-1
2 3 4
5
```

**Explanation:**

- **Day 1**, Volume 1 is released. Purchase volume 1.
- **Day 2**, Volume 4 is released. No volumes are purchased since you do not own volumes 2 and 3. The answer for the second day is `[-1]`.
- **Day 3**, Volume 3 is released. No volumes are purchased since you do not own volume 2. The answer for the third day is `[-1]`.
- **Day 4**, Volume 2 is released. Now, you can purchase all three volumes 2, 3, and 4 together. The answer for the fourth day is `[2, 3, 4]`.
- **Day 5**, Volume 5 is released. Purchase volume 5. The answer for the fifth day is `[5]`.

#### Sample Case 1

**Input:**

```
3
1
2
3
```

**Output:**

```
1
2
3
```

**Explanation:**

- **Day 1**, Volume 1 is released. Purchase volume 1.
- **Day 2**, Volume 2 is released. Purchase volume 2.
- **Day 3**, Volume 3 is released. Purchase volume 3.

---

### Function Template

```java
/*
 * Complete the 'buyVolumes' function below.
 *
 * The function is expected to return a 2D_INTEGER_ARRAY.
 * The function accepts INTEGER_ARRAY volumes as parameter.

public static List<List<Integer>> buyVolumes(List<Integer> volumes) {
    // Write your code here
}
```
 */
public class SolutionWorking {
    /*
     * Complete the 'buyVolumes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY volumes as parameter.
     */
    public static List<List<Integer>> buyVolumes(List<Integer> volumes) {
        int n = volumes.size();
        // Initialize an array to mark available volumes
        boolean[] available = new boolean[n + 2]; // extra space to avoid index issues
        // Initialize the result list
        List<List<Integer>> result = new ArrayList<>(n);
        // Initialize the next volume to buy
        int next_to_buy = 1;
        // Iterate over each day
        for (int i = 0; i < n; i++) {
            int vol = volumes.get(i);
            available[vol] = true;
            List<Integer> todayPurchase = new ArrayList<>();
            // Buy as many as possible starting from next_to_buy
            while (next_to_buy <= n && available[next_to_buy]) {
                todayPurchase.add(next_to_buy);
                next_to_buy++;
            }
            if (todayPurchase.isEmpty()) {
                todayPurchase.add(-1);
            }
            result.add(todayPurchase);
        }
        return result;
    }

    // Helper method to compare two List<List<Integer>>
    private static boolean compareResults(List<List<Integer>> actual, List<List<Integer>> expected) {
        if (actual.size() != expected.size()) {
            return false;
        }
        for (int i = 0; i < actual.size(); i++) {
            List<Integer> act = actual.get(i);
            List<Integer> exp = expected.get(i);
            if (act.size() != exp.size()) {
                return false;
            }
            for (int j = 0; j < act.size(); j++) {
                if (!act.get(j).equals(exp.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper method to print the list of lists
    private static void printResult(List<List<Integer>> result) {
        for (List<Integer> day : result) {
            for (int i = 0; i < day.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(day.get(i));
            }
            System.out.println();
        }
    }

    // Method to run all test cases
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Sample Case 0
        testCases.add(new TestCase(
                Arrays.asList(1, 4, 3, 2, 5),
                Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(-1),
                        Arrays.asList(-1),
                        Arrays.asList(2, 3, 4),
                        Arrays.asList(5)
                )
        ));

        // Test Case 2: Sample Case 1
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3),
                Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(2),
                        Arrays.asList(3)
                )
        ));

        // Test Case 3: All volumes available on first day
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(2),
                        Arrays.asList(3),
                        Arrays.asList(4),
                        Arrays.asList(5)
                )
        ));

        // Test Case 4: Volumes released in reverse order
        testCases.add(new TestCase(
                Arrays.asList(5, 4, 3, 2, 1),
                Arrays.asList(
                        Arrays.asList(-1),
                        Arrays.asList(-1),
                        Arrays.asList(-1),
                        Arrays.asList(-1),
                        Arrays.asList(1, 2, 3, 4, 5)
                )
        ));

        // Test Case 5: Single volume
        testCases.add(new TestCase(
                Arrays.asList(1),
                Arrays.asList(
                        Arrays.asList(1)
                )
        ));

        // Test Case 6: Alternate availability
        testCases.add(new TestCase(
                Arrays.asList(2, 1, 4, 5, 3),
                Arrays.asList(
                        Arrays.asList(-1),
                        Arrays.asList(1, 2),
                        Arrays.asList(-1),
                        Arrays.asList(-1),
                        Arrays.asList(3, 4, 5)
                )
        ));

        // Test Case 7: Large Test Case (n=100000)
        // To prevent excessive output, we won't define expected output here.
        // Instead, we'll ensure that the function runs without error.
        testCases.add(new TestCase(
                generateLargeTestCase(100000),
                null // We won't check expected output for large test case
        ));

        // Run test cases
        int passed = 0;
        int total = testCases.size();
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<List<Integer>> actual = buyVolumes(tc.input);
            boolean isPass;
            if (tc.expected == null) {
                // For large test case, just check if the size matches
                isPass = actual.size() == tc.input.size();
            } else {
                isPass = compareResults(actual, tc.expected);
            }
            if (isPass) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected:");
                if (tc.expected != null) {
                    printResult(tc.expected);
                } else {
                    System.out.println("Large Test Case - Expected Output Not Defined");
                }
                System.out.println("Actual:");
                printResult(actual);
            }
        }
        System.out.println("Passed " + passed + " out of " + total + " test cases.");
    }

    // Helper method to generate a large test case where volumes are in order
    private static List<Integer> generateLargeTestCase(int n) {
        List<Integer> largeTest = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            largeTest.add(i);
        }
        return largeTest;
    }

    // Inner class to represent a test case
    private static class TestCase {
        List<Integer> input;
        List<List<Integer>> expected;

        TestCase(List<Integer> input, List<List<Integer>> expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
