package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GetMinMachinesSolution {

    public static int getMinMachines(List<Integer> start, List<Integer> end) {
        int n = start.size();
        int[] s = start.stream().mapToInt(Integer::intValue).sorted().toArray();
        int[] e = end.stream().mapToInt(Integer::intValue).sorted().toArray();
        int i = 0, j = 0, cur = 0, best = 0;
        while (i < n && j < n) {
            if (s[i] <= e[j]) {
                cur++;
                if (cur > best) best = cur;
                i++;
            } else {
                cur--;
                j++;
            }
        }
        return best;
    }

    private static List<Integer> list(int... a) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    private static boolean run(List<Integer> s, List<Integer> e, int exp) {
        int got = getMinMachines(s, e);
        System.out.println(got == exp ? "PASS" : "FAIL");
        return got == exp;
    }

    public static void main(String[] args) {
        run(list(2, 1, 5, 5, 8), list(5, 3, 8, 6, 12), 3);
        run(list(2, 2, 2, 2), list(5, 5, 5, 5), 4);
        run(list(1, 4, 7), list(2, 5, 8), 1);
        run(list(1, 3, 5), list(5, 7, 9), 2);
        run(list(1), list(1), 1);
        run(list(1_000_000_000, 1_000_000_000), list(1_000_000_000, 1_000_000_000), 2);

        int n1 = 200000;
        List<Integer> s1 = IntStream.range(0, n1).map(i -> i * 2).boxed().collect(Collectors.toList());
        List<Integer> e1 = IntStream.range(0, n1).map(i -> i * 2 + 1).boxed().collect(Collectors.toList());
        boolean largeOk1 = getMinMachines(s1, e1) == 1;
        System.out.println("Large input test passed: " + largeOk1);

        int n2 = 100000;
        List<Integer> s2 = IntStream.range(0, n2).map(i -> 0).boxed().collect(Collectors.toList());
        List<Integer> e2 = IntStream.range(0, n2).map(i -> n2).boxed().collect(Collectors.toList());
        boolean largeOk2 = getMinMachines(s2, e2) == n2;
        System.out.println("Large input test passed: " + largeOk2);
    }
}