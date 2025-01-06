package com.interview.notes.code.year.y2024.dec24.test4;

public class LongestCommonPrefixDemo {
    public static void main(String[] args) {
        // Test cases
        test(new String[]{"India", "Individual", "Indefinite", "Indifferent"}, "Ind");
        test(new String[]{"Flow", "Flower", "Power"}, "");
        test(new String[]{"Car", "Carrot", "Carpool"}, "Car");
        test(new String[]{"Single"}, "Single");
        test(new String[]{}, "");
        test(new String[]{"Same", "Same", "Same"}, "Same");

        // Large data test (example: repeated words)
        String[] largeData = new String[100000];
        for (int i = 0; i < largeData.length; i++) {
            largeData[i] = "CommonPrefix" + i;
        }
        test(largeData, "CommonPrefix");
    }

    private static void test(String[] input, String expected) {
        String result = longestCommonPrefix(input);
        if (result.equals(expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String shortest = strs[0];
        for (String s : strs) {
            if (s.length() < shortest.length()) shortest = s;
        }
        for (int i = 0; i < shortest.length(); i++) {
            char c = shortest.charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != c) {
                    return shortest.substring(0, i);
                }
            }
        }
        return shortest;
    }
}
