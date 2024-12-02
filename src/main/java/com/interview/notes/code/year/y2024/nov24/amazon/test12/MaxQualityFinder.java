package com.interview.notes.code.year.y2024.nov24.amazon.test12;

import java.util.*;

/*
WORKING



### **Problem Description**
Complete the function `findMaximumQuality` in the editor below.

`findMaximumQuality` has the following parameter(s):
- `int packets[n]`: the packet sizes
- `int channels`: the number of channels

Returns:
- `long int`: the maximum sum possible

### **Constraints**
- \(1 \leq \text{len(packets)} \leq 5 \times 10^5\)
- \(1 \leq \text{packets}[i] \leq 10^9\)
- \(1 \leq \text{channels} \leq \text{len(packets)}\)

---

### **Input Format**
The first line contains an integer, `n`, the number of elements in `packets`.
Each line `i` of the `n` subsequent lines (where \(0 \leq i < n\)) contains an integer, `packets[i]`.
The last line contains an integer, `channels`.

---

### **Explanation of Functionality**
Amazon's AWS provides fast and efficient server solutions. The developers want to stress-test the quality of the servers' channels. They must ensure the following:
- Each of the packets must be sent via a single channel.
- Each of the channels must transfer at least one packet.

The **quality** of the transfer for a channel is defined by the **median** of the sizes of all the data packets sent through that channel.
- Note: The median of an array is the middle element if the array is sorted in non-decreasing order. If the number of elements in the array is even, the median is the average of the two middle elements. If the result is a floating-point value, round it to the next higher integer.

**Objective**:
- Find the maximum possible sum of the qualities of all channels.

---

### **Examples**

#### **Example 1**
**Input**:
```
3
89
48
14
3
```

**Output**:
```
151
```

**Explanation**:
There are 3 channels for 3 packets. Each channel carries one packet, so the overall sum of quality is \(89 + 48 + 14 = 151\).

---

#### **Example 2**
**Input**:
```
5
2
2
1
5
3
2
```

**Output**:
```
7
```

**Explanation**:
One solution is to send packet \{5\} through one channel and \{2, 2, 1, 3\} through the other. The sum of quality is:
- Channel 1: \(5\)
- Channel 2: \((2 + 3) / 2 = 2.5\), which rounds up to 3.

The total sum is \(5 + 3 = 7\).

---

#### **Example 3**
**Input**:
```
5
1
2
3
4
5
2
```

**Output**:
```
8
```

**Explanation**:
At least one packet has to go through each of the 2 channels. One maximal solution is to transfer packets \{1, 2, 3, 4\} through channel 1 and packet \{5\} through channel 2.
- Channel 1: Median is \((2 + 3) / 2 = 2.5\), which rounds up to 3.
- Channel 2: Median is 5.

The total sum is \(3 + 5 = 8\).

---

### **Function Signature**
You need to implement a function with the following signature:

 */
public class MaxQualityFinder {

    public static long findMaximumQuality(List<Integer> packets, int channels) {
        // Sort packets in descending order
        Collections.sort(packets, Collections.reverseOrder());
        int n = packets.size();

        // If channels equals packets size, return sum of all packets
        if (channels == n) {
            return packets.stream().mapToLong(Integer::longValue).sum();
        }

        long result = 0;
        // Add largest elements for first (channels-1) channels
        for (int i = 0; i < channels - 1; i++) {
            result += packets.get(i);
        }

        // Calculate median for remaining elements
        List<Integer> remaining = packets.subList(channels - 1, n);
        int size = remaining.size();
        double median;

        if (size % 2 == 0) {
            median = (remaining.get(size / 2 - 1) + remaining.get(size / 2)) / 2.0;
        } else {
            median = remaining.get(size / 2);
        }

        return result + (long) Math.ceil(median);
    }

    // Test method
    private static boolean runTest(List<Integer> packets, int channels, long expected) {
        long result = findMaximumQuality(packets, channels);
        boolean passed = result == expected;
        System.out.printf("Test case with packets=%s, channels=%d: %s (Expected=%d, Got=%d)%n",
                packets, channels, passed ? "PASSED" : "FAILED", expected, result);
        return passed;
    }

    public static void main(String[] args) {
        int passedTests = 0;
        int totalTests = 0;

        // Test Case 1
        totalTests++;
        if (runTest(Arrays.asList(89, 48, 14), 3, 151)) passedTests++;

        // Test Case 2
        totalTests++;
        if (runTest(Arrays.asList(2, 2, 1, 5, 3), 2, 7)) passedTests++;

        // Test Case 3
        totalTests++;
        if (runTest(Arrays.asList(1, 2, 3, 4, 5), 2, 8)) passedTests++;

        // Edge case: Single packet
        totalTests++;
        if (runTest(Arrays.asList(10), 1, 10)) passedTests++;

        // Large data test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            largeInput.add(i);
        }
        totalTests++;
        if (runTest(largeInput, 2, 75001)) passedTests++; // Expected value calculated manually

        // Random large numbers test
        List<Integer> randomLarge = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            randomLarge.add(rand.nextInt(1000000000));
        }
        totalTests++;
        long expectedRandom = findMaximumQuality(new ArrayList<>(randomLarge), 5);
        if (runTest(randomLarge, 5, expectedRandom)) passedTests++;

        System.out.println("\nTest Summary:");
        System.out.printf("Passed: %d/%d tests%n", passedTests, totalTests);
        System.out.printf("Success Rate: %.2f%%%n", (passedTests * 100.0) / totalTests);
    }
}