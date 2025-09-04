package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.*;

public class BracketValidator {

    public static int minOperations(String str) {
        if (str.length() % 2 != 0) return -1;  // only odd length is impossible

        int balance = 0, operations = 0;
        for (char c : str.toCharArray()) {
            if (c == '{') {
                balance++;
            } else {
                if (balance == 0) {
                    operations++;
                    balance = 1;
                } else {
                    balance--;
                }
            }
        }
        operations += balance / 2;
        return operations;
    }

    public static void main(String[] args) {
        Map<String, Integer> testCases = new LinkedHashMap<>();
        testCases.put("{}", 0);
        testCases.put("{{", 1);
        testCases.put("}}", 1);
        testCases.put("}{}{", 2);      // corrected expected
        testCases.put("{{}}", 0);
        testCases.put("{{{", -1);
        testCases.put("{{{{", 2);
        testCases.put("}}{{", 2);
        testCases.put("{{}{}}", 0);
        testCases.put("{{}{{}}", 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append('{');
        for (int i = 0; i < 100000; i++) sb.append('}');
        testCases.put(sb.toString(), 0);

        testCases.forEach((input, expected) -> {
            int result = minOperations(input);
            String status = result == expected ? "PASS" : "FAIL";
            System.out.println("Input: " + (input.length() > 20 ? input.substring(0, 20) + "..." : input)
                    + " | Expected: " + expected + " | Got: " + result + " | " + status);
        });
    }
}