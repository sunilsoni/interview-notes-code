package com.interview.notes.code.year.y2025.jan25.CASPEX.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
WORKING:

Here is the extracted and properly formatted text from the additional screenshots:

---

### Moving Zeros to End

Given an integer array `nums[]`, move all `0`s to the end of it while maintaining the relative order of the non-zero elements.

**Note:** You must do this **in-place** without making a copy of the array.

---

**Input**
- The first line of input contains an integer `N`, representing the size of the array.
- The second line of input contains `N` space-separated integers, representing the array elements.

**Output**
The updated array after moving all `0`s to the end.

---

**Constraints**
- `1 <= N <= 10⁴`
- `-2³¹ <= nums[i] <= 2³¹ - 1`

---

### Example #1

**Input**
```
5
0 1 0 3 12
```

**Output**
```
1 3 12 0 0
```

---

### Example #2

**Input**
```
1
0
```

**Output**
```
0
```

---

### Function Template

```java
class Outcome {
    /**
     * Implement method/function with the name 'solve' below.
     * The function accepts the following parameters:
     * 1. nums is of type List<Integer>.
     *
     * Return:
     * List<Integer>

public static List<Integer> solve(List<Integer> nums) {
    // Write your code here

    return null; // return type "List<Integer>".
}
}
        ```

        Let me know if you'd like the solution implemented or further modifications!
 */
public class MoveZeros {
    public static List<Integer> solve(List<Integer> nums) {
        if (nums == null || nums.size() <= 1) {
            return nums;
        }

        // Convert List to array for in-place modification
        int[] arr = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }

        // Move non-zero elements to front
        int nonZeroIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[nonZeroIndex++] = arr[i];
            }
        }

        // Fill remaining positions with zeros
        while (nonZeroIndex < arr.length) {
            arr[nonZeroIndex++] = 0;
        }

        // Convert back to List
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            result.add(num);
        }
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(Arrays.asList(0, 1, 0, 3, 12), "Test 1: Basic case");
        runTest(Arrays.asList(0), "Test 2: Single zero");
        runTest(Arrays.asList(1), "Test 3: Single non-zero");
        runTest(Arrays.asList(0, 0, 0), "Test 4: All zeros");
        runTest(Arrays.asList(1, 2, 3), "Test 5: No zeros");
        runTest(Arrays.asList(0, 0, 1, 0, 2), "Test 6: Multiple zeros");

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInput.add(i % 2 == 0 ? 0 : i);
        }
        runTest(largeInput, "Test 7: Large input");
    }

    private static void runTest(List<Integer> input, String testName) {
        System.out.println(testName);
        System.out.println("Input: " + input);
        List<Integer> result = solve(input);
        System.out.println("Output: " + result);

        // Verify result
        boolean isValid = validateResult(input, result);
        System.out.println("Test " + (isValid ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private static boolean validateResult(List<Integer> input, List<Integer> result) {
        if (input.size() != result.size()) return false;

        // Check if non-zero elements maintain relative order
        List<Integer> inputNonZeros = input.stream()
                .filter(x -> x != 0)
                .collect(Collectors.toList());
        List<Integer> resultNonZeros = result.stream()
                .filter(x -> x != 0)
                .collect(Collectors.toList());

        if (!inputNonZeros.equals(resultNonZeros)) return false;

        // Check if zeros are at the end
        boolean foundNonZero = false;
        for (int i = result.size() - 1; i >= 0; i--) {
            if (result.get(i) != 0) {
                foundNonZero = true;
            } else if (foundNonZero) {
                return false;
            }
        }

        return true;
    }
}
