package com.interview.notes.code.year.y2026.july.amazon.test4;

import java.util.*;
import java.util.stream.IntStream;

public class MinimumRoundTripLength {

    public static List<Integer> getMinimumLength(
            int roadsNodes,
            List<Integer> roadsFrom,
            List<Integer> roadsTo,
            List<Integer> roadsWeight) {

        var out = IntStream.range(0, roadsNodes)
                .mapToObj(i -> new ArrayList<Road>())
                .toList();

        var in = IntStream.range(0, roadsNodes)
                .mapToObj(i -> new ArrayList<Road>())
                .toList();

        IntStream.range(0, roadsFrom.size()).forEach(i -> {
            int from = roadsFrom.get(i) - 1;
            int to = roadsTo.get(i) - 1;
            int weight = roadsWeight.get(i);

            out.get(from).add(new Road(to, weight));
            in.get(to).add(new Road(from, weight));
        });

        return IntStream.range(0, roadsNodes)
                .map(i -> minimum(i, out, in))
                .boxed()
                .toList();
    }

    static int minimum(
            int source,
            List<ArrayList<Road>> out,
            List<ArrayList<Road>> in) {

        var distance = new long[out.size()];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;

        var queue = new PriorityQueue<Path>(
                Comparator.comparingLong(Path::distance));

        queue.add(new Path(source, 0));

        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current.distance() != distance[current.node()]) {
                continue;
            }

            for (var road : out.get(current.node())) {
                long next = current.distance() + road.weight();

                if (next < distance[road.node()]) {
                    distance[road.node()] = next;
                    queue.add(new Path(road.node(), next));
                }
            }
        }

        long answer = Long.MAX_VALUE;

        for (var road : in.get(source)) {
            if (distance[road.node()] != Long.MAX_VALUE) {
                answer = Math.min(
                        answer,
                        distance[road.node()] + road.weight());
            }
        }

        return answer == Long.MAX_VALUE ? 0 : (int) answer;
    }

    static void test(
            String name,
            int nodes,
            List<Integer> from,
            List<Integer> to,
            List<Integer> weight,
            List<Integer> expected) {

        var actual = getMinimumLength(nodes, from, to, weight);

        System.out.println(
                name + ": " + (actual.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(
                "Sample 0",
                3,
                List.of(1, 2, 3),
                List.of(2, 3, 1),
                List.of(10, 10, 10),
                List.of(30, 30, 30));

        test(
                "Sample 1",
                4,
                List.of(1, 3, 2, 4, 1),
                List.of(3, 2, 1, 2, 4),
                List.of(20, 25, 15, 10, 5),
                List.of(30, 30, 60, 30));

        test(
                "Example",
                4,
                List.of(1, 2, 3, 4),
                List.of(2, 3, 1, 3),
                List.of(14, 23, 23, 30),
                List.of(60, 60, 60, 0));

        test(
                "Self Loop",
                3,
                List.of(1),
                List.of(1),
                List.of(7),
                List.of(7, 0, 0));

        test(
                "Two Nodes",
                2,
                List.of(1, 2),
                List.of(2, 1),
                List.of(5, 8),
                List.of(13, 13));

        int size = 1000;

        var from = IntStream.rangeClosed(1, size)
                .boxed()
                .toList();

        var to = IntStream.rangeClosed(1, size)
                .map(i -> i == size ? 1 : i + 1)
                .boxed()
                .toList();

        test(
                "Large",
                size,
                from,
                to,
                Collections.nCopies(size, 1),
                Collections.nCopies(size, size));
    }

    record Road(int node, int weight) {
    }

    record Path(int node, long distance) {
    }
}