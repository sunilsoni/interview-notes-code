package com.interview.notes.code.months.oct24.amz.test5;

import java.util.*;

/*
MMORPG Gaming company. Today we are building a small module.
 Tell player if its possible to go from City A to City B.
  */
public class ReachabilityChecker {
    private Map<String, Map<String, Set<String>>> worldMap;

    public ReachabilityChecker(Map<String, Map<String, Set<String>>> initialMap) {
        this.worldMap = new HashMap<>(initialMap);
    }

    public static void main(String[] args) {
        // Initialize world map
        Map<String, Map<String, Set<String>>> initialMap = new HashMap<>();
        initialMap.put("CityA", new HashMap<>());
        initialMap.get("CityA").put("CityB", new HashSet<>(Arrays.asList("car", "walk")));
        initialMap.get("CityA").put("CityC", new HashSet<>(Collections.singletonList("car")));

        initialMap.put("CityB", new HashMap<>());
        initialMap.get("CityB").put("CityA", new HashSet<>(Arrays.asList("car", "walk")));
        initialMap.get("CityB").put("CityD", new HashSet<>(Arrays.asList("car", "walk")));

        initialMap.put("CityC", new HashMap<>());
        initialMap.get("CityC").put("CityA", new HashSet<>(Collections.singletonList("car")));
        initialMap.get("CityC").put("CityD", new HashSet<>(Collections.singletonList("walk")));

        initialMap.put("CityD", new HashMap<>());
        initialMap.get("CityD").put("CityB", new HashSet<>(Arrays.asList("car", "walk")));
        initialMap.get("CityD").put("CityC", new HashSet<>(Collections.singletonList("walk")));

        ReachabilityChecker checker = new ReachabilityChecker(initialMap);

        // Test cases
        runTestCase(checker, "CityA", "CityD", "car", true);
        runTestCase(checker, "CityA", "CityD", "walk", true);
        runTestCase(checker, "CityA", "CityC", "walk", false);
        runTestCase(checker, "CityA", "CityE", "car", false);

        // Large data input test
        Map<String, Map<String, Set<String>>> largeMap = generateLargeMap(1000);
        checker.updateWorldMap(largeMap);
        runTestCase(checker, "City0", "City999", "car", true);
    }

    private static void runTestCase(ReachabilityChecker checker, String start, String end, String mode, boolean expected) {
        boolean result = checker.canReach(start, end, mode);
        System.out.printf("Test case: %s to %s via %s - %s%n",
                start, end, mode, result == expected ? "PASS" : "FAIL");
    }

    private static Map<String, Map<String, Set<String>>> generateLargeMap(int size) {
        Map<String, Map<String, Set<String>>> largeMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String cityName = "City" + i;
            largeMap.put(cityName, new HashMap<>());
            if (i > 0) {
                String prevCity = "City" + (i - 1);
                largeMap.get(cityName).put(prevCity, new HashSet<>(Arrays.asList("car", "walk")));
                largeMap.get(prevCity).put(cityName, new HashSet<>(Arrays.asList("car", "walk")));
            }
        }
        return largeMap;
    }

    public void updateWorldMap(Map<String, Map<String, Set<String>>> newMap) {
        this.worldMap = new HashMap<>(newMap);
    }

    public boolean canReach(String startCity, String endCity, String travelMode) {
        if (!worldMap.containsKey(startCity) || !worldMap.containsKey(endCity)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(startCity);
        visited.add(startCity);

        while (!queue.isEmpty()) {
            String currentCity = queue.poll();
            if (currentCity.equals(endCity)) {
                return true;
            }

            Map<String, Set<String>> neighbors = worldMap.get(currentCity);
            for (Map.Entry<String, Set<String>> entry : neighbors.entrySet()) {
                String neighborCity = entry.getKey();
                Set<String> availableModes = entry.getValue();

                if (availableModes.contains(travelMode) && !visited.contains(neighborCity)) {
                    visited.add(neighborCity);
                    queue.offer(neighborCity);
                }
            }
        }

        return false;
    }
}
