package com.interview.notes.code.year.y2025.may.amazon.test4;

import java.util.*;

public class GenomeMutationTime {

    public static int findTime(String genome, char mutation) {
        List<Character> chars = new ArrayList<>();
        for (char c : genome.toCharArray()) chars.add(c);
        int time = 0;

        while (true) {
            Set<Integer> toRemove = new HashSet<>();
            for (int i = 1; i < chars.size(); i++) {
                if (chars.get(i) == mutation && chars.get(i - 1) != mutation) {
                    toRemove.add(i - 1); // Mark left neighbor for removal
                }
            }
            if (toRemove.isEmpty()) break;
            List<Character> next = new ArrayList<>();
            for (int i = 0; i < chars.size(); i++) {
                if (!toRemove.contains(i)) {
                    next.add(chars.get(i));
                }
            }
            chars = next;
            time++;
        }
        return time;
    }

    public static void test(String genome, char mutation, int expected) {
        int result = findTime(genome, mutation);
        String status = result == expected ? "PASS" : "FAIL";
        System.out.printf("Test genome=\"%s\" mutation='%c' | Expected=%d, Got=%d => %s%n",
                genome, mutation, expected, result, status);
    }

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
        test("aaa", 'a', 0);
        test("bbbb", 'a', 0);
        test("aabbaa", 'b', 1);
        test("zzzzzz", 'z', 0);
        test("zabc", 'z', 1);

        // Large test case
        String largeGenome = generateLargeGenome(100_000, 'x', 'y', 2);
        long start = System.currentTimeMillis();
        int result = findTime(largeGenome, 'y');
        long end = System.currentTimeMillis();
        System.out.printf("Large test (len=100_000): result=%d, time=%dms => %s%n", result, (end - start), result == 1 ? "PASS" : "FAIL");
    }
}
