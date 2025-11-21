package com.interview.notes.code.year.y2025.June.amazon.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Amazon Error String Minimization
 * <p>
 * Works in O(N) time and O(1) extra space.
 * Java 8-only, no external libraries.
 */
public class AmazonErrorString {

    private static final long MOD = 1_000_000_007L;

    /* ----------------------------------------------------------------------
       Core algorithm
    ---------------------------------------------------------------------- */
    public static int getMinErrors(String s, int x, int y) {
        if (s == null || s.isEmpty()) return 0;

        /* quick path when costs are equal */
        if (x == y) {
            long zeros = s.chars().filter(ch -> ch == '0').count();
            long ones = s.chars().filter(ch -> ch == '1').count();
            long q = s.length() - zeros - ones;
            long pairs = Math.min((zeros + q) * ones, zeros * (ones + q));
            return (int) ((pairs % MOD) * (x % MOD) % MOD);
        }

        /* choose preferred bit order */
        char left = (x > y) ? '1' : '0';  // expensive bit (prefix side)
        char right = (x > y) ? '0' : '1';  // cheap bit   (suffix side)

        long totalZeros = s.chars().filter(c -> c == '0').count()
                + (right == '0' ? s.chars().filter(c -> c == '!').count() : 0);
        long totalOnes = s.chars().filter(c -> c == '1').count()
                + (right == '1' ? s.chars().filter(c -> c == '!').count() : 0);

        /* initial cost when every '!' == right */
        long cost = 0, zerosSeen = 0, onesSeen = 0;
        for (char ch : s.toCharArray()) {
            char c = (ch == '!') ? right : ch;
            if (c == '0') {
                cost += (long) y * onesSeen;
                zerosSeen++;
            } else {
                cost += (long) x * zerosSeen;
                onesSeen++;
            }
        }

        long best = cost;
        zerosSeen = onesSeen = 0;

        /* sweep, flipping each '!' from right to left */
        for (char ch : s.toCharArray()) {
            if (ch == '!') {
                if (right == '0') {           // flip 0 -> 1
                    long zerosBefore = zerosSeen;
                    long onesBefore = onesSeen;
                    long zerosAfter = totalZeros - zerosSeen - 1;
                    long onesAfter = totalOnes - onesSeen;

                    cost += (long) x * (zerosBefore - onesAfter)
                            + (long) y * (zerosAfter - onesBefore);

                    totalZeros--;
                    totalOnes++;
                } else {                      // flip 1 -> 0
                    long zerosBefore = zerosSeen;
                    long onesBefore = onesSeen;
                    long zerosAfter = totalZeros - zerosSeen;
                    long onesAfter = totalOnes - onesSeen - 1;

                    cost += (long) x * (onesAfter - zerosBefore)
                            + (long) y * (onesBefore - zerosAfter);

                    totalOnes--;
                    totalZeros++;
                }
                if (left == '0') zerosSeen++;
                else onesSeen++;
            } else {
                if (ch == '0') zerosSeen++;
                else onesSeen++;
            }
            best = Math.min(best, cost);
        }
        return (int) (best % MOD);
    }

    /* ----------------------------------------------------------------------
       Simple test harness (PASS / FAIL + large random stress)
    ---------------------------------------------------------------------- */
    public static void main(String[] args) {

        /* helper record (exp == null → just print result) */
        record TC(String s, int x, int y, Long exp) {
        }

        List<TC> tests = new ArrayList<>(Arrays.asList(
                // original samples
                new TC("0!1!1!", 2, 3, 10L),
                new TC("!!!!!!!", 23, 47, 0L),
                new TC("!10!", 5, 0, 0L),

                // large / user-supplied strings (unknown expected)
                new TC(
                        "!0100!0010!!011!0111!1!00!0100!11!1!1110!000!!1010!1011!1100!1100!!001!0101!0!10!0010!01!0!1100!011!!1100!1111!1110!1000!!110!0000!1!11!1011!01!0!0111!000!!1001!0111!0110!0101!!100!0010!1!10!0111!00!1!1101!011!!1111!0101!0001!0111!!100!1001!1!00!0010!00!1!0010!011!!1010!0100!1110!0001!!001!1010!1!01!1001!10!0!0000!101!!1110!1101!1001!0001!!001!1110!0!10!1100!10!1!0110!010!!0011!0010!1011!0010!!000!1010!1!01!0101!10!0!0010!111!!0110!1011!1010!0001!!010!1100!0!11!0001!01!0!1100!001!!0001!0111!0010!0110!!101!1011!0!11!0001!00!0!1111!111!!1000!1010!1111!0110!!100!1010!1!11!1011!01!1!0000!111!!0011!0001!1001!0010!!011!1001!0!00!1111!10!0!0001!100!!0001!0111!1110!1111!!101!1001!1!10!0111!00!0!0011!110!!0111!1100!1010!1000!!000!0111!1!00!0110!11!1!1000!111!!0001!1001!0001!0010!!000!0101!0!01!0101!11!0!0000!100!!1101!0100!0001!0000!!110!0000!1!01!0111!11!1!0011!011!!0010!0110!1010!1001!!000!0011!1!11!0111!11!0!1111!100!!0",
                        980, 790, null),

                new TC("100!11!00000011111111", 0, 100, null),

                new TC(
                        "!1111!0101!!100!0000!1!11!0001!11!0!0101!001!!0000!0010!1010!0010!!000!1100!1!01!0101!11!1!0010!000!!1100!1010!1101!1101!!101!1111!0!11!1001!11!0!1110!100!!0110!0111!1001!0000!!010!1100!0!01!0100!11!1!0111!011!!1000!1000!1111!0000!!101!1101!1!10!0111!11!0!0100!000!!0001!1101!1001!0000!!011!0000!1!00!1101!10!1!0110!1",
                        724, 558, null),

                new TC(
                        "!0011!0111!!010!1101!1!01!0111!01!0!0101!000!!0100!0011!0010!1000!!001!0100!1!01!0101!10!0!0101!100!!0001!1110!0001!0101!!011!0001!0!00!0000!00!0!0010!101!!0110!0011!1000!1100!!011!0110!1!01!1100!01!1!0011!110!!0111!0010!0100!0010!!110!0111!1!00!1010!01!1!0111!110!!1111!0010!1001!1011!!111!1111!0!01!1100!00!1!0000!100!!1111!1110!0001!0011!!000!1110!0!10!0101!10!0!0110!001!!0110!0000!0110!0001!!111!0110!0!00!0101!00!0!0110!011!!1011!1001!1110!1001!!101!0001!1!00!0010!10!1!01",
                        751, 599, null),

                new TC(
                        "!0001!1001!!100!0011!1!00!0011!11!0!0011!001!!1101!0010!1010!1001!!101!0111!0!11!0110!10!0!1100!010!!1101!0011!1010!0100!!001!1001!0!10!0010!10!0!1010!100!!1100!0110!1001!0111!!110!0000!1!11!1111!1",
                        670, 725, null)
        ));

        boolean allKnownOK = true;
        int idx = 1;
        for (TC t : tests) {
            long got = getMinErrors(t.s, t.x, t.y);
            if (t.exp != null) {
                boolean ok = got == t.exp;
                allKnownOK &= ok;
                System.out.printf("T%-2d %-12s x=%-4d y=%-4d → %-12d [%s]%n",
                        idx++, shortStr(t.s), t.x, t.y, got, ok ? "PASS" : "FAIL");
            } else {
                System.out.printf("T%-2d %-12s x=%-4d y=%-4d → %-12d%n",
                        idx++, shortStr(t.s), t.x, t.y, got);
            }
        }

        /* extra quick random stress (100 k chars) */
        int n = 100_000;
        String big = ThreadLocalRandom.current()
                .ints(n, 0, 3)
                .mapToObj(v -> v == 0 ? "0" : v == 1 ? "1" : "!")
                .collect(Collectors.joining());
        long st = System.currentTimeMillis();
        int res = getMinErrors(big, 99_999, 42_042);
        long ms = System.currentTimeMillis() - st;
        System.out.printf("%nRandom 100 000-char test finished in %d ms (result %s)%n",
                ms, res);

        System.out.println("\nKnown-answer tests: " + (allKnownOK ? "ALL PASS ✅" : "FAIL ❌"));
    }

    /* helper: shorten long strings for console */
    private static String shortStr(String s) {
        return s.length() <= 10 ? '"' + s + '"' : '"' + s.substring(0, 10) + "...\"";
    }
}
