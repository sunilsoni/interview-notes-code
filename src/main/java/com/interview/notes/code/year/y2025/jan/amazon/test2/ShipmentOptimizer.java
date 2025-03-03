package com.interview.notes.code.year.y2025.jan.amazon.test2;

import java.util.*;

public class ShipmentOptimizer {
    public static Result findOptimalRoute(List<List<Integer>> carrierRoutes,
                                          int origin, int destination) {
        // Build a graph of connections
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<String, Integer> routeToCarrier = new HashMap<>();

        // Create graph and track which carrier handles each route
        for (int i = 0; i < carrierRoutes.size(); i++) {
            List<Integer> route = carrierRoutes.get(i);
            for (int j = 0; j < route.size() - 1; j++) {
                int from = route.get(j);
                int to = route.get(j + 1);

                graph.putIfAbsent(from, new ArrayList<>());
                graph.get(from).add(to);

                routeToCarrier.put(from + "," + to, i);
            }
        }

        // Find shortest path using BFS
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> prev = new HashMap<>();

        queue.offer(origin);
        prev.put(origin, null);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == destination) break;

            List<Integer> neighbors = graph.getOrDefault(current,
                    new ArrayList<>());
            for (int next : neighbors) {
                if (!prev.containsKey(next)) {
                    prev.put(next, current);
                    queue.offer(next);
                }
            }
        }

        // Reconstruct path and count carriers
        List<Integer> path = new ArrayList<>();
        Set<Integer> carriers = new HashSet<>();

        Integer current = destination;
        while (current != null) {
            path.add(0, current);
            Integer previous = prev.get(current);
            if (previous != null) {
                String key = previous + "," + current;
                carriers.add(routeToCarrier.get(key));
            }
            current = previous;
        }

        return new Result(carriers.size(), path);
    }

    public static void main(String[] args) {
        // Test cases
        test1();
        test2();
        test3();
        testLargeInput();
    }

    private static void test1() {
        List<List<Integer>> routes = Arrays.asList(
                Arrays.asList(1, 3, 7),
                Arrays.asList(7, 18, 36)
        );
        Result result = findOptimalRoute(routes, 3, 36);
        System.out.println("Test 1: " +
                (result.carrierCount == 2 &&
                        result.route.equals(Arrays.asList(3, 7, 36)) ?
                        "PASS" : "FAIL"));
    }

    private static void test2() {
        List<List<Integer>> routes = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 4, 5)
        );
        Result result = findOptimalRoute(routes, 1, 5);
        System.out.println("Test 2: " +
                (result.carrierCount == 2 &&
                        result.route.equals(Arrays.asList(1, 2, 3, 4, 5)) ?
                        "PASS" : "FAIL"));
    }

    private static void test3() {
        // Edge case: No route exists
        List<List<Integer>> routes = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        );
        Result result = findOptimalRoute(routes, 1, 4);
        System.out.println("Test 3: " +
                (result.route.size() == 0 ? "PASS" : "FAIL"));
    }

    private static void testLargeInput() {
        // Generate large test case
        List<List<Integer>> routes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            routes.add(Arrays.asList(i, i + 1, i + 2));
        }
        long start = System.currentTimeMillis();
        Result result = findOptimalRoute(routes, 0, 999);
        long end = System.currentTimeMillis();
        System.out.println("Large Input Test: " +
                ((end - start) < 1000 ? "PASS" : "FAIL") +
                " (Time: " + (end - start) + "ms)");
    }

    static class Result {
        int carrierCount;
        List<Integer> route;

        Result(int count, List<Integer> path) {
            this.carrierCount = count;
            this.route = path;
        }

        @Override
        public String toString() {
            return "carriers=" + carrierCount + ", route=" + route;
        }
    }
}
