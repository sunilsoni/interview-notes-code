package com.interview.notes.code.year.y2026.march.amazon.test1;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BinaryErrorMinimizer {

    public static int getMinErrors(String errorString, int x, int y) {
        return (int) (Math.min(calculate(errorString, x, y, '1'), calculate(errorString, x, y, '0')) % 1000000007);
    }

    private static long calculate(String text, int x, int y, char startChar) {
        long zeros = 0;
        long ones = 0;
        long total01 = 0;
        long total10 = 0;
        long zerosLeft = 0;
        long onesLeft = 0;
        long minCost = 0;
        char[] characters = text.toCharArray();

        for (char c : characters) {
            char current = (c == '!') ? startChar : c;
            if (current == '0') {
                zeros++;
                total10 += ones;
            } else {
                ones++;
                total01 += zeros;
            }
        }

        minCost = (total01 * x) + (total10 * y);

        for (char c : characters) {
            if (c == '!') {
                if (startChar == '1') {
                    total01 += ones - onesLeft - 1 - zerosLeft;
                    total10 += onesLeft - zeros + zerosLeft;
                    zeros++;
                    ones--;
                    zerosLeft++;
                } else {
                    total10 += zeros - zerosLeft - 1 - onesLeft;
                    total01 += zerosLeft - ones + onesLeft;
                    zeros--;
                    ones++;
                    onesLeft++;
                }
                minCost = Math.min(minCost, (total01 * x) + (total10 * y));
            } else {
                if (c == '0') {
                    zerosLeft++;
                } else {
                    onesLeft++;
                }
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        Stream.of(
            new Object[]{"101!1", 2, 3, 9},
            new Object[]{"0!!1!1", 2, 3, 10},
            new Object[]{"!!!!!!!", 23, 47, 0}
        ).forEach(testData -> runTest((String) testData[0], (int) testData[1], (int) testData[2], (int) testData[3]));

        String largeData = IntStream.range(0, 100000)
            .mapToObj(index -> index % 3 == 0 ? "!" : (index % 2 == 0 ? "0" : "1"))
            .collect(Collectors.joining());

        long startTime = System.currentTimeMillis();
        getMinErrors(largeData, 100000, 100000);
        long endTime = System.currentTimeMillis();

        if (endTime - startTime < 2000) {
            System.out.println("Large Data Test: PASS");
        } else {
            System.out.println("Large Data Test: FAIL");
        }
    }

    private static void runTest(String text, int x, int y, int expected) {
        int result = getMinErrors(text, x, y);
        if (result == expected) {
            System.out.println("Test " + text + ": PASS");
        } else {
            System.out.println("Test " + text + ": FAIL");
        }
    }
}