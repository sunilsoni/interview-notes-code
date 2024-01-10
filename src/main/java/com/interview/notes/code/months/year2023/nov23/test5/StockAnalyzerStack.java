package com.interview.notes.code.months.year2023.nov23.test5;

import java.util.Stack;

/**
 * Complexity Analysis
 * Time Complexity: O(n) - The stack operations for finding the next and previous greater elements each take linear time. The final count also involves a single pass through the array.
 * Space Complexity: O(n) - Additional space is used for the stack and the arrays storing the indices of the next and previous greater elements.
 */
public class StockAnalyzerStack {

    public static void main(String[] args) {
        int[] stockPrices = {2, 3, 2}; // Example input
        System.out.println("Number of maximum profitable groups: " + countMaxProfitableGroups(stockPrices));
    }

    private static int countMaxProfitableGroups(int[] prices) {
        int n = prices.length;
        int[] nge = new int[n]; // Next Greater Element
        int[] pge = new int[n]; // Previous Greater Element
        Stack<Integer> stack = new Stack<>();
        long count = 0; // To handle large counts

        // Find Next Greater Element for each index
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] < prices[i]) {
                nge[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            nge[stack.pop()] = n;
        }

        // Find Previous Greater Element for each index
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            pge[i] = (stack.isEmpty()) ? -1 : stack.peek();
            stack.push(i);
        }

        // Count maximum profitable groups
        for (int i = 0; i < n; i++) {
            count += (long) (i - pge[i]) * (nge[i] - i);
        }

        return (int) count;
    }
}
