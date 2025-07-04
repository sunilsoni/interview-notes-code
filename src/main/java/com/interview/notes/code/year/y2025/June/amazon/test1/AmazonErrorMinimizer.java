package com.interview.notes.code.year.y2025.June.amazon.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmazonErrorMinimizer {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String s, int x, int y) {
        State st0 = new State(0, 0, 0);
        State st1 = new State(0, 0, 0);

        for (char c : s.toCharArray()) {
            State next0 = new State(Long.MAX_VALUE, 0, 0);
            State next1 = new State(Long.MAX_VALUE, 0, 0);

            if (c == '0' || c == '!') {
                // choose 0: cost += y * prevOnes
                long c0 = st0.cost + y * st0.ones;
                long z0 = st0.zeros + 1, o0 = st0.ones;
                long c1 = st1.cost + y * st1.ones;
                long z1 = st1.zeros + 1, o1 = st1.ones;
                if (c0 <= c1) next0 = new State(c0, z0, o0);
                else next0 = new State(c1, z1, o1);
            }

            if (c == '1' || c == '!') {
                // choose 1: cost += x * prevZeros
                long c0 = st1.cost + x * st1.zeros;
                long z0 = st1.zeros, o0 = st1.ones + 1;
                long c1 = st0.cost + x * st0.zeros;
                long z1 = st0.zeros, o1 = st0.ones + 1;
                if (c0 <= c1) next1 = new State(c0, z0, o0);
                else next1 = new State(c1, z1, o1);
            }

            next0.cost %= MOD;
            next1.cost %= MOD;
            st0 = next0;
            st1 = next1;
        }

        return (int) (Math.min(st0.cost, st1.cost) % MOD);
    }

    public static void main(String[] args) {
        class Test {
            String s;
            int x, y, exp;

            Test(String s, int x, int y, int exp) {
                this.s = s;
                this.x = x;
                this.y = y;
                this.exp = exp;
            }
        }

        // original tests
        List<Test> tests = new ArrayList<>();
        tests.add(new Test("0!1!1!", 2, 3, 10));
        tests.add(new Test("!!!!!!!", 23, 47, 0));
        tests.add(new Test("", 5, 5, 0));
        // 100k-long "!!!!…"
        String huge = IntStream.range(0, 100_000).mapToObj(i -> "!").collect(Collectors.joining());
        tests.add(new Test(huge, 1, 1, 0));

        for (Test t : tests) {
            int actual = getMinErrors(t.s, t.x, t.y);
            String name = t.s.length() <= 10 ? "\"" + t.s + "\"" : "len=" + t.s.length();
            System.out.printf("%-12s x=%2d,y=%2d → expected=%d, actual=%d : %s%n",
                    name, t.x, t.y, t.exp, actual,
                    actual == t.exp ? "PASS" : "FAIL");
        }

        // ───────────────────────────────────────────────────────────────────
        // your five “failing” test-cases (no expected provided here)
        System.out.println("\nAdditional test cases:");
        String[] strs = new String[]{
                /* Test1 */ "!0100!0010!!011!0111!1!00!0100!11!1!1110!000!!1010!1011!1100!1100!!001!0101!0!10!0010!01!0!1100!011!!1100!1111!1110!1000!!110!0000!1!11!1011!01!0!0111!000!!1001!0111!0110!0101!!100!0010!1!10!0111!00!1!1101!011!!1111!0101!0001!0111!!100!1001!1!00!0010!00!1!0010!011!!1010!0100!1110!0001!!001!1010!1!01!1001!10!0!0000!101!!1110!1101!1001!0001!!001!1110!0!10!1100!10!1!0110!010!!0011!0010!1011!0010!!000!1010!1!01!0101!10!0!0010!111!!0110!1011!1010!0001!!010!1100!0!11!0001!01!0!1100!001!!0001!0111!0010!0110!!101!1011!0!11!0001!00!0!1111!111!!1000!1010!1111!0110!!100!1010!1!11!1011!01!1!0000!111!!0011!0001!1001!0010!!011!1001!0!00!1111!10!0!0001!100!!0001!0111!1110!1111!!101!1001!1!10!0111!00!0!0011!110!!0111!1100!1010!1000!!000!0111!1!00!0110!11!1!1000!111!!0001!1001!0001!0010!!000!0101!0!01!0101!11!0!0000!100!!1101!0100!0001!0000!!110!0000!1!01!0111!11!1!0011!011!!0010!0110!1010!1001!!000!0011!1!11!0111!11!0!1111!100!!0",
                /* Test2 */ "100!11!00000011111111",
                /* Test3 */ "!1111!0101!!100!0000!1!11!0001!11!0!0101!001!!0000!0010!1010!0010!!000!1100!1!01!0101!11!1!0010!000!!1100!1010!1101!1101!!101!1111!0!11!1001!11!0!1110!100!!0110!0111!1001!0000!!010!1100!0!01!0100!11!1!0111!011!!1000!1000!1111!0000!!101!1101!1!10!0111!11!0!0100!000!!0001!1101!1001!0000!!011!0000!1!00!1101!10!1!0110!1",
                /* Test4 */ "!0011!0111!!010!1101!1!01!0111!01!0!0101!000!!0100!0011!0010!1000!!001!0100!1!01!0101!10!0!0101!100!!0001!1110!0001!0101!!011!0001!0!00!0000!00!0!0010!101!!0110!0011!1000!1100!!011!0110!1!01!1100!01!1!0011!110!!0111!0010!0100!0010!!110!0111!1!00!1010!01!1!0111!110!!1111!0010!1001!1011!!111!1111!0!01!1100!00!1!0000!100!!1111!1110!0001!0011!!000!1110!0!10!0101!10!0!0110!001!!0110!0000!0110!0001!!111!0110!0!00!0101!00!0!0110!011!!1011!1001!1110!1001!!101!0001!1!00!0010!10!1!01",
                /* Test5 */ "!0001!1001!!100!0011!1!00!0011!11!0!0011!001!!1101!0010!1010!1001!!101!0111!0!11!0110!10!0!1100!010!!1101!0011!1010!0100!!001!1001!0!10!0010!10!0!1010!100!!1100!0110!1001!0111!!110!0000!1!11!1111!1"
        };
        int[] xs = {980, 0, 724, 751, 670};
        int[] ys = {790, 100, 558, 599, 725};

        for (int i = 0; i < strs.length; i++) {
            int actual = getMinErrors(strs[i], xs[i], ys[i]);
            System.out.printf("Test%-2d x=%4d,y=%4d → actual=%d%n",
                    i + 1, xs[i], ys[i], actual);
        }
    }

    static class State {
        long cost, zeros, ones;

        State(long c, long z, long o) {
            cost = c;
            zeros = z;
            ones = o;
        }
    }
}
