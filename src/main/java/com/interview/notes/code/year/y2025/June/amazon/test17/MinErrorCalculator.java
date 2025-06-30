package com.interview.notes.code.year.y2025.June.amazon.test17;

import java.util.*;
import java.util.stream.*;

public class MinErrorCalculator {
    private static final long MOD = 1_000_000_007L;

    /**
     * Returns the minimum total errors (mod 10^9+7) for errorString
     * where every subsequence "01" costs x, and every "10" costs y.
     */
    public static int getMinErrors(String s, int x, int y) {
        int n = s.length();
        // 1) Count total fixed zeros and ones, and compute base cost among fixed bits.
        long total0 = 0, total1 = 0;
        long baseCost = 0;
        long zerosSoFar = 0, onesSoFar = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                // every prior '1' forms a "10"
                baseCost = (baseCost + onesSoFar * y) % MOD;
                zerosSoFar++;
                total0++;
            } else if (c == '1') {
                // every prior '0' forms a "01"
                baseCost = (baseCost + zerosSoFar * x) % MOD;
                onesSoFar++;
                total1++;
            }
        }

        // 2) Gather positions of '!' and compute their unary costs w.r.t fixed bits.
        int m = 0;
        for (char c : s.toCharArray()) if (c == '!') m++;
        if (m == 0) {
            // no '?' to assign
            return (int)(baseCost % MOD);
        }

        long[] cost0 = new long[m];  // cost if this '!' → '0' against fixed bits
        long[] cost1 = new long[m];  // cost if this '!' → '1' against fixed bits
        zerosSoFar = 0;
        onesSoFar = 0;
        long seenQ = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                zerosSoFar++;
            } else if (c == '1') {
                onesSoFar++;
            } else { // c == '!'
                // fixed bits before = zerosSoFar, onesSoFar
                // fixed bits after = total0 - zerosSoFar, total1 - onesSoFar
                long after0 = total0 - zerosSoFar;
                long after1 = total1 - onesSoFar;
                cost0[(int)seenQ] = (onesSoFar * y + after1 * x) % MOD;
                cost1[(int)seenQ] = (zerosSoFar * x + after0 * y) % MOD;
                seenQ++;
            }
        }

        // 3) Build prefix/suffix sums of cost0 and cost1
        long[] pre0 = new long[m+1], pre1 = new long[m+1];
        for (int i = 1; i <= m; i++) {
            pre0[i] = (pre0[i-1] + cost0[i-1]) % MOD;
            pre1[i] = (pre1[i-1] + cost1[i-1]) % MOD;
        }
        long[] suf0 = new long[m+2], suf1 = new long[m+2];
        for (int i = m; i >= 1; i--) {
            suf0[i] = (suf0[i+1] + cost0[i-1]) % MOD;
            suf1[i] = (suf1[i+1] + cost1[i-1]) % MOD;
        }

        // 4) Try both "zeros-then-ones" and "ones-then-zeros" patterns with a threshold k
        long best = Long.MAX_VALUE;
        for (int k = 0; k <= m; k++) {
            // pattern A: first k '?'→0, rest →1
            //   cost = sum(cost0[1..k]) + sum(cost1[k+1..m]) + k*(m-k)*x
            long cA = pre0[k];
            cA = (cA + suf1[k+1]) % MOD;
            cA = (cA + (long)k*(m-k)%MOD * x % MOD) % MOD;
            best = Math.min(best, cA);

            // pattern B: first k '?'→1, rest →0
            //   cost = sum(cost1[1..k]) + sum(cost0[k+1..m]) + k*(m-k)*y
            long cB = pre1[k];
            cB = (cB + suf0[k+1]) % MOD;
            cB = (cB + (long)k*(m-k)%MOD * y % MOD) % MOD;
            best = Math.min(best, cB);
        }

        long result = (baseCost + best) % MOD;
        return (int) result;
    }

    public static void main(String[] args) {
        // --- Your five custom test cases ---
        String[] customS = new String[] {
            // Test1:
            "!0100!0010!!011!0111!1!00!0100!11!1!1110!000!!1010!1011!1100!1100!!001!0101!0!10!0010!01!0!1100!011!!1100!1111!1110!1000!!110!0000!1!11!1011!01!0!0111!000!!1001!0111!0110!0101!!100!0010!1!10!0111!00!1!1101!011!!1111!0101!0001!0111!!100!1001!1!00!0010!00!1!0010!011!!1010!0100!1110!0001!!001!1010!1!01!1001!10!0!0000!101!!1110!1101!1001!0001!!001!1110!0!10!1100!10!1!0110!010!!0011!0010!1011!0010!!000!1010!1!01!0101!10!0!0010!111!!0110!1011!1010!0001!!010!1100!0!11!0001!01!0!1100!001!!0001!0111!0010!0110!!101!1011!0!11!0001!00!0!1111!111!!1000!1010!1111!0110!!100!1010!1!11!1011!01!1!0000!111!!0011!0001!1001!0010!!011!1001!0!00!1111!10!0!0001!100!!0001!0111!1110!1111!!101!1001!1!10!0111!00!0!0011!110!!0111!1100!1010!1000!!000!0111!1!00!0110!11!1!1000!111!!0001!1001!0001!0010!!000!0101!0!01!0101!11!0!0000!100!!1101!0100!0001!0000!!110!0000!1!01!0111!11!1!0011!011!!0010!0110!1010!1001!!000!0011!1!11!0111!11!0!1111!100!!0",
            // Test2:
            "100!11!00000011111111",
            // Test3:
            "!1111!0101!!100!0000!1!11!0001!11!0!0101!001!!0000!0010!1010!0010!!000!1100!1!01!0101!11!1!0010!000!!1100!1010!1101!1101!!101!1111!0!11!1001!11!0!1110!100!!0110!0111!1001!0000!!010!1100!0!01!0100!11!1!0111!011!!1000!1000!1111!0000!!101!1101!1!10!0111!11!0!0100!000!!0001!1101!1001!0000!!011!0000!1!00!1101!10!1!0110!1",
            // Test4:
            "!0011!0111!!010!1101!1!01!0111!01!0!0101!000!!0100!0011!0010!1000!!001!0100!1!01!0101!10!0!0101!100!!0001!1110!0001!0101!!011!0001!0!00!0000!00!0!0010!101!!0110!0011!1000!1100!!011!0110!1!01!1100!01!1!0011!110!!0111!0010!0100!0010!!110!0111!1!00!1010!01!1!0111!110!!1111!0010!1001!1011!!111!1111!0!01!1100!00!1!0000!100!!1111!1110!0001!0011!!000!1110!0!10!0101!10!0!0110!001!!0110!0000!0110!0001!!111!0110!0!00!0101!00!0!0110!011!!1011!1001!1110!1001!!101!0001!1!00!0010!10!1!01",
            // Test5:
            "!0001!1001!!100!0011!1!00!0011!11!0!0011!001!!1101!0010!1010!1001!!101!0111!0!11!0110!10!0!1100!010!!1101!0011!1010!0100!!001!1001!0!10!0010!10!0!1010!100!!1100!0110!1001!0111!!110!0000!1!11!1111!1"
        };
        int[] customX = {980, 0, 724, 751, 670};
        int[] customY = {790,100, 558, 599, 725};

        System.out.println("=== Your Custom Tests ===");
        for (int i = 0; i < customS.length; i++) {
            int got = getMinErrors(customS[i], customX[i], customY[i]);
            System.out.printf(
              "Custom %d: x=%d, y=%d → errors = %d%n",
              i+1, customX[i], customY[i], got
            );
        }
        System.out.println();

        // --- Original sample tests ---
        class Test { String s; int x,y,w; }
        List<Test> samples = List.of(
          new Test() {{ s = "0!!1!1";  x = 2;  y = 3;  w = 10; }},
          new Test() {{ s = "!!!!!!";  x =23; y =47; w =  0; }}
        );
        System.out.println("=== Sample Tests ===");
        int passed = 0;
        for (int i = 0; i < samples.size(); i++) {
            Test t = samples.get(i);
            int res = getMinErrors(t.s, t.x, t.y);
            boolean ok = res == t.w;
            if (ok) passed++;
            System.out.printf(
              "Sample %d: got=%d want=%d → %s%n",
              i+1, res, t.w, ok ? "PASS" : "FAIL"
            );
        }
        System.out.printf("Sample %d/%d passed%n%n", passed, samples.size());

        // --- Large random test ---
        System.out.println("=== Large Random Test ===");
        Random rnd = new Random(42);
        int N = 200_000; 
        StringBuilder sb = new StringBuilder(N);
        for (int i = 0; i < N; i++) {
            int r = rnd.nextInt(3);
            sb.append(r==0 ? '0' : r==1 ? '1' : '!');
        }
        int x = 100_000, y = 100_000;
        long t0 = System.nanoTime();
        int result = getMinErrors(sb.toString(), x, y);
        long ms = (System.nanoTime() - t0) / 1_000_000;
        System.out.printf("Random N=%d → errors=%d  (%.1f ms)%n",
                          N, result, (double)ms);
    }
}