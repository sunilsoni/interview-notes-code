package com.interview.notes.code.year.y2025.may.amazon.test8;

import java.util.*;

public class GenomeMutationTime {

    public static int findTime(String genome, char mutation) {
        LinkedList<Character> list = new LinkedList<>();
        for (char c : genome.toCharArray()) list.add(c);

        int time = 0;
        while (true) {
            ListIterator<Character> it = list.listIterator();
            List<Integer> toRemove = new ArrayList<>();
            int idx = 0;
            Character prev = null;
            while (it.hasNext()) {
                char cur = it.next();
                if (cur == mutation && prev != null && prev != mutation) {
                    toRemove.add(idx - 1); // mark the previous char to remove
                }
                prev = cur;
                idx++;
            }
            if (toRemove.isEmpty()) break;
            // Remove from right to left to avoid shifting
            for (int i = toRemove.size() - 1; i >= 0; i--) {
                int pos = toRemove.get(i);
                ListIterator<Character> remIt = list.listIterator(pos);
                remIt.next();
                remIt.remove();
            }
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
