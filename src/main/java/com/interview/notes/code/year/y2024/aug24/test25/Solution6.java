package com.interview.notes.code.year.y2024.aug24.test25;

class Solution6 {

    public static void main(String[] args) {
        Solution6 solution = new Solution6();

        // Test case 1
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Test case 1 output: " + solution.canCompleteCircuit(gas1, cost1));

        // Test case 2
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Test case 2 output: " + solution.canCompleteCircuit(gas2, cost2));

        // Additional test case
        int[] gas3 = {5, 1, 2, 3, 4};
        int[] cost3 = {4, 4, 1, 5, 1};
        System.out.println("Test case 3 output: " + solution.canCompleteCircuit(gas3, cost3));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        for (int start = 0; start < n; start++) {
            int tank = 0;
            boolean possible = true;

            for (int i = 0; i < n; i++) {
                int station = (start + i) % n;
                tank += gas[station] - cost[station];

                if (tank < 0) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                return start;
            }
        }

        return -1;
    }
}
