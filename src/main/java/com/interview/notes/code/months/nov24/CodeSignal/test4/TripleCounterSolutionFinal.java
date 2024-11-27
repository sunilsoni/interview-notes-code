package com.interview.notes.code.months.nov24.CodeSignal.test4;

import java.util.*;

/*
WORKING 100/300 SLOW SOLN


### Problem Description

Given an empty array that should contain integers `numbers`, your task is to process a list of queries on it. Specifically, there are two types of queries:
- `"+x"`: Append `x` to `numbers`. `numbers` may contain multiple instances of the same integer.
- `"-x"`: Remove all the instances of `x` from `numbers`.

After processing each query, count the number of triples \((x, y, z)\) in `numbers` which meet this condition: both \(x - y\) and \(y - z\) are equal to a given `diff`. Note that elements in `numbers` can be rearranged to form triples to meet the condition. The final output should be an array of counts after each query in `queries`.

---

### Notes:
- All integers in `queries` are guaranteed to be in the range of \([-10^9, 10^9]\).
- It is also guaranteed that for each `"-x"` query, the specified `x` exists in `numbers`.
- It is guaranteed that the answer for each query fits into a 32-bit integer type.

---

### Example

For:

```java
queries = ["+4", "+5", "+6", "+4", "+3", "-4"];
diff = 1;
```

The output should be:

```java
solution(queries, diff) = [0, 0, 1, 2, 4, 0];
```

**Explanation:**
1. Process `queries[0] = "+4"`:
   - Append `4` to `numbers`, resulting in `numbers = [4]`.
   - There are no triples yet, so append `0` to the output.

2. Process `queries[1] = "+5"`:
   - Append `5` to `numbers`, resulting in `numbers = [4, 5]`.
   - There are no triples yet, so append `0` to the output.

3. Process `queries[2] = "+6"`:
   - Append `6` to `numbers`, resulting in `numbers = [4, 5, 6]`.
   - These can form the triple \((6, 5, 4)\) which meets the condition \(6 - 5 = 5 - 4 = 1 = diff\).
   - Append `1` to the output.

4. Process `queries[3] = "+4"`:
   - Append `4` to `numbers`, resulting in `numbers = [4, 5, 6, 4]`.
   - There are two ways to form the triple \((6, 5, 4)\), which meets the condition, so append `2` to the output.

5. Process `queries[4] = "+3"`:
   - Add `3` to `numbers`, resulting in `numbers = [4, 5, 6, 4, 3]`.
   - There are two ways to form the triple \((6, 5, 4)\) and two ways to form the triple \((5, 4, 3)\), which meet the condition.
   - Append `4` to the output.

6. Process `queries[5] = "-4"`:
   - Remove all instances of `4` from `numbers`, resulting in `numbers = [5, 6, 3]`.
   - There are no ways to form triples which meet the condition, so append `0` to the output.

**Final Output:**
```java
[0, 0, 1, 2, 4, 0]
```

---

### Input/Output

1. **[Execution Time Limit]:** 3 seconds (Java)
2. **[Memory Limit]:** 1 GB
3. **[Input] array.string queries**
   - An array of strings representing queries in the format described above.
   - **Guaranteed Constraints:**
     - \(1 \leq \text{queries.length} \leq 10^5\)
     - \(-10^9 \leq x \leq 10^9\)

4. **[Input] integer diff**
   - A positive integer value representing the expected difference in triples.
   - **Guaranteed Constraints:**
     - \(1 \leq \text{diff} \leq 10^9\)

5. **[Output] array.integer**
   - After processing each query, count the number of triples \((x, y, z)\) in `numbers` which meet the condition:
     \[
     x - y = y - z = \text{diff}.
     \]
   - It is guaranteed that the count will always fit into a signed 32-bit integer type.
   - Return an array of such values for all queries.

---

### Solution Function

```java
int[] solution(String[] queries, int diff) {

}
```


 */
public class TripleCounterSolutionFinal {
    public static int[] solution(String[] queries, int diff) {
        int[] result = new int[queries.length];
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.charAt(0) == '+') {
                numbers.add(Integer.parseInt(query.substring(1)));
            } else {
                int numToRemove = Integer.parseInt(query.substring(1));
                numbers.removeIf(num -> num == numToRemove);
            }
            result[i] = countTriples(numbers, diff);
        }
        return result;
    }

    private static int countTriples(List<Integer> numbers, int diff) {
        if (numbers.size() < 3) return 0;

        // Count frequency of each number
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : numbers) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        // For each potential middle number y
        Set<Integer> uniqueNums = new HashSet<>(numbers);
        for (int y : uniqueNums) {
            int x = y + diff;  // x should be diff more than y
            int z = y - diff;  // z should be diff less than y

            if (freq.containsKey(x) && freq.containsKey(z)) {
                // Calculate combinations based on frequencies
                long combinations;
                if (x == y && y == z) {
                    // All three numbers are the same (only possible when diff = 0)
                    int n = freq.get(y);
                    combinations = (long) n * (n - 1) * (n - 2) / 6;
                } else if (x == y) {
                    // First two numbers are the same
                    int n = freq.get(y);
                    combinations = (long) n * (n - 1) / 2 * freq.get(z);
                } else if (y == z) {
                    // Last two numbers are the same
                    int n = freq.get(y);
                    combinations = (long) freq.get(x) * n * (n - 1) / 2;
                } else {
                    // All numbers are different
                    combinations = (long) freq.get(x) * freq.get(y) * freq.get(z);
                }
                count += combinations;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(1, new String[]{"+4", "+5", "+6", "+4", "+3", "-4"}, 1,
                new int[]{0, 0, 1, 2, 4, 0});

        runTest(2, new String[]{"+1", "+2", "+3", "+4", "+5"}, 2,
                new int[]{0, 0, 0, 1, 2});

        runTest(3, new String[]{"+1", "+1", "+1", "+1"}, 0,
                new int[]{0, 0, 1, 4});

        // Additional test case for verification
        runTest(4, new String[]{"+1", "+3", "+5", "+7", "+9"}, 2,
                new int[]{0, 0, 1, 2, 3});

        // Large test case
        testLargeInput();
    }

    private static void runTest(int testNumber, String[] queries, int diff, int[] expected) {
        System.out.println("\nTest Case " + testNumber + ":");
        System.out.println("Queries: " + Arrays.toString(queries));
        System.out.println("Diff: " + diff);

        long startTime = System.currentTimeMillis();
        int[] result = solution(queries, diff);
        long endTime = System.currentTimeMillis();

        boolean passed = Arrays.equals(result, expected);
        System.out.printf("Status: %s (Time: %dms)%n",
                passed ? "PASS" : "FAIL",
                endTime - startTime);

        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got: " + Arrays.toString(result));
        }
    }

    private static void testLargeInput() {
        int size = 100000;
        String[] largeQueries = new String[size];
        Random rand = new Random(42);

        for (int i = 0; i < size; i++) {
            if (i < size * 0.9) {
                largeQueries[i] = "+" + rand.nextInt(1000);
            } else {
                largeQueries[i] = "-" + rand.nextInt(1000);
            }
        }

        System.out.println("\nLarge Input Test:");
        long startTime = System.currentTimeMillis();
        solution(largeQueries, 1);
        long endTime = System.currentTimeMillis();
        System.out.printf("Completed processing %d queries in %dms%n",
                size, endTime - startTime);
    }
}
