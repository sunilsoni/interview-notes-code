package com.interview.notes.code.year.y2025.June.amazon.test17;

import java.util.Arrays;
import java.util.List;

public class AmazonErrorMinimizer {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String s, int x, int y) {
        State st0 = new State(0, 0, 0);
        State st1 = new State(0, 0, 0);

        for (char c : s.toCharArray()) {
            State next0 = new State(Long.MAX_VALUE, 0, 0);
            State next1 = new State(Long.MAX_VALUE, 0, 0);

            if (c == '0' || c == '!') {
                // choose 0: cost = prevCost + y * prevOnes
                // from st0 → stays zeros+1, ones
                long c0 = st0.cost + y * st0.ones;
                long z0 = st0.zeros + 1, o0 = st0.ones;
                // from st1
                long c1 = st1.cost + y * st1.ones;
                long z1 = st1.zeros + 1, o1 = st1.ones;
                if (c0 <= c1) next0 = new State(c0, z0, o0);
                else next0 = new State(c1, z1, o1);
            }

            if (c == '1' || c == '!') {
                // choose 1: cost = prevCost + x * prevZeros
                // from st1
                long c0 = st1.cost + x * st1.zeros;
                long z0 = st1.zeros, o0 = st1.ones + 1;
                // from st0
                long c1 = st0.cost + x * st0.zeros;
                long z1 = st0.zeros, o1 = st0.ones + 1;
                if (c0 <= c1) next1 = new State(c0, z0, o0);
                else next1 = new State(c1, z1, o1);
            }

            // mod to prevent overflow
            next0.cost %= MOD;
            next1.cost %= MOD;
            st0 = next0;
            st1 = next1;
        }

        return (int) (Math.min(st0.cost, st1.cost) % MOD);
    }

    public static void main(String[] args) {
        class Test {
            final String s;
            final int x, y, exp;

            Test(String s, int x, int y, int exp) {
                this.s = s;
                this.x = x;
                this.y = y;
                this.exp = exp;
            }
        }

        // build a 100k-long "!!!!…"
        StringBuilder sb = new StringBuilder(100_000);
        for (int i = 0; i < 100_000; i++) sb.append('!');
        String huge = sb.toString();

        List<Test> tests = Arrays.asList(
                new Test("0!1!1!", 2, 3, 10),
                new Test("!!!!!!!", 23, 47, 0),
                new Test("", 5, 5, 0),
                new Test(huge, 1, 1, 0)
        );

        for (Test t : tests) {
            int actual = getMinErrors(t.s, t.x, t.y);
            String name = t.s.length() <= 10 ? "\"" + t.s + "\"" : "len=" + t.s.length();
            System.out.printf("%-12s x=%2d,y=%2d → expected=%d, actual=%d : %s%n",
                    name, t.x, t.y, t.exp, actual,
                    actual == t.exp ? "PASS" : "FAIL");
        }
    }

    static class State {
        long cost;
        long zeros;
        long ones;

        State(long c, long z, long o) {
            cost = c;
            zeros = z;
            ones = o;
        }
    }
}
