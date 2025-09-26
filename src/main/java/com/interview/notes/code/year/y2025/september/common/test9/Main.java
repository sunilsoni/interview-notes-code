package com.interview.notes.code.year.y2025.september.common.test9;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static int[] sortAByB(int N, int M, int[] A, int[] B) {
        Map<Integer, Long> freq = Arrays.stream(A).boxed()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        List<Integer> out = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int b : B) {
            if (seen.add(b) && freq.containsKey(b)) {
                long cnt = freq.remove(b);
                for (int i = 0; i < cnt; i++) out.add(b);
            }
        }
        freq.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> {
                    for (int i = 0; i < e.getValue(); i++) out.add(e.getKey());
                });
        return out.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        record TC(int[] A, int[] B, int[] exp) {
        }
        List<TC> cases = Arrays.asList(
                new TC(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6}, new int[]{2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19}),
                new TC(new int[]{5, 5, 5}, new int[]{5}, new int[]{5, 5, 5}),
                new TC(new int[]{1, 2, 3}, new int[]{4, 5}, new int[]{1, 2, 3})
        );
        for (int i = 0; i < cases.size(); i++) {
            TC tc = cases.get(i);
            int[] res = sortAByB(tc.A.length, tc.B.length, tc.A, tc.B);
            System.out.println("Test " + (i + 1) + ": " + (Arrays.equals(res, tc.exp) ? "PASS" : "FAIL"));
        }
        int N = 100000, M = 1000;
        Random rnd = new Random(0);
        int[] A = rnd.ints(N, 0, 10000).toArray();
        int[] B = Arrays.stream(A).distinct().limit(M).toArray();
        int[] big = sortAByB(N, B.length, A, B);
        System.out.println("Large: " + big.length);
    }
}