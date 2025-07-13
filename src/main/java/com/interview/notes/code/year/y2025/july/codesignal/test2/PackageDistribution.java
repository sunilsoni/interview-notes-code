package com.interview.notes.code.year.y2025.july.codesignal.test2;

import java.util.Arrays;

public class PackageDistribution {

    /**
     * Simulates the distribution process and returns the index
     * of the center that processed the most packages (ties → highest index).
     */
    public static int solution(int[] cap, String[] dailyLog) {
        int n = cap.length;
        int[] rem       = Arrays.copyOf(cap, n);
        int[] processed = new int[n];
        boolean[] closed= new boolean[n];
        int current = -1;

        // We'll reset "rem" for all open centers whenever we wrap  n-1 → 0
        Runnable reset = () -> {
            for (int k = 0; k < n; k++) {
                if (!closed[k]) rem[k] = cap[k];
            }
        };

        for (String op : dailyLog) {
            if (op.equals("PACKAGE")) {
                // start searching at current (or 0 if none yet)
                int i = (current < 0 ? 0 : current);
                while (true) {
                    if (!closed[i] && rem[i] > 0) {
                        // assign package here
                        processed[i]++;
                        rem[i]--;
                        current = i;
                        break;
                    }
                    // move to next center
                    int next = (i + 1) % n;
                    // if we just went from n-1 → 0, do a full reset
                    if (i == n - 1 && next == 0) {
                        reset.run();
                    }
                    i = next;
                }

            } else if (op.startsWith("CLOSURE")) {
                // e.g. "CLOSURE 2"
                int j = Integer.parseInt(op.split("\\s+")[1]);
                closed[j] = true;
            }
        }

        // pick the center with max(processed[i]); on tie, the highest i
        int bestIdx = 0, bestCnt = processed[0];
        for (int i = 1; i < n; i++) {
            if (processed[i] >= bestCnt) {
                bestCnt = processed[i];
                bestIdx = i;
            }
        }
        return bestIdx;
    }

    // — Simple main() tests (no JUnit) —
    private static void test(int[] cap, String[] log, int expected, int id) {
        int got = solution(cap, log);
        System.out.printf(
          "Test %2d: %s (expected=%d, got=%d)%n",
          id,
          (got == expected ? "PASS" : "FAIL"),
          expected, got
        );
    }

    public static void main(String[] args) {
        // Provided example
        test(
          new int[]{1,2,1,2,1},
          new String[]{
            "PACKAGE","PACKAGE","CLOSURE 2",
            "PACKAGE","CLOSURE 3","PACKAGE","PACKAGE"
          },
          1, 1
        );

        // Some edge‐case checks
        test(new int[]{3},
             new String[]{"PACKAGE","PACKAGE","PACKAGE"},
             0, 2);

        test(new int[]{2,1},
             new String[]{"PACKAGE","PACKAGE","PACKAGE"},
             0, 3);

        test(new int[]{1,1,1},
             new String[]{"PACKAGE","CLOSURE 0","PACKAGE","PACKAGE"},
             2, 4);

        test(new int[]{1,2,3},
             new String[]{"CLOSURE 0","CLOSURE 2","PACKAGE","PACKAGE"},
             1, 5);

        // Stress test
        int N = 100, M = 500;
        int[] caps = new int[N];
        Arrays.fill(caps, 5);
        String[] bulk = new String[M];
        Arrays.fill(bulk, "PACKAGE");
        long t0 = System.currentTimeMillis();
        int winner = solution(caps, bulk);
        long dt = System.currentTimeMillis() - t0;
        System.out.printf(
          "Stress (N=%d,M=%d) → center %d in %dms%n",
          N, M, winner, dt
        );
    }
}