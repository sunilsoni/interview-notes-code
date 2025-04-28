package com.interview.notes.code.year.y2025.april.CodeSignal.test1;

import java.util.*;
import java.util.stream.*;

public class RateLimiter {

    public static int[] solution(long[] timestamps, String[] ipAddresses, int limit, int timeWindow) {
        Map<String, Deque<Long>> requestLog = new HashMap<>();
        int[] result = new int[timestamps.length];

        for (int i = 0; i < timestamps.length; i++) {
            requestLog.putIfAbsent(ipAddresses[i], new LinkedList<>());
            Deque<Long> requests = requestLog.get(ipAddresses[i]);

            while (!requests.isEmpty() && timestamps[i] - requests.peekFirst() > timeWindow) {
                requests.pollFirst();
            }

            if (requests.size() < limit) {
                requests.offerLast(timestamps[i]);
                result[i] = 1;  // Accepted
            } else {
                result[i] = 0;  // Declined
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case provided by user
        long[] timestamps1 = {1600040547954L, 1600040547957L, 1600040547958L};
        String[] ipAddresses1 = {"127.105.232.211", "127.105.232.211", "127.105.232.211"};
        System.out.println(Arrays.equals(solution(timestamps1, ipAddresses1, 1, 3), new int[]{1,0,1}) ? "PASS" : "FAIL");

        // Additional test case (multiple IPs, limit 2, window 10)
        long[] timestamps2 = {1600000000000L, 1600000000000L, 1600000000001L};
        String[] ipAddresses2 = {"56.75.0.49", "62.2.159.38", "62.2.159.38"};
        System.out.println(Arrays.equals(solution(timestamps2, ipAddresses2, 2, 10), new int[]{1,1,1}) ? "PASS" : "FAIL");

        // Large test case handling
        int largeSize = 100000;
        long[] largeTimestamps = LongStream.range(0, largeSize).map(i -> i * 10L).toArray();
        String[] largeIpAddresses = Stream.generate(() -> "192.168.1.1").limit(largeSize).toArray(String[]::new);
        int[] largeResult = solution(largeTimestamps, largeIpAddresses, 100, 1000);
        System.out.println(largeResult[largeSize - 1] == 1 ? "PASS" : "FAIL"); // Should accept due to consistent spacing
    }
}
