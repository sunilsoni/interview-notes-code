package com.interview.notes.code.year.y2025.may.codesignal.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class SolutionTest {

    public static void main(String[] args) {
        // provided examples
        runTest(
                new int[]{1000, 1500},
                new String[]{
                        "withdraw 1613327630 2 480",
                        "withdraw 1613327644 2 800",
                        "withdraw 1614105244 1 100",
                        "deposit 1614108844 2 200",
                        "withdraw 1614108845 2 150"
                },
                new int[]{900, 295}
        );

        runTest(
                new int[]{20, 1000, 500, 40, 90},
                new String[]{
                        "deposit 1613327630 3 400",
                        "withdraw 1613327635 1 20",
                        "withdraw 1613327651 1 50",
                        "deposit 1613327655 1 50"
                },
                new int[]{-3}
        );

        // large-data stress test
        int n = 100_000;
        int[] bigBalances = IntStream.generate(() -> 100_000).limit(n).toArray();
        String[] bigRequests = IntStream.range(0, n)
                .mapToObj(i -> String.format("deposit %d %d 1", i, (i % n) + 1))
                .toArray(String[]::new);

        long start = System.currentTimeMillis();
        int[] bigResult = solution(bigBalances.clone(), bigRequests);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("Large test – result length %d, time %d ms%n",
                bigResult.length, elapsed);
    }

    private static void runTest(int[] balances, String[] requests, int[] expected) {
        int[] actual = solution(balances.clone(), requests);
        boolean pass = Arrays.equals(actual, expected);
        System.out.printf("Test %s: expected %s, got %s%n",
                pass ? "PASS" : "FAIL",
                Arrays.toString(expected),
                Arrays.toString(actual)
        );
    }

    public static int[] solution(int[] balances, String[] requests) {
        class Cashback {
            long time;
            int idx, amount;

            Cashback(long time, int idx, int amount) {
                this.time = time;
                this.idx = idx;
                this.amount = amount;
            }
        }
        PriorityQueue<Cashback> pq = new PriorityQueue<>(Comparator.comparingLong(c -> c.time));
        long lastTs = 0;
        for (int i = 0; i < requests.length; i++) {
            String[] p = requests[i].split(" ");
            String type = p[0];
            long ts = Long.parseLong(p[1]);
            int id = Integer.parseInt(p[2]) - 1;
            int amt = Integer.parseInt(p[3]);
            // process pending cashbacks
            while (!pq.isEmpty() && pq.peek().time <= ts) {
                Cashback c = pq.poll();
                balances[c.idx] += c.amount;
            }
            lastTs = ts;
            if (id < 0 || id >= balances.length) {
                return new int[]{-(i + 1)};
            }
            if ("deposit".equals(type)) {
                balances[id] += amt;
            } else { // withdraw
                if (balances[id] < amt) {
                    return new int[]{-(i + 1)};
                }
                balances[id] -= amt;
                int cb = (amt * 2) / 100;
                if (cb > 0) {
                    pq.add(new Cashback(ts + 86400, id, cb));
                }
            }
        }
        // final cashback before end
        while (!pq.isEmpty() && pq.peek().time <= lastTs) {
            Cashback c = pq.poll();
            balances[c.idx] += c.amount;
        }
        return balances;
    }
}