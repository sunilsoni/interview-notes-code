package com.interview.notes.code.year.y2025.may.amazon.test7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenomeMutationTime {

    // Main logic for finding mutation time
    public static int findTime(String genome, char mutation) {
        // Convert genome to a modifiable list
        List<Character> chars = genome.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        int time = 0;

        while (true) {
            List<Integer> toRemove = new ArrayList<>();
            // Go through the list from left to right
            for (int i = 1; i < chars.size(); i++) {
                if (chars.get(i) == mutation && chars.get(i - 1) != mutation) {
                    toRemove.add(i - 1);
                }
            }
            if (toRemove.isEmpty()) break; // No more nucleotides can be removed

            // Remove from end to avoid index shifting
            for (int j = toRemove.size() - 1; j >= 0; j--) {
                chars.remove((int) toRemove.get(j));
            }
            time++;
        }
        return time;
    }

    // Helper method for a single test case
    public static void test(String genome, char mutation, int expected) {
        int result = findTime(genome, mutation);
        String status = result == expected ? "PASS" : "FAIL";
        System.out.printf("Test genome=\"%s\" mutation='%c' | Expected=%d, Got=%d => %s%n",
                genome, mutation, expected, result, status);
    }

    // Generate a large test genome with a pattern for performance test
    public static String generateLargeGenome(int len, char normal, char mutation, int mutationFreq) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i % mutationFreq == 0) sb.append(mutation);
            else sb.append(normal);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Provided sample cases
        test("tamem", 'm', 2);
        test("momoz", 'm', 2);
        test("luvzliz", 'z', 3);

        // Additional test cases
        test("aaa", 'a', 0);       // No left neighbor is ever removed
        test("bbbb", 'a', 0);      // No mutation in string
        test("aabbaa", 'b', 1);    // Simple case

        // Edge case: All mutations
        test("zzzzzz", 'z', 0);    // No non-mutation left neighbor

        // Edge case: mutation at the beginning
        test("zabc", 'z', 1);

        // Large case: mutation every other character
        String largeGenome = generateLargeGenome(100_000, 'x', 'y', 2);
        long start = System.currentTimeMillis();
        int result = findTime(largeGenome, 'y');
        long end = System.currentTimeMillis();
        System.out.printf("Large test (len=100_000): result=%d, time=%dms => %s%n", result, (end - start), result == 1 ? "PASS" : "FAIL");
    }
}
