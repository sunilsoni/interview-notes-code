package com.interview.notes.code.year.y2025.may.apple.test4;

import java.util.*;

/**
 * StockTradingConstrained.java
 *
 * This class provides a method to compute the maximum profit from stock trading
 * when each sale incurs a fixed transaction fee and after each sale, there is
 * a cooldown period of a specified number of days before the next buy.
 *
 * It also includes a simple main method demonstrating usage with sample inputs.
 */
public class StockTradingConstrained {

    /**
     * Compute maximum profit with both a transaction fee and an arbitrary cooldown period.
     *
     * @param prices        array of stock prices by day
     * @param fee           fixed transaction fee per sale
     * @param cooldownDays  number of days to wait after selling before buying again
     * @return              maximum net profit under the given constraints
     */
    public static int maxProfitConstrained(int[] prices, int fee, int cooldownDays) {
        // If there are fewer than 2 days, we cannot complete any buy-sell → profit = 0
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;
        // hold[i]    = maximum profit at end of day i if we ARE holding one share
        // notHold[i] = maximum profit at end of day i if we are NOT holding any share
        int[] hold = new int[n];
        int[] notHold = new int[n];

        // Base case on day 0:
        // - If we buy on day 0, our profit becomes -prices[0]
        // - If we do nothing, profit is 0
        hold[0] = -prices[0];
        notHold[0] = 0;

        // Iterate through days 1..n-1
        for (int i = 1; i < n; i++) {
            // 1. Compute the index we can look back to for buying, enforcing cooldown:
            //    After selling on day j, we must wait cooldownDays days, so the earliest
            //    next buy is on j + cooldownDays + 1. To buy on day i, we look at
            //    notHold[i - cooldownDays - 1] if that index exists.
            int prevIndex = i - cooldownDays - 1;

            // Determine the best profit we could have had on the day before the cooldown window.
            // If prevIndex < 0, it means no day is outside the cooldown, so profit = 0.
            int bestPrevNotHold = (prevIndex >= 0) ? notHold[prevIndex] : 0;

            // 2. Update hold[i]:
            //    Either we keep holding from yesterday (hold[i-1]),
            //    or we buy today at prices[i] on top of bestPrevNotHold (paying prices[i]):
            //      buyCandidate = bestPrevNotHold - prices[i]
            hold[i] = Math.max(hold[i - 1], bestPrevNotHold - prices[i]);

            // 3. Update notHold[i]:
            //    Either we remain not holding from yesterday (notHold[i-1]),
            //    or we sell today: we must have held yesterday, so we gain prices[i], but pay fee.
            //      sellCandidate = hold[i-1] + prices[i] - fee
            int sellCandidate = hold[i - 1] + prices[i] - fee;
            notHold[i] = Math.max(notHold[i - 1], sellCandidate);
        }

        // Final answer: maximum profit if we end NOT holding any share on the last day
        return notHold[n - 1];
    }

    /**
     * Main method demonstrating the maxProfitConstrained function with sample inputs.
     * It prints the results to standard output.
     */
    public static void main(String[] args) {
        // Example 1:
        // prices = [1, 3, 2, 8, 4, 9], fee = 2, cooldownDays = 0 (no cooldown)
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int fee1 = 2;
        int cooldown1 = 0;
        int result1 = maxProfitConstrained(prices1, fee1, cooldown1);
        System.out.println("Example 1 (fee=2, cooldown=0) → Expected=8, Got=" + result1);

        // Example 2:
        // prices = [1, 2, 3, 0, 2], fee = 0, cooldownDays = 1 (one-day cooldown)
        int[] prices2 = {1, 2, 3, 0, 2};
        int fee2 = 0;
        int cooldown2 = 1;
        int result2 = maxProfitConstrained(prices2, fee2, cooldown2);
        System.out.println("Example 2 (fee=0, cooldown=1) → Expected=3, Got=" + result2);

        // Example 3:
        // Custom test: prices = [5, 3, 6, 1, 4, 7], fee = 1, cooldownDays = 2
        int[] prices3 = {5, 3, 6, 1, 4, 7};
        int fee3 = 1;
        int cooldown3 = 2;
        int result3 = maxProfitConstrained(prices3, fee3, cooldown3);
        // We can verify by hand: 
        //   - Buy day1@3, sell day2@6 (profit=6-3-1=2), cooldown on day3&4, 
        //   - then buy day4@4, sell day5@7 (profit=7-4-1=2). Total=4
        System.out.println("Example 3 (fee=1, cooldown=2) → Expected=4, Got=" + result3);

        // Example 4:
        // Edge case: strictly decreasing prices → no trades
        int[] prices4 = {10, 9, 8, 7, 6};
        int fee4 = 2;
        int cooldown4 = 1;
        int result4 = maxProfitConstrained(prices4, fee4, cooldown4);
        System.out.println("Example 4 (all decreasing) → Expected=0, Got=" + result4);

        // Example 5:
        // Large random test for performance demonstration
        int N = 100_000;
        Random rand = new Random(42);
        int[] largePrices = new int[N];
        for (int i = 0; i < N; i++) {
            largePrices[i] = rand.nextInt(1000);  // prices between 0 and 999
        }
        int largeFee = 5;
        int largeCooldown = 3;
        long start = System.currentTimeMillis();
        int largeResult = maxProfitConstrained(largePrices, largeFee, largeCooldown);
        long end = System.currentTimeMillis();
        System.out.println("Large test (n=100000, fee=5, cooldown=3) → Profit=" 
                           + largeResult + ", Time=" + (end - start) + " ms");
    }
}