package com.interview.notes.code.year.y2025.november.assessment.test1;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RubbleRoverCollector {

    public static int solve(int P, List<Integer> a) {
        int n = a.size();
        int count = 0;
        int left, right;
        P = P - 1;
        if (a.get(P) == 1) count++;

        for (int d = 1; d < n; d++) {
            left = P - d >= 0 ? a.get(P - d) : 0;
            right = P + d < n ? a.get(P + d) : 0;

            if (left == 1 && right == 1) count += 2;
            else if ((left == 1 && right == 0 && P + d >= n) ||
                     (right == 1 && left == 0 && P - d < 0)) count += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> test1 = Arrays.asList(1, 0, 1, 1, 1);
        List<Integer> test2 = Arrays.asList(0, 1, 1, 0);
        List<Integer> largeData = IntStream.range(0, 100).mapToObj(i -> i % 2).collect(Collectors.toList());

        Map<String, Integer> expectedResults = new LinkedHashMap<>();
        expectedResults.put("Test1", 3);
        expectedResults.put("Test2", 2);
        expectedResults.put("LargeData", 100);

        Map<String, Integer> actualResults = new LinkedHashMap<>();
        actualResults.put("Test1", solve(3, test1));
        actualResults.put("Test2", solve(4, test2));
        actualResults.put("LargeData", solve(50, largeData));

        expectedResults.forEach((k, v) -> {
            int result = actualResults.get(k);
            System.out.println(k + ": " + (result == v ? "PASS" : "FAIL") + " (Expected: " + v + ", Got: " + result + ")");
        });
    }
}
