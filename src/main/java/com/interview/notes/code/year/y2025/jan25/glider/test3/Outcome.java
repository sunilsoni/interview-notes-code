package com.interview.notes.code.year.y2025.jan25.glider.test3;

import java.util.*;

class Outcome {
    public static String sortedHashMap(String string) {
        if (string == null || string.isEmpty()) {
            return "";
        }

        // Create and populate HashMap from input string
        HashMap<String, String> map = new HashMap<>();
        string = string.substring(1, string.length() - 1); // Remove outer braces
        String[] pairs = string.split(", ");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            map.put(keyValue[0], keyValue[1]);
        }

        // Sort entries by values in descending order
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (a, b) -> b.getValue().compareTo(a.getValue()));

        // Build output string
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i).getKey())
                    .append("=")
                    .append(list.get(i).getValue());
            if (i < list.size() - 1) {
                result.append(", ");
            }
        }
        result.append("}");

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        testCase("{A=Apple, B=Banana, C=Cherry, D=Date, F=fruit}",
                "{F=fruit, D=Date, C=Cherry, B=Banana, A=Apple}",
                "Test 1");

        testCase("{X=Zebra, Y=Yellow, Z=Apple}",
                "{X=Zebra, Y=Yellow, Z=Apple}",
                "Test 2");

        testCase("", "", "Test 3");

        testCase("{A=Apple}", "{A=Apple}", "Test 4");
    }

    private static void testCase(String input, String expected, String testName) {
        String result = sortedHashMap(input);
        System.out.println(testName + ": " +
                (result.equals(expected) ? "PASS" : "FAIL") +
                "\nInput: " + input +
                "\nExpected: " + expected +
                "\nGot: " + result + "\n");
    }
}
