package com.interview.notes.code.year.y2024.aug24.amz.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static List<Integer> getServerIds(int num_servers, List<Integer> requests) {
        List<Integer> result = new ArrayList<>(requests.size());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        for (int i = 0; i < num_servers; i++) {
            pq.offer(new int[]{i, 0});
        }

        for (int request : requests) {
            int consideredServers = Math.min(request + 1, num_servers);
            PriorityQueue<int[]> tempPq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

            for (int i = 0; i < consideredServers; i++) {
                if (!pq.isEmpty()) {
                    tempPq.offer(pq.poll());
                }
            }

            int[] selectedServer = tempPq.poll();
            result.add(selectedServer[0]);
            selectedServer[1]++;
            tempPq.offer(selectedServer);

            while (!tempPq.isEmpty()) {
                pq.offer(tempPq.poll());
            }

            while (pq.size() < num_servers) {
                pq.offer(new int[]{pq.size(), 0});
            }
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

        // Test case 3
        int num_servers3 = 5;
        List<Integer> requests3 = Arrays.asList(3, 2, 3, 2, 4);
        System.out.println("Test case 3 output: " + getServerIds(num_servers3, requests3));
    }
}
