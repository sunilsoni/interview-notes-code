package com.interview.notes.code.year.y2025.jan25.amazon.test1;

import java.util.*;

/*
You are responsible for optimizing shipments of inventory between fulfillment centers and docks
(junctions where multiple carriers can transfer inventory) minimizing hand-offs between carriers.
Given a list of carrier routes e.g. [[1, 3, 7], [7, 18, 36]] where each index represents a carrier
route and each value in the route represents a fulfillment center or dock, as well as an origin and
destination fulfillment center, return the route as well the number of carriers required to transport
the inventory.

Example:

carrier_routes = [[1, 3, 7], [7, 18, 36]]
origin_fc = 3
destination_fc = 36

result = {carrier_count=2, route=[3, 7, 36]}
 */
public class InventoryShipmentSolution {

    /**
     * Finds the minimal-carrier route from origin to destination.
     *
     * @param carrierRoutes A list of routes. Each route is a list of stops (FCs or docks).
     * @param origin        The origin fulfillment center.
     * @param destination   The destination fulfillment center.
     * @return A result containing the minimal route (list of stops)
     * and the number of carriers used.
     */
    public static Result findMinimalCarrierRoute(List<List<Integer>> carrierRoutes,
                                                 int origin,
                                                 int destination) {

        // Quick edge case: if origin == destination, no carrier needed.
        if (origin == destination) {
            return new Result(0, Arrays.asList(origin));
        }

        // Convert each route into a Set of stops for quick membership checks
        List<Set<Integer>> routeSets = new ArrayList<>();
        for (List<Integer> route : carrierRoutes) {
            routeSets.add(new HashSet<>(route));
        }

        // Identify which routes contain the origin and which contain the destination
        List<Integer> originRoutes = new ArrayList<>();
        List<Integer> destinationRoutes = new ArrayList<>();
        for (int i = 0; i < routeSets.size(); i++) {
            if (routeSets.get(i).contains(origin)) {
                originRoutes.add(i);
            }
            if (routeSets.get(i).contains(destination)) {
                destinationRoutes.add(i);
            }
        }

        // If no route contains the origin or destination, we cannot form a path
        if (originRoutes.isEmpty() || destinationRoutes.isEmpty()) {
            return null; // indicates no path
        }

        // Build adjacency: two routes are adjacent if they share at least one stop
        // For large data, a more efficient approach is recommended.
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < routeSets.size(); i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < routeSets.size(); i++) {
            for (int j = i + 1; j < routeSets.size(); j++) {
                // Check intersection
                Set<Integer> setA = routeSets.get(i);
                Set<Integer> setB = routeSets.get(j);
                // A quick intersection check (retainAll would be destructive, so we do manual check)
                if (hasIntersection(setA, setB)) {
                    adjacencyList.get(i).add(j);
                    adjacencyList.get(j).add(i);
                }
            }
        }

        // BFS in the "route-graph" to find the minimal number of route hops
        // We'll track:
        // - The route ID
        // - The path (sequence of routes) that led there
        Queue<RouteState> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        // Enqueue starting routes
        for (int startRoute : originRoutes) {
            queue.offer(new RouteState(startRoute, new ArrayList<>(Arrays.asList(startRoute))));
            visited.add(startRoute);
        }

        // Perform BFS
        List<Integer> destinationRoutePath = null;
        while (!queue.isEmpty()) {
            RouteState currentState = queue.poll();
            int currentRoute = currentState.routeId;

            // Check if this route contains the destination
            if (routeSets.get(currentRoute).contains(destination)) {
                // Found a chain of routes that leads us to the destination
                destinationRoutePath = currentState.routePath;
                break;
            }

            // Otherwise explore neighbors
            for (int neighbor : adjacencyList.get(currentRoute)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    // Build the new route path
                    List<Integer> newPath = new ArrayList<>(currentState.routePath);
                    newPath.add(neighbor);

                    queue.offer(new RouteState(neighbor, newPath));
                }
            }
        }

        // If we couldn't reach any destination route
        if (destinationRoutePath == null) {
            return null; // no path found
        }

        // Reconstruct the actual stop-sequence using the route path
        // We know the first route includes origin, the last route includes destination
        List<Integer> finalStops = buildStopPath(destinationRoutePath, routeSets, origin, destination);

        // Number of carriers is the number of routes used
        int carrierCount = destinationRoutePath.size();

        return new Result(carrierCount, finalStops);
    }

    /**
     * Checks if two sets intersect in at least one element.
     */
    private static boolean hasIntersection(Set<Integer> setA, Set<Integer> setB) {
        // Return true if any element in the smaller set is in the larger set
        if (setA.size() < setB.size()) {
            for (Integer x : setA) {
                if (setB.contains(x)) {
                    return true;
                }
            }
        } else {
            for (Integer x : setB) {
                if (setA.contains(x)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Reconstructs the final list of stops based on the route path.
     * <p>
     * Each adjacent pair of routes in routePath must share at least one stop.
     * We pick that shared stop to transition from one route to the next.
     * The first route's relevant starting stop is the origin,
     * the last route's relevant ending stop is the destination.
     */
    private static List<Integer> buildStopPath(List<Integer> routePath,
                                               List<Set<Integer>> routeSets,
                                               int origin,
                                               int destination) {
        List<Integer> result = new ArrayList<>();
        // Start with origin
        result.add(origin);

        // For each consecutive pair of route IDs, find the shared stop 
        // that allows us to move from one route to the next
        for (int i = 0; i < routePath.size() - 1; i++) {
            int routeA = routePath.get(i);
            int routeB = routePath.get(i + 1);
            // Find an intersection stop between routeA and routeB
            Integer intersectionStop = findIntersectionStop(routeSets.get(routeA),
                    routeSets.get(routeB),
                    result.get(result.size() - 1));
            if (intersectionStop != null && intersectionStop != result.get(result.size() - 1)) {
                result.add(intersectionStop);
            }
        }

        // Finally, ensure the last stop is the destination
        if (result.get(result.size() - 1) != destination) {
            result.add(destination);
        }

        return result;
    }

    /**
     * Finds a stop that belongs to both sets, starting from the 'currentStop' if possible.
     * This method tries to keep continuity if 'currentStop' is in both routes,
     * otherwise picks any intersection stop.
     */
    private static Integer findIntersectionStop(Set<Integer> setA,
                                                Set<Integer> setB,
                                                int currentStop) {
        // If currentStop is in both, that is our intersection
        if (setA.contains(currentStop) && setB.contains(currentStop)) {
            return currentStop;
        }
        // Otherwise pick any common stop
        for (Integer s : setA) {
            if (setB.contains(s)) {
                return s;
            }
        }
        return null; // theoretically should not happen in a valid path
    }

    /**
     * Main method to test the solution with multiple scenarios.
     * We will simply print PASS or FAIL for each test.
     */
    public static void main(String[] args) {
        // 1) Provided Example
        List<List<Integer>> carrierRoutes1 = new ArrayList<>();
        carrierRoutes1.add(Arrays.asList(1, 3, 7));
        carrierRoutes1.add(Arrays.asList(7, 18, 36));
        int origin1 = 3;
        int destination1 = 36;

        Result result1 = findMinimalCarrierRoute(carrierRoutes1, origin1, destination1);
        System.out.println("Test 1 result: " + result1);
        // We expect 2 carriers, route [3, 7, 36]
        if (result1 != null
                && result1.getCarrierCount() == 2
                && result1.getStops().equals(Arrays.asList(3, 7, 36))) {
            System.out.println("Test 1: PASS");
        } else {
            System.out.println("Test 1: FAIL");
        }

        // 2) Edge Case: origin == destination
        List<List<Integer>> carrierRoutes2 = new ArrayList<>();
        carrierRoutes2.add(Arrays.asList(5, 10, 15));
        carrierRoutes2.add(Arrays.asList(3, 5, 7));
        int origin2 = 5;
        int destination2 = 5;  // same as origin

        Result result2 = findMinimalCarrierRoute(carrierRoutes2, origin2, destination2);
        // Expect 0 carriers, route [5]
        System.out.println("Test 2 result: " + result2);
        if (result2 != null
                && result2.getCarrierCount() == 0
                && result2.getStops().equals(Arrays.asList(5))) {
            System.out.println("Test 2: PASS");
        } else {
            System.out.println("Test 2: FAIL");
        }

        // 3) No Route Available
        List<List<Integer>> carrierRoutes3 = new ArrayList<>();
        carrierRoutes3.add(Arrays.asList(1, 2));
        carrierRoutes3.add(Arrays.asList(3, 4));
        carrierRoutes3.add(Arrays.asList(5, 6));
        int origin3 = 1;
        int destination3 = 6;

        Result result3 = findMinimalCarrierRoute(carrierRoutes3, origin3, destination3);
        // Expect null (no path)
        System.out.println("Test 3 result: " + result3);
        if (result3 == null) {
            System.out.println("Test 3: PASS");
        } else {
            System.out.println("Test 3: FAIL");
        }

        // 4) Larger Test (just a brief demonstration, not truly "large" but more routes)
        List<List<Integer>> carrierRoutes4 = new ArrayList<>();
        carrierRoutes4.add(Arrays.asList(10, 20, 30, 40));
        carrierRoutes4.add(Arrays.asList(20, 50, 60));
        carrierRoutes4.add(Arrays.asList(60, 70));
        carrierRoutes4.add(Arrays.asList(70, 80, 40)); // connects back to 40
        carrierRoutes4.add(Arrays.asList(80, 90, 100));
        int origin4 = 10;
        int destination4 = 100;

        Result result4 = findMinimalCarrierRoute(carrierRoutes4, origin4, destination4);
        System.out.println("Test 4 result: " + result4);
        // We won't fix an exact route, we just check if it's not null and has a reasonable path
        if (result4 != null) {
            System.out.println("Test 4: PASS (found a path)");
        } else {
            System.out.println("Test 4: FAIL (no path found but expected one)");
        }
    }

    /**
     * Simple container for BFS states (route ID and path of route IDs).
     */
    private static class RouteState {
        int routeId;
        List<Integer> routePath;

        RouteState(int routeId, List<Integer> routePath) {
            this.routeId = routeId;
            this.routePath = routePath;
        }
    }

    /**
     * Holds the final result: number of carriers and the route (list of stops).
     */
    public static class Result {
        int carrierCount;
        List<Integer> stops;

        public Result(int carrierCount, List<Integer> stops) {
            this.carrierCount = carrierCount;
            this.stops = stops;
        }

        public int getCarrierCount() {
            return carrierCount;
        }

        public List<Integer> getStops() {
            return stops;
        }

        @Override
        public String toString() {
            return "{carrier_count=" + carrierCount + ", route=" + stops + "}";
        }
    }
}
