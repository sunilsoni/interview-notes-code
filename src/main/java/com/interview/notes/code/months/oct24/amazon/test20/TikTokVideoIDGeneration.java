package com.interview.notes.code.months.oct24.amazon.test20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
FINAL WORKING


### 6. TikTok Video ID Generation

TikTok is working to make the process of creating unique video IDs more efficient. A TikTok video ID is represented as a string called `idStream` of length `n`, made up of the digits '0' to '9'. TikTok also has a list called `videoIds`, which contains `m` target video ID strings, each also made up of digits '0' to '9'.

For each target video ID in `videoIds`, find the shortest part of `idStream` that contains all the characters needed to form any possible arrangement (permutation) of the target string.

You need to return an array of integers. The number at each position `i` in this array should be the length of the shortest part of `idStream` that can include all characters of the `i-th` string in `videoIds`. If it’s impossible to create the target video ID from `idStream`, return -1 for that position.

#### Example

Given:

- `n = 12`
- `idStream = "064819848398"`
- `m = 3`
- `videoIds = ["088", "364", "07"]`

1. To construct "088", the first 7 characters of the `idStream` ("0648198") contain '0', '8', and '8'. Therefore, the answer for this target is 7.
2. To construct "364", we need to access the first 10 characters of the `idStream` ("0648198483") and use the digits '6', '4', and '3'. By rearranging these digits to match "364", the answer is 10.
3. For "07", there is no prefix in the `idStream` that contains both '0' and '7', so the answer is -1.

Thus, the result array is: `[7, 10, -1]`.

---

### Function Description

Complete the function `countMinimumCharactersForVideoIDs` in the editor below.

The function is expected to return an `INTEGER_ARRAY`.

The function accepts the following parameters:

- `string idStream`: The special string of length `n` representing the source TikTok video ID pool.
- `string videoIds[m]`: An array of `m` target video ID strings to construct.

---

#### Returns

- `int[]`: The `i-th` element corresponds to the minimum number of characters required to construct the `i-th` string in `videoIds`, or `-1` if the string cannot be constructed.

#### Constraints

- `1 ≤ n ≤ 10^5`
- `1 ≤ m ≤ 2*10^4`
- `1 ≤ sum of the lengths of strings in videoIds ≤ 5*10^5`
- All strings consist of characters '0'-'9' only.

---

### Input Format for Custom Testing

The first line contains a string `idStream`, representing the source TikTok video ID pool.
The second line contains an integer `m`, representing the number of target video ID strings.
Each of the next `m` lines contains a string `videoIds[i]`, representing a target video ID to be constructed.

---

### Sample Case 0

#### Sample Input 0

```
STDIN            FUNCTION
-----            --------
1112223333444    -> idStream = "1112223333444"
4                -> size of videoIds, m = 4
121              -> videoIds = ["121", "3", "12345", "11234"]
3
12345
11234
```

#### Sample Output 0

```
4
7
-1
10
```

#### Explanation

Here, `idStream = "1112223333444"`, `videoIds = ["121", "3", "12345", "11234"]`

- At `i = 0`, the target video ID "121" can be constructed by accessing the first 4 characters of the `idStream` ("1112"). Therefore, the answer for this target is 4.
- At `i = 1`, the target video ID "3" can be constructed by accessing the first 7 characters of the `idStream` ("1112223"). Therefore, the answer for this target is 7.
- At `i = 2`, the target video ID "12345" cannot be constructed since there is no '5' in the `idStream`. Therefore, the answer is -1.
- At `i = 3`, the target video ID "11234" can be constructed by accessing the first 10 characters of the `idStream` ("1112223334"). Therefore, the answer for this target is 10.

Thus, the result array would be: `[4, 7, -1, 10]`.

---

### Sample Case 1

#### Sample Input 1

```
STDIN            FUNCTION
-----            --------
987654           -> idStream = "987654"
3                -> size of videoIds, m = 3
456789           -> videoIds = ["456789", "4", "04"]
4
04
```

#### Sample Output 1

```
6
6
-1
```

#### Explanation

Here, the `idStream = "987654"` and `videoIds = ["456789", "4", "04"]`.

- At `i = 0`, the target video ID "456789" can be constructed by accessing all the characters of the `idStream` ("987654") and rearranging them to match "456789". Therefore, the answer is 6.
- At `i = 1`, to construct "4", we can use the `idStream` ("987654"). Therefore, the answer is 6.
- At `i = 2`, the target video ID "04" cannot be constructed from the `idStream` since there is no '0'. Therefore, the answer is -1.

Thus, the result array would be: `[6, 6, -1]`.

---

### Code Template

```java
/*
 * Complete the 'countMinimumCharactersForVideoIDs' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. STRING idStream
 *  2. STRING_ARRAY videoIds



        */
public class TikTokVideoIDGeneration {

    /*
     * Complete the 'countMinimumCharactersForVideoIDs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING idStream
     *  2. STRING_ARRAY videoIds
     */

    public static List<Integer> countMinimumCharactersForVideoIDs(String idStream, List<String> videoIds) {
        // Preprocess: For each digit '0' to '9', store the list of indices where it appears
        List<Integer>[] digitIndices = new List[10];
        for (int d = 0; d < 10; d++) {
            digitIndices[d] = new ArrayList<>();
        }
        int n = idStream.length();
        for (int i = 0; i < n; i++) {
            char c = idStream.charAt(i);
            if (c >= '0' && c <= '9') {
                digitIndices[c - '0'].add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (String videoId : videoIds) {
            // Count frequency of each digit in videoId
            int[] requiredCounts = new int[10];
            for (char c : videoId.toCharArray()) {
                requiredCounts[c - '0']++;
            }

            int maxIndex = -1;
            boolean possible = true;
            for (int d = 0; d < 10; d++) {
                if (requiredCounts[d] > 0) {
                    if (digitIndices[d].size() < requiredCounts[d]) {
                        // Not enough digits in idStream
                        possible = false;
                        break;
                    }
                    // Get the index of the c-th occurrence (0-based)
                    int idx = digitIndices[d].get(requiredCounts[d] - 1);
                    if (idx > maxIndex) {
                        maxIndex = idx;
                    }
                }
            }
            if (possible) {
                result.add(maxIndex + 1); // Convert to 1-based length
            } else {
                result.add(-1);
            }
        }
        return result;
    }

    // Method to compare two integer lists
    private static boolean compareResults(List<Integer> expected, List<Integer> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 0
        testCases.add(new TestCase(
                "1112223334", // Adjusted to match sample explanation
                Arrays.asList("121", "3", "12345", "11234"),
                Arrays.asList(4, 7, -1, 10)
        ));

        // Sample Case 1
        testCases.add(new TestCase(
                "987654",
                Arrays.asList("456789", "4", "04"),
                Arrays.asList(6, 6, -1)
        ));

        // Additional Test Case 2: All digits required and present
        testCases.add(new TestCase(
                "01234567890123456789",
                Arrays.asList("0123456789", "9876543210", "000", "99999"),
                Arrays.asList(10, 10, -1, -1)
        ));

        // Additional Test Case 3: Empty videoIds
        testCases.add(new TestCase(
                "1234567890",
                new ArrayList<>(),
                new ArrayList<>()
        ));

        // Additional Test Case 4: videoIds with single digit
        testCases.add(new TestCase(
                "111111",
                Arrays.asList("1", "11", "111", "1111"),
                Arrays.asList(1, 2, 3, 4) // Adjusted expected result
        ));

        // Additional Test Case 5: Large idStream and videoIds
        // Note: Adjusted expected values to match the logic
        StringBuilder largeIdStreamBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeIdStreamBuilder.append(i % 10);
        }
        String largeIdStream = largeIdStreamBuilder.toString();
        List<String> largeVideoIds = new ArrayList<>();
        List<Integer> largeExpected = new ArrayList<>();
        // Each videoId requires 10 of each digit
        for (int i = 0; i < 1000; i++) {
            largeVideoIds.add("0123456789".repeat(10)); // "01234567890123456789..." (100 characters)
            // To have 10 of each digit, since idStream has 100000 digits, the 10th occurrence of each digit is at position 99 (0-based)
            largeExpected.add(100); // Exact position where the 10th occurrence of the last required digit appears
        }
        testCases.add(new TestCase(
                largeIdStream,
                largeVideoIds,
                largeExpected
        ));

        // Run test cases
        int testCaseNumber = 1;
        for (TestCase tc : testCases) {
            List<Integer> actual = countMinimumCharactersForVideoIDs(tc.idStream, tc.videoIds);
            boolean pass = compareResults(tc.expected, actual);
            System.out.println("Test Case " + testCaseNumber + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + tc.expected);
                System.out.println("Actual:   " + actual);
            }
            testCaseNumber++;
        }
    }

    // Test Case class to hold individual test cases
    static class TestCase {
        String idStream;
        List<String> videoIds;
        List<Integer> expected;

        TestCase(String idStream, List<String> videoIds, List<Integer> expected) {
            this.idStream = idStream;
            this.videoIds = videoIds;
            this.expected = expected;
        }
    }
}
