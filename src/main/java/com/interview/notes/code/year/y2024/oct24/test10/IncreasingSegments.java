package com.interview.notes.code.year.y2024.oct24.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Problem Statement:

A graph with the adjacent points is represented as an array, yCoordinates, such that the array index represents the X-coordinate and the corresponding element represents the Y-coordinate. Formally, each point (x, y) is represented as (i, yCoordinates[i]), where
0
≤
𝑖
<
𝑛
0≤i<n.

Find the number of increasing segments that span exactly
𝑘
k consecutive X-coordinates.

Notes:

A segment of the graph is said to be increasing if, for any two points
(
𝑥
1
,
𝑦
1
)
(x
1
​
 ,y
1
​
 ) and
(
𝑥
2
,
𝑦
2
)
(x
2
​
 ,y
2
​
 ), the value of
(
𝑦
2
−
𝑦
1
)
/
(
𝑥
2
−
𝑥
1
)
(y
2
​
 −y
1
​
 )/(x
2
​
 −x
1
​
 ) is positive.
If the value of
𝑘
k is 1, each point is an increasing segment.
Example:

makefile
Copy code
yCoordinates = [6, 5, 7, 8, 10]
k = 3
In this case, there are two increasing segments:

Formed by points (1, 5), (2, 7), (3, 8)
Formed by points (2, 7), (3, 8), (4, 10)
Any two points in these segments form an increasing segment, and each of these segments spans exactly 3 consecutive X-coordinates. Since there are two increasing segments, the answer is 2.

Function Description: Complete the function countIncreasingSegments in the editor below.

countIncreasingSegments has the following parameters:

int[] yCoordinates: the Y-coordinate of each point
int k: the number of consecutive X-coordinates over which each segment spans.
Returns:

int: the number of increasing segments
Constraints:

1
≤
𝑘
≤
𝑛
≤
2
⋅
1
0
5
1≤k≤n≤2⋅10
5

1
≤
𝑦
𝐶
𝑜
𝑜
𝑟
𝑑
𝑖
𝑛
𝑎
𝑡
𝑒
𝑠
[
𝑖
]
≤
1
0
9
1≤yCoordinates[i]≤10
9


 */
public class IncreasingSegments {

    public static int countIncreasingSegmentsSlow(List<Integer> yCoordinates, int k) {
        int count = 0;
        int n = yCoordinates.size();

        // Edge case: if k is 1, each point is an increasing segment
        if (k == 1) {
            return n;
        }

        // Iterate through the array to find increasing segments
        for (int i = 0; i <= n - k; i++) {
            boolean isIncreasing = true;
            for (int j = i; j < i + k - 1; j++) {
                if (yCoordinates.get(j) >= yCoordinates.get(j + 1)) {
                    isIncreasing = false;
                    break;
                }
            }
            if (isIncreasing) {
                count++;
            }
        }

        return count;
    }

    public static int countIncreasingSegments(List<Integer> yCoordinates, int k) {
        int n = yCoordinates.size();
        int[] inc_lengths = new int[n];
        inc_lengths[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (yCoordinates.get(i) < yCoordinates.get(i + 1)) {
                inc_lengths[i] = inc_lengths[i + 1] + 1;
            } else {
                inc_lengths[i] = 1;
            }
        }

        int count = 0;
        for (int i = 0; i <= n - k; i++) {
            if (inc_lengths[i] >= k) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        // Test cases
        testCase(Arrays.asList(1, 2, 3, 4), 4, 1, "Sample Case 1");
        testCase(Arrays.asList(1, 2, 3, 3, 4, 5), 3, 2, "Sample Case 0");
        testCase(Arrays.asList(6, 5, 7, 8, 10), 3, 2, "Example Case");
        testCase(Arrays.asList(1, 2, 3, 4, 5), 2, 4, "All Increasing");
        testCase(Arrays.asList(5, 4, 3, 2, 1), 2, 0, "All Decreasing");
        testCase(List.of(1), 1, 1, "Single Element");

        // Large data case
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeList.add(i);
        }
        testCase(largeList, 1000, 99001, "Large Data Case");
    }

    private static void testCase(List<Integer> yCoordinates, int k, int expected, String caseName) {
        int result = countIncreasingSegments(yCoordinates, k);
        System.out.println(caseName + ": " + (result == expected ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}
