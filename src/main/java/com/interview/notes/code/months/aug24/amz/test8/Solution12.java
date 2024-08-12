package com.interview.notes.code.months.aug24.amz.test8;

import java.util.*;

public class Solution12 {
    public static List<Integer> getServerIds(int num_servers, List<Integer> requests) {
        List<Integer> result = new ArrayList<>();
        int[] serverLoads = new int[num_servers];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Initialize the priority queue with server loads
        for (int i = 0; i < num_servers; i++) {
            minHeap.offer(new int[]{0, i}); // {load, serverId}
        }

        for (int request : requests) {
            // Get the server with the minimum load
            int[] server = minHeap.poll();
            int selectedServer = server[1];

            // Update the load of the selected server
            serverLoads[selectedServer]++;
            // Add the updated server back to the priority queue
            minHeap.offer(new int[]{serverLoads[selectedServer], selectedServer});
            result.add(selectedServer);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        int num_servers1 = 5;
        List<Integer> requests1 = Arrays.asList(0, 1, 2, 3);
        System.out.println("Test case 1 output: " + getServerIds(num_servers1, requests1));

        // Test case 2
        int num_servers2 = 5;
        List<Integer> requests2 = Arrays.asList(4, 0, 2, 2);
        System.out.println("Test case 2 output: " + getServerIds(num_servers2, requests2));

        // Additional test case
        int num_servers3 = 5;
        List<Integer> requests3 = Arrays.asList(3, 2, 3, 2, 4);
        System.out.println("Test case 3 output: " + getServerIds(num_servers3, requests3));
    }
}
/*
Actual:
Test case 1 output: [0, 4, 1, 3]
Test case 2 output: [0, 4, 1, 3]
Test case 3 output: [0, 4, 1, 3, 2]

Expected:

Test case 1 output: [0, 1, 2, 3]
Test case 2 output: [0, 0, 1, 2]
Test case 3 output: [0, 1, 2, 0, 3]
 */