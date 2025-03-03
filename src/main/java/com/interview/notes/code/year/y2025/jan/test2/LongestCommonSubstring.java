package com.interview.notes.code.year.y2025.jan.test2;

public class LongestCommonSubstring {
    public static String findLongestCommonSubstring(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        // Start with first string as reference
        String result = arr[0];

        // Compare with other strings
        for (int i = 1; i < arr.length; i++) {
            result = findCommonSubstring(result, arr[i]);
            if (result.isEmpty()) {
                return "";
            }
        }

        return result;
    }

    private static String findCommonSubstring(String str1, String str2) {
        String result = "";
        int maxLength = 0;

        // Compare all possible substrings
        for (int i = 0; i < str1.length(); i++) {
            for (int j = i + 1; j <= str1.length(); j++) {
                String current = str1.substring(i, j);
                // Check if substring has no repeating characters
                if (hasNoRepeatingChars(current) &&
                        str2.contains(current) &&
                        current.length() > maxLength) {
                    maxLength = current.length();
                    result = current;
                }
            }
        }
        return result;
    }

    private static boolean hasNoRepeatingChars(String str) {
        boolean[] seen = new boolean[128];
        for (char c : str.toCharArray()) {
            if (seen[c]) {
                return false;
            }
            seen[c] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Given array
        String[] filledArr = {
                "India", "Individual", "Indefinite", "Indifferent",
                "Flow", "Flower", "Power"
        };

        System.out.println("Test Case 1:");
        String result = findLongestCommonSubstring(filledArr);
        System.out.println("Result: " + result);
        System.out.println("Expected: Indi");
        System.out.println("Status: " + (result.equals("Indi") ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 2: Empty array
        System.out.println("Test Case 2: Empty array");
        result = findLongestCommonSubstring(new String[]{});
        System.out.println("Result: " + result);
        System.out.println("Expected: \"\"");
        System.out.println("Status: " + (result.equals("") ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 3: Single string
        System.out.println("Test Case 3: Single string");
        result = findLongestCommonSubstring(new String[]{"Hello"});
        System.out.println("Result: " + result);
        System.out.println("Expected: Hello");
        System.out.println("Status: " + (result.equals("Hello") ? "PASS" : "FAIL"));
        System.out.println();

        // Performance Test
        System.out.println("Performance Test:");
        String[] largeArray = new String[1000];
        for (int i = 0; i < 1000; i++) {
            largeArray[i] = "Test" + i + "Individual";
        }

        long startTime = System.currentTimeMillis();
        result = findLongestCommonSubstring(largeArray);
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Result: " + result);
    }
}
