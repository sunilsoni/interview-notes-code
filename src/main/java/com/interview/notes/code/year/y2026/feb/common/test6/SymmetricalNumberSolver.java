package com.interview.notes.code.year.y2026.feb.common.test6;

import java.util.LinkedHashMap;
import java.util.stream.LongStream;

public class SymmetricalNumberSolver {

    public static void main(String[] args) {
        var testCases = new LinkedHashMap<Integer, String>();
        testCases.put(808, "818");
        testCases.put(2133, "2222");
        testCases.put(-100, "0");
        testCases.put(9, "11");
        testCases.put(12345, "12421");
        testCases.put(99999, "100001");

        testCases.forEach((input, expected) -> {
            var result = findNextSymmetrical(input);
            var status = result.equals(expected) ? "PASS" : "FAIL";
            System.out.println("Input: " + input + " | Expected: " + expected + " | Actual: " + result + " -> " + status);
        });
    }

    public static String findNextSymmetrical(int k) {
        return String.valueOf(
            LongStream.iterate((long) k + 1, n -> n + 1)
                .filter(n -> {
                    var s = String.valueOf(n);
                    return s.contentEquals(new StringBuilder(s).reverse());
                })
                .findFirst()
                .getAsLong()
        );
    }
}