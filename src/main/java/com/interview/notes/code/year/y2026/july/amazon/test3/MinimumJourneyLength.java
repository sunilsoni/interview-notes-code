package com.interview.notes.code.year.y2026.july.amazon.test3;

import java.util.*;
import java.util.stream.IntStream;

public class MinimumJourneyLength {

    public static List<Integer> getMinimumLength(int roadNodes, List<Integer> roadsFrom, List<Integer> roadsTo, List<Integer> roadsWeight) {
        List<List<Edge>> adj = IntStream.rangeClosed(0, roadNodes)
                .<List<Edge>>mapToObj(i -> new ArrayList<>())
                .toList();

        for (var i = 0; i < roadsFrom.size(); i++) {
            adj.get(roadsFrom.get(i)).add(new Edge(roadsTo.get(i), roadsWeight.get(i)));
        }

        return IntStream.rangeClosed(1, roadNodes).mapToObj(start -> {
            var dist = new int[roadNodes + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));

            for (var e : adj.get(start)) {
                if (e.weight() < dist[e.to()]) {
                    dist[e.to()] = e.weight();
                    pq.add(new int[]{e.to(), e.weight()});
                }
            }

            while (!pq.isEmpty()) {
                var curr = pq.poll();
                var u = curr[0];
                var d = curr[1];

                if (d > dist[u]) {
                    continue;
                }

                if (u == start) {
                    return d;
                }

                for (var e : adj.get(u)) {
                    if (d + e.weight() < dist[e.to()]) {
                        dist[e.to()] = d + e.weight();
                        pq.add(new int[]{e.to(), dist[e.to()]});
                    }
                }
            }

            return 0;
        }).toList();
    }

    public static void main(String[] args) {
        test(3, List.of(1, 2, 3), List.of(2, 3, 1), List.of(10, 10, 10), List.of(30, 30, 30));
        test(4, List.of(1, 2, 3, 4), List.of(2, 3, 1, 3), List.of(14, 23, 23, 30), List.of(60, 60, 60, 0));
        test(4, List.of(1, 3, 2, 4, 1), List.of(3, 2, 1, 2, 4), List.of(20, 25, 15, 10, 5), List.of(30, 30, 60, 30));

        var largeFrom = new ArrayList<Integer>();
        var largeTo = new ArrayList<Integer>();
        var largeWeight = new ArrayList<Integer>();
        var expected = new ArrayList<Integer>();

        for (var i = 1; i <= 1000; i++) {
            largeFrom.add(i);
            largeTo.add(i % 1000 + 1);
            largeWeight.add(1);
            expected.add(1000);
        }

        test(1000, largeFrom, largeTo, largeWeight, expected);
    }

    private static void test(int nodes, List<Integer> from, List<Integer> to, List<Integer> weight, List<Integer> expected) {
        var result = getMinimumLength(nodes, from, to, weight);
        if (result.equals(expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }

    record Edge(int to, int weight) {}
}