package com.interview.notes.code.year.y2025.july.common.test5;

import java.util.*;
import java.util.stream.IntStream;

public class SlotMachine {

    /**
     * Given history of n spins (each a string of m digits),
     * where the digits in each spin are unordered,
     * compute the minimal total stops:
     * 1) For each spin, sort its digits ascending.
     * 2) For j = m-1 down to 0, take the max digit at index j over all spins.
     * 3) Sum those maxima.
     */
    public static int slotWheels(List<String> history) {
        if (history.isEmpty()) return 0;
        int n = history.size();
        int m = history.get(0).length();

        // sortedDigits[i][j] = j-th digit (ascending) of spin i
        int[][] sortedDigits = new int[n][m];
        for (int i = 0; i < n; i++) {
            // convert to int array and sort
            int[] digs = history.get(i).chars()
                    .map(c -> c - '0')
                    .sorted()
                    .toArray();
            sortedDigits[i] = digs;
        }

        // for each position j from largest to smallest, find max over spins
        int total = 0;
        for (int j = m - 1; j >= 0; j--) {
            final int col = j;
            int maxAtJ = IntStream.range(0, n)
                    .map(i -> sortedDigits[i][col])
                    .max()
                    .orElse(0);
            total += maxAtJ;
        }
        return total;
    }

    public static void main(String[] args) {
        // ---- sample cases ----
        LinkedHashMap<List<String>, Integer> samples = new LinkedHashMap<>();
        samples.put(
                Arrays.asList("712", "246", "365", "312"),
                15
        );
        samples.put(
                Arrays.asList("1112", "1111", "1211", "1111"),
                5
        );
        samples.put(
                Arrays.asList("137", "364", "115", "724"),
                14
        );

        System.out.println("=== Sample Tests ===");
        samples.forEach((hist, expected) -> {
            int got = slotWheels(hist);
            String passFail = (got == expected) ? "PASS" : "FAIL";
            System.out.printf("%s => expected=%d, got=%d [%s]%n",
                    hist, expected, got, passFail);
        });

        // ---- a large random test ----
        Random rnd = new Random(0);
        int n = 50, m = 50;
        List<String> big = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(m);
            for (int j = 0; j < m; j++) {
                sb.append(rnd.nextInt(9) + 1);  // digits 1–9
            }
            big.add(sb.toString());
        }
        System.out.println("\n=== Large random test (n=50, m=50) stops="
                + slotWheels(big));
    }
}