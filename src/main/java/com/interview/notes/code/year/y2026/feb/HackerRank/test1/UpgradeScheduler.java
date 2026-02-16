package com.interview.notes.code.year.y2026.feb.HackerRank.test1;

public class UpgradeScheduler {

    public static long getMinUpgradationTime(int req1, int t1, int req2, int t2) {
        long low = 1;
        long high = 200000000000000L; 
        long ans = high;
        long lcmVal = lcm(req1, req2);

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canComplete(mid, req1, t1, req2, t2, lcmVal)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private static boolean canComplete(long time, int req1, int t1, int req2, int t2, long lcm) {
        long validFor1 = time - (time / req1);
        long validFor2 = time - (time / req2);
        long validForBoth = time - (time / lcm);
        
        return validFor1 >= t1 && validFor2 >= t2 && validForBoth >= ((long) t1 + t2);
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
        System.out.println("Running Tests...");

        runTest(1, 2, 1, 2, 3, 7L);
        runTest(2, 3, 2, 4, 1, 3L);
        runTest(3, 2, 5, 3, 4, 9L); 
        
        // Large Data Test
        runTest(4, 20000, 100000000, 20000, 100000000, 200010000L);
    }

    private static void runTest(int id, int req1, int t1, int req2, int t2, long expected) {
        long result = getMinUpgradationTime(req1, t1, req2, t2);
        String status = (result == expected) ? "PASS" : "FAIL (Got " + result + ")";
        System.out.println("Test Case " + id + ": " + status);
    }
}