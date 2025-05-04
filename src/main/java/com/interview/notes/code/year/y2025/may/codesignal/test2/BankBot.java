package com.interview.notes.code.year.y2025.may.codesignal.test2;

import java.util.*;
import java.util.stream.*;

public class BankBot {

    static int[] solution(int[] balances, String[] requests) {
        int n = balances.length;
        Map<Integer, List<Integer>> cashback = new HashMap<>();
        int currentTime = 0;

        for (int i = 0; i < requests.length; i++) {
            String[] parts = requests[i].split(" ");
            String type = parts[0];
            int timestamp = Integer.parseInt(parts[1]);
            int holder = Integer.parseInt(parts[2]) - 1;
            int amount = Integer.parseInt(parts[3]);

            // process cashback before the current request
            for (int t = currentTime + 1; t <= timestamp; t++) {
                if (cashback.containsKey(t)) {
                    for (int h : cashback.get(t)) {
                        balances[h] += cashback.get(t).stream().mapToInt(x -> x).sum();
                    }
                    cashback.remove(t);
                }
            }

            currentTime = timestamp;

            if (holder < 0 || holder >= n)
                return new int[]{-(i + 1)};

            if ("deposit".equals(type)) {
                balances[holder] += amount;
            } else if ("withdraw".equals(type)) {
                if (balances[holder] < amount)
                    return new int[]{-(i + 1)};

                balances[holder] -= amount;
                int cbAmount = (amount * 2) / 100;
                cashback.computeIfAbsent(timestamp + 86400, k -> new ArrayList<>()).add(holder);
                cashback.get(timestamp + 86400).add(cbAmount);
            } else {
                return new int[]{-(i + 1)};
            }
        }

        return balances;
    }

    // Simple main method for testing
    public static void main(String[] args) {
        runTest(new int[]{1000, 1500}, new String[]{"deposit 1000 1 500", "withdraw 2000 2 100", "withdraw 90000 1 1000"}, new int[]{-3});
        runTest(new int[]{500, 1000}, new String[]{"withdraw 100 1 400", "deposit 86500 2 500", "withdraw 86500 2 1500"}, new int[]{600, 0});

        // Large input test
        int[] largeBalances = IntStream.range(0, 100).map(i -> 100000).toArray();
        String[] largeRequests = IntStream.range(1, 101)
                .mapToObj(i -> "withdraw " + (i * 100) + " " + i + " 5000")
                .toArray(String[]::new);
        int[] expectedLarge = IntStream.range(0, 100).map(i -> 95000).toArray();

        runTest(largeBalances, largeRequests, expectedLarge);
    }

    private static void runTest(int[] balances, String[] requests, int[] expected) {
        int[] result = solution(balances.clone(), requests);
        if (Arrays.equals(result, expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL - Expected: " + Arrays.toString(expected) + ", Got: " + Arrays.toString(result));
        }
    }
}
