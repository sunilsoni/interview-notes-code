package com.interview.notes.code.year.y2025.september.Hackerank.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static int countFaults(int n, List<String> logs) {
        Map<String, Integer> errorCount = new HashMap<>();
        int replacements = 0;

        for (String log : logs) {
            String[] parts = log.split(" ");
            String server = parts[0];
            String status = parts[1];

            if (status.equals("success")) {
                errorCount.put(server, 0);
            } else {
                int count = errorCount.getOrDefault(server, 0) + 1;
                if (count == 3) {
                    replacements++;
                    errorCount.put(server, 0);
                } else {
                    errorCount.put(server, count);
                }
            }
        }
        return replacements;
    }

    public static void main(String[] args) {
        List<String> logs1 = Arrays.asList("s1 error", "s2 error", "s1 error", "s4 success", "s5 error", "s3 success", "s1 error");
        int expected1 = 1;
        int result1 = countFaults(5, logs1);
        System.out.println("Test 1: " + (result1 == expected1 ? "PASS" : "FAIL") + " | Expected=" + expected1 + ", Got=" + result1);

        List<String> logs2 = Arrays.asList("s2 error", "s3 error", "s2 error", "s2 error", "s3 error", "s3 error");
        int expected2 = 2;
        int result2 = countFaults(3, logs2);
        System.out.println("Test 2: " + (result2 == expected2 ? "PASS" : "FAIL") + " | Expected=" + expected2 + ", Got=" + result2);

        List<String> logs3 = Arrays.asList("s1 error", "s1 error", "s1 error");
        int expected3 = 1;
        int result3 = countFaults(1, logs3);
        System.out.println("Test 3: " + (result3 == expected3 ? "PASS" : "FAIL") + " | Expected=" + expected3 + ", Got=" + result3);

        List<String> logs4 = Arrays.asList("s1 success", "s1 success", "s1 success");
        int expected4 = 0;
        int result4 = countFaults(1, logs4);
        System.out.println("Test 4: " + (result4 == expected4 ? "PASS" : "FAIL") + " | Expected=" + expected4 + ", Got=" + result4);

        List<String> logs5 = IntStream.range(0, 20000).mapToObj(i -> "s1 error").collect(Collectors.toList());
        int expected5 = 20000 / 3;
        int result5 = countFaults(1, logs5);
        System.out.println("Large Test: " + (result5 == expected5 ? "PASS" : "FAIL") + " | Expected=" + expected5 + ", Got=" + result5);
    }
}