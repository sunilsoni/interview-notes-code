package com.interview.notes.code.months.oct24.test25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
FINAL WORKING
Engineers have redesigned a keypad used by ambulance drivers in urban areas. In order to determine which key takes the longest time to press, the keypad is tested by a driver. Given the results of that test, determine which key takes the longest to press.

### Example

Given `keyTimes = [[0, 2], [1, 5], [0, 9], [2, 15]]`:

Elements in `keyTimes[i][0]` represent encoded characters in the range `ascii[a-z]` where `a = 0`, `b = 1`, ..., `z = 25`. The second element, `keyTimes[i][1]`, represents the time the key is pressed since the start of the test. The elements will be given in ascending time order.

In the example, keys pressed in order are `0102` (encoded) = `abac` at times `2, 5, 9, 15`.
From the start time, it took:
- \(2 - 0 = 2\) seconds to press the first key,
- \(5 - 2 = 3\) seconds to press the second,
- \(9 - 5 = 4\) seconds to press the third, and
- \(15 - 9 = 6\) seconds to press the fourth.

The longest time it took to press a key was key `2`, or `c`, at \(15 - 9 = 6\).

### Function Description

Complete the function `slowestKey` in the editor below.

`slowestKey` has the following parameter(s):
- `int keyTimes[n][2]`: the first column contains the encoded key pressed, the second contains the time at which it was pressed.

**Returns:**
- `char`: the key that took the longest time to press.

### Constraints
- \(1 \leq n \leq 10^5\)
- \(0 \leq keyTimes[i][0] \leq 25\) (for \(0 \leq i < n\))
- \(1 \leq keyTimes[i][1] \leq 10^8\) (for \(0 \leq i < n\))
- There will only be one key with the worst time.
- `keyTimes` is sorted in ascending order of `keyTimes[i][1]`.

### Input Format For Custom Testing

The first line contains an integer, `n`, the number of elements in `keyTimes`.
The second line contains the integer `2`, the number of columns in each `keyTimes[i]`.
Each line `i` of the `n` subsequent lines (where \(0 \leq i < n\)) contains two space-separated integers, `keyTimes[i][0]` and `keyTimes[i][1]`.

---

### Sample Case 0

**Sample Input For Custom Testing**

```
STDIN         Function
-----         --------
3             → keyTimes[] size n = 3
2             → keyTimes[] size columns = 2, always
0 2           → keyTimes = [[0, 2], [1, 3], [0, 7]]
1 3
0 7
```

**Sample Output**

```
a
```

**Explanation**

The time taken to press 'a' in the worst case is \(7 - 3 = 4\). To press 'b' the worst case is \(3 - 2 = 1\).

---

### Sample Case 1

**Sample Input For Custom Testing**

```
STDIN         Function
-----         --------
5             → keyTimes[] size n = 5
2             → keyTimes[] size = 2
0 1           → keyTimes = [[0, 1], [0, 3], [4, 5], [5, 6], [4, 10]]
0 3
4 5
5 6
4 10
```

**Sample Output**

```
e
```

**Explanation**

The time taken to press 'a' in the worst case is \(3 - 1 = 2\), for 'e' is \(10 - 6 = 4\), and for 'f' is \(6 - 5 = 1\). The letter 'e' is the slowest key.

---

### Code Skeleton

```java
/*
 * Complete the 'slowestKey' function below.
 *
 * The function is expected to return a CHARACTER.
 * The function accepts 2D_INTEGER_ARRAY keyTimes as parameter.
 */
class KeypadTesterWorking {
    public static char slowestKey(List<List<Integer>> keyTimes) {
        int maxDiff = 0;
        char slowestChar = 'a';
        
        for(int i = 0; i < keyTimes.size(); i++) {
            int currentTime = keyTimes.get(i).get(1);
            int prevTime = i == 0 ? 0 : keyTimes.get(i-1).get(1);
            int timeDiff = currentTime - prevTime;
            
            if(timeDiff > maxDiff) {
                maxDiff = timeDiff;
                slowestChar = (char)('a' + keyTimes.get(i).get(0));
            }
        }
        return slowestChar;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1
        List<List<Integer>> test1 = Arrays.asList(
            Arrays.asList(0, 2), 
            Arrays.asList(1, 3),
            Arrays.asList(0, 7)
        );
        testCase(test1, 'a', "Test Case 1");

        // Test Case 2
        List<List<Integer>> test2 = Arrays.asList(
            Arrays.asList(0, 1),
            Arrays.asList(0, 3),
            Arrays.asList(4, 5),
            Arrays.asList(5, 6),
            Arrays.asList(4, 10)
        );
        testCase(test2, 'e', "Test Case 2");

        // Edge Case: Single Key Press
        List<List<Integer>> test3 = Arrays.asList(
            Arrays.asList(0, 5)
        );
        testCase(test3, 'a', "Edge Case - Single Key");

        // Large Input Test
        List<List<Integer>> test4 = generateLargeInput(10000);
        testCase(test4, 'z', "Large Input Test");

        // Same Key Multiple Times
        List<List<Integer>> test5 = Arrays.asList(
            Arrays.asList(0, 1),
            Arrays.asList(0, 3),
            Arrays.asList(0, 8)
        );
        testCase(test5, 'a', "Same Key Multiple Times");
    }

    private static void testCase(List<List<Integer>> input, char expected, String testName) {
        long startTime = System.currentTimeMillis();
        char result = slowestKey(input);
        long endTime = System.currentTimeMillis();

        System.out.println("\n" + testName);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Status: " + (result == expected ? "PASS" : "FAIL"));
    }

    private static List<List<Integer>> generateLargeInput(int size) {
        List<List<Integer>> largeInput = new ArrayList<>();
        Random rand = new Random();
        int currentTime = 0;
        
        for(int i = 0; i < size; i++) {
            currentTime += rand.nextInt(5) + 1;
            largeInput.add(Arrays.asList(
                rand.nextInt(26),  // random key (0-25)
                currentTime        // increasing time
            ));
        }
        // Add a known maximum time difference
        largeInput.add(Arrays.asList(25, currentTime + 1000));  // 'z' key with large time gap
        return largeInput;
    }
}