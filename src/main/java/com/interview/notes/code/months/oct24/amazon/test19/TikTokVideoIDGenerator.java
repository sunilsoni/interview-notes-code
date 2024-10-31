package com.interview.notes.code.months.oct24.amazon.test19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TikTokVideoIDGenerator {

    /*
     * Complete the 'countMinimumCharactersForVideoIDs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING idStream
     *  2. STRING_ARRAY videoIds
     */

    public static List<Integer> countMinimumCharactersForVideoIDs(String idStream, List<String> videoIds) {
        int n = idStream.length();
        int[][] cumulativeCounts = new int[10][n + 1];

        // Build cumulative counts for each digit
        for (int i = 1; i <= n; i++) {
            int digit = idStream.charAt(i - 1) - '0';
            for (int d = 0; d <= 9; d++) {
                cumulativeCounts[d][i] = cumulativeCounts[d][i - 1];
            }
            cumulativeCounts[digit][i]++;
        }

        List<Integer> result = new ArrayList<>();
        for (String target : videoIds) {
            int[] targetCounts = new int[10];
            for (char ch : target.toCharArray()) {
                targetCounts[ch - '0']++;
            }

            // Quick check if target can be formed at all
            boolean possible = true;
            for (int d = 0; d <= 9; d++) {
                if (targetCounts[d] > cumulativeCounts[d][n]) {
                    possible = false;
                    break;
                }
            }
            if (!possible) {
                result.add(-1);
                continue;
            }

            // Binary search to find minimal index
            int left = 1;
            int right = n;
            int answer = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                boolean canForm = true;
                for (int d = 0; d <= 9; d++) {
                    if (cumulativeCounts[d][mid] < targetCounts[d]) {
                        canForm = false;
                        break;
                    }
                }
                if (canForm) {
                    answer = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            result.add(answer);
        }
        return result;
    }

    public static void main(String[] args) {
        // Sample Test Cases
        testSample0();
        testSample1();
        testAdditionalCases();
        testEdgeCases();
        testLargeInputCase();
    }

    private static void testSample0() {
        System.out.println("Running Sample Test Case 0");
        String idStream = "064819848398";
        List<String> videoIds = Arrays.asList("088", "364", "07");
        List<Integer> expected = Arrays.asList(7, 10, -1);
        List<Integer> result = countMinimumCharactersForVideoIDs(idStream, videoIds);
        System.out.println("Expected: " + expected);
        System.out.println("Result:   " + result);
        System.out.println(result.equals(expected) ? "PASS\n" : "FAIL\n");
    }

    private static void testSample1() {
        System.out.println("Running Sample Test Case 1");
        String idStream = "1112223333444";
        List<String> videoIds = Arrays.asList("121", "3", "12345", "11234");
        List<Integer> expected = Arrays.asList(4, 7, -1, 10);
        List<Integer> result = countMinimumCharactersForVideoIDs(idStream, videoIds);
        System.out.println("Expected: " + expected);
        System.out.println("Result:   " + result);
        System.out.println(result.equals(expected) ? "PASS\n" : "FAIL\n");
    }

    private static void testAdditionalCases() {
        System.out.println("Running Additional Test Cases");
        String idStream = "987654";
        List<String> videoIds = Arrays.asList("456789", "4", "04");
        List<Integer> expected = Arrays.asList(6, 6, -1);
        List<Integer> result = countMinimumCharactersForVideoIDs(idStream, videoIds);
        System.out.println("Expected: " + expected);
        System.out.println("Result:   " + result);
        System.out.println(result.equals(expected) ? "PASS\n" : "FAIL\n");
    }

    private static void testEdgeCases() {
        System.out.println("Running Edge Test Cases");

        // Edge case where idStream is empty
        String idStream = "";
        List<String> videoIds = Arrays.asList("1", "");
        List<Integer> expected = Arrays.asList(-1, 0);
        List<Integer> result = countMinimumCharactersForVideoIDs(idStream, videoIds);
        System.out.println("Test Case with empty idStream");
        System.out.println("Expected: " + expected);
        System.out.println("Result:   " + result);
        System.out.println(result.equals(expected) ? "PASS\n" : "FAIL\n");

        // Edge case where videoIds contains empty string
        idStream = "12345";
        videoIds = Arrays.asList("");
        expected = Arrays.asList(0);
        result = countMinimumCharactersForVideoIDs(idStream, videoIds);
        System.out.println("Test Case with empty target video ID");
        System.out.println("Expected: " + expected);
        System.out.println("Result:   " + result);
        System.out.println(result.equals(expected) ? "PASS\n" : "FAIL\n");

        // Edge case where idStream does not contain any digits from target
        idStream = "11111";
        videoIds = Arrays.asList("2");
        expected = Arrays.asList(-1);
        result = countMinimumCharactersForVideoIDs(idStream, videoIds);
        System.out.println("Test Case with idStream not containing target digits");
        System.out.println("Expected: " + expected);
        System.out.println("Result:   " + result);
        System.out.println(result.equals(expected) ? "PASS\n" : "FAIL\n");
    }

    private static void testLargeInputCase() {
        System.out.println("Running Large Input Test Case");

        // Generate large idStream and videoIds
        StringBuilder idStreamBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            idStreamBuilder.append((char) ('0' + (i % 10)));
        }
        String idStream = idStreamBuilder.toString();

        List<String> videoIds = new ArrayList<>();
        videoIds.add("0123456789".repeat(5)); // total length 50
        videoIds.add("99999");
        videoIds.add("1234567890");

        List<Integer> expected = Arrays.asList(500, 50000, 100);
        List<Integer> result = countMinimumCharactersForVideoIDs(idStream, videoIds);

        System.out.println("Expected: [500, 50000, 100]");
        System.out.println("Result:   " + result);
        System.out.println(result.equals(expected) ? "PASS\n" : "FAIL\n");
    }
}
