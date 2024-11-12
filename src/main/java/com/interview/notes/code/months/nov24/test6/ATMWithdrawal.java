package com.interview.notes.code.months.nov24.test6;

import java.util.*;

/*
WORKING




### Complete the `getFinalOrder` function below.

The function is expected to return an INTEGER_ARRAY.

The function accepts the following parameters:
1. INTEGER k
2. INTEGER_ARRAY amount

```java
public static List<Integer> getFinalOrder(int k, List<Integer> amount) {
    // Write your code here
}
```

---

### Sample Case 1

**Sample Input For Custom Testing**
```
2 > k = 2
6 > n = 6
6 > amount = [6, 1, 2, 3, 2, 7]
```
```
2
3
2
```

**Sample Output**
```
2
3
1
6
```

**Explanation**
| Person | Total - Current | Withdraws | Current | Q                 | Result         |
|--------|------------------|-----------|---------|-------------------|----------------|
| 1      | 6 - 0 = 6        | 2         | 2       | [2, 3, 4, 5, 6]   | [1]            |
| 2      | 1 - 0 = 1        | 1         | *       | [3, 4, 5, 6, 1]   | [2]            |
| 3      | 2 - 0 = 2        | 2         | *       | [4, 5, 6, 1]      | [2, 3]         |
| 4      | 3 - 0 = 3        | 2         | 2       | [5, 6, 1, 4]      | [2, 3, 5]      |
| 5      | 2 - 0 = 2        | *         | 2       | [6, 1, 4]         | [2, 3, 5]      |
| 6      | 7 - 0 = 7        | 2         | 2       | [1, 4, 6]         | [1]            |
| 1      | 6 - 2 = 4        | 2         | 4       | [4, 6, 1]         | [2, 3, 5, 4]   |
| 4      | 3 - 2 = 1        | 1         | *       | [6, 1]            | [2, 3, 5, 4, 1]|
| 6      | 7 - 2 = 5        | 2         | 4       | [1, 6]            | [2, 3, 5, 4, 1, 6]|
| 6      | 7 - 6 = 1        | *         |         | []                | [2, 3, 5, 4, 1, 6]|

The "Current" column shows an asterisk when the person leaves the queue.

---

### Sample Output
```
1
3
2
```

**Explanation**
- Person 1 withdraws 1 and leaves the queue. Q = [2, 3], result = [1].
- Person 2 withdraws 2 and moves to the back of the queue. current[2] = 0 + 2 = 2, Q = [3, 2], result = [1]
- Person 3 withdraws 2 and leaves the queue. Q = [2], result = [1, 3]
- Person 2 withdraws 1 and leaves the queue. Q = [], result = [1, 3, 2]

---

### Function Description
Complete the function `getFinalOrder` in the editor below.

`getFinalOrder` has the following parameters:
- `int k`: the maximum withdrawal amount
- `int amount[n]`: amounts needed per person

**Returns**
- `int[]`: the order in which people leave the queue.

---

### Constraints
- \(1 \leq n \leq 10^5\)
- \(1 \leq k \leq 10^6\)
- \(1 \leq amount[i] \leq 10^9\)

---

### Input Format For Custom Testing
The first line contains an integer, k.
The next line contains an integer, n, the number of elements in amount.
Each line \(i\) of the \(n\) subsequent lines (where \(0 \leq i < n\)) contains an integer, amount[i].

### Sample Case 0
**Sample Input For Custom Testing**
```
2
3
amount = [1, 3, 2]
```
```
3
2
```

**Given an array of integers, amount[n], each amount[i] represents the amount of money required by person i.**
There are n people numbered 1 through n standing in an ATM queue in increasing order. A person can withdraw at most k units of currency at one time. Return an array of people numbers in the order that they leave the queue, i.e., the order their needed amounts have been withdrawn.

Call the cumulative amount of money withdrawn `current`, and the money required `total`. Repeat the following process until the queue is empty:
1. If total - current ≤ k
   - withdraw (total - current)
   - leave the queue
   - store the person's index in the return array
2. Otherwise
   - withdraw k
   - current = current + k
   - move to the back of the queue

---

### Example
Given \(k = 2\), \(n = 3\), and `amount = [2, 5, 1]`:
- The initial queue: \(Q = [1, 2, 3]\).
- Person 1 needs to withdraw `amount[1] = 2`. Since \(k = 2\), the money can be withdrawn and person 1 leaves the queue. \(Q = [2, 3], \text{result} = [1]\).
- Person 2 needs to withdraw `amount[2] = 5`, withdraws 2, current = 0 + 2 = 2, and moves to the back of the queue. \(Q = [3, 2], \text{result} = [1]\).
- Person 3 needs to withdraw `amount[3] = 1`, withdraws 1 and leaves the queue. \(Q = [2], \text{result} = [1, 3]\).
- Person 2 still needs 5 - 2 = 3. Withdraw 2, current = 2 + 2 = 4, move to the back of the queue. \(Q = [2], \text{result} = [1, 3]\).
- Person 2 still needs 5 - 4 = 1, withdraws 1 and leaves the queue. \(Q = [], \text{result} = [1, 3, 2]\).

Return the result array, \([1, 3, 2]\).

 */
public class ATMWithdrawal {

    /**
     * Determines the order in which people leave the ATM queue.
     *
     * @param k      The maximum amount that can be withdrawn in one transaction.
     * @param amount The list of amounts each person needs to withdraw.
     * @return A list of integers representing the order in which people leave the queue.
     */
    public static List<Integer> getFinalOrder(int k, List<Integer> amount) {
        int n = amount.size();
        List<Person> people = new ArrayList<>(n);

        // Compute the number of withdrawals needed for each person
        for (int i = 0; i < n; i++) {
            long amt = amount.get(i);
            long withdrawals = (amt + k - 1) / k; // Equivalent to ceil(amt / k)
            people.add(new Person(i + 1, withdrawals));
        }

        // Sort the people based on withdrawals ascending, then by original index
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                if (p1.withdrawals != p2.withdrawals) {
                    return Long.compare(p1.withdrawals, p2.withdrawals);
                }
                return Integer.compare(p1.index, p2.index);
            }
        });

        // Extract the sorted order
        List<Integer> result = new ArrayList<>(n);
        for (Person p : people) {
            result.add(p.index);
        }

        return result;
    }

    /**
     * Main method to test the getFinalOrder function with various test cases.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 1
        testCases.add(new TestCase(
                2,
                Arrays.asList(6, 1, 2, 3, 2, 7),
                Arrays.asList(2, 3, 5, 4, 1, 6)
        ));

        // Sample Case 2
        testCases.add(new TestCase(
                2,
                Arrays.asList(1, 3, 2),
                Arrays.asList(1, 3, 2)
        ));

        // Additional Test Case 1: All require one withdrawal
        testCases.add(new TestCase(
                5,
                Arrays.asList(5, 5, 5, 5, 5),
                Arrays.asList(1, 2, 3, 4, 5)
        ));

        // Additional Test Case 2: All require maximum withdrawals
        testCases.add(new TestCase(
                1,
                Arrays.asList(1000000000, 1000000000, 1000000000),
                Arrays.asList(1, 2, 3)
        ));

        // Additional Test Case 3: Mixed withdrawals
        testCases.add(new TestCase(
                3,
                Arrays.asList(4, 9, 2, 15, 7),
                Arrays.asList(1, 3, 2, 5, 4)
        ));

        // Additional Test Case 4: Large Input
        List<Integer> largeAmount = new ArrayList<>();
        int largeN = 100000;
        for (int i = 0; i < largeN; i++) {
            largeAmount.add(1000000); // Each requires 1,000,000 / k withdrawals
        }
        List<Integer> expectedLarge = new ArrayList<>();
        for (int i = 1; i <= largeN; i++) {
            expectedLarge.add(i);
        }
        testCases.add(new TestCase(
                1000,
                largeAmount,
                expectedLarge
        ));

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> output = getFinalOrder(tc.k, tc.amount);
            if (output.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got:      " + output);
            }
        }

        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    /**
     * Represents a person in the queue with their index and number of withdrawals needed.
     */
    static class Person {
        int index;      // Original position in the queue (1-based)
        long withdrawals; // Number of withdrawals needed

        Person(int index, long withdrawals) {
            this.index = index;
            this.withdrawals = withdrawals;
        }
    }

    /**
     * Represents a test case with input parameters and the expected output.
     */
    static class TestCase {
        int k;
        List<Integer> amount;
        List<Integer> expected;

        TestCase(int k, List<Integer> amount, List<Integer> expected) {
            this.k = k;
            this.amount = amount;
            this.expected = expected;
        }
    }
}
