package com.interview.notes.code.year.y2026.april.assessments.test8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class MinimumStressPathResolver {
    
    public static int getMinimumStress(int graphNodes, List<Integer> graphFrom, List<Integer> graphTo, List<Integer> graphWeight, int source, int destination) {
        if (source == destination) return 0;
        
        var p = IntStream.rangeClosed(0, graphNodes).toArray();
        var edges = new int[graphFrom.size()][3];
        
        for (var i = 0; i < edges.length; i++) {
            edges[i] = new int[]{graphFrom.get(i), graphTo.get(i), graphWeight.get(i)};
        }
        
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        
        for (var e : edges) {
            var r1 = find(p, e[0]);
            var r2 = find(p, e[1]);
            if (r1 != r2) p[r1] = r2;
            if (find(p, source) == find(p, destination)) return e[2];
        }
        
        return -1;
    }

    static int find(int[] p, int i) {
        return p[i] == i ? i : (p[i] = find(p, p[i]));
    }

    public static void main(String[] args) {
        test(1, 4, List.of(1, 1, 2, 4), List.of(2, 4, 3, 3), List.of(100, 10, 200, 20), 1, 3, 20);
        
        test(2, 5, List.of(1, 2, 1, 4, 1, 5), List.of(2, 3, 4, 3, 5, 3), List.of(10, 5, 3, 2, 4, 6), 1, 3, 3);
        
        test(3, 3, List.of(1), List.of(2), List.of(10), 1, 3, -1);
        
        test(4, 100000, List.of(1), List.of(100000), List.of(1000000000), 1, 100000, 1000000000);
    }

    static void test(int id, int nodes, List<Integer> from, List<Integer> to, List<Integer> w, int s, int d, int exp) {
        var res = getMinimumStress(nodes, from, to, w, s, d);
        System.out.println("Test " + id + ": " + (res == exp ? "PASS" : "FAIL"));
    }
}