package com.interview.notes.code.year.y2026.september.amazon.test1;

public class BranchMergeConflictResolver {

    public static int getMinimumConflicts(String primary, String secondary) {
        int n = primary.length();
        int m = secondary.length();
        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (primary.charAt(i) > primary.charAt(j)) {
                    total++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (secondary.charAt(i) > secondary.charAt(j)) {
                    total++;
                }
            }
        }

        int[][] cp = new int[n][m + 1];
        int[][] cs = new int[n + 1][m];
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                cp[i][j] = cp[i][j - 1] + (secondary.charAt(j - 1) > primary.charAt(i) ? 1 : 0);
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 1; i <= n; i++) {
                cs[i][j] = cs[i - 1][j] + (primary.charAt(i - 1) > secondary.charAt(j) ? 1 : 0);
            }
        }

        for (int[] row : dp) {
            java.util.Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        dp[0][0] = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                if (i < n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + cp[i][j]);
                }
                if (j < m) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + cs[i][j]);
                }
            }
        }

        return total + dp[n][m];
    }

    static void main(String[] args) {
        record TestCase(String p, String s, int exp) {}
        
        var tcs = java.util.List.of(
            new TestCase("dae", "add", 1),
            new TestCase("aaa", "abb", 0),
            new TestCase("zc", "d", 2),
            new TestCase("a".repeat(1000), "b".repeat(1000), 0),
            new TestCase("z".repeat(1000), "a".repeat(1000), 1000000)
        );

        for (var t : tcs) {
            int res = getMinimumConflicts(t.p(), t.s());
            if (res == t.exp()) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL (Exp: " + t.exp() + ", Got: " + res + ")");
            }
        }
    }
}