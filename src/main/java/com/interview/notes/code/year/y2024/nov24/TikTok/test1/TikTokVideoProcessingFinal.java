package com.interview.notes.code.year.y2024.nov24.TikTok.test1;

import java.util.*;

/*
WORKING:


### **Input Format for Custom Testing**
- The first line contains an integer `n`, the number of video analyzers.
- Each of the next `n` lines contains an integer, `processingPower[i]`, representing the processing power of the video analyzer.

---

### **Sample Case 0**
#### **Sample Input 0**
```
STDIN           FUNCTION
7               -> the size of processingPower n = 7
3               -> processingPower = [3, 3, 5, 5, 2, 2, 5]
3
5
5
2
2
5
```

#### **Sample Output 0**
```
21
```

#### **Explanation**
One of the optimal ways to choose the video analyzers for maximum processing power is to select the analyzers at indices `0, 1, 2, 3, and 6`. The sum of capacities is `3 + 3 + 5 + 5 + 5 = 21`. Hence, we return `21`.

---

### **Sample Case 1**
#### **Sample Input 1**
```
STDIN           FUNCTION
4               -> the size of processingPower n = 4
8               -> processingPower = [8, 5, 1, 5]
5
1
5
```

#### **Sample Output 1**
```
19
```

#### **Explanation**
In this scenario, the processing powers of the TikTok video analyzers are such that all of them can be selected without interference. Hence, the maximum achievable total processing power is `8 + 5 + 1 + 5 = 19`.

---

### **Problem Statement**
#### **TikTok Video Processing**
TikTok is optimizing its video processing system. There are `n` video analyzers that can process videos based on their individual processing power. Each analyzer, represented by `processingPower[i]`, has a unique capability in terms of how many videos it can handle at a time.

However, not all video analyzers can be used simultaneously for video processing. If an analyzer with processing power `processingPower[i]` is selected, then:
- Any analyzer with a processing power of `(processingPower[i] + 1)` or `(processingPower[i] - 1)` **cannot be used** due to interference in video content processing.

Each analyzer can only be used once.

---

#### **Task**
Given `n` integers representing the video analyzers’ processing power, determine the **maximum achievable total processing power** by selecting the best combination of analyzers that don’t interfere with each other.

---

### **Example**
**Input:** `n = 5`, `processingPower = [2, 3, 9, 2, 3]`
**Output:** `15`

#### **Explanation:**
- Select the video analyzers with processing power `3, 9, and 3`, at indices `1, 2, and 4`.
- The total video processing power is `3 + 9 + 3 = 15`.
- No other valid selection yields a higher processing power.

---

### **Function Description**
Complete the function `maximizeProcessingPower` in the editor below.

#### **Function Signature**
```java
public static long maximizeProcessingPower(List<Integer> processingPower);
```

#### **Parameters**
- `int processingPower[n]`: The processing power of the TikTok video analyzers.

#### **Returns**
- `long`: The maximum achievable total video processing power.

---

### **Constraints**
- `1 ≤ n ≤ 10⁵`
- `1 ≤ processingPower[i] ≤ 10⁵`

---

#### **Starter Code**
```java
/*
 * Complete the 'maximizeProcessingPower' function below.
 *
 * The function is expected to return a LONG_INTEGER.
 * The function accepts INTEGER_ARRAY processingPower as parameter.


public static long maximizeProcessingPower(List<Integer> processingPower) {
    // Write your code here
}
 */
public class TikTokVideoProcessingFinal {

    /*
     * Complete the 'maximizeProcessingPower' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY processingPower as parameter.
     */

    public static long maximizeProcessingPower(List<Integer> processingPower) {
        // Count the frequency of each processing power value
        Map<Integer, Long> freqMap = new HashMap<>();
        for (int power : processingPower) {
            freqMap.put(power, freqMap.getOrDefault(power, 0L) + 1);
        }

        // Get the unique processing power values and sort them
        List<Integer> uniquePowers = new ArrayList<>(freqMap.keySet());
        Collections.sort(uniquePowers);

        int n = uniquePowers.size();
        long[] totalPower = new long[n];
        long[] dp = new long[n];

        // Compute total power for each unique processing power
        for (int i = 0; i < n; i++) {
            int power = uniquePowers.get(i);
            long count = freqMap.get(power);
            totalPower[i] = (long) power * count;
        }

        // Dynamic programming to find the maximum total processing power
        for (int i = 0; i < n; i++) {
            int currentPower = uniquePowers.get(i);
            long includeCurrent = totalPower[i];

            // Check if previous power is adjacent
            if (i > 0 && currentPower == uniquePowers.get(i - 1) + 1) {
                if (i > 1) {
                    includeCurrent += dp[i - 2];
                }
            } else {
                if (i > 0) {
                    includeCurrent += dp[i - 1];
                }
            }

            long excludeCurrent = (i > 0) ? dp[i - 1] : 0;
            dp[i] = Math.max(includeCurrent, excludeCurrent);
        }

        return dp[n - 1];
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case 0
        List<Integer> processingPower0 = Arrays.asList(3, 3, 5, 5, 2, 2, 5);
        long expected0 = 21;
        long result0 = maximizeProcessingPower(processingPower0);
        System.out.println("Test Case 0: " + (result0 == expected0 ? "PASS" : "FAIL") + " - Output: " + result0);

        // Test case 1
        List<Integer> processingPower1 = Arrays.asList(8, 5, 1, 5);
        long expected1 = 19;
        long result1 = maximizeProcessingPower(processingPower1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") + " - Output: " + result1);

        // Test case from the example
        List<Integer> processingPowerExample = Arrays.asList(2, 3, 9, 2, 3);
        long expectedExample = 15;
        long resultExample = maximizeProcessingPower(processingPowerExample);
        System.out.println("Example Test Case: " + (resultExample == expectedExample ? "PASS" : "FAIL") + " - Output: " + resultExample);

        // Edge case: All processing powers are the same
        List<Integer> processingPowerSame = Arrays.asList(4, 4, 4, 4);
        long expectedSame = 16;
        long resultSame = maximizeProcessingPower(processingPowerSame);
        System.out.println("All Same Powers Test Case: " + (resultSame == expectedSame ? "PASS" : "FAIL") + " - Output: " + resultSame);

        // Edge case: Processing powers are consecutive
        List<Integer> processingPowerConsecutive = Arrays.asList(1, 2, 3, 4, 5);
        long expectedConsecutive = 9; // Select 1, 3, 5
        long resultConsecutive = maximizeProcessingPower(processingPowerConsecutive);
        System.out.println("Consecutive Powers Test Case: " + (resultConsecutive == expectedConsecutive ? "PASS" : "FAIL") + " - Output: " + resultConsecutive);

        // Large input test case
        List<Integer> processingPowerLarge = new ArrayList<>();
        int n = 100000;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            processingPowerLarge.add(rand.nextInt(1000) + 1);
        }
        long resultLarge = maximizeProcessingPower(processingPowerLarge);
        System.out.println("Large Input Test Case: Output: " + resultLarge);

        // Additional custom test cases can be added here
    }
}