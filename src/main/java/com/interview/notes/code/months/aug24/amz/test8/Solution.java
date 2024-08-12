package com.interview.notes.code.months.aug24.amz.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static List<Integer> getServerIds(int num_servers, List<Integer> requests) {
        List<Integer> result = new ArrayList<>(requests.size());
        int[] loads = new int[num_servers];

        for (int request : requests) {
            int consideredServers = Math.min(request + 1, num_servers);
            int selectedServer = 0;
            int minLoad = Integer.MAX_VALUE;

            for (int i = 0; i < consideredServers; i++) {
                if (loads[i] < minLoad) {
                    minLoad = loads[i];
                    selectedServer = i;
                }
            }

            result.add(selectedServer);
            loads[selectedServer]++;
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
