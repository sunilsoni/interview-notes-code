package com.interview.notes.code.year.y2025.June.amazon.test14;

import java.util.List;
import java.util.Random;

public class MinErrorCalculator {
    private static final long MOD = 1_000_000_007L;

    public static int getMinErrors(String s, int x, int y) {
        int n = s.length();

        // 1) First scan: count total fixed '0's and '1's
        long total0 = 0, total1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') total0++;
            else if (c == '1') total1++;
        }

        // 2) Second scan: accumulate
        long zerosBefore = 0, onesBefore = 0;
        long baseCost = 0;   // errors among fixed chars only
        long costIfAllZero = 0;  // relative cost if all '!'→'0'
        long costIfAllOne = 0;  // relative cost if all '!'→'1'

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '0') {
                // every prior '1' paired with this '0' as "10"
                baseCost += onesBefore * y;
                zerosBefore++;
            } else if (c == '1') {
                // every prior '0' paired with this '1' as "01"
                baseCost += zerosBefore * x;
                onesBefore++;
            } else { // c == '!'
                long onesAfter = total1 - onesBefore;
                long zerosAfter = total0 - zerosBefore;
                // if we pick '0' here: 
                //   pairs with prior '1' as "10" at cost y each
                //   pairs with later '1' as "01" at cost x each
                costIfAllZero += onesBefore * y + onesAfter * x;
                // if we pick '1' here:
                //   pairs with prior '0' as "01" at cost x each
                //   pairs with later '0' as "10" at cost y each
                costIfAllOne += zerosBefore * x + zerosAfter * y;
            }

            // keep baseCost and relatives under control
            if (baseCost > MOD) baseCost %= MOD;
            if (costIfAllZero > MOD) costIfAllZero %= MOD;
            if (costIfAllOne > MOD) costIfAllOne %= MOD;
        }

        long result = (baseCost + Math.min(costIfAllZero, costIfAllOne)) % MOD;
        return (int) result;
    }

    public static void main(String[] args) {
        // --- Sample tests ---
        class Test {
            String s;
            int x, y, want;
        }
        List<Test> samples = List.of(
                new Test() {{
                    s = "0!!1!1";
                    x = 2;
                    y = 3;
                    want = 10;
                }},
                new Test() {{
                    s = "!!!!!!";
                    x = 23;
                    y = 47;
                    want = 0;
                }}
        );

        System.out.println("=== Sample Tests ===");
        int pass = 0;
        for (int i = 0; i < samples.size(); i++) {
            Test t = samples.get(i);
            int got = getMinErrors(t.s, t.x, t.y);
            boolean ok = got == t.want;
            if (ok) pass++;
            System.out.printf(
                    "Test %d: getMinErrors(\"%s\",%d,%d) = %d  want=%d  → %s%n",
                    i + 1, t.s, t.x, t.y, got, t.want, ok ? "PASS" : "FAIL");
        }
        System.out.printf("Sample results: %d/%d passed%n%n",
                pass, samples.size());

        // --- Large random test ---
        System.out.println("=== Large Random Test ===");
        Random rnd = new Random(42);
        int N = 200_000;  // up to 1e5 allowed; we test 2e5
        StringBuilder sb = new StringBuilder(N);
        for (int i = 0; i < N; i++) {
            int r = rnd.nextInt(3);
            sb.append(r == 0 ? '0' : r == 1 ? '1' : '!');
        }
        int x = 100_000, y = 100_000;
        long t0 = System.nanoTime();
        int res = getMinErrors(sb.toString(), x, y);
        long ms = (System.nanoTime() - t0) / 1_000_000;
        System.out.printf("N=%d (random) → result=%d  (took %d ms)%n",
                N, res, ms);
    }
}