package com.interview.notes.code.year.y2025.october.CodeSignal.test8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class ContiguousHousesLongestSegment {
    static int[] solution(int[] queries) {
        Map<Integer, Integer> startToEnd = new HashMap<>();
        Map<Integer, Integer> endToStart = new HashMap<>();
        int[] ans = new int[queries.length];
        int best = 0;
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i];
            boolean hasLeft = endToStart.containsKey(x - 1);
            boolean hasRight = startToEnd.containsKey(x + 1);

            int newStart = hasLeft ? endToStart.get(x - 1) : x;
            int newEnd = hasRight ? startToEnd.get(x + 1) : x;

            if (hasLeft) {
                int leftStart = endToStart.remove(x - 1);
                startToEnd.remove(leftStart);
            }
            if (hasRight) {
                int rightEnd = startToEnd.remove(x + 1);
                endToStart.remove(rightEnd);
            }

            startToEnd.put(newStart, newEnd);
            endToStart.put(newEnd, newStart);

            best = Math.max(best, newEnd - newStart + 1);
            ans[i] = best;
        }
        return ans;
    }

    static int[] dsuCheck(int[] queries) {
        class DSU {
            final Map<Integer, Integer> parent = new HashMap<>();
            final Map<Integer, Integer> size = new HashMap<>();
            int max = 0;

            int find(int x) {
                int p = parent.get(x);
                if (p != x) parent.put(x, p = find(p));
                return p;
            }

            void add(int x) {
                if (parent.containsKey(x)) return;
                parent.put(x, x);
                size.put(x, 1);
                max = Math.max(max, 1);
                if (parent.containsKey(x - 1)) unite(x, x - 1);
                if (parent.containsKey(x + 1)) unite(x, x + 1);
            }

            void unite(int a, int b) {
                int ra = find(a), rb = find(b);
                if (ra == rb) return;
                if (size.get(ra) < size.get(rb)) {
                    int t = ra;
                    ra = rb;
                    rb = t;
                }
                parent.put(rb, ra);
                size.put(ra, size.get(ra) + size.get(rb));
                max = Math.max(max, size.get(ra));
            }
        }
        DSU d = new DSU();
        int[] out = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            d.add(queries[i]);
            out[i] = d.max;
        }
        return out;
    }

    static void run(String name, int[] queries, int[] expected) {
        int[] got = solution(queries);
        boolean pass = Arrays.equals(got, expected);
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL") + " -> " + Arrays.toString(got));
    }

    static void crossCheck(String name, int[] queries) {
        int[] a = solution(queries);
        int[] b = dsuCheck(queries);
        boolean pass = Arrays.equals(a, b);
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL") + " -> length=" + a.length);
    }

    public static void main(String[] args) {
        run("Example1", new int[]{2, 1, 3}, new int[]{1, 2, 3});
        run("Example2", new int[]{1, 3, 0, 4}, new int[]{1, 1, 2, 2});
        run("BridgeMerge", new int[]{1, 2, 4, 3}, new int[]{1, 2, 2, 4});
        run("Single", new int[]{100}, new int[]{1});
        run("NonAdjacent", new int[]{10, 20, 30}, new int[]{1, 1, 1});

        Random r = new Random(42);
        int n = 100000;
        int[] large = IntStream.range(0, n).map(i -> i).toArray();
        for (int i = n - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int tmp = large[i];
            large[i] = large[j];
            large[j] = tmp;
        }
        crossCheck("LargeRandom", large);
    }
}
