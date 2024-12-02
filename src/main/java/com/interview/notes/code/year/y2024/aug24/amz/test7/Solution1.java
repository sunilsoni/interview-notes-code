package com.interview.notes.code.year.y2024.aug24.amz.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
    public static List<Integer> getServerIds(int num_servers, List<Integer> requests) {
        List<Integer> result = new ArrayList<>();
        int[] serverLoads = new int[num_servers];

        for (int request : requests) {
            int minLoad = Integer.MAX_VALUE;
            int selectedServer = 0;

            for (int i = 0; i < Math.min(request + 1, num_servers); i++) {
                if (serverLoads[i] < minLoad) {
                    minLoad = serverLoads[i];
                    selectedServer = i;
                }
            }

            serverLoads[selectedServer]++;
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
