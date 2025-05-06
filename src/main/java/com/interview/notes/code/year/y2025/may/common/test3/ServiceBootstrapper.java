package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.*;

public class ServiceBootstrapper {
    static class Service {
        String name;
        int bootTime;
        List<String> dependencies;

        Service(String name, int bootTime, List<String> dependencies) {
            this.name = name;
            this.bootTime = bootTime;
            this.dependencies = dependencies;
        }
    }

    // Brute Force Solution
    public static int calculateMinBootTime(Map<String, Service> services) {
        if (services.isEmpty()) return 0;
        
        int maxTime = 0;
        Set<String> visited = new HashSet<>();
        
        // Calculate boot time for each service
        for (Service service : services.values()) {
            if (!visited.contains(service.name)) {
                maxTime = Math.max(maxTime, getServiceBootTime(service, services, new HashSet<>()));
            }
        }
        
        return maxTime;
    }

    private static int getServiceBootTime(Service service, 
                                        Map<String, Service> services, 
                                        Set<String> visited) {
        if (visited.contains(service.name)) {
            throw new RuntimeException("Circular dependency detected!");
        }

        visited.add(service.name);
        
        // If no dependencies, return just the boot time
        if (service.dependencies.isEmpty()) {
            return service.bootTime;
        }

        int maxDependencyTime = 0;
        
        // Find the maximum time among dependencies
        for (String dep : service.dependencies) {
            Service dependentService = services.get(dep);
            if (dependentService == null) {
                throw new RuntimeException("Dependent service not found: " + dep);
            }
            int depTime = getServiceBootTime(dependentService, services, new HashSet<>(visited));
            maxDependencyTime = Math.max(maxDependencyTime, depTime);
        }

        // Return max dependency time plus current service boot time
        return maxDependencyTime + service.bootTime;
    }

    public static void main(String[] args) {
        // Test Case 1: Simple linear dependency
        Map<String, Service> test1 = new HashMap<>();
        test1.put("A", new Service("A", 1, Arrays.asList()));
        test1.put("B", new Service("B", 2, Arrays.asList("A")));
        test1.put("C", new Service("C", 3, Arrays.asList("B")));

        // Test Case 2: Multiple dependencies
        Map<String, Service> test2 = new HashMap<>();
        test2.put("A", new Service("A", 1, Arrays.asList()));
        test2.put("B", new Service("B", 2, Arrays.asList("A")));
        test2.put("C", new Service("C", 3, Arrays.asList("A")));
        test2.put("D", new Service("D", 1, Arrays.asList("B", "C")));

        // Additional Test Case 3: Complex dependencies
        Map<String, Service> test3 = new HashMap<>();
        test3.put("A", new Service("A", 2, Arrays.asList()));
        test3.put("B", new Service("B", 3, Arrays.asList("A")));
        test3.put("C", new Service("C", 1, Arrays.asList("A")));
        test3.put("D", new Service("D", 4, Arrays.asList("B", "C")));
        test3.put("E", new Service("E", 2, Arrays.asList("D")));

        // Run tests
        runTest("Test 1 - Linear Dependency", test1, 6);
        runTest("Test 2 - Multiple Dependencies", test2, 7);
        runTest("Test 3 - Complex Dependencies", test3, 11);
    }

    private static void runTest(String testName, Map<String, Service> services, int expectedResult) {
        try {
            int result = calculateMinBootTime(services);
            boolean passed = result == expectedResult;
            System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
            System.out.println("Expected: " + expectedResult + ", Got: " + result);
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception: " + e.getMessage() + ")");
        }
    }
}
