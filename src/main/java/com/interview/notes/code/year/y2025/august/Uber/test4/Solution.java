package com.interview.notes.code.year.y2025.august.Uber.test4;

import java.util.*;

public class Solution {
    static int[] solution(int[] primary, int[] secondary, int[][] operations) {
        Map<Integer, Integer> pri = new HashMap<>(), sec = new HashMap<>();
        Arrays.stream(primary).forEach(v -> pri.merge(v, 1, Integer::sum));
        Arrays.stream(secondary).forEach(v -> sec.merge(v, 1, Integer::sum));
        List<Integer> out = new ArrayList<>();
        for (int[] op : operations) {
            if (op[0] == 0) {
                int i = op[1], nv = op[2], ov = secondary[i];
                if (ov != nv) {
                    sec.computeIfPresent(ov, (k, c) -> c == 1 ? null : c - 1);
                    secondary[i] = nv;
                    sec.merge(nv, 1, Integer::sum);
                }
            } else {
                long t = op[1], ans = 0;
                for (Map.Entry<Integer, Integer> e : pri.entrySet()) {
                    long need = t - e.getKey();
                    if (need >= Integer.MIN_VALUE && need <= Integer.MAX_VALUE) {
                        Integer sc = sec.get((int) need);
                        if (sc != null) ans += (long) e.getValue() * sc;
                    }
                }
                out.add((int) ans);
            }
        }
        return out.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3}, new int[]{3, 4}, new int[][]{{1, 5}, {0, 0, 1}, {1, 5}}))); // [2,1]
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 2}, new int[]{2, 3}, new int[][]{{1, 4}, {0, 0, 3}, {1, 5}}))); // [3,4]
    }
}