package com.interview.notes.code.year.y2024.april24.test7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Profit Targets
 * Description
 * A financial analyst is responsible for a portfolio of profitable stocks represented in an array. Each item in the array represents the yearly profit of a corresponding stock. The analyst gathers all distinct pairs of stocks that reached the target profit. Distinct pairs are pairs that differ in at least one element. Given the array of profits, find the number of distinct pairs of stocks where the sum of each pair's profits is exactly equal to the target profit.
 * Example
 * stocksProfit = (5, 7, 9, 13, 11, 6, 6, 3, 3)
 * target = 12 profit's target
 * • There are 4 pairs of stocks that have the sum of their profits equals to the target 12. Note that because there are two instances of 3 in stocksProfit there are two pairs matching (9, 3): stocksProfits indices 2 and 7, and indices 2 and 8, but only one can be included.
 * • There are 3 distinct pairs of stocks: (5, 7), (3, 9), and (6, 6) and the retum value is 3.
 * Function Description
 * Complete the function stockPairs in the editor below.
 * stockPairs has the following parameter(s): int stocksProfit[n]: an array of integers
 * representing the stocks profits
 * target: an integer representing the yearly target profit
 * Returns:
 * int the total number of pairs determined
 * Constraints
 * • 1≤n ≤5 × 105
 * • 0 ≤ stocksProfit] ≤ 10°
 * • 0s target ≤ 5 × 10°
 */
public class ProfitTargets {
    // Function to find unique pairs that add up to target.
    public static int stockPairs(List<Integer> stocksProfit, long target) {
        // Using a set to store unique pairs as strings to avoid counting duplicates
        HashSet<String> uniquePairs = new HashSet<>();

        // Creating a hash map to store the frequency of each number
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int profit : stocksProfit) {
            frequencyMap.put(profit, frequencyMap.getOrDefault(profit, 0) + 1);
        }

        for (int profit : stocksProfit) {
            int complement = (int) (target - profit);
            // Check if the complement exists and it's not counting the same element twice
            if (frequencyMap.containsKey(complement) && (complement != profit || frequencyMap.get(complement) > 1)) {
                int smaller = Math.min(profit, complement);
                int larger = Math.max(profit, complement);
                String pair = smaller + ":" + larger;

                // Add the unique pair to the set
                uniquePairs.add(pair);

                // Decrease the frequency of the numbers forming the pair to avoid reuse
                frequencyMap.put(profit, frequencyMap.get(profit) - 1);
                frequencyMap.put(complement, frequencyMap.get(complement) - 1);
            }
        }

        // The size of the set represents the number of unique pairs
        return uniquePairs.size();
    }
}

// The main method is assumed to be correct and is not included for brevity.
