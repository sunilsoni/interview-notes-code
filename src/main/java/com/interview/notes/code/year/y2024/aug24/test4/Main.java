package com.interview.notes.code.year.y2024.aug24.test4;

public class Main {

    public static void main(String[] args) {
        int[] prices6 = {9, 6, 3, 2, 9, 10, 10, 11};
        int k6 = 4;
        int threshold6 = 1;
        System.out.println(reduceGifts(prices6, k6, threshold6)); // Adjusted approach
    }

    public static int reduceGifts(int prices[], int k, int threshold) {
        // Sort the array in ascending order
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = 0; j < prices.length - i - 1; j++) {
                if (prices[j] > prices[j + 1]) {
                    int temp = prices[j];
                    prices[j] = prices[j + 1];
                    prices[j + 1] = temp;
                }
            }
        }

        // Initialize variables
        int removedItemsCount = 0;

        for (int i = 0; i < k - 1; i++) {
            int sumWithinWindow = 0;
            for (int j = i; j <= Math.min(i + k, prices.length); j++) {
                sumWithinWindow += prices[j];
                if (sumWithinWindow > threshold) {
                    removedItemsCount++;
                    break;
                }
            }
        }

        return removedItemsCount;
    }


}