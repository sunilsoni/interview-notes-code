package com.interview.notes.code.year.y2026.april.assessments.test11;

import java.util.ArrayList;
import java.util.List;

public class ApiBandwidthAllocator {

    public static long getMaxRequests(List<Integer> bandwidth, List<Integer> request, int totalBandwidth) {
        long[] dp = new long[totalBandwidth + 1];
        
        for (int i = 0; i < bandwidth.size(); i++) {
            int bw = bandwidth.get(i);
            long req = request.get(i);
            
            for (int w = totalBandwidth; w >= bw; w--) {
                dp[w] = Math.max(dp[w], dp[w - bw] + req);
            }
        }
        
        return dp[totalBandwidth];
    }

    public static void main(String[] args) {
        test(List.of(100, 500, 80, 25, 400), List.of(100, 1000, 120, 110, 100), 200, 230L);
        test(List.of(100, 200, 500), List.of(5, 5, 5), 1000, 15L);
        test(List.of(200, 100, 350, 50, 100), List.of(270, 142, 450, 124, 189), 500, 763L);
        
        List<Integer> largeBw = new ArrayList<>();
        List<Integer> largeReq = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeBw.add(1000);
            largeReq.add(1000000000);
        }
        test(largeBw, largeReq, 100000, 100000000000L);
    }

    private static void test(List<Integer> bw, List<Integer> req, int total, long expected) {
        long result = getMaxRequests(bw, req, total);
        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }
}