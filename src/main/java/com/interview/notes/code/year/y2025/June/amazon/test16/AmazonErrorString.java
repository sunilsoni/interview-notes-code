package com.interview.notes.code.year.y2025.June.amazon.test16;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Amazon Error-String minimization.
 */
public class AmazonErrorString {

    private static final long MOD = 1_000_000_007L;

    public static int getMinErrors(String s, int x, int y) {
        if (s == null || s.isEmpty()) return 0;

        /* ------------ trivial equal-cost case ------------ */
        if (x == y) {
            long zeros = s.chars().filter(c -> c == '0').count();
            long ones = s.chars().filter(c -> c == '1').count();
            long q = s.length() - zeros - ones;
            long bestPairs = Math.min((zeros + q) * ones, zeros * (ones + q));
            return (int) ((x * bestPairs) % MOD);
        }

        /* ------------ which value should each '?' take first? ------------ */
        // If putting 0 before 1 is more expensive (x > y), keep all early '?' == '1'
        // If putting 1 before 0 is more expensive (x < y), keep all early '?' == '0'
        char left = (x > y) ? '1' : '0';   // value a '?' turns **into** as we sweep
        char right = (x > y) ? '0' : '1';   // value every '?' holds **initially**

        long totalZeros = s.chars().filter(c -> c == '0').count()
                + (right == '0' ? s.chars().filter(c -> c == '!').count() : 0);
        long totalOnes = s.chars().filter(c -> c == '1').count()
                + (right == '1' ? s.chars().filter(c -> c == '!').count() : 0);

        /* ------------ cost of the initial string (all '?' == right) ------------ */
        long cost = 0, zerosSeen = 0, onesSeen = 0;
        for (char ch : s.toCharArray()) {
            char c = (ch == '!') ? right : ch;
            if (c == '0') {
                cost += (long) y * onesSeen;            // "10" pairs
                zerosSeen++;
            } else {
                cost += (long) x * zerosSeen;           // "01" pairs
                onesSeen++;
            }
        }

        long best = cost;
        zerosSeen = onesSeen = 0;                       // restart sweep

        /* ------------ sweep once, flipping each '?' from right -> left ------------ */
        for (char ch : s.toCharArray()) {

            if (ch == '!') {
                if (right == '0') {                     // flipping 0 -> 1
                    long zerosBefore = zerosSeen;
                    long onesBefore = onesSeen;
                    long zerosAfter = totalZeros - zerosSeen - 1;
                    long onesAfter = totalOnes - onesSeen;

                    long delta = (long) x * (zerosBefore - onesAfter)
                            + (long) y * (zerosAfter - onesBefore);
                    cost += delta;

                    totalZeros--;                       // reflect the flip in global counts
                    totalOnes++;

                } else {                               // flipping 1 -> 0
                    long zerosBefore = zerosSeen;
                    long onesBefore = onesSeen;
                    long zerosAfter = totalZeros - zerosSeen;
                    long onesAfter = totalOnes - onesSeen - 1;

                    long delta = (long) x * (onesAfter - zerosBefore)
                            + (long) y * (onesBefore - zerosAfter);
                    cost += delta;

                    totalOnes--;
                    totalZeros++;
                }
                // add the new (flipped) character to the prefix counters
                if (left == '0') zerosSeen++;
                else onesSeen++;

            } else {                                   // fixed character
                if (ch == '0') zerosSeen++;
                else onesSeen++;
            }
            best = Math.min(best, cost);
        }
        return (int) (best % MOD);
    }

    /* ---------------------- simple main for quick tests ---------------------- */
    public static void main(String[] args) {
        class TC {
            final String s;
            final int x;
            final int y;
            final int exp;

            TC(String s, int x, int y, int e) {
                this.s = s;
                this.x = x;
                this.y = y;
                this.exp = e;
            }
        }

        List<TC> tests = Arrays.asList(
                new TC("0!1!1!", 2, 3, 10),   // sample-1
                new TC("!!!!!!!", 23, 47, 0), // sample-2
                new TC("!10!", 5, 0, 0)    // corner where y == 0
        );

        boolean all = true;
        for (TC t : tests) {
            int got = getMinErrors(t.s, t.x, t.y);
            boolean ok = got == t.exp;
            all &= ok;
            System.out.printf("Input %-8s  x=%-3d y=%-3d  -> %-5d  [%s]%n",
                    '"' + t.s + '"', t.x, t.y, got, ok ? "PASS" : "FAIL");
        }

        // quick large-data sanity run (no expected value, just timing)
        int n = 100_000;
        String big = ThreadLocalRandom.current()
                .ints(n, 0, 3)                // 0,1,2 -> '0','1','!'
                .mapToObj(v -> v == 0 ? "0" : v == 1 ? "1" : "!")
                .collect(Collectors.joining());
        long start = System.currentTimeMillis();
        int ansBig = getMinErrors(big, 99_999, 42_042);
        long time = System.currentTimeMillis() - start;
        System.out.printf("Large test (%,d chars) finished in %d ms, result=%d%n",
                n, time, ansBig);

        System.out.println(all ? "\nAll sample cases PASS ✅" : "\nSome sample cases FAIL ❌");
    }
}
