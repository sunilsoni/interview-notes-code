package com.interview.notes.code.year.y2025.december.common.test2;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

class Solution {

    public static int bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) return 0;

        return Arrays.stream(scores)
                .collect(Collectors.groupingBy(
                        e -> e[0],
                        Collectors.averagingDouble(e -> Integer.parseInt(e[1]))
                ))
                .values()
                .stream()
                .mapToInt(avg -> (int) Math.floor(avg))
                .max()
                .orElse(0);
    }

    public static boolean doTestsPass() {
        boolean allPass = true;

        String[][] tc1 = {
                {"Bobby", "87"},
                {"Charles", "100"},
                {"Eric", "64"},
                {"Charles", "22"}
        };
        int result1 = bestAverageGrade(tc1);
        boolean pass1 = (result1 == 87);
        System.out.println("Test 1: " + (pass1 ? "PASS" : "FAIL") + " Expected:87 Got:" + result1);
        allPass &= pass1;

        String[][] tc2 = {};
        int result2 = bestAverageGrade(tc2);
        boolean pass2 = (result2 == 0);
        System.out.println("Test 2: " + (pass2 ? "PASS" : "FAIL") + " Expected:0 Got:" + result2);
        allPass &= pass2;

        int result3 = bestAverageGrade(null);
        boolean pass3 = (result3 == 0);
        System.out.println("Test 3: " + (pass3 ? "PASS" : "FAIL") + " Expected:0 Got:" + result3);
        allPass &= pass3;

        String[][] tc4 = {{"Alice", "95"}};
        int result4 = bestAverageGrade(tc4);
        boolean pass4 = (result4 == 95);
        System.out.println("Test 4: " + (pass4 ? "PASS" : "FAIL") + " Expected:95 Got:" + result4);
        allPass &= pass4;

        String[][] tc5 = {
                {"John", "-10"},
                {"Jane", "-5"},
                {"John", "-20"}
        };
        int result5 = bestAverageGrade(tc5);
        boolean pass5 = (result5 == -5);
        System.out.println("Test 5: " + (pass5 ? "PASS" : "FAIL") + " Expected:-5 Got:" + result5);
        allPass &= pass5;

        String[][] tc6 = {
                {"Tom", "90"},
                {"Tom", "91"},
                {"Jim", "89"}
        };
        int result6 = bestAverageGrade(tc6);
        boolean pass6 = (result6 == 90);
        System.out.println("Test 6: " + (pass6 ? "PASS" : "FAIL") + " Expected:90 Got:" + result6);
        allPass &= pass6;

        String[][] tc7 = {
                {"Mike", "-10"},
                {"Mike", "-11"},
                {"Sara", "-12"}
        };
        int result7 = bestAverageGrade(tc7);
        boolean pass7 = (result7 == -11);
        System.out.println("Test 7: " + (pass7 ? "PASS" : "FAIL") + " Expected:-11 Got:" + result7);
        allPass &= pass7;

        System.out.println("Test 8 - Large data running...");
        int numStudents = 1000;
        int scoresPerStudent = 100;
        int totalEntries = numStudents * scoresPerStudent;
        String[][] tc8 = new String[totalEntries][2];
        Random random = new Random(42);
        int index = 0;

        for (int i = 0; i < numStudents; i++) {
            String name = "Student_" + i;
            for (int j = 0; j < scoresPerStudent; j++) {
                tc8[index][0] = name;
                tc8[index][1] = String.valueOf(random.nextInt(101));
                index++;
            }
        }

        long start = System.currentTimeMillis();
        int result8 = bestAverageGrade(tc8);
        long end = System.currentTimeMillis();
        boolean pass8 = (result8 >= 0 && result8 <= 100);
        System.out.println("Test 8: " + (pass8 ? "PASS" : "FAIL") + " Result:" + result8 + " Time:" + (end - start) + "ms");
        allPass &= pass8;

        String[][] tc9 = {
                {"Dan", "50"},
                {"Dan", "50"},
                {"Eve", "50"}
        };
        int result9 = bestAverageGrade(tc9);
        boolean pass9 = (result9 == 50);
        System.out.println("Test 9: " + (pass9 ? "PASS" : "FAIL") + " Expected:50 Got:" + result9);
        allPass &= pass9;

        String[][] tc10 = {
                {"Zero", "0"},
                {"Zero", "0"},
                {"One", "1"}
        };
        int result10 = bestAverageGrade(tc10);
        boolean pass10 = (result10 == 1);
        System.out.println("Test 10: " + (pass10 ? "PASS" : "FAIL") + " Expected:1 Got:" + result10);
        allPass &= pass10;

        return allPass;
    }

    public static void main(String[] args) {
        System.out.println("Running Tests...");
        boolean ok = doTestsPass();
        System.out.println(ok ? "All tests PASSED" : "Some tests FAILED");
    }
}
