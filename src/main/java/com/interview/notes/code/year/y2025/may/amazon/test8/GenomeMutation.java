package com.interview.notes.code.year.y2025.may.amazon.test8;

import java.util.*;

public class GenomeMutation {

    public static int findTime(String genome, char mutation) {
        int maxTime = 0;
        int currentMutationCount = 0;

        for (int i = 0; i < genome.length(); i++) {
            if (genome.charAt(i) == mutation) {
                currentMutationCount++;
            } else if (currentMutationCount > 0) {
                maxTime = Math.max(maxTime + 1, currentMutationCount);
            }
        }

        return maxTime;
    }

    // Simple main method testing without JUnit
    public static void main(String[] args) {
        Map<String[], Integer> testCases = new LinkedHashMap<>();

        testCases.put(new String[]{"tamem", "m"}, 2);
        testCases.put(new String[]{"momoz", "m"}, 2);
        testCases.put(new String[]{"luvzliz", "z"}, 3);
        testCases.put(new String[]{"aaa", "a"}, 0);
        testCases.put(new String[]{"bbbb", "a"}, 0);
        testCases.put(new String[]{"aabbaa", "b"}, 1);
        testCases.put(new String[]{"zzzzzz", "z"}, 0);
        testCases.put(new String[]{"zabc", "z"}, 1);

        // Large input test case
        char[] largeGenomeArr = new char[100000];
        Arrays.fill(largeGenomeArr, 'a');
        largeGenomeArr[largeGenomeArr.length - 1] = 'm';
        testCases.put(new String[]{new String(largeGenomeArr), "m"}, 99999);

        testCases.forEach((input, expected) -> {
            int result = findTime(input[0], input[1].charAt(0));
            System.out.printf("Test genome=\"%s\" mutation='%s' | Expected=%d, Got=%d => %s%n",
                    input[0].length() > 20 ? "[large input]" : input[0],
                    input[1], expected, result,
                    (result == expected ? "PASS" : "FAIL"));
        });
    }
}
