package com.interview.notes.code.year.y2025.June.amazon.test16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmazonErrorMinimizer {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String s, int x, int y) {
        long dp0 = 0, dp1 = 0;
        for (char c : s.toCharArray()) {
            long new0 = dp0, new1 = dp1;
            if (c == '0') {
                new0 = Math.min(dp0, dp1 + y);
                new1 = Long.MAX_VALUE;
            } else if (c == '1') {
                new1 = Math.min(dp1, dp0 + x);
                new0 = Long.MAX_VALUE;
            } else { // '!'
                long costIf0 = Math.min(dp0, dp1 + y);
                long costIf1 = Math.min(dp1, dp0 + x);
                new0 = costIf0;
                new1 = costIf1;
            }
            dp0 = new0 % MOD;
            dp1 = new1 % MOD;
        }
        return (int) (Math.min(dp0, dp1) % MOD);
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
        List<Test> tests = Arrays.asList(
                new Test("0!1!1!", 2, 3, 10),
                new Test("!!!!!!!", 23, 47, 0),
                new Test("", 5, 5, 0),
                // large input: 100k '!'
                new Test(IntStream.range(0, 100_000).mapToObj(i -> "!").collect(Collectors.joining()), 1, 1, 0)
        );

        tests.stream().forEach(t -> {
            int actual = getMinErrors(t.s, t.x, t.y);
            String name = t.s.length() <= 10 ? "\"" + t.s + "\"" : "len=" + t.s.length();
            System.out.printf("%-12s x=%2d,y=%2d → expected=%d, actual=%d : %s%n",
                    name, t.x, t.y, t.exp, actual,
                    actual == t.exp ? "PASS" : "FAIL");
        });
    }
}
