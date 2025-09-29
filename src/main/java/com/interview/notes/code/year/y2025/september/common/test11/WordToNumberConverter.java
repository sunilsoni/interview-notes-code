package com.interview.notes.code.year.y2025.september.common.test11;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordToNumberConverter {

    private static final Map<String, Integer> numberMap = new HashMap<>();

    static {
        numberMap.put("zero", 0);
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
        numberMap.put("ten", 10);
        numberMap.put("eleven", 11);
        numberMap.put("twelve", 12);
        numberMap.put("thirteen", 13);
        numberMap.put("fourteen", 14);
        numberMap.put("fifteen", 15);
        numberMap.put("sixteen", 16);
        numberMap.put("seventeen", 17);
        numberMap.put("eighteen", 18);
        numberMap.put("nineteen", 19);
        numberMap.put("twenty", 20);
        numberMap.put("thirty", 30);
        numberMap.put("forty", 40);
        numberMap.put("fifty", 50);
        numberMap.put("sixty", 60);
        numberMap.put("seventy", 70);
        numberMap.put("eighty", 80);
        numberMap.put("ninety", 90);
        numberMap.put("hundred", 100);
        numberMap.put("thousand", 1000);
    }

    public static int convertWordToNumber(String input) {
        input = input.toLowerCase().replaceAll(" and ", " ").trim();
        String[] tokens = input.split("\\s+");

        int result = 0;
        int current = 0;

        for (String token : tokens) {
            if (!numberMap.containsKey(token)) {
                throw new IllegalArgumentException("Invalid token: " + token);
            }

            int value = numberMap.get(token);

            if (value == 100) {
                current *= 100;
            } else if (value == 1000) {
                current *= 1000;
                result += current;
                current = 0;
            } else {
                current += value;
            }
        }

        return result + current;
    }

    // ✅ Test method
    public static void main(String[] args) {
        Map<String, Integer> testCases = new LinkedHashMap<>();
        testCases.put("one hundred and twenty five", 125);
        testCases.put("two thousand and twenty four", 2024);
        testCases.put("seven hundred", 700);
        testCases.put("nineteen", 19);
        testCases.put("forty two", 42);
        testCases.put("one thousand", 1000);
        testCases.put("one thousand one hundred", 1100);
        testCases.put("zero", 0);

        System.out.println("---- Word to Number Conversion Tests ----");
        for (Map.Entry<String, Integer> entry : testCases.entrySet()) {
            String input = entry.getKey();
            int expected = entry.getValue();
            try {
                int result = convertWordToNumber(input);
                System.out.println("Input: \"" + input + "\" → Output: " + result + " | Expected: " + expected + " | " + (result == expected ? "PASS" : "FAIL"));
            } catch (Exception e) {
                System.out.println("Input: \"" + input + "\" → Error: " + e.getMessage() + " | FAIL");
            }
        }
    }
}