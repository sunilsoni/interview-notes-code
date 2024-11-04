package com.interview.notes.code.months.nov24.google;

import java.util.*;

/*
Let's define a kind of message called "Broadcast & Shut Down."
When a router receives this message, it broadcasts the same message to all other routers within its wireless range.
Then, that router shuts down, and can no longer send or receive messages.
For example, Router A is at (0, 0); Router B is at (0, 8); Router C is at (0, 17); Router D is at (11, 0).
If the wireless range is 10, when Router A sends a message, it could first reach B;
the message from Router B would further reach Router C but Router D would never receive this message.
Given a list of routers' locations (their names and the corresponding 2D coordinates), tell me whether a message from Router A can reach Router B.
Write a method / function with appropriate input and output arguments.

 */
public class RouterBroadcastShutdown {

    public static boolean canMessageReach(List<Router> routers, String startRouter, String endRouter, int range) {
        Map<String, Set<String>> graph = buildGraph(routers, range);
        Set<String> visited = new HashSet<>();
        return dfs(graph, startRouter, endRouter, visited);
    }

    private static Map<String, Set<String>> buildGraph(List<Router> routers, int range) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (Router r : routers) {
            graph.put(r.name, new HashSet<>());
        }

        for (int i = 0; i < routers.size(); i++) {
            for (int j = i + 1; j < routers.size(); j++) {
                Router r1 = routers.get(i);
                Router r2 = routers.get(j);
                if (distance(r1, r2) <= range) {
                    graph.get(r1.name).add(r2.name);
                    graph.get(r2.name).add(r1.name);
                }
            }
        }
        return graph;
    }

    private static boolean dfs(Map<String, Set<String>> graph, String current, String end, Set<String> visited) {
        if (current.equals(end)) return true;
        visited.add(current);
        for (String neighbor : graph.get(current)) {
            if (!visited.contains(neighbor) && dfs(graph, neighbor, end, visited)) {
                return true;
            }
        }
        return false;
    }

    private static double distance(Router r1, Router r2) {
        return Math.sqrt(Math.pow(r1.x - r2.x, 2) + Math.pow(r1.y - r2.y, 2));
    }

    public static void main(String[] args) {
        // Test case 1
        List<Router> routers1 = Arrays.asList(
                new Router("A", 0, 0),
                new Router("B", 0, 8),
                new Router("C", 0, 17),
                new Router("D", 11, 0)
        );
        System.out.println("Test case 1: " + (canMessageReach(routers1, "A", "B", 10) ? "PASS" : "FAIL"));
        System.out.println("Test case 2: " + (canMessageReach(routers1, "A", "C", 10) ? "PASS" : "FAIL"));
        System.out.println("Test case 3: " + (!canMessageReach(routers1, "A", "D", 10) ? "PASS" : "FAIL"));

        // Test case 4: Large data input
        List<Router> routers2 = new ArrayList<>();
        Random rand = new Random(0);
        for (int i = 0; i < 1000; i++) {
            routers2.add(new Router("R" + i, rand.nextInt(1000), rand.nextInt(1000)));
        }
        long startTime = System.currentTimeMillis();
        boolean result = canMessageReach(routers2, "R0", "R999", 50);
        long endTime = System.currentTimeMillis();
        System.out.println("Test case 4 (Large data): " + (result ? "PASS" : "FAIL"));
        System.out.println("Execution time: " + (endTime - startTime) + " ms");

        // Test case 5: Edge case - empty list
        List<Router> routers3 = new ArrayList<>();
        System.out.println("Test case 5: " + (!canMessageReach(routers3, "A", "B", 10) ? "PASS" : "FAIL"));

        // Test case 6: Edge case - single router
        List<Router> routers4 = Arrays.asList(new Router("A", 0, 0));
        System.out.println("Test case 6: " + (canMessageReach(routers4, "A", "A", 10) ? "PASS" : "FAIL"));
    }

    static class Router {
        String name;
        int x, y;

        Router(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}
