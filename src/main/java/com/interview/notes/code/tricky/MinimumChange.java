package com.interview.notes.code.tricky;

import java.util.Arrays;

public class MinimumChange {
    public static int findMinChange(int[] coins) {
        Arrays.sort(coins); // Sort the coins in ascending order
        int minChange = 1; // Initialize the minimum change to 1

        // Iterate through the sorted coins
        for (int coin : coins) {
            // If the current coin is greater than the minimum change,
            // then we cannot make the minimum change with the coins we have
            if (coin > minChange) {
                break;
            }
            // Otherwise, update the minimum change to be the next value that cannot be made
            minChange += coin;
        }
        return minChange;
    }

    public static void main(String[] args) {
        //  int[] coins = {1, 1, 2, 5, 11};
        int[] coins = {0, 1, 2, 5, 6};
        int minChange = findMinChange(coins);
        System.out.println(minChange); // Output: 10
    }
}
/**
 * The findMinChange method takes an array of coins as input, sorts them in ascending order,
 * and then iterates through them to find the minimum change that cannot be constructed.
 * We initialize the minimum change to 1 and then update it to be the next value that cannot be made using the coins we have.
 * <p>
 * In the main method, we create an array of coins and call the findMinChange method to find the minimum change that cannot be constructed.
 * The program then outputs the result, which is 10 for the given set of coins {1, 1, 2, 5, 11}.
 */