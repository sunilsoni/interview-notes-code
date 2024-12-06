package com.interview.notes.code.months.dec24.test4;

public class LongestPrefixMajority {
    public static void main(String[] args) {
        test(new String[]{"India", "Individual", "Indefinite", "Indifferent", "Flow", "Flower", "Power"}, "Ind");
        test(new String[]{"Flow", "Flower"}, "Flo");
        test(new String[]{"Power"}, "P");
        test(new String[]{"Car", "Carrot", "Carpool"}, "Car");
        test(new String[]{"Same", "Same", "Same"}, "Same");
        test(new String[]{"No", "Match"}, "N"); // For example

        // Additional large test: all start with "Data"
        String[] large = new String[100000];
        for (int i = 0; i < large.length; i++) {
            large[i] = "DataSomething" + i;
        }
        test(large, "Data");
    }

    private static void test(String[] input, String expected) {
        String result = findPrefix(input);
        if (result.equals(expected)) System.out.println("PASS");
        else System.out.println("FAIL: expected=" + expected + " got=" + result);
    }

    private static String findPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // Find the prefix with the maximum number of words sharing it
        String bestPrefix = "";
        int bestCount = 0;

        // Choose a reference word to generate possible prefixes
        String reference = shortestString(strs);

        // Check all prefixes of the reference word
        for (int length = 1; length <= reference.length(); length++) {
            String prefix = reference.substring(0, length);
            int count = countPrefix(strs, prefix);
            // Update best prefix if we find more words or same count but longer prefix
            if (count > bestCount || (count == bestCount && prefix.length() > bestPrefix.length())) {
                bestPrefix = prefix;
                bestCount = count;
            }
        }
        return bestPrefix;
    }

    private static String shortestString(String[] strs) {
        String shortest = strs[0];
        for (String s : strs) {
            if (s.length() < shortest.length()) shortest = s;
        }
        return shortest;
    }

    private static int countPrefix(String[] strs, String prefix) {
        int count = 0;
        for (String s : strs) {
            if (s.startsWith(prefix)) count++;
        }
        return count;
    }
}
