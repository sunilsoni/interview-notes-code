package com.interview.notes.code.months.aug24.test32;

import java.util.*;

class Result {
    public static List<Integer> getMinimumCost(List<Integer> cost, List<String> featureAvailability) {
        int n = cost.size();
        List<Model> models = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            models.add(new Model(cost.get(i), featureAvailability.get(i)));
        }
        Collections.sort(models);

        long[][] dp = new long[n + 1][n + 1];
        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (Model model : models) {
            for (int i = n; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    int newI = i + (model.featureA ? 1 : 0);
                    int newJ = j + (model.featureB ? 1 : 0);
                    if (newI <= n && newJ <= n && dp[i][j] != Long.MAX_VALUE) {
                        dp[newI][newJ] = Math.min(dp[newI][newJ], dp[i][j] + model.cost);
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int k = 1; k <= n; k++) {
            long minCost = Long.MAX_VALUE;
            for (int i = k; i <= n; i++) {
                minCost = Math.min(minCost, dp[i][i]);
            }
            result.add(minCost == Long.MAX_VALUE ? -1 : (int) minCost);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> cost1 = Arrays.asList(3, 6, 9, 1, 2, 5);
        List<String> featureAvailability1 = Arrays.asList("10", "01", "11", "01", "11", "10");
        System.out.println("Test Case 1 Result: " + getMinimumCost(cost1, featureAvailability1));

        // Test case 2
        List<Integer> cost2 = Arrays.asList(5, 6, 10, 1);
        List<String> featureAvailability2 = Arrays.asList("10", "01", "11", "00");
        System.out.println("Test Case 2 Result: " + getMinimumCost(cost2, featureAvailability2));

        // Test case 3
        List<Integer> cost3 = Arrays.asList(1, 1);
        List<String> featureAvailability3 = Arrays.asList("10", "10");
        System.out.println("Test Case 3 Result: " + getMinimumCost(cost3, featureAvailability3));
    }

    static class Model implements Comparable<Model> {
        int cost;
        boolean featureA;
        boolean featureB;

        Model(int cost, String features) {
            this.cost = cost;
            this.featureA = features.charAt(0) == '1';
            this.featureB = features.charAt(1) == '1';
        }

        @Override
        public int compareTo(Model other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}
