package com.interview.notes.code.year.y2023.Oct23.test13;

import java.util.Arrays;

public class ShoppingList1 {

    public static void main(String[] args) {
        ShoppingList1 list = new ShoppingList1();
        int[] prices = {11, 15, 2, 7};
        int money = 9;
        int[] answer = list.findItems(prices, money);
        System.out.println("You can buy items at indices: " + answer[0] + " and " + answer[1]);
    }

    public int[] findItems(int[] prices, int money) {
        // Make a copy of original indices before sorting
        int[][] numsWithOriginalIndices = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            numsWithOriginalIndices[i][0] = prices[i];
            numsWithOriginalIndices[i][1] = i;
        }

        // Sort the array
        Arrays.sort(numsWithOriginalIndices, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0, right = prices.length - 1;

        while (left < right) {
            int sum = numsWithOriginalIndices[left][0] + numsWithOriginalIndices[right][0];

            if (sum == money) {
                return new int[]{numsWithOriginalIndices[left][1], numsWithOriginalIndices[right][1]};
            } else if (sum < money) {
                left++;
            } else {
                right--;
            }
        }

        return null; // Return null if no solution is found.
    }
}
