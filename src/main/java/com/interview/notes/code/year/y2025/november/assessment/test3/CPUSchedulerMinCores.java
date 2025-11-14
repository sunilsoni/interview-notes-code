package com.interview.notes.code.year.y2025.november.assessment.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CPUSchedulerMinCores {

    public static int getMinCores(List<Integer> start, List<Integer> end) {
        List<Integer> startList = start.stream().sorted().collect(Collectors.toList());
        List<Integer> endList = end.stream().sorted().collect(Collectors.toList());
        int cores = 0;
        int maxCores = 0;
        int i = 0, j = 0;
        int n = startList.size();
        while (i < n && j < n) {
            if (startList.get(i) <= endList.get(j)) {
                cores++;
                maxCores = Math.max(maxCores, cores);
                i++;
            } else {
                cores--;
                j++;
            }
        }
        return maxCores;
    }

    public static void main(String[] args) {
        List<Integer> start1 = Arrays.asList(1, 2, 3);
        List<Integer> end1 = Arrays.asList(3, 3, 5);
        int result1 = getMinCores(start1, end1);
        System.out.println("Test Case 1: " + (result1 == 3 ? "PASS" : "FAIL"));

        List<Integer> start2 = Arrays.asList(1, 4, 7);
        List<Integer> end2 = Arrays.asList(2, 4, 10);
        int result2 = getMinCores(start2, end2);
        System.out.println("Test Case 2: " + (result2 == 1 ? "PASS" : "FAIL"));

        List<Integer> start3 = Arrays.asList(1, 3, 4);
        List<Integer> end3 = Arrays.asList(3, 5, 6);
        int result3 = getMinCores(start3, end3);
        System.out.println("Test Case 3: " + (result3 == 2 ? "PASS" : "FAIL"));

        int largeN = 100000;
        List<Integer> largeStart = IntStream.range(0, largeN).boxed().collect(Collectors.toList());
        List<Integer> largeEnd = IntStream.range(1, largeN + 1).boxed().collect(Collectors.toList());
        long startTime = System.currentTimeMillis();
        int result4 = getMinCores(largeStart, largeEnd);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test: " + (result4 == 1 ? "PASS" : "FAIL") + " | Time: " + (endTime - startTime) + "ms");
    }
}
