package com.interview.notes.code.year.y2025.march.city.test1;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HugeSaleStreams {
    
    /**
     * Calculates the maximum amount of money John can earn using Java 8 streams.
     * @param M maximum number of items John can carry
     * @param arr list of prices for available items
     * @return maximum amount of money John can earn
     */
    public static int getAmountOfMoney(int M, List<Integer> arr) {
        // Filter negative prices, sort them, limit to M items, and sum their absolute values
        return arr.stream()
                 .filter(price -> price < 0)             // Only consider items that pay John
                 .sorted()                               // Sort in ascending order (most negative first)
                 .limit(M)                               // Take at most M items
                 .mapToInt(price -> Math.abs(price))     // Convert to absolute values (earnings)
                 .sum();                                 // Calculate the total earnings
    }
    
    // Same test method as above...
}
