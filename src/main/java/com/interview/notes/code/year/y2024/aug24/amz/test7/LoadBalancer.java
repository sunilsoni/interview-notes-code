package com.interview.notes.code.year.y2024.aug24.amz.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LoadBalancer {

    public static List<Integer> getServerIds(int num_servers, List<Integer> requests) {
        List<Integer> result = new ArrayList<>();
        int[] serverLoad = new int[num_servers];

        // Priority queue to keep track of servers with the minimum load
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // Initialize the priority queue with all servers
        for (int i = 0; i < num_servers; i++) {
            pq.offer(new int[]{i, 0});
        }

        for (int request : requests) {
            List<int[]> temp = new ArrayList<>();
            int[] assignedServer = null;

            // Find the server with minimum load within the first 'request + 1' servers
            while (!pq.isEmpty()) {
                int[] server = pq.poll();
                if (server[0] <= request) {
                    if (assignedServer == null || server[1] < assignedServer[1] ||
                            (server[1] == assignedServer[1] && server[0] < assignedServer[0])) {
                        if (assignedServer != null) temp.add(assignedServer);
                        assignedServer = server;
                    } else {
                        temp.add(server);
                    }
                } else {
                    temp.add(server);
                }
            }

            // Assign the request to the chosen server and increase its load
            if (assignedServer != null) {
                result.add(assignedServer[0]);
                assignedServer[1]++;
                temp.add(assignedServer);
            }

            // Add all servers back to the priority queue
            pq.addAll(temp);
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

    public static void main1(String[] args) {
        System.out.println(getServerIds(5, Arrays.asList(3, 2, 3, 2, 4))); // Output: [0, 1, 2, 0, 3]
        System.out.println(getServerIds(5, Arrays.asList(4, 0, 2, 2)));    // Output: [0, 0, 1, 2]
    }
}
