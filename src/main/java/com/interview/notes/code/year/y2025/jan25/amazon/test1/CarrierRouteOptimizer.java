package com.interview.notes.code.year.y2025.jan25.amazon.test1;

import java.util.*;

public class CarrierRouteOptimizer {

    public static Result findOptimalRoute(List<List<Integer>> carrierRoutes, int origin, int destination) {
        // Edge case: origin equals destination
        if (origin == destination) {
            return new Result(0, Arrays.asList(origin));
        }

        // Map station -> list of route indices
        Map<Integer, List<Integer>> stationToRoutes = new HashMap<>();
        for (int i = 0; i < carrierRoutes.size(); i++) {
            for (int station : carrierRoutes.get(i)) {
                stationToRoutes.computeIfAbsent(station, k -> new ArrayList<>()).add(i);
            }
        }

        // BFS initialization
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> routeParent = new HashMap<>();  // For route graph traversal reconstruction
        Set<Integer> visitedRoutes = new HashSet<>();

        // Start with all routes that contain the origin
        List<Integer> originRoutes = stationToRoutes.getOrDefault(origin, new ArrayList<>());
        for (int routeIndex : originRoutes) {
            queue.offer(routeIndex);
            visitedRoutes.add(routeIndex);
            routeParent.put(routeIndex, -1); // no parent for starting routes
        }

        int destinationRoute = -1;
        // BFS over routes
        while (!queue.isEmpty()) {
            int currentRoute = queue.poll();
            // If current route contains destination, we're done
            if (carrierRoutes.get(currentRoute).contains(destination)) {
                destinationRoute = currentRoute;
                break;
            }
            // Traverse neighboring routes (those sharing any station with current route)
            for (int station : carrierRoutes.get(currentRoute)) {
                for (int neighborRoute : stationToRoutes.getOrDefault(station, new ArrayList<>())) {
                    if (!visitedRoutes.contains(neighborRoute)) {
                        visitedRoutes.add(neighborRoute);
                        queue.offer(neighborRoute);
                        routeParent.put(neighborRoute, currentRoute);
                    }
                }
            }
        }

        // If no route found containing destination
        if (destinationRoute == -1) {
            return new Result(-1, new ArrayList<>()); // or appropriate "no route" marker
        }

        // Reconstruct the route chain of carrier routes from origin to destination
        List<Integer> routeChain = new ArrayList<>();
        int current = destinationRoute;
        while (current != -1) {
            routeChain.add(current);
            current = routeParent.get(current);
        }
        Collections.reverse(routeChain); // Now routeChain has route indices from start to destination

        // Reconstruct the station sequence using intersections between consecutive routes
        List<Integer> stationPath = new ArrayList<>();
        stationPath.add(origin);
        for (int i = 0; i < routeChain.size(); i++) {
            List<Integer> currentRouteList = carrierRoutes.get(routeChain.get(i));
            // If it's the last route, directly add destination
            if (i == routeChain.size() - 1) {
                stationPath.add(destination);
            } else {
                // Find common station between current and next route
                List<Integer> nextRouteList = carrierRoutes.get(routeChain.get(i + 1));
                Set<Integer> set = new HashSet<>(currentRouteList);
                for (int station : nextRouteList) {
                    if (set.contains(station) && station != origin) {
                        // select a transfer station that's not the origin
                        stationPath.add(station);
                        break;
                    }
                }
            }
        }

        return new Result(routeChain.size(), stationPath);
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> carrierRoutes = new ArrayList<>();
        carrierRoutes.add(Arrays.asList(1, 3, 7));
        carrierRoutes.add(Arrays.asList(7, 18, 36));

        // Test 1: Provided example
        int origin = 3;
        int destination = 36;
        Result result = findOptimalRoute(carrierRoutes, origin, destination);
        System.out.println("Test 1 result: " + result + " Expected: {carrier_count=2, route=[3, 7, 36]}");

        // Test 2: Direct route available
        carrierRoutes.clear();
        carrierRoutes.add(Arrays.asList(3, 7, 36));
        origin = 3;
        destination = 36;
        result = findOptimalRoute(carrierRoutes, origin, destination);
        System.out.println("Test 2 result: " + result + " Expected: {carrier_count=1, route=[3, 36]}");

        // Test 3: No possible route
        carrierRoutes.clear();
        carrierRoutes.add(Arrays.asList(1, 2, 3));
        carrierRoutes.add(Arrays.asList(4, 5, 6));
        origin = 3;
        destination = 6;
        result = findOptimalRoute(carrierRoutes, origin, destination);
        System.out.println("Test 3 result: " + result + " Expected: {carrier_count=-1, route=[]}");

        // Test 4: Origin equals destination
        carrierRoutes.clear();
        carrierRoutes.add(Arrays.asList(1, 2, 3));
        origin = 2;
        destination = 2;
        result = findOptimalRoute(carrierRoutes, origin, destination);
        System.out.println("Test 4 result: " + result + " Expected: {carrier_count=0, route=[2]}");

        // Additional tests can be added as needed.
    }

    static class Result {
        int carrierCount;
        List<Integer> route;

        Result(int count, List<Integer> route) {
            this.carrierCount = count;
            this.route = route;
        }

        @Override
        public String toString() {
            return "{carrier_count=" + carrierCount + ", route=" + route + "}";
        }
    }
}
