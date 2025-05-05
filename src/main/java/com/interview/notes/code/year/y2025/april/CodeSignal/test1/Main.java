package com.interview.notes.code.year.y2025.april.CodeSignal.test1;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static int[] solution(long[] timestamps, String[] ipAddresses, int limit, int timeWindow) {
        Map<String, Deque<Long>> map = new HashMap<>();
        return IntStream.range(0, timestamps.length).map(i -> {
            String ip = ipAddresses[i];
            long ts = timestamps[i];
            Deque<Long> dq = map.computeIfAbsent(ip, k -> new LinkedList<>());
            while (!dq.isEmpty() && dq.peekFirst() < ts - timeWindow) {
                dq.pollFirst();
            }
            if (dq.size() < limit) {
                dq.addLast(ts);
                return 1;
            } else {
                return 0;
            }
        }).toArray();
    }

    private static void test(long[] ts, String[] ips, int limit, int window, int[] expected, String name) {
        int[] res = solution(ts, ips, limit, window);
        System.out.println(name + ": " + (Arrays.equals(res, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(
                new long[]{1600040547954L, 1600040547957L, 1600040547958L},
                new String[]{"127.105.232.211", "127.105.232.211", "127.105.232.211"},
                1, 3,
                new int[]{1, 0, 1},
                "Test1"
        );
        test(
                new long[]{1600000000000L, 1600000000000L, 1600000000001L},
                new String[]{"56.75.0.49", "62.2.159.38", "62.2.159.38"},
                2, 10,
                new int[]{1, 1, 1},
                "Test2"
        );
        test(
                new long[]{1L},
                new String[]{"1.2.3.4"},
                1, 1,
                new int[]{1},
                "Test3"
        );

        int n = 100_000;
        long[] tsl = new long[n];
        String[] ipsl = new String[n];
        for (int i = 0; i < n; i++) {
            tsl[i] = i;
            ipsl[i] = "192.168.0.1";
        }
        int[] resl = solution(tsl, ipsl, 100, 1000);
        boolean passLarge = IntStream.range(0, n)
                .map(i -> (i < 100 ? 1 : 0))
                .allMatch(i -> resl[i] == i);
        System.out.println("LargeTest: " + (passLarge ? "PASS" : "FAIL"));
    }
}