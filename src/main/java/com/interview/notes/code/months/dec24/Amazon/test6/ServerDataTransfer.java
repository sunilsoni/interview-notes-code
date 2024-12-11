package com.interview.notes.code.months.dec24.Amazon.test6;

import java.util.*;

public class ServerDataTransfer {
    
    public static int getMinTime(int total_servers, List<Integer> servers) {
        // Sort servers to work with ordered positions
        Collections.sort(servers);
        
        // If only 1 or 2 servers that are adjacent, return 1
        if (servers.size() <= 2 && 
            (servers.size() == 1 || Math.abs(servers.get(0) - servers.get(1)) == 1 || 
             Math.abs(servers.get(0) - servers.get(1)) == total_servers - 1)) {
            return servers.size() == 1 ? 0 : 1;
        }
        
        int minTime = Integer.MAX_VALUE;
        
        // Try each server as starting point
        for (int startIdx = 0; startIdx < servers.size(); startIdx++) {
            // Calculate clockwise and counterclockwise distances
            int totalTime = 0;
            int currentPos = servers.get(startIdx);
            
            // Track visited servers
            Set<Integer> visited = new HashSet<>();
            visited.add(currentPos);
            
            // Find nearest unvisited server and calculate minimum distance
            while (visited.size() < servers.size()) {
                int minDist = Integer.MAX_VALUE;
                int nextServer = -1;
                
                for (int server : servers) {
                    if (!visited.contains(server)) {
                        // Calculate both clockwise and counterclockwise distances
                        int clockwise = Math.abs(server - currentPos);
                        int counterClockwise = total_servers - clockwise;
                        int dist = Math.min(clockwise, counterClockwise);
                        
                        if (dist < minDist) {
                            minDist = dist;
                            nextServer = server;
                        }
                    }
                }
                
                totalTime += minDist;
                currentPos = nextServer;
                visited.add(nextServer);
            }
            
            minTime = Math.min(minTime, totalTime);
        }
        
        return minTime;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(1, 10, Arrays.asList(4, 6, 2, 9), 7);
        runTest(2, 5, Arrays.asList(1, 5), 1);
        runTest(3, 8, Arrays.asList(2, 6, 8), 4);
        
        // Edge cases
        runTest(4, 3, Arrays.asList(1), 0);
        runTest(5, 4, Arrays.asList(1, 2), 1);
        runTest(6, 1000000000, Arrays.asList(1, 999999999), 1);
        
        // Large data test
        List<Integer> largeServers = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeServers.add(i * 2 + 1);
        }
        runTest(7, 1000000000, largeServers, null);
    }
    
    private static void runTest(int testNumber, int total_servers, 
                              List<Integer> servers, Integer expectedResult) {
        try {
            System.out.println("Test Case " + testNumber + ":");
            System.out.println("Total Servers: " + total_servers);
            System.out.println("Server List: " + 
                (servers.size() > 10 ? "[Large Data Set]" : servers));
            
            long startTime = System.currentTimeMillis();
            int result = getMinTime(total_servers, servers);
            long endTime = System.currentTimeMillis();
            
            System.out.println("Execution Time: " + (endTime - startTime) + "ms");
            
            if (expectedResult != null) {
                boolean passed = result == expectedResult;
                System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
                System.out.println("Expected: " + expectedResult);
                System.out.println("Got: " + result);
            } else {
                System.out.println("Performance Test Result: " + result);
            }
            
            System.out.println("-------------------");
            
        } catch (Exception e) {
            System.out.println("Test Case " + testNumber + " FAILED with exception:");
            e.printStackTrace();
            System.out.println("-------------------");
        }
    }
}
